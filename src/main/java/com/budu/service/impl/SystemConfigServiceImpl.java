package com.budu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.budu.common.ResponseResult;
import com.budu.entity.SystemConfig;
import com.budu.entity.User;
import com.budu.mapper.SystemConfigMapper;
import com.budu.service.SystemConfigService;
import com.budu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.budu.common.Constants.USER_ROLE_ID;
import static com.budu.common.SqlConf.ID;
import static com.budu.common.SqlConf.LIMIT_ONE;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author blue
 * @since 2021-11-25
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {


    private final UserService userService;

    /**
     * 获取系统配置
     * @return
     */
    @Override
    public ResponseResult getConfig() {
        QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (user.getRoleId() > USER_ROLE_ID) queryWrapper.orderByDesc(ID);
        queryWrapper.last(LIMIT_ONE);
        SystemConfig systemConfig = baseMapper.selectOne(queryWrapper);
        return ResponseResult.success(systemConfig);
    }

    /**
     * 修改系统配置
     * @param systemConfig
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateConfig(SystemConfig systemConfig) {
        baseMapper.updateById(systemConfig);
        return ResponseResult.success();
    }

    //---------自定义方法----------

    /**
     * 获取最后一条配置
     * @return
     */
    @Override
    public SystemConfig getCustomizeOne() {
        return baseMapper.selectOne(new QueryWrapper<SystemConfig>().last(LIMIT_ONE));
    }
}
