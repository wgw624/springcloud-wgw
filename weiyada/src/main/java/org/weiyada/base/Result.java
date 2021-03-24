package org.weiyada.base;

import io.swagger.annotations.ApiModelProperty;
import org.weiyada.base.enumpage.ResultCode;

import java.io.Serializable;
import java.util.Map;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:25 下午
 */
public class Result<T> implements Serializable {

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("业务数据")
    private T data;

    @ApiModelProperty(value = "扩展数据",required = false)
    private Map<T,T> extData;

    public Result(T data){
        this.data = data;
    }
    public Result(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public Result(int code,T data){
        this.code = code;
        this.data = data;
    }
    public static <T>Result<T> successResult(T data){
        return new Result<>(ResultCode.SUCCESS.getCode(),data);
    }
    public static <T> Result<T> successResult(int code,T data){
        return new Result<T>(code,data);
    }
}
