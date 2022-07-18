package com.example.assignment.service.impl;

import com.example.assignment.common.enums.CarRentalStatus;
import com.example.assignment.common.errorcode.CarRentalErrorCode;
import com.example.assignment.controller.BusinessException;
import com.example.assignment.dao.CarRentalLogMapper;
import com.example.assignment.dao.CarRentalOrderMapper;
import com.example.assignment.domain.domain.CarDailyRentalLogDO;
import com.example.assignment.domain.domain.CarInfoDO;
import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import com.example.assignment.domain.po.CarRentalOrderPO;
import com.example.assignment.service.CarInfoQueryService;
import com.example.assignment.service.CarRentalLogQueryService;
import com.example.assignment.service.CarRentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.assignment.common.errorcode.CarRentalErrorCode.*;


@Service("carRentalService")
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarRentalOrderMapper carRentalOrderMapper;

    @Autowired
    private CarRentalLogMapper carRentalLogMapper;

    @Autowired
    private CarRentalLogQueryService carRentalLogQueryService;

    @Autowired
    private CarInfoQueryService carInfoQueryService;

    @Resource
    private TransactionTemplate transactionTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalServiceImpl.class);


    @Override
    public void rentCar(CarRentalDO carRentalDO) {

        // check params
        checkParams(carRentalDO);

        // assign available car
        String assignCarNo = assignAvailableCar(carRentalDO);

        //assemble rental info
        CarRentalOrderPO carRentalOrderPO = assembleCarRentalOrderInfo(carRentalDO, assignCarNo);
        CarDailyRentalLogPO updateStatusPO = assembleCarStatusInfo(carRentalDO, assignCarNo);

        //store rental order & update car available status
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    carRentalOrderMapper.insertCarRentalOrder(carRentalOrderPO);
                    carRentalLogMapper.updateCarRentalLogStatus(updateStatusPO);
                } catch (Exception e) {
                    LOGGER.error("add rental order fail.", e);
                    throw new BusinessException(SAVE_RENTAL_ORDER_FAIL);
                }

            }
        });

    }

    private void checkParams(CarRentalDO carRentalDO) {
        if (null == carRentalDO) {
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(), "object empty");
        }

        if (StringUtils.isEmpty(carRentalDO.getCarModel())
                || StringUtils.isEmpty(carRentalDO.getCustomerName())) {
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(), "object empty");
        }

        if (carRentalDO.getRentalEndTime() == null
                || carRentalDO.getRentalStartTime() == null) {
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(), "Rent date missing");
        }

        if (carRentalDO.getRentalStartTime() >= carRentalDO.getRentalEndTime()) {
            throw new BusinessException(CarRentalErrorCode.RENTAL_DATE_ILLEGAL.getCode(), "Start time should less than end time");
        }

    }

    /**
     * assign available car
     *
     * @param carRentalDO rental info
     * @return assign car no
     */
    private String assignAvailableCar(CarRentalDO carRentalDO) {

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

        List<Long> requireRentalDateList = getOccupiedTimeList(carRentalDO.getRentalStartTime(), carRentalDO.getRentalEndTime());

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


    private List<Long> getOccupiedTimeList(Long startTime, Long endTime) {
        List<Long> occupiedTimeList = new ArrayList<>();
        Long temp = startTime;
        while (temp < endTime) {
            Long occupiedTime = temp;
            occupiedTimeList.add(occupiedTime);
            temp += 3600 * 24 * 1000;
        }
        return occupiedTimeList;
    }


    private CarRentalOrderPO assembleCarRentalOrderInfo(CarRentalDO carRentalDO, String assignedCarNo) {
        CarRentalOrderPO carRentalOrderPO = new CarRentalOrderPO();
        carRentalOrderPO.setCarNo(assignedCarNo);
        carRentalOrderPO.setCreateTime(System.currentTimeMillis());
        carRentalOrderPO.setCustomerName(carRentalDO.getCustomerName());
        carRentalOrderPO.setStartTime(carRentalDO.getRentalStartTime());
        carRentalOrderPO.setEndTime(carRentalDO.getRentalEndTime());
        carRentalOrderPO.setModelName(carRentalDO.getCarModel());
        return carRentalOrderPO;
    }

    private CarDailyRentalLogPO assembleCarStatusInfo(CarRentalDO carRentalDO, String assignCarNo) {
        CarDailyRentalLogPO updateStatusInfoPO = new CarDailyRentalLogPO();
        List<Long> occupiedTimeList = getOccupiedTimeList(carRentalDO.getRentalStartTime(), carRentalDO.getRentalEndTime());
        updateStatusInfoPO.setCarNo(assignCarNo);
        updateStatusInfoPO.setStatus(CarRentalStatus.OCCUPIED.value);
        updateStatusInfoPO.setOperateTimeList(occupiedTimeList);
        return updateStatusInfoPO;
    }

}
