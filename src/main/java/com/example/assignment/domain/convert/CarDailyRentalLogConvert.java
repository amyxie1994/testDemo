package com.example.assignment.domain.convert;

import com.example.assignment.domain.domain.CarDailyRentalLogDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CarDailyRentalLogConvert {

    public static List<CarDailyRentalLogDO> carDailyRentalLogPOList2DOList(List<CarDailyRentalLogPO> carDailyRentalLogPOList){
        if(CollectionUtils.isEmpty(carDailyRentalLogPOList)){
            return null;
        }
        return carDailyRentalLogPOList.stream().map(carDailyRentalLogPO -> carDailyRentalLogPO2DO(carDailyRentalLogPO)).collect(Collectors.toList());
    }

    public static CarDailyRentalLogDO carDailyRentalLogPO2DO(CarDailyRentalLogPO carDailyRentalLogPO){

        if(null == carDailyRentalLogPO){
            return null;
        }

        CarDailyRentalLogDO carDailyRentalLogDO = new CarDailyRentalLogDO();
        carDailyRentalLogDO.setCarNo(carDailyRentalLogPO.getCarNo());
        carDailyRentalLogDO.setRecordTime(carDailyRentalLogPO.getRecordTime());
        carDailyRentalLogDO.setStatus(carDailyRentalLogPO.getStatus());

        return carDailyRentalLogDO;
    }
}
