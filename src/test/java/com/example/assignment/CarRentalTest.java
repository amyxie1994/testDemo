package com.example.assignment;


import com.example.assignment.controller.CarRentalController;
import com.example.assignment.domain.vo.CarRentalVO;
import com.example.assignment.service.CarRentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AssignmentApplication.class)
public class CarRentalTest {

    @Autowired
    private CarRentalController carRentalController;

    @Test
    public void test(){
        CarRentalVO carRentalVO = new CarRentalVO();
        carRentalVO.setCustomerName("testCrad");
        carRentalVO.setRentalStartTime(12345678L);
        carRentalVO.setRentalEndTime(123645676L);
        carRentalVO.setCarModel("carModel");
        carRentalController.rentalCar(carRentalVO);
    }

}
