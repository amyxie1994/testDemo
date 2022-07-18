package com.example.assignment.service;

import com.example.assignment.domain.domain.CarRentalDO;

public interface CarRentalService {

    /**
     * rent car
     * @param carRentalDO car rental vo
     * @return assign car no
     */
    String rentCar(CarRentalDO carRentalDO);
}
