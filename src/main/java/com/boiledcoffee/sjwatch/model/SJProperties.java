package com.boiledcoffee.sjwatch.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jason on 2018/8/22.
 *
 */
@Component
@ConfigurationProperties(prefix = "sj-config")
public class SJProperties {
    private int tokenValidTime; //单位 小时
    private int smsValidTime; //单位 分钟

    public int getTokenValidTime() {
        return tokenValidTime;
    }

    public void setTokenValidTime(int tokenValidTime) {
        this.tokenValidTime = tokenValidTime;
    }

    public int getSmsValidTime() {
        return smsValidTime;
    }

    public void setSmsValidTime(int smsValidTime) {
        this.smsValidTime = smsValidTime;
    }
}
