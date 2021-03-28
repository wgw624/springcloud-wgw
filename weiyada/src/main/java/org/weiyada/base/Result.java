package org.weiyada.base;

import com.sun.javafx.scene.traversal.TraversalContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.weiyada.base.enumpage.ResultCode;

import java.io.Serializable;
import java.util.Map;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:25 下午
 */
@ApiModel("前段统一结果数据返回")
@Data
public class  Result<T> implements Serializable {

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty(value = "状态码",required = true)
    private Integer code;

    @ApiModelProperty(value = "是否成功",required = true)
    private boolean success;

    @ApiModelProperty("业务数据")
    private T data;

    @ApiModelProperty(value = "扩展数据")
    private Map<T,T> extData;

    private String tranceId;

    private Result(T data){
        this.data = data;
    }
//    private Result(int code,String msg){
//        this.code = code;
//        this.msg = msg;
//        this.success = ResultCode.SUCCESS.getCode() == code;
//    }
    private Result(int code,T data){
        this.code = code;
        this.data = data;
        this.success = ResultCode.SUCCESS.getCode() == code;
    }
    public static <T>Result<T> successResult(T data){
        return new Result<T>(ResultCode.SUCCESS.getCode(),data);
    }
    public static <T> Result<T> successResult(int code,T data){
        return new Result<T>(code,data);
    }
}
