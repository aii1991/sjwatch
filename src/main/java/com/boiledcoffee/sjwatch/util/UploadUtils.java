package com.boiledcoffee.sjwatch.util;

import com.aliyun.oss.common.auth.ServiceSignature;
import com.google.gson.Gson;
import com.qiniu.util.Auth;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by jason on 2018/2/13.
 */
public class UploadUtils {
    public final static DateFormat FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateFormat FORMAT_HMS = new SimpleDateFormat("HH:mm:ss");

    public static String generatorQiNiuToken(String ak, String sk, String bucket){
        Auth auth = Auth.create(ak,sk);
        String uploadToken = auth.uploadToken(bucket,null,24 * 3600L,null,true);
        System.out.print("uploadToken=>" + uploadToken);
        return uploadToken;
    }

    /**
     *
     * @param ak
     * @param sk
     * @param expiration
     * @return 返回上传OSS凭证,数据包含policy,ak,signature
     * @throws JSONException
     */
    public static String generateOssPolicyAndToken(String ak,String sk,int expiration) throws JSONException {
        String[] result = new String[3];
        long expirationTimeMillis = 1000 * 60 * 60 * expiration;
        Date date = new Date(System.currentTimeMillis() + expirationTimeMillis);
        String expirationData = FORMAT_YMD.format(date) + "T" + FORMAT_HMS.format(date) + "Z";
        long contentLengthRange = 1024 * 1024 * 5; //上传文件不得超过5M

        String policy = "{\"expiration\": \"" + expirationData + "\",\"conditions\": [[\"content-length-range\", 0, " + contentLengthRange + "]]}";
        String encodePolicy = new String(Base64.encodeBase64(policy.getBytes()));
        result[0] = encodePolicy;
        result[1] = ak;
        String signature = ServiceSignature.create().computeSignature(sk, encodePolicy);
        result[2] = signature;
        System.out.println("policy=>"+policy);
        System.out.println("encodePolicy=>"+encodePolicy);
        System.out.println("signature=>"+signature);
        return result[0] + "." + result[1] + "." + result[2];
    }
}
