package org.weiyada.entity.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 2:01 下午
 */

@Data
public class QuerySaleOrderReq extends RequestPage{

    @ApiModelProperty(value = "0、待生产，1、生产中，2、以生产完成，3、待收款，4、已收款，5、已发货")
    private Integer states;

    @ApiModelProperty(value = "0、未付款，1、已付款")
    private Integer isPay;
}
