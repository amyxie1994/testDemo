package com.example.assignment.domain.po;


import lombok.Data;

//@Data
public class CarRentalOrderPO {

    /**
     * model name
     */
    private String modelName;

    /**
     * customer name
     */
    private String customerName;

    /**
     * carId;
     */
    private String carNo;

    /**
     * start rental time
     */
    private Long startTime;

    /**
     * end rental time
     */
    private Long endTime;

    /**
     * create time
     */
    private Long createTime;

    /**
     * update time
     */
    private Long updateTime;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
