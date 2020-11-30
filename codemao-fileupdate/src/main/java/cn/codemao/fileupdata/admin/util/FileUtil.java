package cn.codemao.fileupdata.admin.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

/**
 * @author ：weiguangwei
 * @date ：3:41 下午 2020/11/27
 */
public class FileUtil {

    public static String getClassPath(){
        String classPath = null;
        try{
            classPath = ResourceUtils.getURL("classpath:").getPath();
        }catch (Exception e){
            e.printStackTrace();
        }
        return classPath;
    }
    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getFileByte(File file){
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
    public static List<File> getPathAllFile(String sourcePath, List<File> list) {
        String classPath = FileUtil.getClassPath();
        File file = new File(sourcePath);
        if(file.isDirectory()){
            String []fileList = file.list();
            for(int i=0;i<fileList.length;i++){
                File readFile = new File(sourcePath+File.separatorChar+fileList[i]);
                if(readFile.isDirectory()){
                    getPathAllFile(sourcePath+File.separatorChar+fileList[i],list);
                }else{
                    list.add(readFile);
                }
            }
        }else{
            list.add(file);
        }
        return list;
    }

    public static String getFileNameFromUrl(String url){
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                int  len = name.indexOf("?");
                if(len>0){
                    name = name.substring(0,len);
                }
                return name;
            }
        }
        return name;
    }
    public static String getFilePathFromUrl(String url,String fileDir){
        String path = new Long(System.currentTimeMillis()).toString() + ".X";
        int startIndex = url.indexOf(fileDir);
        int endIndex = url.lastIndexOf("/");
        if(startIndex>0&&endIndex>0){
            path = url.substring(startIndex,endIndex);
        }
        return path;
    }


//    public static void copyFileToPath(File file,String outputFilePath){
//        try{
//            FileInputStream fis = new FileInputStream(file);
//            FileOutputStream fos = new FileOutputStream(outputFilePath,true);
//            byte []buffer = new byte[1024];
//            int len = 0;
//            while((len=fis.read(buffer))!=-1){
//                fos.write(buffer);
//            }
//            fos.close();
//            fis.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
