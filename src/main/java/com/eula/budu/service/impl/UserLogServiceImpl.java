package com.eula.budu.service.impl;

import com.eula.budu.entity.UserLog;
import com.eula.budu.mapper.UserLogMapper;
import com.eula.budu.service.IUserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements IUserLogService {

}
