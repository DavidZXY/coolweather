package com.example.lsgo14.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Weather {

    public String mStatus;

    public Basic mBasic;

    public AQI mAQI;

    public Now mNow;

    public Suggestion mSuggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> mForecastList;
}
