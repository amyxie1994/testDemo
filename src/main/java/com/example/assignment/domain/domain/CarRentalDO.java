package com.example.assignment.domain.domain;


import lombok.Data;

import java.util.Date;

@Data
public class CarRentalDO {

    /**
     * start date of rental
     */
    private Date rentalStartDate;
    /**
     * end date of rental
     */
    private Date rentalEndDate;

    /**
     * start time of rental
     */
    private Long rentalStartTime;
    /**
     * end time of rental
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
