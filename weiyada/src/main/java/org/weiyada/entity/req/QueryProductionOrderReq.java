package org.weiyada.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 3:01 下午
 */

@Data
public class QueryProductionOrderReq extends RequestPage{

    @ApiModelProperty(value = "生产单状态。0、生产中，1，已生产完成，2、待发货，3、已发货")
    private Integer states;
}
