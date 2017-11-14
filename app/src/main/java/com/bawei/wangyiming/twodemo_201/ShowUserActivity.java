package com.bawei.wangyiming.twodemo_201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import MyUtils.SharedPreferencesUtils;
import bean.Messages;

public class ShowUserActivity extends AppCompatActivity {

    Button btn_outlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        btn_outlogin = (Button) findViewById(R.id.btn_outLogin);


        btn_outlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtils.setParam(ShowUserActivity.this, "LOGIN_CODE", "1");
                EventBus.getDefault().post(new Messages("未登录"));
                finish();
            }
        });

    }
}
