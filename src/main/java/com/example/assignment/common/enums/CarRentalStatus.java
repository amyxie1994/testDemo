package com.example.assignment.common.enums;

public enum CarRentalStatus {

    OCCUPIED(1, "OCCUPIED"),

    AVALIABLE(0, "AVALIABLE");

    public Integer value;

    public String desc;

    CarRentalStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
