package cn.codemao.fileupdata.admin.service.impl;

import cn.codemao.fileupdata.admin.service.CheckFileAndUpdateService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
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
        remoteSourceAddrList = getPathAllFile(remoteSourceAddr,remoteSourceAddrList);
        clientSourceAddrList = getPathAllFile(clientSourceAddr,clientSourceAddrList);
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
            byte []remoteFileBytes = getFileByte(remoteFile);
            byte []clientFileBytes = getFileByte(clientFile);
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
     * @param file
     * @return
     * @throws IOException
     */
    public byte[] getFileByte(File file){
        byte []buffer;
        try{
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
            byte []bytes = new byte[fis.available()];
            int temp;
            while((temp=fis.read(bytes))!=-1){
                baos.write(bytes,0,temp);
            }
            fis.close();
            baos.close();
            buffer = baos.toByteArray();
            return buffer;
        }catch (Exception e){
            e.printStackTrace();
        }

       return null;

    }
    public List<File> getPathAllFile(String sourcePath, List<File> list){
        File file = new File(sourcePath);
        if(file.isDirectory()){
            String []fileList = file.list();
            for(int i=0;i<fileList.length;i++){
                File readFile = new File(sourcePath+File.separatorChar+fileList[i]);
                if(readFile.isDirectory()){
                    getPathAllFile(sourcePath+File.separatorChar+fileList[i],list);
                }else{
//                    map.put(readFile.getName(),readFile.getPath());
                    list.add(readFile);
                }
            }
        }else{
            list.add(file);
        }
        return list;
    }
}
