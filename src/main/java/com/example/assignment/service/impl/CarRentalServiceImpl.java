package com.example.assignment.service.impl;

import com.example.assignment.common.enums.CarRentalStatus;
import com.example.assignment.common.errorcode.CarRentalErrorCode;
import com.example.assignment.controller.BusinessException;
import com.example.assignment.controller.CarRentalController;
import com.example.assignment.dao.CarRentalLogMapper;
import com.example.assignment.dao.CarRentalOrderMapper;
import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import com.example.assignment.domain.po.CarRentalOrderPO;
import com.example.assignment.service.CarRentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.assignment.common.errorcode.CarRentalErrorCode.SAVE_RENTAL_ORDER_FAIL;


@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarRentalOrderMapper carRentalOrderMapper;

    @Autowired
    private CarRentalLogMapper carRentalLogMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalServiceImpl.class);


    @Override
    public void rentCar(CarRentalDO carRentalDO){

        // 参数校验
        paramsCheck(carRentalDO);

        String assignCarNo = assignAvailableCar(carRentalDO);

        CarRentalOrderPO carRentalOrderPO = assembleCarRentalOrderInfo(carRentalDO,assignCarNo);
        CarDailyRentalLogPO updateStatusPO = assembleCarStatusInfo(carRentalDO,assignCarNo);


        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try{
                    carRentalOrderMapper.insertCarRentalOrder(carRentalOrderPO);
                    carRentalLogMapper.updateCarRentalLogStatus(updateStatusPO);
                } catch (Exception e){
                    LOGGER.error("add rental order fail.", e);
                    throw new BusinessException(SAVE_RENTAL_ORDER_FAIL);
                }

            }
        });

    }

    private void paramsCheck(CarRentalDO carRentalDO){
        if(null == carRentalDO){
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(),"object empty");
        }

        if (StringUtils.isEmpty(carRentalDO.getCarModel())
                || StringUtils.isEmpty(carRentalDO.getCustomerName())) {
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(), "object empty");
        }

        if (carRentalDO.getRentalEndTime() == null
                || carRentalDO.getRentalStartTime() == null) {
            throw new BusinessException(CarRentalErrorCode.PARAMS_EMPTY.getCode(), "Rent date missing");
        }

        if(carRentalDO.getRentalStartTime()>=carRentalDO.getRentalEndTime()){
            throw new BusinessException(CarRentalErrorCode.RENTAL_DATE_ILLEGAL.getCode(), "Start time should less than end time");
        }

    }

    private String assignAvailableCar(CarRentalDO carRentalDO){

      //  String rentalModel = carRentalDO=
        //String carRentalDO = carRental
        return "Toyota1";
    }


    private CarRentalOrderPO assembleCarRentalOrderInfo(CarRentalDO carRentalDO,String assignedCarNo){
        CarRentalOrderPO carRentalOrderPO = new CarRentalOrderPO();
        carRentalOrderPO.setCarNo(assignedCarNo);
        carRentalOrderPO.setCreateTime(System.currentTimeMillis());
        carRentalOrderPO.setCustomerName(carRentalDO.getCustomerName());
        carRentalOrderPO.setStartTime(carRentalDO.getRentalStartTime());
        carRentalOrderPO.setEndTime(carRentalDO.getRentalEndTime());
        carRentalOrderPO.setModelName(carRentalDO.getCarModel());
        return carRentalOrderPO;
    }

    private CarDailyRentalLogPO assembleCarStatusInfo(CarRentalDO carRentalDO,String assignCarNo){
        CarDailyRentalLogPO updateStatusInfoPO = new CarDailyRentalLogPO();
        List<Long> occupiedTimeList = new ArrayList<>();
        Long startTime = carRentalDO.getRentalStartTime();
        Long endTime = carRentalDO.getRentalEndTime();
        Long occupiedDateTimeStamp = startTime;
        while(occupiedDateTimeStamp<endTime){
            occupiedTimeList.add(occupiedDateTimeStamp);
            occupiedDateTimeStamp+=3600*24*1000;
        }
        updateStatusInfoPO.setCarNo(assignCarNo);
        updateStatusInfoPO.setStatus(CarRentalStatus.OCCUPIED.value);
        updateStatusInfoPO.setOperateTimeList(occupiedTimeList);
        return updateStatusInfoPO;
    }

}
