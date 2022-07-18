package com.example.assignment.common.utils;

import sun.jvm.hotspot.debugger.Page;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 8737398350798658667L;
    private String ret;
    private String errCode;
    private String errMsg;
    private Object data;

    private Page page;
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

    public Result(String ret, Object data, Page page) {
        this(ret, data);
        this.page = page;
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

    public String getRet() {
        return this.ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
