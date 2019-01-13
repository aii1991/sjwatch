package com.boiledcoffee.sjwatch.model.communication;

import com.boiledcoffee.sjwatch.constant.BussinessCode;
import org.springframework.util.StringUtils;

/**
 * Created by jason on 2016/7/5.
 */
public class HandleResult<T> {
    private int status;
    private T result;
    private String errorMsg;

    public HandleResult<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public HandleResult<T> updateStatusToSuccess() {
        return setStatus(BussinessCode.SUCCESS.getCode());
    }

    public HandleResult<T> updateStatusToError(){
        return setStatus(BussinessCode.ERROR.getCode());
    }

    public HandleResult<T> setResult(T result) {
        updateStatusToSuccess();
        this.result = result;
        return this;
    }
    public HandleResult<T> setErrorMsg(String errorMsg) {
        updateStatusToError();
        this.errorMsg = errorMsg;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    public T getResult() {
        return result;
    }
    public int getStatus() {
        return status;
    }
    public boolean isError(){
        return !StringUtils.isEmpty(errorMsg) && status != BussinessCode.SUCCESS.getCode();
    }

}
