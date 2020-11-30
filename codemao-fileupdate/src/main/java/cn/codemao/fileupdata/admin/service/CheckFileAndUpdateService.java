package cn.codemao.fileupdata.admin.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author weiguangwei
 *
 */
public interface CheckFileAndUpdateService {
    /**
     * 检测客户端文件和远程服务器文件是否一致，不一致的文件更新
     *
     * @param remoteSourceAddr
     * @param clientSourceAddr
     */
    public void checkFileAndUpdateDifferent(String remoteSourceAddr, String clientSourceAddr);

    /**
     *
     * @param downloadPath
     * @param response
     */
    public void downloadFileService(String downloadPath, HttpServletResponse response);

    /**
     *
     * @param differentFileList
     * @param tempPath
     * @param fileName
     */
    public void differentFileToTempFile(List<File> differentFileList, String tempPath, String fileName);

    /**
     * 从服务器上下载文件
     * @param url
     * @param path
     */
    public void downloadFileFromServer(String url,String path);


    public void checkFileIsUpdate(String dirSourceFile);

}
