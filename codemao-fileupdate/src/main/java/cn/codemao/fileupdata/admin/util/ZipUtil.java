package cn.codemao.fileupdata.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ：weiguangwei
 * @date ：4:50 下午 2020/11/27
 */

@Slf4j
public class ZipUtil {

    public static void zipFile(String filePath, String fileName, String outPathFile) {
//        logger.info("filePath:{}, fileName:{}, outPath:{}", filePath, fileName, outPath);
        try {
            //创建Test.zip文件

             String outPath = outPathFile.substring(0,outPathFile.lastIndexOf(File.separatorChar));
             File checkFile = new File(outPath);
             if(!checkFile.exists()){
                 checkFile.mkdirs();
             }
            OutputStream is = new FileOutputStream(outPathFile);
            //检查输出流,采用CRC32算法，保证文件的一致性
            CheckedOutputStream cos = new CheckedOutputStream(is, new CRC32());

            //创建zip文件的输出流
            ZipOutputStream zos = new ZipOutputStream(cos);
            //需要压缩的文件或文件夹对象
            File file = new File(filePath);
            //压缩文件的具体实现函数
            zipFilePost(zos,file,filePath,fileName,outPathFile);
            zos.close();
            cos.close();
            is.close();
            System.out.println("压缩完成");
        } catch (Exception e) {
//            logger.error("压缩失败zipFile，Exception:" + e);
            System.out.println("压缩失败");
        }
    }

    /**
     * 压缩文件
     * @param zos       zip文件的输出流
     * @param file      需要压缩的文件或文件夹对象
     * @param filePath  压缩的文件路径
     * @param fileName  需要压缩的文件夹名
     * @param outPath   缩完成后保存为Test.zip文件
     */
    private static void zipFilePost(ZipOutputStream zos, File file, String filePath, String fileName, String outPath){

        try{
            String path = file.getPath();
            String zosName = "";
            if(!StringUtils.isEmpty(path)){
                zosName = path.substring(path.indexOf(fileName));
            }
            File[] files = file.listFiles();
            if(file.isDirectory() && files != null && files.length > 0) {
                // 创建压缩文件的目录结构
                zos.putNextEntry(new ZipEntry(zosName + File.separator));
                for(File f : files) {
                    zipFilePost(zos, f, filePath, fileName, outPath);
                }
            } else {
//                logger.info("正在压缩文件:{}",file.getName());
                // 创建压缩文件
                zos.putNextEntry(new ZipEntry(zosName));
                // 用字节方式读取源文件
                InputStream is = new FileInputStream(file.getPath());
                // 创建一个缓存区
                BufferedInputStream bis = new BufferedInputStream(is);
                // 字节数组,每次读取1024个字节
                byte [] b = new byte[1024];
                // 循环读取，边读边写
                while(bis.read(b)!=-1) {
                    // 写入压缩文件
                    zos.write(b);
                }
                //关闭流
                bis.close();
                is.close();
            }
        } catch (Exception e) {
//            logger.error("压缩文件失败zipFilePost，Exception：" + e);
        }
    }


}
