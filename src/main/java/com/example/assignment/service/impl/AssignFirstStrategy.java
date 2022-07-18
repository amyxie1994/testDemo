package com.example.assignment.service.impl;

import com.example.assignment.common.assembler.CarRentalAssembler;
import com.example.assignment.controller.BusinessException;
import com.example.assignment.domain.domain.CarDailyRentalLogDO;
import com.example.assignment.domain.domain.CarInfoDO;
import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.service.CarInfoQueryService;
import com.example.assignment.service.CarRentalLogQueryService;
import com.example.assignment.service.IAssignStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.assignment.common.errorcode.CarRentalErrorCode.CAR_MODEL_NOT_EXIST;
import static com.example.assignment.common.errorcode.CarRentalErrorCode.NOT_AVAILABLE_CAR;

@Service("assignFirstStrategy")
public class AssignFirstStrategy implements IAssignStrategy {


    @Autowired
    private CarRentalLogQueryService carRentalLogQueryService;

    @Autowired
    private CarInfoQueryService carInfoQueryService;


    /**
     * assignFirst strategy
     * will return the first match available car
     * algorithm:
     * take rental time period into time slot
     * get the daily rental list of available car (1 day as a rental time slot)
     * check whether all rental time slot are included for each available car
     *
     *
     * @param carRentalDO rental info
     * @return assign car no
     */
    @Override
    public String assign(CarRentalDO carRentalDO) {

        String rentalModel = carRentalDO.getCarModel();
        List<CarInfoDO> carInfoDOList = carInfoQueryService.queryCarInfoListByModel(rentalModel);
        if (CollectionUtils.isEmpty(carInfoDOList)) {
            throw new BusinessException(CAR_MODEL_NOT_EXIST, "Request car model not exist");
        }

        List<String> carNoList = carInfoDOList.stream().map(CarInfoDO::getCarNo).collect(Collectors.toList());
        List<CarDailyRentalLogDO> curAvailableRentalList = carRentalLogQueryService.queryAvailableDailyRentalByCarNoList(carNoList);
        if (CollectionUtils.isEmpty(curAvailableRentalList)) {
            throw new BusinessException(NOT_AVAILABLE_CAR, "All cars have been occupied");
        }

        Map<String, Set<Long>> carAvailableTimeMap = new HashMap<>();
        curAvailableRentalList.forEach(p -> {
            Set<Long> availableTimeSet = carAvailableTimeMap.get(p.getCarNo());
            if (CollectionUtils.isEmpty(availableTimeSet)) {
                availableTimeSet = new HashSet<>();
            }
            availableTimeSet.add(p.getRecordTime());
            carAvailableTimeMap.put(p.getCarNo(), availableTimeSet);
        });

        List<Long> requireRentalDateList = CarRentalAssembler.assembleOccupiedTimeList(carRentalDO.getRentalStartTime(), carRentalDO.getRentalEndTime());
        List<String> satisfyCarNoList = new ArrayList<>();
        carNoList.forEach(carNo -> {
                    if (carAvailableTimeMap.get(carNo)
                            .containsAll(requireRentalDateList)) {
                        satisfyCarNoList.add(carNo);
                    }
                }
        );

        if (CollectionUtils.isEmpty(satisfyCarNoList)) {
            throw new BusinessException(NOT_AVAILABLE_CAR, "Please change another time slot");
        }
        return satisfyCarNoList.get(0);
    }

}
