package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class DetailActivity extends Activity {

    private TextView text_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        inteView();
    }
    public void inteView(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = (wm.getDefaultDisplay().getHeight())*2/3;
        text_detail = (TextView)findViewById(R.id.text_detail);
        text_detail.setMinHeight(width);
        findViewById(R.id.tv_emotion).setVisibility(View.GONE);
        findViewById(R.id.im_weather).setVisibility(View.GONE);
        findViewById(R.id.text_tadle).setVisibility(View.VISIBLE);
    }
}
