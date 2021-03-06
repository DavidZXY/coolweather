package com.example.lsgo14.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class City extends DataSupport {

    private int mId;

    private String mCityName;

    private int mCityCode;

    private int mProvinceId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public int getCityCode() {
        return mCityCode;
    }

    public void setCityCode(int cityCode) {
        mCityCode = cityCode;
    }

    public int getProvinceId() {
        return mProvinceId;
    }

    public void setProvinceId(int provinceId) {
        mProvinceId = provinceId;
    }
}
