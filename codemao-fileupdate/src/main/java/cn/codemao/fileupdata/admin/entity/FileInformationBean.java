package cn.codemao.fileupdata.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

/**
 * @author ：weiguangwei
 * @date ：9:20 下午 2020/11/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInformationBean {

    private String fileVersion;
    private String filePath;
    private String fileName;
}
