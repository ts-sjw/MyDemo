package com.example.administrator.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapp.RequestDemo.MyRequestActivity;
import com.example.administrator.myapp.StepViewDemo.StepViewDemoActivity;
import com.example.administrator.myapp.alibaba.sophix.AliHotFixActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.myapp.R.id.text_hello;

public class MainActivity extends Activity implements View.OnClickListener {
    /**
     * 注解
     */
    @Bind(text_hello)
    TextView textHello;
    @Bind(R.id.myListView)
    ListView myListView;
    private MyAdapter myAdapter;
    private List<String> myList;
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 设置好布局之后调用ButterKnife.bind方法
         */
        ButterKnife.bind(this);
        initView();
        setViewOnClicks(textHello);

    }

    void initView() {
        myList=new ArrayList<>();
        myList.add("网络请求（天气预报）");
        myList.add("物流指示器控件");
        myList.add("阿里巴巴热更新");
//        myList.add("反正先加个空的");
        myAdapter=new MyAdapter(this,myList);
        myListView.setAdapter(myAdapter);
        myAdapter.selectOne(0);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myAdapter.selectOne(position);
                switch (position){
                    case 0:
                        Intent wlqq=new Intent(MainActivity.this,MyRequestActivity.class);
                        startActivity(wlqq);
                        break;
                    case 1:
                        Intent zsq=new Intent(MainActivity.this,StepViewDemoActivity.class);
                        startActivity(zsq);
                        break;
                    case 2:
                        Intent albb=new Intent(MainActivity.this,AliHotFixActivity.class);
                        startActivity(albb);
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    public void setViewOnClicks(View... views) {
        for (View view : views) {
            view.setOnClickListener(MainActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
