package com.chen.exception;

public class NoRoleException extends RuntimeException{
    private Long code;
    private String message;
    public NoRoleException(){

    }
    public NoRoleException(Long code,String message){
        this.code=code;
        this.message=message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
