package com.example.lsgo14.coolweather.gson;


import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Forecast {

    @SerializedName("date")
    public String mDate;

    @SerializedName("tmp")
    public Temperature mTemperature;

    @SerializedName("cond")
    public More mMore;

    public class Temperature {

        @SerializedName("max")
        public String mMax;

        @SerializedName("min")
        public String mMin;
    }

    public class More {

        @SerializedName("txt_d")
        public String mInfo;
    }
}
