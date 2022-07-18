package com.example.assignment.common.errorcode;

import java.util.HashMap;
import java.util.Map;

public enum CommonErrorCode implements ErrorCode{

    SYSTEM_ERROR("SYSTEM_ERROR", "SYSTEM_ERROR"),
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
    CommonErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (CommonErrorCode e : CommonErrorCode.values()) {
            map.put(String.valueOf(e.getCode()), e.getDesc());
        }
        return map;
    }

    public static Map<String, CommonErrorCode> getEntityMap() {
        Map<String, CommonErrorCode> map = new HashMap<>();
        for (CommonErrorCode e : CommonErrorCode.values()) {
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
