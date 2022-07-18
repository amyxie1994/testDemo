package com.example.assignment.domain.vo;

import lombok.Data;
import java.io.Serializable;


@Data
public class CarRentalVO implements Serializable {

    private static final long serialVersionUID = 3665050214434514191L;


    /**
     * start time of rental,timestamp
     */
    private Long rentalStartTime;
    /**
     * end time of rental,timestamp
     */
    private Long rentalEndTime;

    /**
     * name of car model
     */
    private String carModel;
    /**
     * name of customer
     */
    private String customerName;


}
