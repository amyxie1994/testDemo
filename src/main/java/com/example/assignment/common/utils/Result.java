package com.example.assignment.common.utils;



import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 8737398350798658667L;
    private String ret;
    private String errCode;
    private String errMsg;
    private Object data;

    public static final String SUCCESS = "1";
    public static final String FAIL = "0";

    public Result() {
    }

    public Result(String ret) {
        this.ret = ret;
    }

    public Result(String ret, Object data) {
        this.ret = ret;
        this.data = data;
    }


    public Result(String ret, String errCode, String errMsg) {
        this.ret = ret;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Result(String ret, String errCode, String errMsg, Object data) {
        this(ret, errCode, errMsg);
        this.data = data;
    }


}
