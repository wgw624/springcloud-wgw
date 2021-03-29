package org.weiyada.config.enumclass;

import lombok.Data;

public enum IgnoreUriEnum {
    SWAGGER_URI("/swagger-ui.hmtl","swagger路径"),
    LOGIN_URI("/user/login","用户登录");
    private String uri;
    private String msg;

    private IgnoreUriEnum(String uri,String msg){
        this.uri = uri;
        this.msg = msg;
    }
    public String toString(){
        return "";
    }

}
