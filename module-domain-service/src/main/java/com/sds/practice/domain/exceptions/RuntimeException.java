package com.sds.practice.domain.exceptions;

/**
 * Created by Tandy on 2016/6/25.
 */
public class RuntimeException extends Exception{
    private String errorCode;
    private String errorMsg;
    private Object[] ctxParams;

//    public RuntimeException(AllErrors error){
//        super(error.toString());
//        this.errorCode = error.getErrorCode();
//        this.errorMsg = error.getErrorMsg();
//
//    }

    public void params(Object ... params){
        this.ctxParams = params;
    }

}
