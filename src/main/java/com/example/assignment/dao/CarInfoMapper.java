package com.example.assignment.dao;


import com.example.assignment.domain.po.CarInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarInfoMapper {

    /**
     * query car info list by params
     * @param queryPO queryPO
     * @return list of car info
     */
    List<CarInfoPO> queryCarInfoListByParams(CarInfoPO queryPO);
}
