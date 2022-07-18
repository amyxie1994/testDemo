package com.example.assignment.domain.po;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

    /**
     * car no list
     */
    private List<String> carNoList = new ArrayList<>();

}
