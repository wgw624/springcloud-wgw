package org.weiyada.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/30 2:30 下午
 */

@Data
@Component
public class UserAuth implements Serializable {
    private Long userId;
    private String userName;
    private String loginName;
}
