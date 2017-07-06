package com.example.administrator.myapp.RequestDemo;

/**
 * Created by Administrator on 2017/5/20.
 */

public interface IRequestCallback<T> {
    /**
     * 执行网络访问之前的操作
     */
    void onStart(String msg);

    /**
     * 返回结果错误或解析失败
     */
    void onFailed(String msg);

    /**
     * 返回结果成功或解析成功
     */
    void onSuccessed(T t);
}
