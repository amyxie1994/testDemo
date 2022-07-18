package com.example.assignment.service.impl;


import com.example.assignment.common.enums.CarRentalStatus;
import com.example.assignment.dao.CarRentalLogMapper;
import com.example.assignment.domain.convert.CarDailyRentalLogConvert;
import com.example.assignment.domain.domain.CarDailyRentalLogDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import com.example.assignment.service.CarRentalLogQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("carRentalLogQueryService")
public class CarRentalLogQueryServiceImpl implements CarRentalLogQueryService {

    @Autowired
    private CarRentalLogMapper carRentalLogMapper;

    @Override
    public List<CarDailyRentalLogDO> queryAvailableDailyRentalByCarNoList(List<String> carNoList) {
        if(CollectionUtils.isEmpty(carNoList)){
            return null;
        }
        CarDailyRentalLogPO queryPO = new CarDailyRentalLogPO();
        queryPO.setCarNoList(carNoList);
        queryPO.setStatus(CarRentalStatus.AVALIABLE.value);
        return queryRentalLogList(queryPO);

    }

    private List<CarDailyRentalLogDO> queryRentalLogList(CarDailyRentalLogPO queryPO){
        return CarDailyRentalLogConvert.carDailyRentalLogPOList2DOList(carRentalLogMapper.queryCarDailyRentalLogList(queryPO));
    }

}
