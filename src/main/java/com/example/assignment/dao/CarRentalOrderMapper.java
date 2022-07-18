package com.example.assignment.dao;


import com.example.assignment.domain.po.CarRentalOrderPO;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRentalOrderMapper {

    /**
     * insert car rental order info
     * @param carRentalOrderPO carRentalOrderInfo
     *
     */
    void insertCarRentalOrder(CarRentalOrderPO carRentalOrderPO);
}
