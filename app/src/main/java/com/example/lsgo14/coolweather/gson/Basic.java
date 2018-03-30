package com.example.lsgo14.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Basic {

    @SerializedName("city")
    public String mCityName;

    @SerializedName("id")
    public String mWeatherId;

    @SerializedName("update")
    public Update mUpdate;

    public class Update {

        @SerializedName("loc")
        public String mUpdateTime;
    }
}
