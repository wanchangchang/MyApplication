/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.administrator.myapplication.Application;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by taodoen on 2016/8/25 19:07
 */
public abstract class BaseActivity extends Activity {
    public TextView tv_title;
    public final static int PAGE_SIZE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        initData();

    }

    /**
     * 加载view控件
     */
    public abstract void initView();

    /**
     * 加载数据
     */
    public abstract void initData();

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




    public void onBackClick(View v) {
        onBackPressed();
    }

    public void onRightClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    /*
     * 加载设备信息
     */
    protected String[] initDeviceInfo() {
        String[] result = new String[3];
        PackageManager manager = this.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            result[0] = info.versionName; // 版本号
            ApplicationInfo ai = manager.getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            result[1] = ai.metaData.getString("UMENG_CHANNEL");// 客户端编号
            result[2] = android.os.Build.MODEL + "|" + android.os.Build.VERSION.RELEASE;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * hide input method.
     */
    public void hideSoftKeyboard(View view) {
        if (view == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            IBinder localIBinder = view.getWindowToken();
            if (localIBinder != null)
                inputMethodManager.hideSoftInputFromWindow(localIBinder, 0);
        }
    }

    /**
     * hide inputMethod
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = this.getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }



    /**
     * 显示输入法键盘
     *
     * @param view
     */
    public void showSoftKeyboard(View view) {
        if (view == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            IBinder localIBinder = view.getWindowToken();
            if (localIBinder != null)
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void onBackPressed() {
        hideSoftKeyboard();
        finish();
    }
}
