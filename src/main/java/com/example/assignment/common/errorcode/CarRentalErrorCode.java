package com.example.assignment.common.errorcode;

import java.util.HashMap;
import java.util.Map;

public enum CarRentalErrorCode implements ErrorCode {

    PARAMS_EMPTY("001", "PARAMS_EMPTY"),
    CAR_MODEL_NOT_EXIST("002", "CAR_MODEL_NOT_EXIST"),
    RENTAL_DATE_ILLEGAL("003","Rental date illegal"),
    NOT_AVAILABLE_CAR("004", "NOT_AVAILABLE_CAR"),
    SAVE_RENTAL_ORDER_FAIL("005","Save rental order fail");
    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * @param code
     *            错误码
     * @param desc
     *            描述
     */
    CarRentalErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (CarRentalErrorCode e : CarRentalErrorCode.values()) {
            map.put(String.valueOf(e.getCode()), e.getDesc());
        }
        return map;
    }

    public static Map<String, CarRentalErrorCode> getEntityMap() {
        Map<String, CarRentalErrorCode> map = new HashMap<>();
        for (CarRentalErrorCode e : CarRentalErrorCode.values()) {
            map.put(String.valueOf(e.getCode()), e);
        }
        return map;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
