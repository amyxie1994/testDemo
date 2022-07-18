package com.example.assignment.service;

import com.example.assignment.domain.domain.CarInfoDO;

import java.util.List;

public interface CarInfoQueryService {

    /**
     * query car info list by model
     * @return list of car info
     */
    List<CarInfoDO> queryCarInfoListByModel(String model);
}
