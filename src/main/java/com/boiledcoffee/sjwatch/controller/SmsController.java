package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.service.sms.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2018/8/22.
 *
 */
@RestController
public class SmsController extends ApiBaseController {
    @Autowired
    ISmsService smsService;

    @RequestMapping(value = "/sms/{phoneNumber}",method = RequestMethod.POST)
    public HandleResult sendVerifyCodeToPhone(@PathVariable("phoneNumber") String phoneNumber){
        return smsService.sendSms(phoneNumber);
    }
}
