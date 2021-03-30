package org.weiyada.base.exception;

import org.springframework.stereotype.Component;
import org.weiyada.config.enumclass.ExceptionEnum;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/30 3:38 下午
 */

@Component
public class CustomException extends RuntimeException{

    private int exceptionCode;
    private String msg;
    private ExceptionEnum exceptionEnum;
    public CustomException(){
    }
    public CustomException(Throwable throwable){
        super(throwable);
    }
    public CustomException(int exceptionCode, String msg,Throwable throwable){
        super(msg,throwable);
        this.exceptionCode = exceptionCode;
    }
    public CustomException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMsg());
        this.exceptionCode = exceptionEnum.getCode();
//        this.msg = exceptionEnum.getMsg();
    }

    public CustomException(String msg){
        super(msg);
    }

}
