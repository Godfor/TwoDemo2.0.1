package com.bawei.wangyiming.twodemo_201;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import MyUtils.SharedPreferencesUtils;
import api.API;
import bean.Messages;
import mine.presenter.MinePresenter;
import mine.view.IMineView;

public class LoginActivity extends AppCompatActivity implements IMineView,View.OnClickListener {


    Button btn_login;
    EditText username,password;

    MinePresenter minePresenter;
    String name;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();
        initdata();

    }

    public void initview(){
        btn_login = (Button) findViewById(R.id.user_login_btn);
        username = (EditText) findViewById(R.id.user_login_username);
        password = (EditText) findViewById(R.id.userpass);
        minePresenter = new MinePresenter(this);

    }

    public void initdata(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = username.getText().toString();
                pass = password.getText().toString();

                Map<String,String> mymap = new HashMap();

                mymap.put("mobile",name);
                mymap.put("password",pass);

                minePresenter.getUrl(API.API_LOGIN,mymap);

            }
        });
    }


    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        Intent data = new Intent();
//        data.putExtra("user_phone",name);
//        setResult(RESULT_OK,data);

        EventBus.getDefault().post(new Messages(name));
        SharedPreferencesUtils.setParam(this, "LOGIN_CODE", "0");
        finish();

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        SharedPreferencesUtils.setParam(this, "LOGIN_CODE", "1");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_login_forgetpassword:

                break;

            case R.id.user_login_newuserzhuce:
                Intent intent_newuserActivity = new Intent(LoginActivity.this,NewUserAcitivity.class);
                startActivity(intent_newuserActivity);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
