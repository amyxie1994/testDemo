package com.example.assignment.domain.po;

import lombok.Data;

@Data
public class CarModelPO {

    /**
     * name of model
     */
    private String modelName;

    /**
     * name of brand
     */
    private String brand;

    /**
     * create time
     */
    private Long createTime;


    /**
     * update time
     */
    private Long updateTime;
}
