package com.example.lsgo14.coolweather.gson;


import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Forecast {

    public String mDate;

    @SerializedName("tmp")
    public Temperature mTemperature;

    @SerializedName("cond")
    public More mMore;

    public class Temperature {

        public String mMax;

        public String mMin;
    }

    public class More {

        @SerializedName("txt_d")
        public String mInfo;
    }
}
