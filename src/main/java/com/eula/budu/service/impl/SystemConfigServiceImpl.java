package com.eula.budu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eula.budu.entity.SystemConfig;
import com.eula.budu.mapper.SystemConfigMapper;
import com.eula.budu.service.ISystemConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import static com.eula.budu.common.SqlConstants.LIMIT_ONE;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements ISystemConfigService {

    /**
     * 获取一个自定义的对象SystemConfig
     * @return
     */
    @Override
    public SystemConfig getCustomizeOne() {
        return baseMapper.selectOne(new QueryWrapper<SystemConfig>().last(LIMIT_ONE));
    }
}
