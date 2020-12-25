package cn.codemao.fileupdata.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 精灵
 * @program: springcloud-wgw
 * @classname: Genie
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2020/12/25 5:01 下午
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genie {
    private long id;
    private String name;
    private String lessonName;
}
