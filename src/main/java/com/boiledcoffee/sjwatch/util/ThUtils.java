package com.boiledcoffee.sjwatch.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class ThUtils {
    private static Gson mGson;

    static {
        mGson = new Gson();
    }

    public static String getGoodsImgUrl(String str){
        return mGson.fromJson(str, JsonArray.class).get(0).getAsJsonObject().get("url").getAsString();
    }


}
