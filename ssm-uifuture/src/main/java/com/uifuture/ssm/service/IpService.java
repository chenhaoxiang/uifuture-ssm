package com.uifuture.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uifuture.ssm.entity.IpEntity;

/**
 * <p>
 * 用户ip表 服务类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
public interface IpService extends IService<IpEntity> {

    /**
     * 新增IP记录
     *
     * @param ipAddress ip
     * @param userId    用户id
     */
    void saveIp(String ipAddress, Integer userId);
}
