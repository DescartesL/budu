package com.eula.budu.config;

import com.eula.budu.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;

/**
 * 定义HandlerMapping
 * @author DesLUO
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {


    /**
     * 添加@ApiVersion到 controller 类
     * @param handlerType
     * @return
     */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(@NotNull Class<?> handlerType){
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return null == apiVersion ? super.getCustomTypeCondition(handlerType) : new ApiVersionCondition(apiVersion.value());
    }

    /**
     * 添加@ApiVersion 到method方法
     * @param method
     * @return
     */

    @Override
    protected RequestCondition<?> getCustomMethodCondition(@NotNull Method method){
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return null == apiVersion ? super.getCustomMethodCondition(method) : new ApiVersionCondition(apiVersion.value());
    }

}
