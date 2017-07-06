package com.example.administrator.myapp.RequestDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapp.Interface.MyInterface;
import com.example.administrator.myapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.RequestParams;

public class MyRequestActivity extends Activity implements HttpCycleContext, View.OnClickListener {
    /**
     * 注解
     */
    @Bind(R.id.text_wenzi)
    TextView text_wenzi;
    private RequestServer requestServer;

    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrequest);
        /**
         * 设置好布局之后调用ButterKnife.bind方法
         */
        ButterKnife.bind(this);
        /**
         * 初始化OkHttpFinal
         */
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        //设置请求超时时间
        builder.setTimeout(60000);
        //初始化OkHttpFinalConfiguration
        OkHttpFinal.getInstance().init(builder.build());

        initView();
        setViewOnClicks(text_wenzi);
        requestServer = new RequestServer(this);
        //网络请求OKhttp
        sendRequest();
        //接口回传数据
//       getInterfaceData();

    }

    void initView() {
//        text_hello=(TextView)findViewById(R.id.text_hello);
    }

    public void setViewOnClicks(View... views) {
        for (View view : views) {
            view.setOnClickListener(MyRequestActivity.this);
        }
    }

    void getInterfaceData() {
        DataBean dataBean = new DataBean();
        dataBean.getData(new MyInterface() {
            @Override
            public void getDate(String name) {
                System.out.println("MainActivity拿到的数据：" + name);
            }
        });
    }

    private void sendRequest() {
        RequestParams params = new RequestParams();
//        Gson gson=new Gson();
//        DataRequest dataRequest=new DataRequest();
//        dataRequest.setUsername("name");
//        String Strparams=gson.toJson(dataRequest);
        params.addFormDataPart("city", "北京");
        requestServer.getRequestServer(params, new IRequestCallback<DataResponse>() {
            @Override
            public void onStart(String msg) {
                /**
                 * 开始请求
                 */
            }

            @Override
            public void onFailed(String msg) {
                /**
                 * 返回失败
                 */
            }

            @Override
            public void onSuccessed(DataResponse dataResponse) {
                /**
                 * 成功
                 */
//                Gson gson=new Gson();
                if (dataResponse.getData().getGanmao() != null) {
                    text_wenzi.setText(dataResponse.getData().getGanmao().toString());
                }
            }
        });
    }

    @Override
    public String getHttpTaskKey() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_wenzi:

                break;
        }
    }
}
