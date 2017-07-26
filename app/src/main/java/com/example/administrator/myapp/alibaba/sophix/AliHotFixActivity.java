package com.example.administrator.myapp.alibaba.sophix;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapp.MyApplication;
import com.example.administrator.myapp.R;
import com.taobao.sophix.SophixManager;

/**
 * Created by Administrator on 2017/7/5.
 */

public class AliHotFixActivity extends AppCompatActivity implements MyApplication.MsgDisplayListener{

    private TextView mStatusTv;
    private String mStatusStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alihotfix);
        SophixManager.getInstance().queryAndLoadNewPatch();
        mStatusTv = (TextView) findViewById(R.id.tv_status);
        MyApplication myApplication=(MyApplication)getApplication();
        myApplication.msgDisplayListener=this;
    }
    /**
     * 更新监控台的输出信息
     *
     * @param content 更新内容
     */
    private void updateConsole(String content) {
        mStatusStr += content + "\n";
        if (mStatusTv != null) {
            mStatusTv.setText(mStatusStr);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
//                SophixManager.getInstance().queryAndLoadNewPatch();
                break;
            case R.id.btn_clean_patch:
                SophixManager.getInstance().cleanPatches();
                break;
            case R.id.btn_clean_console:
                mStatusStr="";
                updateConsole(mStatusStr);
                break;
            default:
                break;
        }
    }
    @Override
    public void handle(final int code, final String msg, int handlePatchVersion) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateConsole("返回响应码："+code+","+msg);
                if(code==12){
                    createDialog(msg);
                }
            }
        });
    }

    /**
     * 弹出一个对话框
     * @param msg
     */
    private void createDialog(String msg){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(msg+"\n"+"是否重新启动应用");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //重启新程序
                restartApplication();
                //杀死进程
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
    /**
     * 重启应用程序
     */
    private void restartApplication() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}

