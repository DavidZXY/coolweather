package com.example.lsgo14.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LSGO14 on 2018/3/29.
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort mComfort;

    @SerializedName("cw")
    public CarWash mCarWash;

    @SerializedName("sport")
    public Sport mSport;

    public class Comfort {

        @SerializedName("txt")
        public String mInfo;
    }

    public class CarWash {

        @SerializedName("txt")
        public String mInfo;
    }

    public class Sport {

        @SerializedName("txt")
        public String mInfo;
    }
}
