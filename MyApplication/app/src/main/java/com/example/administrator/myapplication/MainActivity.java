package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.Servicers.MyDataBaseSql;

import java.text.DecimalFormat;

public class MainActivity extends FragmentActivity {
    private RadioGroup radioGroup;
    private LocationManager locationManager;
    private String privade;
    private String Longitude;//经度
    private String Latitude;//纬度
    private MyDataBaseSql dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.imv_addimg).setClickable(true);
        radioGroup = (RadioGroup) findViewById(R.id.activity_group_radioGroup);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, AbleFragment.newInstance("记录")).commit();
        }
        initView();
        intLocation();

    }

    public void initView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.order_process:
                        ft.replace(R.id.container, AbleFragment.newInstance("记录"));
                        break;
                    case R.id.order_query:
                        ft.replace(R.id.container, AbleFragment.newInstance("总结"));
                        break;
                    case R.id.merchant_manager:
                        ft.replace(R.id.container, AbleFragment.newInstance("畅想"));
                        break;
                    case R.id.setting:
                        ft.replace(R.id.container, new SetToolFragment());
                        break;
                    default:
                        break;
                }
                ft.commit();
            }
        });
        openDateBase();
    }

    public void addWorldClick(View v) {
//        if (Latitude != null && Longitude != null) {
            Intent intent = new Intent();
            intent.setClass(this, AddTextActivity.class);
            intent.putExtra("Latitude", 56);
            intent.putExtra("Longitude", 56);
            startActivity(intent);
//        }
    }

    public void intLocation() {
        Criteria locationcriteria = new Criteria();
        locationcriteria.setAccuracy(Criteria.ACCURACY_FINE);
        //不提供海拔高度信息
        locationcriteria.setAltitudeRequired(false);
        //不提供方向信息
        locationcriteria.setBearingRequired(false);
        //设置电池消耗为低耗费
        locationcriteria.setPowerRequirement(Criteria.POWER_LOW);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkgps()) {
            privade = locationManager.getBestProvider(locationcriteria, true);
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        showLocation(location);
                    }else {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            showLocation(location);
                        }
                    }
                    privade = locationManager.getBestProvider(locationcriteria, true);
                    locationManager.requestLocationUpdates(privade, 5000, 1000, locationmanagerlistener);
            }
        }

    }

    LocationListener locationmanagerlistener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void showLocation(Location location) {
        if (location != null) {
            Longitude = new DecimalFormat("#0.00").format(location.getLongitude());//经度
            Latitude = new DecimalFormat("#0.00").format(location.getLatitude());//纬度
        } else {
            Toast.makeText(this, "获取位置失败", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkgps() {
        boolean providerEnabled
                = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //若被激活，则返回真值
        if (providerEnabled == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打开数据库
     */
    public void openDateBase() {
        dbhelper = new MyDataBaseSql(this, "TextBase", null, 1);
        dbhelper.getWritableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(locationmanagerlistener);
            }
        }
    }
}

