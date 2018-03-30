package com.example.lsgo14.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class AQI {

    @SerializedName("city")
    public AQICity mCity;

    public class AQICity {

        @SerializedName("aqi")
        public String aqi;

        @SerializedName("pm25")
        public String pm25;
    }
}
