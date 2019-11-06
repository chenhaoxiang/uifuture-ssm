package com.uifuture.ssm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uifuture.ssm.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ip详情表-通过数据中心查找ip查找出来的
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ip_details")
public class IpDetailsEntity extends BaseEntity {

    public static final String USER_ID = "user_id";
    public static final String IP = "ip";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String DISTRICT = "district";
    public static final String ISP = "isp";
    public static final String AREACODE = "areacode";
    public static final String LAND = "land";
    public static final String COUNTRY = "country";
    public static final String REFERENCE = "reference";
    public static final String IDC = "idc";
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * ip
     */
    private String ip;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区、县
     */
    private String district;
    /**
     * 运营商
     */
    private String isp;
    /**
     * 行政区划代码
     */
    private String areacode;
    /**
     * 州 -例如:亚洲
     */
    private String land;
    /**
     * 国家
     */
    private String country;
    /**
     * 数据中心节点
     */
    private String reference;
    /**
     * 0-不是 1-是 是否为数据中心（非人类甄别）
     */
    private String idc;

}
