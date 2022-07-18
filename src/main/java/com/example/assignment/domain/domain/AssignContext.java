package com.example.assignment.domain.domain;

import com.example.assignment.service.IAssignStrategy;

public class AssignContext {

    private IAssignStrategy strategy;

    public AssignContext(IAssignStrategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(IAssignStrategy strategy){
        this.strategy = strategy;
    }
    public String assign(CarRentalDO carRentalDO){
        return this.strategy.assign(carRentalDO);
    }

}
