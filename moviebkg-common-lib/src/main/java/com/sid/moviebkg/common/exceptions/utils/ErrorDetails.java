package com.sid.moviebkg.common.exceptions.utils;

public class ErrorDetails {

    private String errorCode;
    private String errorDesc;
    private String stackTrace;
    private String systemErrorCode;
    private String systemType;

    public ErrorDetails() {
    }

    public ErrorDetails(String errorCode, String errorDesc, String stackTrace, String systemErrorCode, String systemType) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.stackTrace = stackTrace;
        this.systemErrorCode = systemErrorCode;
        this.systemType = systemType;
    }

    public ErrorDetails(String errorCode, String errorDesc, String stackTrace) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.stackTrace = stackTrace;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getSystemErrorCode() {
        return systemErrorCode;
    }

    public void setSystemErrorCode(String systemErrorCode) {
        this.systemErrorCode = systemErrorCode;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }
}
