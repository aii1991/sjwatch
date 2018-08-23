package com.boiledcoffee.sjwatch.util;

import java.util.Random;

/**
 * Created by jason on 2018/8/22.
 *
 */
public class GeneratorUtils {
    /**
     * 生成6位数的短信验证码
     * @return 6为啥校验码
     */
    public static String generateSmSVerifyCode(){
        String[] verifyString = new String[] { "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9" };
        Random random = new Random(System.currentTimeMillis());
        StringBuilder verifyBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rd = random.nextInt(10);
            verifyBuilder.append(verifyString[rd]);
        }
        return verifyBuilder.toString();
    }

    /**
     * 生成订单号
     * @return
     */
    public static long generateOrderNO(){
        return 1 + System.currentTimeMillis();
    }
}
