package com.example.assignment;


import com.example.assignment.common.errorcode.CarRentalErrorCode;
import com.example.assignment.common.utils.Result;
import com.example.assignment.controller.CarRentalController;
import com.example.assignment.domain.vo.CarRentalVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AssignmentApplication.class)
public class CarRentalTest {

    @Autowired
    private CarRentalController carRentalController;

    /**
     * params check testing
     * customer name / car model / start time & end time
     * should not be empty
     */

    @Test
    public void testParamsCheck(){
        CarRentalVO carRentalVO = new CarRentalVO();
        Result res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue(CarRentalErrorCode.PARAMS_EMPTY.getCode().equals(res.getErrCode()));

        carRentalVO.setCustomerName("tester");
        res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue(CarRentalErrorCode.PARAMS_EMPTY.getCode().equals(res.getErrCode()));

        carRentalVO.setCarModel("TOYOTA");
        res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue(CarRentalErrorCode.PARAMS_EMPTY.getCode().equals(res.getErrCode()));

        carRentalVO.setRentalStartTime(10000L);
        res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue(CarRentalErrorCode.PARAMS_EMPTY.getCode().equals(res.getErrCode()));

        carRentalVO.setRentalEndTime(10000L);
        res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue(CarRentalErrorCode.RENTAL_DATE_ILLEGAL.getCode().equals(res.getErrCode()));
    }

    /**
     * test stock - enough stock
     */
    @Test
    public void testStock(){
        CarRentalVO carRentalVO = new CarRentalVO();
        carRentalVO.setCustomerName("test");
        carRentalVO.setCarModel("TOYOTA Carmry");
        carRentalVO.setRentalStartTime(10002L);
        carRentalVO.setRentalEndTime(10004L);
        Result res = carRentalController.rentalCar(carRentalVO);
        Assert.assertTrue("1".equals(res.getRet()));

    }






}
