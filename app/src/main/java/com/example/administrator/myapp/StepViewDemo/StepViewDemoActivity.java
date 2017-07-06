package com.example.administrator.myapp.StepViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class StepViewDemoActivity extends Activity {
    private com.example.administrator.myapp.StepViewDemo.MyVerticalStepView mStepView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepviewlayout);
        mStepView= (MyVerticalStepView) findViewById(R.id.stepview);
        init();
    }
    private void init(){
        List<SetModel> setModels=new ArrayList<>();
        SetModel step1=new SetModel("您已提交订单，等待系统确认",SetModel.STATE_COMPLETED);
        SetModel step2=new SetModel("订单已确认并打包，预计12月16日送达",SetModel.STATE_COMPLETED);
        SetModel step3=new SetModel("包裹正在路上",SetModel.STATE_COMPLETED);
        SetModel step4=new SetModel("包裹正在派送",SetModel.STATE_PROCESSING);
        SetModel step5=new SetModel("本人签收！",SetModel.STATE_DEFAULT);
        setModels.add(step1);
        setModels.add(step2);
        setModels.add(step3);
        setModels.add(step4);
        setModels.add(step5);
        mStepView.setmDatas(setModels);

    }
}
