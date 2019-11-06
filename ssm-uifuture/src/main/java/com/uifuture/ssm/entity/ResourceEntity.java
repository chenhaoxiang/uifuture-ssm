package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource")
public class ResourceEntity extends BaseEntity {

    public static final String CREATE_ID = "create_id";
    public static final String TOKEN = "token";
    public static final String TITLE = "title";
    public static final String IMAGE_URLS = "image_urls";
    public static final String STATE = "state";
    public static final String VISIT_TIMES = "visit_times";
    public static final String DOWNLOAD = "download";
    public static final String PATH = "path";
    public static final String NEW_NAME = "new_name";
    public static final String OLD_NAME = "old_name";
    public static final String PRICE_UB = "price_ub";
    public static final String PRICE = "price";
    public static final String COLLECT_NUMBER = "collect_number";
    public static final String DESCRIBE = "describe";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id，与用户的关系为一对多
     */
    private Integer createId;
    /**
     * 资源token
     */
    private String token;
    /**
     * 资源标题
     */
    private String title;
    /**
     * 图片资源的封面图片地址
     */
    private String imageUrls;
    /**
     * 状态：0-审核未通过 1-审核已通过 2-审核中 3-资源被封
     */
    private Integer state;
    /**
     * 访问量
     */
    private Integer visitTimes;
    /**
     * 下载量
     */
    private Integer download;
    /**
     * 资源存储路径，当然，可以选择阿里云的OSS
     */
    private String path;
    /**
     * 上传文件后，存储的文件名
     */
    private String newName;
    /**
     * 上传时文件原来的文件名
     */
    private String oldName;
    /**
     * U币价格(只有整数)
     */
    private Integer priceUb;
    /**
     * 人民币价格,单位分
     */
    private Integer price;
    /**
     * 收藏数
     */
    private Integer collectNumber;
    /**
     * 描述
     */
    private String describe;

}
