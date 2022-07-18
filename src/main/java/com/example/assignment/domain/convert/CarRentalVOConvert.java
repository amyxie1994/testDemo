package com.example.assignment.domain.convert;

import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.domain.vo.CarRentalVO;

public class CarRentalVOConvert {

    public static CarRentalDO carRentalVO2DO(CarRentalVO carRentalVO){

        if(null == carRentalVO){
            return null;
        }
        CarRentalDO carRentalDO = new CarRentalDO();
        carRentalDO.setCarModel(carRentalVO.getCarModel());
        carRentalDO.setCustomerName(carRentalVO.getCustomerName());
        carRentalDO.setCarModel(carRentalVO.getCarModel());
        carRentalDO.setRentalStartTime(carRentalVO.getRentalStartTime());
        carRentalDO.setRentalEndTime(carRentalVO.getRentalEndTime());

        return carRentalDO;
    }

}
