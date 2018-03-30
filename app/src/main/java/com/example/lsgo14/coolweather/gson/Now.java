package com.example.lsgo14.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Now {

    @SerializedName("tmp")
    public String mTemperature;

    @SerializedName("cond")
    public More mMore;

    public class More {

        @SerializedName("txt")
        public String mInfo;
    }
}
