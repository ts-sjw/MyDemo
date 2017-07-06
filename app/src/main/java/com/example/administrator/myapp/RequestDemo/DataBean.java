package com.example.administrator.myapp.RequestDemo;

import com.example.administrator.myapp.Interface.MyInterface;

/**
 * Created by Administrator on 2017/5/22.
 */

 public class DataBean {
    DataBean() {
    }

   void getData(MyInterface myInterface) {
        myInterface.getDate("传回的数据");
    }
}
