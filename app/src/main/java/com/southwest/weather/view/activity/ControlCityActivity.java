package com.southwest.weather.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.southwest.weather.adapter.ControlCityAdapter;
import com.southwest.weather.utils.ContentUtil;
import com.southwest.weather.utils.SpUtils;
import com.southwest.weather.R;
import com.southwest.weather.bean.CityBean;
import com.southwest.weather.bean.CityBeanList;

import java.util.ArrayList;
import java.util.List;

public class ControlCityActivity extends BaseActivity {
    private List<CityBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_city);
        ImageView ivBack = findViewById(R.id.iv_control_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView rcControl = findViewById(R.id.recycle_control);
        CityBeanList cityBeanList = SpUtils.getBean(this, "cityBean", CityBeanList.class);
        if (ContentUtil.APP_SETTING_LANG.equals("en") || ContentUtil.APP_SETTING_LANG.equals("sys") && ContentUtil.SYS_LANG.equals("en")) {
            cityBeanList = SpUtils.getBean(this, "cityBeanEn", CityBeanList.class);
        }
        if (cityBeanList != null) {
            datas = cityBeanList.getCityBeans();
        }
        LinearLayoutManager forecastManager = new LinearLayoutManager(this);
        forecastManager.setOrientation(LinearLayoutManager.VERTICAL);
        ControlCityAdapter followCityAdapter = new ControlCityAdapter(this, datas);

        rcControl.setAdapter(followCityAdapter);
        rcControl.setLayoutManager(forecastManager);
        rcControl.setItemAnimator(new DefaultItemAnimator());
    }
}
