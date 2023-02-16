package com.eula.budu.service.impl;

import com.eula.budu.entity.UserAuth;
import com.eula.budu.mapper.UserAuthMapper;
import com.eula.budu.service.IUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

}
