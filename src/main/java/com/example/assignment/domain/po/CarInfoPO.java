package com.example.assignment.domain.po;

import lombok.Data;

@Data
public class CarInfoPO {

    /**
     * car no
     */
    private String carNo;
    /**
     * car model name
     */
    private String carModel;
    /**
     * create time
     */
    private Long createTime;
    /**
     * update time
     */
    private Long updateTime;
}
