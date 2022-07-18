package com.example.assignment.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


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


    public Long getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(Long rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public Long getRentalEndTime() {
        return rentalEndTime;
    }

    public void setRentalEndTime(Long rentalEndTime) {
        this.rentalEndTime = rentalEndTime;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
