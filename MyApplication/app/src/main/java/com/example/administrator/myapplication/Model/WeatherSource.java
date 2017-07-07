package com.example.administrator.myapplication.Model;

import android.os.Handler;

import com.example.commenttool.source.BaseSource;
import com.example.commenttool.source.ISource;

/**
 * Created by wanchangchang on 2016/12/2.
 */

public class WeatherSource extends BaseSource implements ISource {
    private WeatherParamter paramter;
    private String key ;
    private String city ;
    private String PARKING_VERIFY_CAR_INFO ;
    public WeatherSource(int requestID, Handler myHandler, WeatherParamter paramter) {
        this.paramter = paramter;
        setRequestID(requestID);
        setHandler(myHandler);
        city = paramter.getCity();
        key = paramter.getKey();
        PARKING_VERIFY_CAR_INFO="http://api.yytianqi.com/observe?city="+city+"&key="+key;
    }

    @Override
    public void CallSource() {
        setResultModel(OkhttpGet(PARKING_VERIFY_CAR_INFO,WeatherResult.class));
    }
}
