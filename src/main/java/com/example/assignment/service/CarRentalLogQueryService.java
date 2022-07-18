package com.example.assignment.service;

import com.example.assignment.domain.domain.CarDailyRentalLogDO;


import java.util.List;

public interface CarRentalLogQueryService {

    /**
     * query car daily rental log
     * @param  carNoList query car no list
     * @return list of rental log
     */
    List<CarDailyRentalLogDO> queryAvailableDailyRentalByCarNoList(List<String> carNoList);
}
