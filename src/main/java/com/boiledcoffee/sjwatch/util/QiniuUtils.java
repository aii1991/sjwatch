package com.boiledcoffee.sjwatch.util;

import com.qiniu.util.Auth;

/**
 *
 * Created by jason on 2018/2/13.
 */
public class QiniuUtils {
    public static String generatorToken(String ak,String sk,String bucket){
        Auth auth = Auth.create(ak,sk);
        String uploadToken = auth.uploadToken(bucket,null,24 * 3600L,null,true);
        System.out.print("uploadToken=>" + uploadToken);
        return uploadToken;
    }
}
