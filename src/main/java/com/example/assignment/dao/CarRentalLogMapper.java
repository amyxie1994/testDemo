package com.example.assignment.dao;

import com.example.assignment.domain.po.CarDailyRentalLogPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentalLogMapper {


    /**
     * query car daily rental log list
     * will scan the whole table
     * @param queryPO query param po
     * @return list of rental log
     */
    List<CarDailyRentalLogPO> queryCarDailyRentalLogList(CarDailyRentalLogPO queryPO);

    /**
     * update car rental status
     * @param carDailyRentalLogPO car status update info
     *
     */
    void updateCarRentalLogStatus(CarDailyRentalLogPO carDailyRentalLogPO);
}
