package com.example.administrator.myapp.RequestDemo;

import com.google.gson.Gson;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2017/5/24.
 */

public class RequestParamsBuilder {

    public static RequestParams buildRequestParams(HttpCycleContext context) {
        String token = "";
        RequestParams params = new RequestParams(context);
        /**
         * 公共请求头
         */
        params.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        /**
         * 登录状态的token
         */
//        params.addFormDataPart("token","token");
        return params;
    }

    /**
     * 服务端返回错误信息，解析得到message
     * 如果返回数据解析异常时可调用该方法
     */
    public static String getMessage(String errorJson) {
        try {
            Gson gson = new Gson();
            NetErrorBean errorBean = gson.fromJson(errorJson, NetErrorBean.class);
            String errorMsg = errorBean.getMessage();
            errorMsg = errorMsg == null ? "" : errorJson;
            return errorMsg;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
