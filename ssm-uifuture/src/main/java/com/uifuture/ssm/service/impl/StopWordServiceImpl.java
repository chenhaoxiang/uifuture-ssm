package com.uifuture.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uifuture.ssm.entity.StopWordEntity;
import com.uifuture.ssm.mapper.StopWordMapper;
import com.uifuture.ssm.service.StopWordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 敏感词过滤之-停顿词 服务实现类
 * </p>
 *
 * @author chenhx
 * @since 2019-09-23
 */
@Service
public class StopWordServiceImpl extends ServiceImpl<StopWordMapper, StopWordEntity> implements StopWordService {

}
