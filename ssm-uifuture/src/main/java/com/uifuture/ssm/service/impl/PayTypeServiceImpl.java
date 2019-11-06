package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.PayTypeEntity;
import com.uifuture.ssm.mapper.PayTypeMapper;
import com.uifuture.ssm.service.PayTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付类型表。 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
@Service
public class PayTypeServiceImpl extends ServiceImpl<PayTypeMapper, PayTypeEntity> implements PayTypeService {

}
