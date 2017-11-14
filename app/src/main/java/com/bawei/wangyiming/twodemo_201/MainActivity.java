package com.bawei.wangyiming.twodemo_201;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import MyUtils.SharedPreferencesUtils;
import cart.Fragment_cart;
import home.Fragment_Home;
import mine.Fragment_user;
import sort.Fragment_class;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fm;
    FragmentTransaction transaction;

    Fragment_Home fragment_home;
    Fragment_class fragment_class;
    Fragment_cart fragment_cart;
    Fragment_user fragment_user;

    RadioGroup radioGroup;
    RadioButton nav_home,nav_class,nav_cart,nav_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    public void initView(){

        fragment_home = new Fragment_Home();
        fragment_class = new Fragment_class();
        fragment_cart = new Fragment_cart();
        fragment_user = new Fragment_user();

        nav_home = (RadioButton) findViewById(R.id.nav_home);
        nav_class = (RadioButton) findViewById(R.id.nav_class);
        nav_cart = (RadioButton) findViewById(R.id.nav_cart);
        nav_mine = (RadioButton) findViewById(R.id.nav_mine);

        radioGroup = (RadioGroup) findViewById(R.id.nav_radioGroup);

        SharedPreferencesUtils.setParam(this, "LOGIN_CODE", "1");

    }

    public void initData(){
        nav_home.setChecked(true);
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ftransaction = fm1.beginTransaction();
        ftransaction.replace(R.id.layout,fragment_home);
        ftransaction.commit();
        nav_home.setTextColor(Color.RED);
        nav_class.setTextColor(Color.BLACK);
        nav_cart.setTextColor(Color.BLACK);
        nav_mine.setTextColor(Color.BLACK);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                fm = getSupportFragmentManager();
                transaction = fm.beginTransaction();
                switch (i) {
                    case R.id.nav_home:
//                        Toast.makeText(MainActivity.this,"首页",Toast.LENGTH_SHORT).show();

//                        transaction.show(fragment_home);
//                        transaction.hide(fragment_class);
//                        transaction.hide(fragment_cart);
//                        transaction.hide(fragment_user);

                        transaction.replace(R.id.layout,fragment_home);
                        nav_home.setTextColor(Color.RED);
                        nav_class.setTextColor(Color.BLACK);
                        nav_cart.setTextColor(Color.BLACK);
                        nav_mine.setTextColor(Color.BLACK);

                        break;
                    case R.id.nav_class:
//                        Toast.makeText(MainActivity.this,"分类",Toast.LENGTH_SHORT).show();

//                        transaction.hide(fragment_home);
//                        transaction.show(fragment_class);
//                        transaction.hide(fragment_cart);
//                        transaction.hide(fragment_user);

                        transaction.replace(R.id.layout,fragment_class);
                        nav_home.setTextColor(Color.BLACK);
                        nav_class.setTextColor(Color.RED);
                        nav_cart.setTextColor(Color.BLACK);
                        nav_mine.setTextColor(Color.BLACK);

                        break;
                    case R.id.nav_cart:
//                        Toast.makeText(MainActivity.this,"购物车",Toast.LENGTH_SHORT).show();

//                        transaction.hide(fragment_home);
//                        transaction.hide(fragment_class);
//                        transaction.show(fragment_cart);
//                        transaction.hide(fragment_user);

                        Object login_code = SharedPreferencesUtils.getParam(MainActivity.this, "LOGIN_CODE", "").toString();

                        if(login_code.equals("0")){
                            transaction.replace(R.id.layout,fragment_cart);
                            nav_home.setTextColor(Color.BLACK);
                            nav_class.setTextColor(Color.BLACK);
                            nav_cart.setTextColor(Color.RED);
                            nav_mine.setTextColor(Color.BLACK);
                        }else{
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }



                        break;
                    case R.id.nav_mine:
//                        Toast.makeText(MainActivity.this,"个人",Toast.LENGTH_SHORT).show();

//                        transaction.hide(fragment_home);
//                        transaction.hide(fragment_class);
//                        transaction.hide(fragment_cart);
//                        transaction.show(fragment_user);

                        transaction.replace(R.id.layout,fragment_user);
                        nav_home.setTextColor(Color.BLACK);
                        nav_class.setTextColor(Color.BLACK);
                        nav_cart.setTextColor(Color.BLACK);
                        nav_mine.setTextColor(Color.RED);

                        break;

                }
                transaction.commit();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nav_top_zxing:
                Toast.makeText(MainActivity.this,"扫描二维码",Toast.LENGTH_SHORT).show();
                Intent intent_zxing = new Intent(MainActivity.this,ZxingActivity.class);
                startActivity(intent_zxing);

                break;
            case R.id.nav_top_talk:
                Toast.makeText(MainActivity.this,"消息",Toast.LENGTH_SHORT).show();
                Intent intent_talk = new Intent(MainActivity.this,TalkActivity.class);
                startActivity(intent_talk);
                break;
        }
    }
}
