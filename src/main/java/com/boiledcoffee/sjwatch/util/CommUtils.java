package com.boiledcoffee.sjwatch.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ${juha} on 2019/9/24.
 *
 */
public class CommUtils {
    public static String getDomain(HttpServletRequest req){
        StringBuffer url = req.getRequestURL();
        return url.delete(url.length() - req.getRequestURI().length(), url.length()).toString().replace("https","http");
    }
}
