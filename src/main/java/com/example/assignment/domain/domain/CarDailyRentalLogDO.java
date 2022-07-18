package com.example.assignment.domain.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarDailyRentalLogDO {

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


    private List<Long> operateTimeList=new ArrayList<>();
}
