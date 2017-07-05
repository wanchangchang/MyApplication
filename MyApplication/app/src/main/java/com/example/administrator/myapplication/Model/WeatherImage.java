package com.example.administrator.myapplication.Model;

import com.example.administrator.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanchangchang on 2016/12/9.
 */

public class WeatherImage {
    private int resint;
    private int ImageId[]=new int[]{R.drawable.weather_0,R.drawable.weather_1,R.drawable.weather_2,
            R.drawable.weather_3,R.drawable.weather_4,R.drawable.weather_5,
            R.drawable.weather_6,R.drawable.weather_7,R.drawable.weather_8,
            R.drawable.weather_9,R.drawable.weather_10,R.drawable.weather_11,
            R.drawable.weather_12,R.drawable.weather_13,R.drawable.weather_14,
            R.drawable.weather_15,R.drawable.weather_16,R.drawable.weather_17,
            R.drawable.weather_18,R.drawable.weather_19,R.drawable.weather_20,
            R.drawable.weather_21,R.drawable.weather_22,R.drawable.weather_23,
            R.drawable.weather_24,R.drawable.weather_25,R.drawable.weather_26,
            R.drawable.weather_27,R.drawable.weather_28,R.drawable.weather_29,
            R.drawable.weather_30,R.drawable.weather_31,R.drawable.weather_32,
            R.drawable.weather_33,R.drawable.weather_34,R.drawable.weather_35,
            R.drawable.weather_36,R.drawable.weather_37,R.drawable.weather_38,
            R.drawable.weather_39,R.drawable.weather_40,R.drawable.weather_41,
    };
    public static  int selectImage(String key){
        Map<String,Integer> hm = new HashMap<String,Integer>();
        hm.put("00",R.drawable.weather_0);
        hm.put("01",R.drawable.weather_1);
        hm.put("02",R.drawable.weather_2);
        hm.put("03",R.drawable.weather_3);
        hm.put("04",R.drawable.weather_4);
        hm.put("05",R.drawable.weather_5);
        hm.put("06",R.drawable.weather_6);
        hm.put("07",R.drawable.weather_7);
        hm.put("08",R.drawable.weather_8);
        hm.put("09",R.drawable.weather_9);
        hm.put("10",R.drawable.weather_10);
        hm.put("11",R.drawable.weather_11);
        hm.put("12",R.drawable.weather_12);
        hm.put("13",R.drawable.weather_13);
        hm.put("14",R.drawable.weather_14);
        hm.put("15",R.drawable.weather_15);
        hm.put("16",R.drawable.weather_16);
        hm.put("17",R.drawable.weather_17);
        hm.put("18",R.drawable.weather_18);
        hm.put("19",R.drawable.weather_19);
        hm.put("20",R.drawable.weather_20);
        hm.put("21",R.drawable.weather_21);
        hm.put("22",R.drawable.weather_22);
        hm.put("23",R.drawable.weather_23);
        hm.put("24",R.drawable.weather_24);
        hm.put("25",R.drawable.weather_25);
        hm.put("26",R.drawable.weather_26);
        hm.put("27",R.drawable.weather_27);
        hm.put("28",R.drawable.weather_28);
        hm.put("29",R.drawable.weather_29);
        hm.put("30",R.drawable.weather_30);
        hm.put("31",R.drawable.weather_31);
        hm.put("32",R.drawable.weather_32);
        hm.put("49",R.drawable.weather_33);
        hm.put("53",R.drawable.weather_34);
        hm.put("54",R.drawable.weather_35);
        hm.put("55",R.drawable.weather_36);
        hm.put("56",R.drawable.weather_37);
        hm.put("57",R.drawable.weather_38);
        hm.put("58",R.drawable.weather_39);
        hm.put("99",R.drawable.weather_40);
        hm.put("100",R.drawable.weather_41);
        return hm.get(key);
    }
}
