package com.example.assignment.common.assembler;

import com.example.assignment.common.enums.CarRentalStatus;
import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import com.example.assignment.domain.po.CarRentalOrderPO;

import java.util.ArrayList;
import java.util.List;

public class CarRentalAssembler {

    public static CarRentalOrderPO assembleCarRentalOrderInfo(CarRentalDO carRentalDO, String assignedCarNo) {
        CarRentalOrderPO carRentalOrderPO = new CarRentalOrderPO();
        carRentalOrderPO.setCarNo(assignedCarNo);
        carRentalOrderPO.setCreateTime(System.currentTimeMillis());
        carRentalOrderPO.setCustomerName(carRentalDO.getCustomerName());
        carRentalOrderPO.setStartTime(carRentalDO.getRentalStartTime());
        carRentalOrderPO.setEndTime(carRentalDO.getRentalEndTime());
        carRentalOrderPO.setModelName(carRentalDO.getCarModel());
        return carRentalOrderPO;
    }


    public static CarDailyRentalLogPO assembleCarStatusInfo(CarRentalDO carRentalDO, String assignCarNo) {
        CarDailyRentalLogPO updateStatusInfoPO = new CarDailyRentalLogPO();
        List<Long> occupiedTimeList = assembleOccupiedTimeList(carRentalDO.getRentalStartTime(), carRentalDO.getRentalEndTime());
        updateStatusInfoPO.setCarNo(assignCarNo);
        updateStatusInfoPO.setStatus(CarRentalStatus.OCCUPIED.value);
        updateStatusInfoPO.setOperateTimeList(occupiedTimeList);
        return updateStatusInfoPO;
    }

    public static List<Long> assembleOccupiedTimeList(Long startTime, Long endTime) {
        List<Long> occupiedTimeList = new ArrayList<>();
        Long temp = startTime;
        while (temp < endTime) {
            Long occupiedTime = temp;
            occupiedTimeList.add(occupiedTime);
            //temp += 3600 * 24 * 1000;
            temp +=1;
        }
        return occupiedTimeList;
    }



}
