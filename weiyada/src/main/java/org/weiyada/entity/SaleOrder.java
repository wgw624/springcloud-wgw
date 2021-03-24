package org.weiyada.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 销售订单表
 * </p>
 *
 * @author 
 * @since 2021-03-24
 */
@TableName("sale_order")
@ApiModel(value="SaleOrder对象", description="销售订单表")
public class SaleOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增Id")
    private Long id;

    @ApiModelProperty(value = "订单Id")
    private String orderId;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "销售单号")
    private String saleOrderNo;

    @ApiModelProperty(value = "客户地址")
    private String customerAddress;

    @ApiModelProperty(value = "客户电话")
    private String customerPhone;

    @ApiModelProperty(value = "编号")
    private String no;

    @ApiModelProperty(value = "规格（长、宽）")
    private String model;

    @ApiModelProperty(value = "平米")
    private String size;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "多少钱一套")
    private BigDecimal money;

    @ApiModelProperty(value = "包装费")
    private BigDecimal packageFee;

    @ApiModelProperty(value = "弯弧")
    private String curve;

    @ApiModelProperty(value = "颜色型号")
    private String colour;

    @ApiModelProperty(value = "总金额")
    private BigDecimal amountMoney;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "0、待生产，1、生产中，2、以生产完成，3、待收款，4、已收款，5、已发货")
    private Integer states;

    @ApiModelProperty(value = "0、未付款，1、已付款")
    private Integer isPay;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(BigDecimal packageFee) {
        this.packageFee = packageFee;
    }

    public String getCurve() {
        return curve;
    }

    public void setCurve(String curve) {
        this.curve = curve;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", customerName=" + customerName +
        ", saleOrderNo=" + saleOrderNo +
        ", customerAddress=" + customerAddress +
        ", customerPhone=" + customerPhone +
        ", no=" + no +
        ", model=" + model +
        ", size=" + size +
        ", amount=" + amount +
        ", price=" + price +
        ", money=" + money +
        ", packageFee=" + packageFee +
        ", curve=" + curve +
        ", colour=" + colour +
        ", amountMoney=" + amountMoney +
        ", remarks=" + remarks +
        ", states=" + states +
        ", isPay=" + isPay +
        ", createTime=" + createTime +
        "}";
    }
}
