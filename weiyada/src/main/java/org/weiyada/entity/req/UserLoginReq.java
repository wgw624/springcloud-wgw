package org.weiyada.entity.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/29 7:23 下午
 */

@Data
public class UserLoginReq implements Serializable {
    private String loginName;
    private String password;
}
