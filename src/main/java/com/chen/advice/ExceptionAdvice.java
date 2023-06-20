package com.chen.advice;

import com.chen.common.R;
import com.chen.exception.NoRoleException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NoRoleException.class)
    public R failOrSuccess(NoRoleException noRoleException){
        return R.error().message(noRoleException.getMessage());
    }
}
