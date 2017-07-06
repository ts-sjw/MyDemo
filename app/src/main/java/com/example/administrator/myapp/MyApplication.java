package com.example.administrator.myapp;

import android.app.Application;

import com.example.administrator.myapp.util.AppManager;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by Administrator on 2017/7/5.
 * 阿里热
 */

public class MyApplication extends Application {
    public interface MsgDisplayListener{
        void handle(int code,String msg,int handlePatchVersion);
    }
    private AppManager appManager;
    public static MsgDisplayListener msgDisplayListener=null;

    @Override
    public void onCreate() {
        super.onCreate();
        appManager=AppManager.getAppManager(this);
        initHotfix();
    }
    private void initHotfix(){
        String appVersion;
        try {
            appVersion=this.getPackageManager().getPackageInfo(this.getPackageName(),0).versionName;
        } catch (Exception e) {
            appVersion="1.0.0";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        switch (code){
                            /**
                             * PatchStatus.CODE_DOWNLOAD_BROKEN
                             * 该类中查看code对应错误
                             */
                            case 1:
                                info="补丁加载成功";
                                break;
                            case 6:
                                info="服务端没有最新可用的补丁";
                                break;
                            case 9:
                                //patch文件下载成功
                                info="patch文件下载成功";
                                break;
                            case 11:
                                info="RSASECRET错误，官网中的密钥是否正确请检查";
                                break;
                            case 12:
                                info="当前应用已经存在一个旧补丁, 应用重启尝试加载新补丁";
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后应用自杀
                                //如果应用不在前台启用，补丁生效完毕直接杀掉应用，否则弹出对话框提示用户bug更新完毕，需要用户选择是否重启应用
                                if (!appManager.isAppOnForeground()){
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                }else{
                                    if (msgDisplayListener!=null){
                                        msgDisplayListener.handle(code,info,handlePatchVersion);
                                    }
                                }
                                break;
                            case 13:
                                info="补丁加载失败, 导致的原因很多种, 比如UnsatisfiedLinkError等异常, 此时应该严格检查logcat异常日志";
                                // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                                break;
                            case 16:
                                info="APPSECRET错误，官网中的密钥是否正确请检查";
                                break;
                            case 18:
                                info="一键清除补丁";
                                break;
                            case 19:
                                info="连续两次queryAndLoadNewPatch()方法调用不能短于3s";
                                break;
                            default:
                                break;
                        }
                        if (msgDisplayListener != null) {
                            msgDisplayListener.handle(code,info,handlePatchVersion);
                        }
                    }
                }).initialize();
    }
}
