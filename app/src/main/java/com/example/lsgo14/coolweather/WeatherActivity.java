package com.example.lsgo14.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lsgo14.coolweather.gson.Forecast;
import com.example.lsgo14.coolweather.gson.Weather;
import com.example.lsgo14.coolweather.service.AutoUpdateService;
import com.example.lsgo14.coolweather.util.HttpUtil;
import com.example.lsgo14.coolweather.util.Utility;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;

    private Button mNavButton;

    public SwipeRefreshLayout mSwipeRefresh;

    private ScrollView mWeatherLayout;

    private TextView mTitleCity;

    private TextView mTitleUpdateTime;

    private TextView mDegreeText;

    private TextView mWeatherInfoText;

    private LinearLayout mForecastLayout;

    private TextView mAqiText;

    private TextView mPM25Text;

    private TextView mComfortText;

    private TextView mCarWashText;

    private TextView mSportText;

    private ImageView mBingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        mBingPicImg = findViewById(R.id.bing_pic_img);
        mWeatherLayout = findViewById(R.id.weather_layout);
        mTitleCity = findViewById(R.id.title_city);
        mTitleUpdateTime = findViewById(R.id.title_update_time);
        mDegreeText = findViewById(R.id.degree_text);
        mWeatherInfoText = findViewById(R.id.weather_info_text);
        mForecastLayout = findViewById(R.id.forecast_layout);
        mAqiText = findViewById(R.id.aqi_text);
        mPM25Text = findViewById(R.id.pm25_text);
        mComfortText = findViewById(R.id.comfort_text);
        mCarWashText = findViewById(R.id.car_wash_text);
        mSportText = findViewById(R.id.sport_text);
        mSwipeRefresh = findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavButton = findViewById(R.id.nav_button);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        final String weatherId;
        if (weatherString != null) {
            Weather weather = Utility.handleWeatherResponse(weatherString);
            weatherId = weather.mBasic.mWeatherId;
            showWeatherInfo(weather);
        } else {
            weatherId = getIntent().getStringExtra("weather_id");
            mWeatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherId);
            }
        });
        mNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(mBingPicImg);
        } else {
            loadBingPic();
        }
    }

    public void requestWeather(final String weatherId) {

        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=d55480611c5640eeadd4dfe0e9a84f99";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.mStatus)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        mSwipeRefresh.setRefreshing(false);
                    }

                });
            }

        });

        loadBingPic();
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(mBingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

        });
    }

    private void showWeatherInfo(Weather weather) {
        String cityName = weather.mBasic.mCityName;
        String updateTime = weather.mBasic.mUpdate.mUpdateTime.split(" ")[1];
        String degree = weather.mNow.mTemperature + "℃";
        String weatherInfo = weather.mNow.mMore.mInfo;
        mTitleCity.setText(cityName);
        mTitleUpdateTime.setText(updateTime);
        mDegreeText.setText(degree);
        mWeatherInfoText.setText(weatherInfo);
        mForecastLayout.removeAllViews();
        for (Forecast forecast : weather.mForecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, mForecastLayout, false);
            TextView dateText = view.findViewById(R.id.date_text);
            TextView infoText = view.findViewById(R.id.info_text);
            TextView maxText = view.findViewById(R.id.max_text);
            TextView minText = view.findViewById(R.id.min_text);
            dateText.setText(forecast.mDate);
            infoText.setText(forecast.mMore.mInfo);
            maxText.setText(forecast.mTemperature.mMax);
            minText.setText(forecast.mTemperature.mMin);
            mForecastLayout.addView(view);
        }
        if (weather.mAQI != null) {
            mAqiText.setText(weather.mAQI.mCity.aqi);
            mPM25Text.setText(weather.mAQI.mCity.pm25);
        }
        String comfort = "舒适度：" + weather.mSuggestion.mComfort.mInfo;
        String carWash = "洗车指数" + weather.mSuggestion.mCarWash.mInfo;
        String sport = "运动建议" + weather.mSuggestion.mSport.mInfo;
        mComfortText.setText(comfort);
        mCarWashText.setText(carWash);
        mSportText.setText(sport);
        mWeatherLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startActivity(intent);
    }
}




























