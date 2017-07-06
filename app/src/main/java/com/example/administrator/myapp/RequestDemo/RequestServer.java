package com.example.administrator.myapp.RequestDemo;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by Administrator on 2017/5/20.
 */

public class RequestServer {
    private HttpCycleContext context;

    public RequestServer(HttpCycleContext context) {
        this.context = context;
    }

    //传字符串参数
    public void getRequestServer(String request, IRequestCallback<DataResponse> callback) {
        RequestParams requestParams = RequestParamsBuilder.buildRequestParams(context);
        requestParams.addFormDataPart("request", request);
        getRequestServer(requestParams,callback);
    }
    //直接请求对象的参数
    public void getRequestServer(RequestParams params, final IRequestCallback<DataResponse> call) {
        final String msg = "";
        HttpRequest.get("http://wthrcdn.etouch.cn/weather_mini?", params, new BaseHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
                if (call != null) {
                    call.onStart(msg);
                }
            }

            @Override
            public void onResponse(String response, Headers headers) {
                super.onResponse(response, headers);
                if (response != null) {
                    try {
                        Gson gson = new Gson();
                        DataResponse dataResponse = gson.fromJson(response, DataResponse.class);
                        /**
                         * 如果返回的是该类型的list数据，使用如下方法
                         */
//                        List<DataResponse> dataResponses = gson.fromJson(response,new TypeToken<List<DataResponse>>(){}.getType());
                        if (dataResponse != null) {
                            if (dataResponse.getDesc() != null) {
                                int code = dataResponse.getStatus();
                                if (code==1000) {
                                    call.onSuccessed(dataResponse);
                                } else {
                                    call.onFailed(dataResponse.getDesc());
                                }
                            }
                        }
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        call.onFailed(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                if (call!=null){
                    call.onFailed(msg);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                /**
                 * 请求结束,不管成功与否都会执行,可在此处写dialog消失逻辑等等
                 */
            }
        });

    }
}
