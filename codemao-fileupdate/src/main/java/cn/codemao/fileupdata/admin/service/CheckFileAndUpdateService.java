package cn.codemao.fileupdata.admin.service;

/**
 * @author weiguangwei
 *
 */
public interface CheckFileAndUpdateService {
    /**
     * 检测客户端文件和远程服务器文件是否一致，不一致的文件更新
     * @param remoteSourceAddr
     * @param clientSourceAddr
     */
    public void checkFileAndUpdateDifferent(String remoteSourceAddr,String clientSourceAddr);
}
