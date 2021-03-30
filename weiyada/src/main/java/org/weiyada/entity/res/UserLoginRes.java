package org.weiyada.entity.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.weiyada.entity.UserInfo;

import java.io.Serializable;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/30 2:46 下午
 */
@Data
public class UserLoginRes implements Serializable {

    @ApiModelProperty("是否登录成功")
    BooleanRes booleanRes;

    @ApiModelProperty("用户信息")
    UserInfo userInfo;

    @ApiModelProperty("token")
    private String token;

}
