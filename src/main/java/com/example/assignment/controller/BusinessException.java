package com.example.assignment.controller;

import com.example.assignment.common.errorcode.ErrorCode;

public class BusinessException extends RuntimeException{

        private static final long serialVersionUID = 6156819242711585850L;
        private String code;
        private Object data;

        public BusinessException(Throwable e) {
            super(e);
        }

        public BusinessException(String code, String msg) {
            super(msg);
            this.code = code;
        }

        public BusinessException(String code, String msg, Object data) {
            super(msg);
            this.code = code;
            this.data = data;
        }

        public BusinessException(String code, String msg, Object data, Throwable t) {
            super(msg, t);
            this.code = code;
            this.data = data;
        }

        public BusinessException(ErrorCode errorCode) {
            super(errorCode.getDesc());
            this.code = errorCode.getCode();
        }

        public BusinessException(ErrorCode errorCode, Object data) {
            super(errorCode.getDesc());
            this.code = errorCode.getCode();
            this.data = data;
        }

        public BusinessException(ErrorCode errorCode, Object data, Throwable t) {
            super(errorCode.getDesc(), t);
            this.code = errorCode.getCode();
            this.data = data;
        }

        public String getCode() {
            return this.code;
        }

        public Object getData() {
            return this.data;
        }


}
