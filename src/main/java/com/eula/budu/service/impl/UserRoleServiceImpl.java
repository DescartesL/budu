package com.eula.budu.service.impl;

import com.eula.budu.entity.UserRole;
import com.eula.budu.mapper.UserRoleMapper;
import com.eula.budu.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统管理 - 用户角色关联表  服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
