package com.example.administrator.myapplication.Model;

/**
 * Created by wanchangchang on 2016/12/8.
 */

public class NowInfo {
    private String text;        //     //"多云", //天气现象文字
    private String code;     //  "4", //天气现象代码
    private String temperature;   //     : "14", //温度，单位为c摄氏度或f华氏度
    private String feels_like;   //    : "14", //体感温度，单位为c摄氏度或f华氏度
    private String pressure;       //  :     "1018", //气压，单位为mb百帕或in英寸
    private String humidity;       //: "76", //相对湿度，0~100，单位为百分比
    private String visibility;       //    : "16.09", //能见度，单位为km公里或mi英里
    private String wind_direction;       //   : "西北", //风向文字
    private String wind_direction_degree;       //  : "340", //风向角度，范围0~360，0为正北，90为正东，180为正南，270为正西
    private String wind_speed;       // : "8.05", //风速，单位为km/h公里每小时或mph英里每小时
    private String wind_scale;       // : "2", //风力等级，请参考：http://baike.baidu.com/view/465076.htm
    private String clouds;       // : "90", //云量，范围0~100，天空被云覆盖的百分比 #目前不支持中国城市#
    private String dew_point;       // : "-12" //露点温度，请参考：http://baike.baidu.com/view/118348.htm #目前不支持中国城市#

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(String wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDew_point() {
        return dew_point;
    }

    public void setDew_point(String dew_point) {
        this.dew_point = dew_point;
    }
}
