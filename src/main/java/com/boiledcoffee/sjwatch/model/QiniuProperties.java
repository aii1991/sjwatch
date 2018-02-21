package com.boiledcoffee.sjwatch.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jason on 2018/2/13.
 *
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {
    private String ak;
    private String sk;
    private String bucket;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
