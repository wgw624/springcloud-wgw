package org.weiyada.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 生产订单表
 * </p>
 *
 * @author 
 * @since 2021-03-21
 */
@TableName("production_order")
@ApiModel(value="ProductionOrder对象", description="生产订单表")
public class ProductionOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "生成订单号")
    private String prductionOrderId;

    @ApiModelProperty(value = "销售单号")
    private String saleOrderId;

    @ApiModelProperty(value = "序号")
    private String serialNo;

    @ApiModelProperty(value = "长")
    private BigDecimal length;

    @ApiModelProperty(value = "宽")
    private BigDecimal width;

    @ApiModelProperty(value = "帘片宽度")
    private Double curtainWidth;

    @ApiModelProperty(value = "罩壳帘片")
    private String shellCurtain;

    @ApiModelProperty(value = "卷管63")
    private String drup63;

    @ApiModelProperty(value = "卷管85")
    private String drup85;

    @ApiModelProperty(value = "小端板轨道长度")
    private BigDecimal xdbTrackLength;

    @ApiModelProperty(value = "小端板流利轮长度")
    private BigDecimal xdbLllLength;

    @ApiModelProperty(value = "中端板轨道长度")
    private BigDecimal zdbTrackLength;

    @ApiModelProperty(value = "中端板流利轮长度")
    private BigDecimal zdbLllLength;

    @ApiModelProperty(value = "大端板轨道长度")
    private BigDecimal ddbTrackLength;

    @ApiModelProperty(value = "大端板流利轮长度")
    private BigDecimal ddbLllLength;

    @ApiModelProperty(value = "帘片型号")
    private String curtainType;

    @ApiModelProperty(value = "颜色")
    private String color;

    @ApiModelProperty(value = "套数")
    private Integer num;

    @ApiModelProperty(value = "电机方向")
    private String electricDirection;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "生产时间")
    private Long createTime;

    @ApiModelProperty(value = "完成时间")
    private Long finishTime;

    @ApiModelProperty(value = "创建人Id")
    private Long createUserId;

    @ApiModelProperty(value = "生产单状态。0、生产中，1，已生产完成，2、待发货，3、已发货")
    private Integer states;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrductionOrderId() {
        return prductionOrderId;
    }

    public void setPrductionOrderId(String prductionOrderId) {
        this.prductionOrderId = prductionOrderId;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public Double getCurtainWidth() {
        return curtainWidth;
    }

    public void setCurtainWidth(Double curtainWidth) {
        this.curtainWidth = curtainWidth;
    }

    public String getShellCurtain() {
        return shellCurtain;
    }

    public void setShellCurtain(String shellCurtain) {
        this.shellCurtain = shellCurtain;
    }

    public String getDrup63() {
        return drup63;
    }

    public void setDrup63(String drup63) {
        this.drup63 = drup63;
    }

    public String getDrup85() {
        return drup85;
    }

    public void setDrup85(String drup85) {
        this.drup85 = drup85;
    }

    public BigDecimal getXdbTrackLength() {
        return xdbTrackLength;
    }

    public void setXdbTrackLength(BigDecimal xdbTrackLength) {
        this.xdbTrackLength = xdbTrackLength;
    }

    public BigDecimal getXdbLllLength() {
        return xdbLllLength;
    }

    public void setXdbLllLength(BigDecimal xdbLllLength) {
        this.xdbLllLength = xdbLllLength;
    }

    public BigDecimal getZdbTrackLength() {
        return zdbTrackLength;
    }

    public void setZdbTrackLength(BigDecimal zdbTrackLength) {
        this.zdbTrackLength = zdbTrackLength;
    }

    public BigDecimal getZdbLllLength() {
        return zdbLllLength;
    }

    public void setZdbLllLength(BigDecimal zdbLllLength) {
        this.zdbLllLength = zdbLllLength;
    }

    public BigDecimal getDdbTrackLength() {
        return ddbTrackLength;
    }

    public void setDdbTrackLength(BigDecimal ddbTrackLength) {
        this.ddbTrackLength = ddbTrackLength;
    }

    public BigDecimal getDdbLllLength() {
        return ddbLllLength;
    }

    public void setDdbLllLength(BigDecimal ddbLllLength) {
        this.ddbLllLength = ddbLllLength;
    }

    public String getCurtainType() {
        return curtainType;
    }

    public void setCurtainType(String curtainType) {
        this.curtainType = curtainType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getElectricDirection() {
        return electricDirection;
    }

    public void setElectricDirection(String electricDirection) {
        this.electricDirection = electricDirection;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "ProductionOrder{" +
        "id=" + id +
        ", prductionOrderId=" + prductionOrderId +
        ", saleOrderId=" + saleOrderId +
        ", serialNo=" + serialNo +
        ", length=" + length +
        ", width=" + width +
        ", curtainWidth=" + curtainWidth +
        ", shellCurtain=" + shellCurtain +
        ", drup63=" + drup63 +
        ", drup85=" + drup85 +
        ", xdbTrackLength=" + xdbTrackLength +
        ", xdbLllLength=" + xdbLllLength +
        ", zdbTrackLength=" + zdbTrackLength +
        ", zdbLllLength=" + zdbLllLength +
        ", ddbTrackLength=" + ddbTrackLength +
        ", ddbLllLength=" + ddbLllLength +
        ", curtainType=" + curtainType +
        ", color=" + color +
        ", num=" + num +
        ", electricDirection=" + electricDirection +
        ", remarks=" + remarks +
        ", createTime=" + createTime +
        ", finishTime=" + finishTime +
        ", createUserId=" + createUserId +
        ", states=" + states +
        "}";
    }
}
