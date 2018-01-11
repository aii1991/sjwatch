package com.boiledcoffee.sjwatch.constant;

/**
 * Created by songlive on 2018/1/10.
 */
public enum  BussinessCode {
    SUCCESS(1),ERROR(-1);
    private int code;
    BussinessCode(int code){
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
