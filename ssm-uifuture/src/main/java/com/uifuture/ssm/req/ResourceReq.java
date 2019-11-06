package com.uifuture.ssm.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源表。
 * </p>
 *
 * @author chenhx
 * @since 2019-09-18
 */
@Data
public class ResourceReq implements Serializable {
    private static final long serialVersionUID = 4498278945810719514L;
    /**
     * 资源标题
     */
    @NotEmpty(message = "资源标题不能为空")
    private String title;

    /**
     * 图片资源的封面图片地址
     */
    private String imageUrls;
    /**
     * 资源存储路径，当然，可以选择阿里云的OSS
     */
    private String path;
    /**
     * 上传时文件原来的文件名
     */
    private String oldName;

    /**
     * 上传文件后，存储的文件名
     */
    private String newName;

    /**
     * U币价格(只有整数)
     */
    private Integer priceUb = 0;

    /**
     * 人民币价格,单位分
     */
    private Integer price = 0;

    /**
     * 描述
     */
    private String describe;

    /**
     * 资源描述与内容
     */
    @NotEmpty(message = "资源内容不能为空")
    private String content;

    /**
     * 分类id集合
     */
    private List<Integer> typeIds = new ArrayList<>();

    /**
     * 标签名称
     */
    private List<String> tagsNames = new ArrayList<>();

    /**
     * 专题id集合
     */
    private List<Integer> subjectIds = new ArrayList<>();
    ;

}
