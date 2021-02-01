package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * Created by ${juha} on 2020/9/1.
 *
 */
@RestController
public class CommController extends ApiBaseController{
    private Gson mGson = new Gson();
    private static final String SAVE_DIR = "/home/zjh/sjwatch/";
//    private static final String SAVE_DIR = "d://";

    @RequestMapping(value = "/comm/test",method = RequestMethod.GET)
    public HandleResult test(){
        HandleResult handleResult = new HandleResult();
        handleResult.updateStatusToSuccess();
        return handleResult;
    }

    @RequestMapping(value = "/comm/ff",method = RequestMethod.POST)
    public HandleResult saveData(@RequestParam(value = "open_id")String openId,@RequestParam(value = "param") String b){
        HandleResult handleResult = new HandleResult();
        String path = SAVE_DIR + openId;
        File f = new File(path);
        if(!f.getParentFile().exists()){
            f.mkdirs();
        }
        JsonObject jo = mGson.fromJson(b, JsonObject.class);
        if(jo.has("t_goods") && jo.has("t_bill") && jo.has("t_statistics")){
            boolean res = write(f, jo.toString());
            if(res){
                handleResult.updateStatusToSuccess();
            }else{
                handleResult.setErrorMsg("保存失败");
            }
            return handleResult;
        }else{
            handleResult.setErrorMsg("参数错误");
            return handleResult;
        }
    }

    @RequestMapping(value = "/comm/ff/{open_id}",method = RequestMethod.GET)
    public HandleResult getData(@PathVariable("open_id") String openId){
        HandleResult<String> handleResult = new HandleResult<>();
        String path = SAVE_DIR + openId;
        File f = new File(path);
        if(!f.exists()){
            handleResult.setErrorMsg("找不到文件");
            return handleResult;
        }

        String data = read(f);
        if(data == null){
            handleResult.setErrorMsg("找不到文件");
            return handleResult;
        }

        handleResult.setResult(data);
        return handleResult;
    }

    private String read(File f){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));

            String data = "";
            String line;
            while ((line = br.readLine()) != null){
                data += line;
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return  null;
    }

    private boolean write(File path, String s){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(s);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return true;
    }
}
