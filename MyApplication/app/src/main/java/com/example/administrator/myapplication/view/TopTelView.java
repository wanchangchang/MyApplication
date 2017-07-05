package com.example.administrator.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;

/**
 * Created by wanchangchang on 2016/12/1.
 */

public class TopTelView extends LinearLayout implements View.OnClickListener{


    public TopTelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_layout,this);
        ImageView back_img = (ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                ((Activity)getContext()).finish();
                break;
        }
    }
}
