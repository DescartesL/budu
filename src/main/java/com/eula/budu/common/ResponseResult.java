package com.eula.budu.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import static com.eula.budu.common.ResultStatus.*;

@Data
@ApiModel(value = "统一返回结果类")
public class ResponseResult {

    @ApiModelProperty(value = "响应消息", required = false)
    private String message;

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应数据", required = false)
    private Object data;

    @ApiModelProperty(value = "额外响应数据", required = false)
    private Map<String, Object> extra = new HashMap<>();

    /**
     * 接收额外参数到extra
     * @param key 额外参数的key
     * @param value 额外参数的value
     * @return this
     */
    public ResponseResult putExtra(String key, Object value){
        this.extra.put(key, value);
        return this;
    }

    public ResponseResult(String message, Integer code, Object data){
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // 成功返回

    public static ResponseResult success(){
        return new ResponseResult(SUCCESS.getDesc(), SUCCESS.getCode(), null);
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(SUCCESS.getDesc(), SUCCESS.getCode(), data);
    }

    public static ResponseResult success(String message, Object data){
        return new ResponseResult(message, SUCCESS.getCode(), data);
    }

    public static ResponseResult success(String message, Integer code){
        return new ResponseResult(message, code, null);
    }


    public static ResponseResult success(String message, Integer code, Object data){
        return new ResponseResult(message, code, data);
    }

    // 失败返回

    public static ResponseResult error(){
        return new ResponseResult(ERROR.getDesc(), FAILURE.getCode(), null);
    }

    public static ResponseResult error(String message){
        return new ResponseResult(message,FAILURE.getCode(),null);
    }

    public static ResponseResult error(Integer code, String message){
        return new ResponseResult(message, code, null);
    }

}
