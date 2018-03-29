package com.example.lsgo14.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class County extends DataSupport {

    private int mId;

    private String mCountyName;

    private String mWeatherId;

    private int mCityId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCountyName() {
        return mCountyName;
    }

    public void setCountyName(String countyName) {
        mCountyName = countyName;
    }

    public String getWeatherId() {
        return mWeatherId;
    }

    public void setWeatherId(String weatherId) {
        mWeatherId = weatherId;
    }

    public int getCityId() {
        return mCityId;
    }

    public void setCityId(int cityId) {
        mCityId = cityId;
    }
}
