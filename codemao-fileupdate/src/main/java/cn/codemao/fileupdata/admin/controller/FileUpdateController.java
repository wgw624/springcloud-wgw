package cn.codemao.fileupdata.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiguangwei
 */

@RestController
@RequestMapping("updateFile")
public class FileUpdateController {
    @RequestMapping("checkAndUpdateDifferent")
    public void checkUpdateDifferent(String updateResourceAddr,String localResourceAddr){

    }
}
