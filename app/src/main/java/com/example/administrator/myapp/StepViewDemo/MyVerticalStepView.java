package com.example.administrator.myapp.StepViewDemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyVerticalStepView extends LinearLayout {
    private List<SetModel> mDatas = new ArrayList<>();
    private Context mContext;

    public MyVerticalStepView(Context context) {
        this(context, null);
    }

    public MyVerticalStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public List<SetModel> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<SetModel> mDatas) {
        this.mDatas = mDatas;
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        mDatas = getmDatas();//获取数据
        for (int i = 0; i < mDatas.size(); i++) {
            //获取布局，注意第二个参数一定是ViewGroup，否则margin padding之类的属性将不能使用
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.stepview_item, this, false);
            TextView description = (TextView) itemView.findViewById(R.id.description_tv);
            View line = itemView.findViewById(R.id.line_v);
            ImageView icon = (ImageView) itemView.findViewById(R.id.stepicon_iv);
            description.setText(mDatas.get(i).getDescription());
            //根据不同状态设置不同图标
            switch (mDatas.get(i).getCurrentState()) {
                case SetModel.STATE_PROCESSING:
                    icon.setImageResource(R.drawable.icon_processing);
                    break;
                case SetModel.STATE_DEFAULT:
                    icon.setImageResource(R.drawable.icon_default);
                    //结尾图标隐藏竖线
                    line.setVisibility(View.GONE);
                    break;
                case SetModel.STATE_COMPLETED:
                    icon.setImageResource(R.drawable.icon_completed);
                    break;
            }
            this.addView(itemView);
        }
        //重新绘制布局
        requestLayout();
        //刷新当前界面
        invalidate();

    }
}
