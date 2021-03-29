package org.weiyada.entity.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/29 5:45 下午
 */

@Data
public class BooleanRes implements Serializable {
    private String msg;
    private Boolean flag;
}
