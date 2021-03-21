package org.weiyada.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@TableName("department")
@ApiModel(value="Department对象", description="部门表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Department{" +
        "id=" + id +
        ", departName=" + departName +
        ", createTime=" + createTime +
        "}";
    }
}
