package com.example.administrator.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.myapplication.Model.DataForBase;
import com.example.administrator.myapplication.Model.WeatherImage;
import com.example.administrator.myapplication.Model.WeatherParamter;
import com.example.administrator.myapplication.Model.WeatherResult;
import com.example.administrator.myapplication.Model.WeatherSource;
import com.example.commenttool.Uitl.TimeUtil;
import com.example.commenttool.source.BaseService;

public class AddTextActivity extends AppCompatActivity {
    private EditText ed_think;
    private TextView tv_emotion;
    private String[] areas = new String[]{"开心", "愤怒", "忧愁", "快乐", "平静", "感恩", "无"};
    private int positon = 0;
    private LocationManager locationManager;
    private String privade;
    private String Longitude = null;
    private String Latitude = null;
    private ImageView imv;
    private TextView tv_weater;
    private DataForBase data;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    initResult((WeatherResult) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);
        intView();
    }

    public void intView() {
        ed_think = (EditText) findViewById(R.id.ed_think);
        tv_emotion = (TextView) findViewById(R.id.tv_emotion);
        imv = (ImageView) findViewById(R.id.im_weather);
        tv_weater = (TextView)findViewById(R.id.tv_weater);
        tv_weater.setVisibility(View.VISIBLE);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = (wm.getDefaultDisplay().getHeight()) * 2 / 3;
        ed_think.setMinHeight(width);
        Intent intent = getIntent();
        Latitude = intent.getStringExtra("Latitude");
        Longitude = intent.getStringExtra("Longitude");
        if (Latitude != null || Longitude != null) {
            intWeather(Longitude, Latitude);
        } else {
            Toast.makeText(this, "获取地址失败", Toast.LENGTH_LONG).show();
        }
    }

    private void intWeather(String strlong, String strlati) {
        final WeatherParamter paramter = new WeatherParamter();
        paramter.setKey("8h5a9ad12rvv4ti5");
        paramter.setCity(strlati + "," + strlong);
        BaseService.dispatch(new WeatherSource(0, mHandler, paramter));

    }

    private void initResult(WeatherResult obj) {
        if (obj != null) {
            if (obj.getMsg().equals("Sucess")) {
//                Toast.makeText(this, obj.getData().getNumtq(), Toast.LENGTH_LONG).show();
                imv.setImageResource(WeatherImage.selectImage(obj.getData().getNumtq()));
                tv_weater.setText(obj.getData().getTq()+" "+obj.getData().getQw()+getString(R.string.wendu));
            }
        } else {
            Toast.makeText(this, "请检查网络,无法定位天气", Toast.LENGTH_LONG).show();
        }
    }

    public void emotionClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(areas, positon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView lw = ((AlertDialog) dialog).getListView();
                // which表示点击的条目
                Object checkedItem = lw.getAdapter().getItem(which);
                // 既然你没有cancel或者ok按钮，所以需要在点击item后使dialog消失
                dialog.dismiss();
                // 更新你的view
                tv_emotion.setText((String) checkedItem);
                positon = which;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 保存数据到数据库
     */
    public void sevaDataToBase(){
        data.setData(TimeUtil.dateTimeNow());
        data.setMotion(tv_weater.getText().toString());
//        data.setType();
    }




}
