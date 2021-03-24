package org.weiyada.entity.req;
import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author：weiguangwei
 * @email: weiguangwei@codemao.cn
 * @time：2021/3/24 11:55 上午
 */

@Data
public class RequestPage implements Serializable {
    private int pageIndex = 1;
    private int pageSize = 20;
}
