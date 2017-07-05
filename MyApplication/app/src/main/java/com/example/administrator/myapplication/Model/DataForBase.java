package com.example.administrator.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanchangchang on 2016/12/15.
 */

public class DataForBase implements Parcelable {
    protected DataForBase(Parcel in) {
    }

    public static final Creator<DataForBase> CREATOR = new Creator<DataForBase>() {
        @Override
        public DataForBase createFromParcel(Parcel in) {
            return new DataForBase(in);
        }

        @Override
        public DataForBase[] newArray(int size) {
            return new DataForBase[size];
        }
    };
    private String data;
    private String type;
    private String weather;
    private String motion;
    private String content;

    public static Creator<DataForBase> getCREATOR() {
        return CREATOR;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(data);
        parcel.writeString(type);
        parcel.writeString(weather);
        parcel.writeString(motion);
        parcel.writeString(content);
    }
}
