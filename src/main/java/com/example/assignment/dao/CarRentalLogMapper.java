package com.example.assignment.dao;

import com.example.assignment.domain.po.CarDailyRentalLogPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentalLogMapper {


    /**
     * update car rental status
     * @param carDailyRentalLogPO car status update info
     *
     */
    void updateCarRentalLogStatus(CarDailyRentalLogPO carDailyRentalLogPO);
}
