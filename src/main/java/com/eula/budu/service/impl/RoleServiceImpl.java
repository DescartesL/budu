package com.eula.budu.service.impl;

import com.eula.budu.entity.Role;
import com.eula.budu.mapper.RoleMapper;
import com.eula.budu.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统管理-角色表  服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
