package com.boiledcoffee.sjwatch.controller;

import com.boiledcoffee.sjwatch.model.communication.HandleResult;
import com.boiledcoffee.sjwatch.util.CommUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.security.MD5Encoder;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${juha} on 2019/9/23.
 *
 */
@RestController
public class WatermarkController extends ApiBaseController{
    @Value("${server.http.port}")
    private int httpPort;
    @Value("${debug}")
    private boolean isDebug;
    @Value("${file.downloadDir}")
    private String downloadFileDir;

    private static final Logger logger = LogManager.getLogger(WatermarkController.class);
    /**
     * 去除水印
     */
    @RequestMapping(value = "/wr",method = RequestMethod.POST)
    public HandleResult rmWatermark(HttpServletRequest req,@RequestParam(value = "url",required = true)String finalUrl) {
        HandleResult<Map> handleResult = new HandleResult<>();
        Map<String,String> map = new HashMap<>();
        try {
            //1.利用Jsoup抓取抖音链接
            //抓取抖音网页
            String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
            //System.out.println(htmls); //做测试时使用

            //2.利用正则匹配可以抖音下载链接
            //playAddr: "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200ffc0000bfil6o4mavffbmroeo80&line=0",
            //具体匹配内容格式：「https://aweme.snssdk.com/aweme/...line=0」
            Pattern patternCompile = Pattern.compile("(?<=playAddr: \")https?://.+(?=\",)");
            //利用Pattern.compile("正则条件").matcher("匹配的字符串对象")方法可以将需要匹配的字段进行匹配封装 返回一个封装了匹配的字符串Matcher对象

            //3.匹配后封装成Matcher对象
            Matcher m = patternCompile.matcher(htmls);

            //4.①利用Matcher中的group方法获取匹配的特定字符串 ②利用String的replace方法替换特定字符,得到抖音的去水印链接
            String matchUrl ="";
            while(m.find()) {
                matchUrl = m.group(0).replaceAll("playwm", "play");
            }

            //5.将链接封装成流
            //注:由于抖音对请求头有限制,只能设置一个伪装手机浏览器请求头才可实现去水印下载
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Connection", "keep-alive");
            headers.put("Host", "aweme.snssdk.com");
            headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

            String fileName = String.valueOf(matchUrl.hashCode());
            String fileAddress;
            if (isDebug){
                fileAddress = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/static/video/dy" + fileName + ".mp4";
            }else{
                fileAddress = downloadFileDir + "/dy" + fileName + ".mp4";
            }
            System.out.println("save dir=>" + fileAddress);
            File fileSavePath = new File(fileAddress);
            if (!fileSavePath.exists()){
                //6.利用Joup获取视频对象,并作封装成一个输入流对象
                BufferedInputStream in = Jsoup.connect(matchUrl).headers(headers).timeout(10000).ignoreContentType(true).execute().bodyStream();
                //注:如果保存文件夹不存在,那么则创建该文件夹
                File fileParent = fileSavePath.getParentFile();
                if(!fileParent.exists()){
                    fileParent.mkdirs();
                }

                //8.新建一个输出流对象
                OutputStream out =
                        new BufferedOutputStream(
                                new FileOutputStream(fileSavePath));

                //9.遍历输出文件
                int b ;
                while((b = in.read()) != -1) {
                    out.write(b);
                }

                out.close();//关闭输出流
                in.close(); //关闭输入流
            }

            if (fileSavePath.length() > 1024 * 1024 * 5){
                handleResult.setErrorMsg("解析文件过大");
            }else{
                String domain = CommUtils.getDomain(req);
                String downloadUrl = domain + "/video/" + fileSavePath.getName();

                map.put("url",downloadUrl);
                handleResult.setResult(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
            handleResult.setErrorMsg("解析失败");
        }

        return handleResult;
    }

    @RequestMapping(value = "/check")
    public HandleResult checkFileValidate(){
        HandleResult<List<String>> handleResult = new HandleResult<>();
        List<String> delFiles = new ArrayList<>();
        String dirPath;
        if (isDebug){
            dirPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/static/video/dy";
        }else{
            dirPath = downloadFileDir;
        }
        File dir = new File(dirPath);
        long timeout = 10 * 60 * 1000;
        if (dir.exists()){
            File[] files = dir.listFiles();
            if (files != null){
                for (File f : files){
                    long lastModified = f.lastModified();
                    if (System.currentTimeMillis() - lastModified >= timeout){
                        if(f.delete()){
                            delFiles.add(f.getAbsolutePath());
                            logger.debug("del file=>" + f.getAbsolutePath());
                        }
                    }
                }
            }
        }
        handleResult.setResult(delFiles);
        return handleResult;
    }
}
