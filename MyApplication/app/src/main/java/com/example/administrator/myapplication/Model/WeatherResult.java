package com.example.administrator.myapplication.Model;

import com.example.commenttool.Model.BaseResultModel;

/**
 * Created by wanchangchang on 2016/12/2.
 */

public class WeatherResult extends BaseResultModel {

            private String code=null;   ;//: 0,
            private String msg    ;// : "Sucess",
            private String counts ;// : 2362,  //访问的剩余次数。
            private DataInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public DataInfo getData() {
        return data;
    }

    public void setData(DataInfo data) {
        this.data = data;
    }

    public class DataInfo{
                private String cityId    ;  //: "CH010100",  //城市id
                private String cityName  ;   //: "北京",  //城市名称
                private String lastUpdate;   //    : "2016-03-09 17:10:00",  //实况更新时间
                private String tq        ;   //: "多云"      ,  //天气现象
                private String numtq     ;   //: "01",  //天气现象编码
                private String qw        ;   //: "5.0",  //当前气温
                private String fl        ;   //: "微风",  //当前风力
                private String numfl     ;   //: "0",  //当前风力编码
                private String fx        ;   //: "无持续风向",  //当前风向
                private String numfx     ;   //: "0",  //当前风向编码
                private String sd        ;   //: "10.0"  //相对湿度，直接在此数值后添加%即可

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(String lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getTq() {
            return tq;
        }

        public void setTq(String tq) {
            this.tq = tq;
        }

        public String getNumtq() {
            return numtq;
        }

        public void setNumtq(String numtq) {
            this.numtq = numtq;
        }

        public String getQw() {
            return qw;
        }

        public void setQw(String qw) {
            this.qw = qw;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getNumfl() {
            return numfl;
        }

        public void setNumfl(String numfl) {
            this.numfl = numfl;
        }

        public String getFx() {
            return fx;
        }

        public void setFx(String fx) {
            this.fx = fx;
        }

        public String getNumfx() {
            return numfx;
        }

        public void setNumfx(String numfx) {
            this.numfx = numfx;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }
    }

}