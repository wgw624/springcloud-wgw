package org.weiyada.base.enumpage;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:45 下午
 */
public enum ResultCode {
    SUCCESS(200,"操作成功")
    ;
    private final int code;
    private final String msg;

    public int getCode(){
        return this.code;
    }
    public String getMsg(){
        return this.msg;
    }
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
