package com.example.assignment.domain.po;


import lombok.Data;

@Data
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

}
