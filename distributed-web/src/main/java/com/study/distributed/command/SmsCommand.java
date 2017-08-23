package com.study.distributed.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by wangyonghua on 2017/8/22.
 */
public class SmsCommand extends HystrixCommand<String> {
    public SmsCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("smsGroup"));
    }

    @Override
    protected String run() throws Exception {
        String result = sendGet("http://localhost:9701/msg", null, "utf-8");
        System.out.println("retrun:" + result);
        return result;
    }

    @Override
    protected String getFallback() {
        //降级策略  再次查询，查询备用接口，返回mock值
        //super.getFallback();
        return "服务器错误";
    }

    public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                sbParams.append(entry.getValue());
                sbParams.append("&");
            }
        }
        BufferedReader br = null;
        try {
            URL url = null;
            if (sbParams != null && sbParams.length() > 0) {
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));
            } else {
                url = new URL(urlParam);
            }
            URLConnection con = url.openConnection();
            // 设置请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立连接
            con.connect();
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }
}
