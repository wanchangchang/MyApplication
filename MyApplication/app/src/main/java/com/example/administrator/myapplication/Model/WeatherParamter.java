package com.example.administrator.myapplication.Model;

import com.example.commenttool.Model.BaseParamterModel;

/**
 * Created by wanchangchang on 2016/12/2.
 */

public class WeatherParamter extends BaseParamterModel {
    public String key;
    public String city;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
