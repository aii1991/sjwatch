package com.boiledcoffee.sjwatch.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ThUtils {
    private static Gson mGson;

    static {
        mGson = new Gson();
    }

    public static String getGoodsImgUrl(String str){
        try{
            JsonArray jsonArray = mGson.fromJson(str, JsonArray.class);
            if (jsonArray != null && jsonArray.size() > 0){
                return jsonArray.get(0).getAsJsonObject().get("url").getAsString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> getGoodsImgArray(String source){
        List<String> urls = new ArrayList<>();
        try {
            JsonArray jsonArray = mGson.fromJson(source,JsonArray.class);
            for (int i=0; i<jsonArray.size(); i++){
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                urls.add(object.get("url").getAsString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (urls.isEmpty()){
            urls.add("");
        }
        return urls;
    }

    public static String getGoodsSearchUrl(String url,String page,String searchName,String brandId,String sex,String sortSalePrice){
        String result = "";
        if (!StringUtils.isEmpty(searchName)){
            result += "?" + "searchName="+searchName;
        }

        if (!StringUtils.isEmpty(brandId)){
            if (result.contains("?")){
                result += "&brandId=" + brandId;
            }else {
                result += "?brandId=" + brandId;
            }
        }

        if (!StringUtils.isEmpty(sex)){
            if (result.contains("?")){
                result += "&sex=" + sex;
            }else {
                result += "?sex=" + sex;
            }
        }

        if (!StringUtils.isEmpty(sortSalePrice)){
            if ("0".equals(sortSalePrice)){
                sortSalePrice = "1";
            }else if ("1".equals(sortSalePrice)){
                sortSalePrice = "2";
            }else if ("2".equals(sortSalePrice)){
                sortSalePrice = "1";
            }
            if (result.contains("?")){
                result += "&sortSalePrice=" + sortSalePrice;
            }else {
                result += "?sortSalePrice=" + sortSalePrice;
            }
        }

        return url + "/" + page + "/" + result;
    }


}
