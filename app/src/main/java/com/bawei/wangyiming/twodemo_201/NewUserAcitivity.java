package com.bawei.wangyiming.twodemo_201;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import api.API;
import mine.presenter.NewUserPresenter;
import mine.view.INewView;

public class NewUserAcitivity extends AppCompatActivity implements INewView {

    EditText username,password,password2,email;
    Button btn_submit;

    NewUserPresenter newUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_acitivity);

        initview();
        initdata();
    }

    public void initview(){
        username = (EditText) findViewById(R.id.newuser_username);
        password = (EditText) findViewById(R.id.newuser_password);
        password2 = (EditText) findViewById(R.id.newuser_password_two);
        btn_submit = (Button) findViewById(R.id.newuser_submit_btn);

        newUserPresenter = new NewUserPresenter(this);
    }

    public void initdata(){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();

                Map<String,String> mymap = new HashMap<String, String>();

                mymap = new HashMap();
                mymap.put("mobile",name);
                mymap.put("password",pass);

                newUserPresenter.getUrl(API.API_ZHUCE,mymap);


//                OkHttp3Utils.getInstance().doPost("http://120.27.23.105/user/reg", mymap, new GsonObjectCallback<NewUserBean>() {
//                    @Override
//                    public void onUi(NewUserBean newUserBean) {
//                        String sss = newUserBean.getMsg();
//                        Toast.makeText(NewUserAcitivity.this, sss, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailed(Call call, IOException e) {
//
//                    }
//                });
//                if(pass != pass2){
//
//                }else{
//                    Toast.makeText(NewUserAcitivity.this,"两次输入密码不同",Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }


    @Override
    public void onSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
