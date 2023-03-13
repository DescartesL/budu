package com.eula.budu.exception;


import cn.dev33.satoken.exception.NotLoginException;
import com.eula.budu.common.ResponseResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.NoPermissionException;

import static com.eula.budu.common.ResultConstants.*;


@ControllerAdvice(basePackages = "com.eula.budu")
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 业务异常
     * @param ex 自己封装的业务异常对象
     * @return 返回异常消息
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseResult BusinessExceptionHandler(BusinessException ex){
        if(ex.getCode() != -1){
            logger.error("code : " + ex.getCode() + "msg : " + ex.getMessage());
        }
        if(StringUtils.isBlank(ex.getLocalizedMessage() )|| StringUtils.isBlank(ex.getMessage())){
            return ResponseResult.error(ERROR.getCode(), ERROR.getDesc());
        }
        return ResponseResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 登录异常
     * @param ex 已经封装好的satoekn认证异常
     * @return 返回异常消息
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public ResponseResult NotLoginException(NotLoginException ex){
        logger.error("msg : " + ex.getMessage(), ex);
        return ResponseResult.error(NOT_LOGIN.getCode(), NOT_LOGIN.getDesc());
    }

    /**
     * 无权限异常
     * @param ex 已经封装好的satoken无权限异常
     * @return 返回异常消息
     */
    @ExceptionHandler(NoPermissionException.class)
    @ResponseBody
    public ResponseResult NoPermissionException(NoPermissionException ex){
        logger.error("msg : " + ex.getMessage(), ex);
        return ResponseResult.error(NO_PERMISSION.getCode(), NO_PERMISSION.getDesc());
    }

    /**
     * java异常异常
     * @param ex 异常类
     * @return 返回异常消息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult ExceptionHandler(Exception ex){
        logger.error("msg : " + ex.getMessage());
        if(StringUtils.isBlank(ex.getLocalizedMessage())){
            return ResponseResult.error(ERROR.getCode(), ERROR.getDesc());
        }
        return ResponseResult.error(ex.getMessage());
    }

}
