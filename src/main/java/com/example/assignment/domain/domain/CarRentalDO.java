package com.example.assignment.domain.domain;


import java.util.Date;

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


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

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


}
