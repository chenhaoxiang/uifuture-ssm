package com.uifuture.ssm.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的超类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1549634521453074321L;
    /**
     * 主键，唯一标识符
     * 主键策略： 数据库ID自增
     * IdType.AUTO	数据库ID自增
     * IdType.INPUT	用户输入ID
     * IdType.ID_WORKER	全局唯一ID，内容为空自动填充（默认配置）
     * IdType.UUID	全局唯一ID，内容为空自动填充
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 更新时间
     */
    protected Date updateTime;
    /**
     * 删除时间，0-未删除，单位秒
     */
    protected Integer deleteTime;


    public static final String ID = "id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DELETE_TIME = "delete_time";


}
