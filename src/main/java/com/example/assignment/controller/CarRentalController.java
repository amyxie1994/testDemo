package com.example.assignment.controller;

import com.example.assignment.common.errorcode.CommonErrorCode;
import com.example.assignment.common.utils.Result;
import com.example.assignment.common.utils.ResultUtil;
import com.example.assignment.domain.convert.CarRentalVOConvert;
import com.example.assignment.domain.vo.CarRentalVO;
import com.example.assignment.service.CarRentalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/car")
public class CarRentalController {

    @Autowired
    private CarRentalService carRentalService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarRentalController.class);

    @ResponseBody
    @RequestMapping("/rent")
    public Result rentalCar(@RequestBody CarRentalVO carRentalVO) {
        try{
            carRentalService.rentCar(CarRentalVOConvert.carRentalVO2DO(carRentalVO));
            return ResultUtil.success();
        } catch (BusinessException e) {
            LOGGER.warn("Rent car error,errMsg:{} errCode:{}", e.getMessage(), e.getCode());
            return ResultUtil.fail(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            LOGGER.error("CarRental failed", e);
            return ResultUtil.fail(CommonErrorCode.SYSTEM_ERROR.getDesc(), CommonErrorCode.SYSTEM_ERROR.getCode());
        }

    }


}


