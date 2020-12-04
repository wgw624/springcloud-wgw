package cn.codemao.fileupdata.admin.controller;

import cn.codemao.fileupdata.admin.service.CheckFileAndUpdateService;
import cn.codemao.fileupdata.admin.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * @author weiguangwei
 */
@RestController
@RequestMapping("updateFile")
public class FileUpdateController {

    @Autowired
    CheckFileAndUpdateService checkFileAndUpdateService;


    /**
     * 检测是否有更新
     * @param fileDir
     */
    public void checkIsUpdate(String fileDir){

    }

    /**
     * 获取需要更新的文件列表
     * @param fileDir
     * @param fileVersionInf
     */
    public void getUpdateFileList(String fileDir,String fileVersionInf){

    }

    @RequestMapping("checkAndUpdateDifferent")
    public void checkUpdateDifferent(String updateResourceAddr,String localResourceAddr){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        checkFileAndUpdateService.downloadFileService("ddd",response);
    }

    @RequestMapping("/downloadFileByUrl")
    public void downloadFileByUrl(String url,String fileDir){
        url ="http://qkk9p1wmu.hb-bkt.clouddn.com/source/src/main.js";
        fileDir = "source";

        String filePath = FileUtil.getFilePathFromUrl(url,fileDir);
        String fileName = FileUtil.getFileNameFromUrl(url);
        String path=FileUtil.getClassPath()+File.separatorChar+filePath+File.separatorChar+fileName;
        checkFileAndUpdateService.downloadFileFromServer(url,path);
        System.out.println("download success");
    }
    @RequestMapping("/download")
    public void downLoad(String downPath){
        try{
            String remoteFilePath = FileUtil.getClassPath()+"/download";
            String serverFilePath = FileUtil.getClassPath()+"/clientFile";
//            List<File> differentFileList = checkFileAndUpdateService.checkFileAndUpdateDifferent();


            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//           checkFileAndUpdateService.

            checkFileAndUpdateService.downloadFileService(downPath,response);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
