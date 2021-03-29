package cn.codemao.fileupdata.admin.service.impl;

import cn.codemao.fileupdata.admin.service.CheckFileAndUpdateService;
import cn.codemao.fileupdata.admin.util.FileUtil;
import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author weiguangwei
 * @date 2020-11-26
 */

@Service
public class CheckFileAndUpdateServiceImpl implements CheckFileAndUpdateService {
    @Override
    public void checkFileAndUpdateDifferent(String remoteSourceAddr, String clientSourceAddr) {
        List<File> remoteSourceAddrList = new ArrayList<>();
        List<File> clientSourceAddrList = new ArrayList<>();
        remoteSourceAddrList = FileUtil.getPathAllFile(remoteSourceAddr,remoteSourceAddrList);
        clientSourceAddrList = FileUtil.getPathAllFile(clientSourceAddr,clientSourceAddrList);
        Map<String,File> remoteSourceFileMap = remoteSourceAddrList.stream().collect(Collectors.toMap(file -> file.getName(),ff->ff,(f1,f2)->f2));
        Map<String,File> clientSourceFileMap = clientSourceAddrList.stream().collect(Collectors.toMap(file -> file.getName(),ff->ff,(f1,f2)->f2));

        List<String> remoteFileNameList =
                remoteSourceAddrList.stream().map(file -> file.getName()).collect(Collectors.toList());
        List<String> clientFileNameList
                = clientSourceAddrList.stream().map(file->file.getName()).collect(Collectors.toList());

        List<File> differentFileList = new ArrayList<>();
        List<File> newFileList = new ArrayList<>();
        List<File> delFileList = new ArrayList<>();

        clientFileNameList.forEach(item->{
            File file = clientSourceFileMap.get(item);
            if(!remoteSourceFileMap.containsKey(item)){
               delFileList.add(file);
            }
        });

        remoteFileNameList.forEach(item->{
            File remoteFile = remoteSourceFileMap.get(item);
            File clientFile = clientSourceFileMap.get(item);

            if(!clientSourceFileMap.containsKey(item)){
                newFileList.add(remoteFile);
            }
            byte []remoteFileBytes = FileUtil.getFileByte(remoteFile);
            byte []clientFileBytes = FileUtil.getFileByte(clientFile);
            String remoteFileMd5 = DigestUtils.md5Hex(remoteFileBytes);
            String clientFileMd5 = DigestUtils.md5Hex(clientFileBytes);
            if(!remoteFileMd5.equals(clientFileMd5)){
                //System.out.println(remoteFile.getName()+"-----不相同");
                differentFileList.add(remoteFile);
            }
        });
    }

    /**
     *
     * @param differentFileList 文件列表
     * @param tempPath
     * @param fileName 比对的文件夹名字
     */
    @Override
    public void differentFileToTempFile(List<File> differentFileList, String tempPath, String fileName){
        differentFileList.forEach(item->{
            try{
                String path = item.getPath();
                String outFileNamePath = path.substring(path.indexOf(fileName+File.separatorChar),path.length());
                String filePath = outFileNamePath.substring(outFileNamePath.indexOf(0,outFileNamePath.lastIndexOf(File.separatorChar)));
                String tmpPath = FileUtil.getClassPath()+File.separatorChar+"temp"+File.separatorChar+filePath+File.separatorChar;
                File chekFile = new File(tmpPath);
                if(!chekFile.exists()){
                    chekFile.mkdirs();
                }
                String outTempPath = tempPath+File.separatorChar+item.getName();
//            FileUtil.copyFileToPath(item,outTempPath);
//                FileUtils.copyFileToDirectory(item,new File(outFileNamePath));
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

    @Override
    public void downloadFileFromServer(String url, String path) {
        try{
            URL httpUrl = new URL(url);
            File f = new File(path);
//            FileUtils.copyURLToFile(httpUrl,f);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void checkFileIsUpdate(String dirSourceFile) {

    }

    @Override
    public void downloadFileService(String downloadPath, HttpServletResponse response) {
        String serverPath = FileUtil.getClassPath()+File.separatorChar+"download";
        List<File> returnList = new ArrayList<>();
        returnList = FileUtil.getPathAllFile(serverPath,returnList);
        returnList.forEach(item->{
            try{
               // FileInputStream fis =  new FileInputStream(item);
                System.out.println(item.getParent());
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(item));
                response.reset();
                response.setContentType("bin");
                response.addHeader("Content-Disposition", "attachment; filename=\"" + item.getName() + "\"");

                OutputStream  ops = response.getOutputStream();
                byte[] b = new byte[100];
                int len ;
                while((len =bis.read(b))>0){
                    ops.write(b,0,len);
                }
                bis.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        System.out.println(returnList);
    }

}
