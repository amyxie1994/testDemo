package com.example.assignment.service;


import com.example.assignment.domain.domain.CarRentalDO;

public interface IAssignStrategy {


    /**
     * assign available car no startegy
     * @param carRentalDO rental info
     * @return assigned car no
     */
    String assign(CarRentalDO carRentalDO);
}
