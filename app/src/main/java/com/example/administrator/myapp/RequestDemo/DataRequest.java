package com.example.administrator.myapp.RequestDemo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */

public class DataRequest implements Serializable{
    private String username;
    private String psw;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "DataRequest{" +
                "username='" + username + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
