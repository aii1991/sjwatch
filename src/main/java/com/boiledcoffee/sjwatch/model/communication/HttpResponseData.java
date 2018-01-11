package com.boiledcoffee.sjwatch.model.communication;



/**
 * Created by jason on 2016/7/5.
 */
public class HttpResponseData {
    private int status;
    private Object data;
    private String msg;

    public HttpResponseData(int status) {
        this.status = status;
    }

    private HttpResponseData(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    private HttpResponseData(int status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static HttpResponseData createInstance(int status, Object data, String msg){
        HttpResponseData instance = new HttpResponseData(status,data,msg);
        return instance;
    }

    public static HttpResponseData createInstance(int status, Object data){
        HttpResponseData instance = new HttpResponseData(status,data);
        return instance;
    }

    public static HttpResponseData createInstance(int status){
        HttpResponseData instance = new HttpResponseData(status);
        return instance;
    }
}
