package com.example.assignment.domain.convert;

import com.example.assignment.domain.domain.CarInfoDO;
import com.example.assignment.domain.po.CarInfoPO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CarInfoConvert {


    public static List<CarInfoDO> carInfoPOList2DOList(List<CarInfoPO> carInfoPOList){
        if(CollectionUtils.isEmpty(carInfoPOList)){
            return null;
        }
        return carInfoPOList.stream().map(carInfoPO -> carInfoPO2DO(carInfoPO)).collect(Collectors.toList());
    }

    public static CarInfoDO carInfoPO2DO(CarInfoPO carInfoPO){

        if(null == carInfoPO){
            return null;
        }
        CarInfoDO carInfoDO = new CarInfoDO();
        carInfoDO.setCarModel(carInfoPO.getCarModel());
        carInfoDO.setCarNo(carInfoPO.getCarNo());

        return carInfoDO;
    }
}
