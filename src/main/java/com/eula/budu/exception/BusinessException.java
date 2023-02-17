package com.eula.budu.exception;

import com.eula.budu.common.ResultStatus;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import static com.eula.budu.common.ResultStatus.ERROR;

@Data
@ControllerAdvice(basePackages = "com.eula.budu")
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 6401507641198338287L;

    protected Integer code;

    protected String message;

    public BusinessException(){super();}

    public BusinessException(ResultStatus resultStatus){
        super(resultStatus.getDesc());
        this.code = resultStatus.getCode();
        this.message = resultStatus.getDesc();
    }

    public BusinessException(String message){
        super(message);
        this.code = ERROR.getCode();
        this.message = message;
    }

    public BusinessException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Integer code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Throwable cause){
        super(cause);
        this.code = ERROR.getCode();
        this.message = ERROR.getDesc();
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
