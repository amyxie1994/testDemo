package com.example.assignment.domain.po;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Data
public class CarDailyRentalLogPO {
    /**
     * car available status
     * 0: available
     * 1: occupied
     */
    private Integer status;
    /**
     * car no
     */
    private String carNo;
    /**
     * date of this car
     */
    private Long recordTime;
    /**
     * order id, if it is occupied, record its order id
     */
    private Long orderId;
    /**
     * create time
     */
    private Long createTime;
    /**
     * update time
     */
    private Long updateTime;

    private List<Long> operateTimeList=new ArrayList<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public List<Long> getOperateTimeList() {
        return operateTimeList;
    }

    public void setOperateTimeList(List<Long> operateTimeList) {
        this.operateTimeList = operateTimeList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
