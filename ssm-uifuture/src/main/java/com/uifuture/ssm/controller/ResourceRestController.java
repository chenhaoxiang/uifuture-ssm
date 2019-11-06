package com.uifuture.ssm.controller;

import com.uifuture.ssm.aliyun.AliyunOssHandle;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.common.UsersConstants;
import com.uifuture.ssm.config.SysConfig;
import com.uifuture.ssm.convert.ResourceContentConvert;
import com.uifuture.ssm.convert.ResourceConvert;
import com.uifuture.ssm.convert.TagsConvert;
import com.uifuture.ssm.dto.FileInfoDTO;
import com.uifuture.ssm.dto.FileOssUrlDTO;
import com.uifuture.ssm.dto.ResourceContentDTO;
import com.uifuture.ssm.dto.ResourceDTO;
import com.uifuture.ssm.dto.TagsDTO;
import com.uifuture.ssm.entity.RResourceTagsEntity;
import com.uifuture.ssm.entity.ResourceContentEntity;
import com.uifuture.ssm.entity.ResourceEntity;
import com.uifuture.ssm.entity.ResourceSubjectEntity;
import com.uifuture.ssm.entity.ResourceTypeEntity;
import com.uifuture.ssm.entity.TagsEntity;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.enums.DeleteEnum;
import com.uifuture.ssm.enums.ResultCodeEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.ResourceReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.RResourceTagsService;
import com.uifuture.ssm.service.ResourceContentService;
import com.uifuture.ssm.service.ResourceService;
import com.uifuture.ssm.service.ResourceSubjectService;
import com.uifuture.ssm.service.ResourceTypeService;
import com.uifuture.ssm.service.TagsService;
import com.uifuture.ssm.util.CookieUtils;
import com.uifuture.ssm.util.DateUtils;
import com.uifuture.ssm.util.FileUtils;
import com.uifuture.ssm.util.PasswordUtils;
import com.uifuture.ssm.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源表。 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/resource")
public class ResourceRestController extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceTypeService resourceTypeService;
    @Autowired
    private ResourceSubjectService resourceSubjectService;

    @Autowired
    private ResourceContentService resourceContentService;

    @Autowired
    private AliyunOssHandle aliyunOssHandle;

    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private RResourceTagsService rResourceTagsService;

    @Autowired
    private TagsService tagsService;

    /**
     * 用户上传图片文件的路径
     */
    private static final String FILE_IMAGES_UPLOAD_PATH = File.separator + "images" + File.separator;

    /**
     * 用户上传资源文件的路径
     */
    private static final String FILE_RESOURCES_UPLOAD_PATH = File.separator + "resources" + File.separator;


    @Autowired
    private RedisClient redisClient;

    /**
     * 发表资源
     *
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResultModel submit(ResourceReq resourceReq, HttpServletRequest request, HttpServletResponse response) {
        //校验
        ValidateUtils.validate(resourceReq);

        //获取当前用户
        UsersEntity usersEntity = getLoginInfo(request);
        if (usersEntity == null) {
            return ResultModel.fail(ResultCodeEnum.USER_NOT_LOGGED);
        }

        //判断资源文件是否存在
        if (!FileUtils.exists(resourceReq.getPath() + resourceReq.getNewName())) {
            return ResultModel.fail(ResultCodeEnum.PLEASE_UPLOAD_THE_RESOURCE_FILE_FIRST);
        }

        //资源信息
        ResourceEntity resourceEntity = ResourceConvert.INSTANCE.toEntity(resourceReq);

        //资源内容
        ResourceContentEntity resourceContentEntity = new ResourceContentEntity();
        resourceContentEntity.setContent(resourceReq.getContent());

        //查询类型
        List<Integer> typeIds = resourceReq.getTypeIds();
        if (!CollectionUtils.isEmpty(typeIds)) {
            Collection<ResourceTypeEntity> resourceTypeEntityList = resourceTypeService.listByIds(typeIds);
            typeIds = new ArrayList<>();
            for (ResourceTypeEntity resourceTypeEntity : resourceTypeEntityList) {
                if (DeleteEnum.NO_DELETE.getValue().equals(resourceTypeEntity.getDeleteTime())) {
                    //未被删除
                    typeIds.add(resourceTypeEntity.getId());
                }
            }
        }
        //查询专题
        List<Integer> subjectIds = resourceReq.getSubjectIds();
        if (!CollectionUtils.isEmpty(subjectIds)) {
            Collection<ResourceSubjectEntity> resourceSubjectEntities = resourceSubjectService.listByIds(subjectIds);
            subjectIds = new ArrayList<>();
            for (ResourceSubjectEntity resourceSubjectEntity : resourceSubjectEntities) {
                if (DeleteEnum.NO_DELETE.getValue().equals(resourceSubjectEntity.getDeleteTime())) {
                    //未被删除
                    subjectIds.add(resourceSubjectEntity.getId());
                }
            }
        }
        //资源发表
        resourceService.saveResource(resourceEntity, resourceContentEntity, usersEntity, typeIds, subjectIds, com.uifuture.ssm.util.CollectionUtils.listToSet(resourceReq.getTagsNames()));
        return ResultModel.success("成功");
    }

    /**
     * 上传图片
     * 上传至阿里云OSS
     * @return
     */
    @RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
    public ResultModel uploadImages(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("uploadFile") MultipartFile[] uploadFile) throws IOException {
        if (uploadFile.length == 0) {
            return ResultModel.failNoData("请选择文件再上传");
        }
        UsersEntity users = getLoginInfo(request);
        if (users == null) {
            return ResultModel.fail(ResultCodeEnum.USER_NOT_LOGGED);
        }

        //单个用户一天最多上传100张图片
        int times = redisClient.incrInt(RedisConstants.getUploadFileTimesKey(users.getUsername()), RedisConstants.REG_MAX_TIME_1_DAY);
        if (times > UsersConstants.UPLOAD_TIMES) {
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }

        List<FileOssUrlDTO> fileOssUrlDTOList = new ArrayList<>();
        Date date = new Date();
        String dateStr = DateUtils.getDateString(date, "yyyyMM") + "/" + DateUtils.getDateString(date, "dd");
        for (MultipartFile multipartFile : uploadFile) {
            //原文件名称 - 需要带后缀
            String fileName = multipartFile.getOriginalFilename();
            if (StringUtils.isEmpty(fileName)) {
                return ResultModel.fail("文件名为空。原文件名为:" + fileName);
            }
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if (StringUtils.isEmpty(fileType)) {
                return ResultModel.fail("文件后缀名称错误。原文件名为:" + fileName + "，后缀名为:" + fileType);
            }
            InputStream inputStream = multipartFile.getInputStream();
            //文件上传到 OSS ，oss 路径
            String ossFileName = PasswordUtils.getToken() + fileType;
            String ossUrl = aliyunOssHandle.uploadObject2OSS(inputStream, fileName, multipartFile.getSize(),
                    "user/" + dateStr + "/", ossFileName, users.getUsername());
            FileOssUrlDTO fileOssUrlDTO = new FileOssUrlDTO();
            fileOssUrlDTO.setFileName(fileName);
            fileOssUrlDTO.setOssUrl(sysConfig.getCdnImagesHref() + "/" + ossUrl);
            fileOssUrlDTOList.add(fileOssUrlDTO);

        }
//        返回文件的存储信息
        return ResultModel.resultModel(200, "上传成功", fileOssUrlDTOList);
    }

    /**
     * 上传资源文件
     *
     * @return
     */
    @RequestMapping(value = "/uploadResources", method = RequestMethod.POST)
    public ResultModel uploadResources(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        if (uploadFile == null) {
            return ResultModel.failNoData("请选择文件再上传");
        }
        UsersEntity users = getLoginInfo(request);
        if (users == null) {
            return ResultModel.fail(ResultCodeEnum.USER_NOT_LOGGED);
        }

        //单个用户一天最多上传100次资源
        int times = redisClient.incrInt(RedisConstants.getUploadFileTimesKey(users.getUsername()), RedisConstants.REG_MAX_TIME_1_DAY);
        if (times > UsersConstants.UPLOAD_TIMES) {
            return ResultModel.fail(ResultCodeEnum.ALL_TOO_OFTEN);
        }

        List<FileInfoDTO> fileOssUrlDTOList = new ArrayList<>();
        Date date = new Date();
        String dateStr = DateUtils.getDateString(date, "yyyyMM") + "/" + DateUtils.getDateString(date, "dd");

        //原文件名称 - 需要带后缀
        String fileName = uploadFile.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return ResultModel.fail("文件名为空。原文件名为:" + fileName);
        }
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isEmpty(fileType)) {
            return ResultModel.fail("文件后缀名称错误。原文件名为:" + fileName + "，后缀名为:" + fileType);
        }
        //后缀名限制为ZIP压缩文件
        if (!UsersConstants.UPLOAD_SUFFIX.equals(fileType.toLowerCase())) {
            return ResultModel.fail("文件后缀名错误，只能上传zip压缩文件");
        }

        //保存文件到本地
        uploadFile(uploadFile, fileOssUrlDTOList, dateStr, fileName, fileType, request.getSession().getServletContext().getRealPath("user") + FILE_RESOURCES_UPLOAD_PATH);

        //返回文件的存储信息
        return ResultModel.resultModel(200, "上传成功", fileOssUrlDTOList);
    }

    /**
     * 保存文件到本地
     *
     * @param uploadFile
     * @param fileOssUrlDTOList
     * @param dateStr
     * @param fileName
     * @param fileType
     * @param fileResourcesUploadPath
     * @throws IOException
     */
    private void uploadFile(MultipartFile uploadFile, List<FileInfoDTO> fileOssUrlDTOList, String dateStr, String fileName, String fileType, String fileResourcesUploadPath) throws IOException {
        InputStream inputStream = uploadFile.getInputStream();

        //文件上传到 OSS ，oss 路径
        String newFileName = PasswordUtils.getToken() + fileType;
        String path = fileResourcesUploadPath + dateStr + File.separator;
        FileUtils.writeToLocal(path, newFileName, inputStream);

        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        fileInfoDTO.setOldFileName(fileName);
        fileInfoDTO.setNewFileName(newFileName);
        fileInfoDTO.setPath(path);
        fileOssUrlDTOList.add(fileInfoDTO);
    }


    /**
     * 获取资源信息
     *
     * @return
     */
    @RequestMapping(value = "/resourceInfo", method = RequestMethod.POST)
    public ResultModel resourceInfo(HttpServletRequest request, HttpServletResponse response, String token) throws IOException {
        if (StringUtils.isEmpty(token)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        ResourceEntity resourceEntity = resourceService.getByToken(token);
        if (resourceEntity == null) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }

        ResourceDTO resourceDTO = ResourceConvert.INSTANCE.entityToDto(resourceEntity);

        //查询标签
        List<RResourceTagsEntity> rResourcesTagsEntities = rResourceTagsService.listByResourceId(resourceEntity.getId());
        List<Integer> tagsId = new ArrayList<>();
        for (RResourceTagsEntity rResourcesTagsEntity : rResourcesTagsEntities) {
            tagsId.add(rResourcesTagsEntity.getTagsId());
        }
        if (!CollectionUtils.isEmpty(tagsId)) {
            Collection<TagsEntity> tagsEntities = tagsService.listByIds(tagsId);
            List<TagsDTO> tagsDTOS = TagsConvert.INSTANCE.entityToDTOList(tagsEntities);
            resourceDTO.setTagsDTOS(tagsDTOS);
        }
        return ResultModel.success(resourceDTO);
    }

    /**
     * 获取资源内容，具体信息
     *
     * @return
     */
    @RequestMapping(value = "/resourceContentInfo", method = RequestMethod.POST)
    public ResultModel resourceContentInfo(HttpServletRequest request, HttpServletResponse response, String token) throws IOException {
        if (StringUtils.isEmpty(token)) {
            return ResultModel.fail(ResultCodeEnum.PARAMETER_ERROR);
        }
        ResourceContentEntity resourceContentEntity = resourceContentService.getByToken(token);

        //异步增加资源的访问量
        String value = CookieUtils.getCookie(request, token);
        if (StringUtils.isEmpty(value)) {
            //一个小时之内增加一次
            CookieUtils.setCookie(response, token, "1", 60 * 60);
            //增加资源的访问量  异步进行增加
            resourceService.addViewsOne(token);
        }

        ResourceContentDTO resourceContentDTO = ResourceContentConvert.INSTANCE.entityToDto(resourceContentEntity);
        return ResultModel.success(resourceContentDTO);
    }


    /**
     * TODO 资源删除
     *
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResultModel collect(HttpServletRequest request, HttpServletResponse response, String token) throws IOException {
        return ResultModel.success();
    }




}
