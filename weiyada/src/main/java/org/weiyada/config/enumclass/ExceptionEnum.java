package org.weiyada.config.enumclass;

import lombok.Data;
import lombok.Setter;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/30 3:39 下午
 */

public enum ExceptionEnum {

    PERMISSION_TOKEN_EXPIRED(401,"token已过期"),
    PERMISSION_TOKEN_MISS(401,"没有发现token"),
    PERMISSION_TOKEN_INVALID(402,"token不和法"),
    PERMISSION_PATH_NOTFOUND(404,"路径没有发现"),
    PERMISSION_SIGNATURE_ERROR(405,"签名失败"),
    //用户
    USER_NOT_LOGGED_IN(301,"请先登录用户")
    ;
    private int code;
    private String msg;

    ExceptionEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ExceptionEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
