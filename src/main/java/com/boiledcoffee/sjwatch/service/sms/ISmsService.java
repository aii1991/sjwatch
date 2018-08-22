package com.boiledcoffee.sjwatch.service.sms;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;

/**
 * Created by ${juha} on 2018/8/20.
 */
public interface ISmsService {
    /**
     * 发送短信
     * @param phoneNumber
     * @return
     */
    HandleResult sendSms(String phoneNumber);

    /**
     * 验证校验码
     * @param phoneNumber
     * @param inputVerifyCode
     * @return
     */
    boolean validateCode(String phoneNumber,String inputVerifyCode);
}
