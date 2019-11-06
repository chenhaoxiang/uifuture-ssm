package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.IpEntity;
import com.uifuture.ssm.mapper.IpMapper;
import com.uifuture.ssm.service.IpService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户ip表 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Service
public class IpServiceImpl extends ServiceImpl<IpMapper, IpEntity> implements IpService {

    /**
     * 异步增加IP记录
     *
     * @param ipAddress IP地址
     * @param id        用户id
     */
    @Async
    @Override
    public void saveIp(String ipAddress, Integer id) {
        //增加IP记录
        IpEntity ipEntity = new IpEntity();
        ipEntity.setUserId(id);
        ipEntity.setIp(ipAddress);
        this.save(ipEntity);
    }
}
