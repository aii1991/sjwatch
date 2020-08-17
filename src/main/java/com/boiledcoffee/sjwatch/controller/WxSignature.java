package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by ${juha} on 2019/5/20.
 */

@RestController
public class WxSignature extends ApiBaseController{
    OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/wx/signature",method = RequestMethod.POST)
    public HandleResult signature(@RequestParam(value = "url")String url) throws Exception {
        String wxToken = getToken();
        String jsApiTicket = getJsApiTicket(wxToken);
        String noncestr = getNoncestr();
        long timestamp = System.currentTimeMillis();
        String str1 = "jsapi_ticket=" + jsApiTicket+"&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = new String(DigestUtils.sha1(str1));

        HandleResult<JSONObject> handleResult = new HandleResult<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("access_token",wxToken);
        jsonObject.put("timestamp",timestamp);
        jsonObject.put("nonceStr",noncestr);
        jsonObject.put("signature",signature);

        handleResult.setResult(jsonObject);
        return handleResult;
    }

    private String getToken() throws Exception {
        String wxToken = stringRedisTemplate.opsForValue().get("wxToken");
        if (StringUtils.isEmpty(wxToken)){
            JSONObject jsonObject = createGetReq("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2856bb3ed35dba82&secret=3fcd3a24cb68de885867df76c7d7f464");
            String accessToken = jsonObject.getString("access_token");
            stringRedisTemplate.opsForValue().set("wxToken",accessToken,7200, TimeUnit.SECONDS);
            return accessToken;
        }
        return wxToken;
    }

    private String getJsApiTicket(String wxToken)throws Exception{
        JSONObject jsonObject = createGetReq("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + wxToken + "&type=jsapi");
        return jsonObject.getString("ticket");
    }

    private JSONObject createGetReq(String url) throws Exception {
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String body = response.body().string();
        System.out.println("===========body==========");
        System.out.println(body);
        System.out.println("===========body==========");
        return new JSONObject(body);
    }

    private String getNoncestr(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
