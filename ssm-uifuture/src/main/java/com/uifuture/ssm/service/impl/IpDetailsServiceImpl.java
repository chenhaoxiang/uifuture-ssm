package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.IpDetailsEntity;
import com.uifuture.ssm.mapper.IpDetailsMapper;
import com.uifuture.ssm.service.IpDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ip详情表-通过数据中心查找ip查找出来的 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-19
 */
@Service
public class IpDetailsServiceImpl extends ServiceImpl<IpDetailsMapper, IpDetailsEntity> implements IpDetailsService {

}
