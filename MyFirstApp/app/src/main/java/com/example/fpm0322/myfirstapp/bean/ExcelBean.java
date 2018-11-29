package com.example.fpm0322.myfirstapp.bean;

/**
 * 融资申请excel单据模板
 */
public class ExcelBean {

    /**
     * 客户名称
     */
    private String customerName = null;
    /**
     * 单据类型
     */
    private String billType = null;
    /**
     * 订单号
     */
    private String orderNo = null;
    /**
     * 单据号
     */
    private String billNo = null;
    /**
     * 采购法人
     */
    private String purchasePerson = null;
    /**
     * 单据币种
     */
    private String billCurrency = null;
    /**
     * 单据金额
     */
    private String billAmount = null;
    /**
     * 单据开立日前
     */
    private String billDate = null;
    /**
     * 备注
     */
    private String remark = null;

    public String getCustomerName() {
        return customerName == null ? "" : customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBillType() {
        return billType == null ? "" : billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getOrderNo() {
        return orderNo == null ? "" : orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillNo() {
        return billNo == null ? "" : billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPurchasePerson() {
        return purchasePerson == null ? "" : purchasePerson;
    }

    public void setPurchasePerson(String purchasePerson) {
        this.purchasePerson = purchasePerson;
    }

    public String getBillCurrency() {
        return billCurrency == null ? "" : billCurrency;
    }

    public void setBillCurrency(String billCurrency) {
        this.billCurrency = billCurrency;
    }

    public String getBillAmount() {
        return billAmount == null ? "" : billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillDate() {
        return billDate == null ? "" : billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getRemark() {
        return remark == null ? "" : remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ExcelBean{" +
                "customerName='" + customerName + '\'' +
                ", billType='" + billType + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", billNo='" + billNo + '\'' +
                ", purchasePerson='" + purchasePerson + '\'' +
                ", billCurrency='" + billCurrency + '\'' +
                ", billAmount='" + billAmount + '\'' +
                ", billDate='" + billDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
