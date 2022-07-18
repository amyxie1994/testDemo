package com.example.assignment.service.impl;

import com.example.assignment.common.assembler.CarRentalAssembler;
import com.example.assignment.common.enums.CarRentalStatus;
import com.example.assignment.common.errorcode.CarRentalErrorCode;
import com.example.assignment.controller.BusinessException;
import com.example.assignment.dao.CarRentalLogMapper;
import com.example.assignment.dao.CarRentalOrderMapper;
import com.example.assignment.domain.domain.AssignContext;
import com.example.assignment.domain.domain.CarDailyRentalLogDO;
import com.example.assignment.domain.domain.CarInfoDO;
import com.example.assignment.domain.domain.CarRentalDO;
import com.example.assignment.domain.po.CarDailyRentalLogPO;
import com.example.assignment.domain.po.CarRentalOrderPO;
import com.example.assignment.service.CarInfoQueryService;
import com.example.assignment.service.CarRentalLogQueryService;
import com.example.assignment.service.CarRentalService;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

import static com.example.assignment.common.errorcode.CarRentalErrorCode.*;


@Service("carRentalService")
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    private CarRentalOrderMapper carRentalOrderMapper;

    @Autowired
    private CarRentalLogMapper carRentalLogMapper;

    @Autowired
    private AssignFirstStrategy assignFirstStrategy;

    @Autowired
    private CarInfoQueryService carInfoQueryService;

    @Resource
    private TransactionTemplate transactionTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalServiceImpl.class);


    @Override
    public String rentCar(CarRentalDO carRentalDO) {

        synchronized(this){
            // check params
            checkParams(carRentalDO);

            // assign available car - use choose assign first strategy
            AssignContext assignContext = new AssignContext(assignFirstStrategy);
            String assignCarNo = assignContext.assign(carRentalDO);

            //assemble rental info
            CarRentalOrderPO carRentalOrderPO = CarRentalAssembler.assembleCarRentalOrderInfo(carRentalDO, assignCarNo);
            CarDailyRentalLogPO updateStatusPO = CarRentalAssembler.assembleCarStatusInfo(carRentalDO, assignCarNo);

            //store rental order info & update car available status
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

            return assignCarNo;
        }

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

}
