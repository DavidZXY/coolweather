package com.example.lsgo14.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Province extends DataSupport {

    private int mId;

    private String mProvinceName;

    private int mProvinceCode;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getProvinceName() {
        return mProvinceName;
    }

    public void setProvinceName(String provinceName) {
        mProvinceName = provinceName;
    }

    public int getProvinceCode() {
        return mProvinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        mProvinceCode = provinceCode;
    }
}
