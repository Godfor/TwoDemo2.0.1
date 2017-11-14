package mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.wangyiming.twodemo_201.LoginActivity;
import com.bawei.wangyiming.twodemo_201.R;
import com.bawei.wangyiming.twodemo_201.ShowUserActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import MyUtils.SharedPreferencesUtils;
import bean.Messages;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class Fragment_user extends Fragment {
    View view;

    Button btn_login;
    int Phone_CODE=11;

    Fragment_user context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_user,null);
        initview();
        initdata();

        context = this;

        return view;
    }
    public void initview(){
        btn_login = (Button) view.findViewById(R.id.btn_user_login);
    }
    public void initdata(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!EventBus.getDefault().isRegistered(context)){
                    EventBus.getDefault().register(context);
                }
                Object login_code = SharedPreferencesUtils.getParam(getActivity(), "LOGIN_CODE", "").toString();

                if(login_code.equals("0")){
                    Intent intent2 = new Intent(getActivity(), ShowUserActivity.class);
                    startActivity(intent2);
                }else{
                    Intent intent_loginActivity = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent_loginActivity);
                }

                //startActivityForResult(intent_loginActivity,Phone_CODE);

                //String login_code = SharedPreferencesUtils.getParam(getActivity(), "LOGIN_CODE", "").toString();

                //Log.d("Main", "==============================================="+login_code);

            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == Phone_CODE){
//            String user_phone = data.getStringExtra("user_phone");
//            btn_login.setText(user_phone);
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(Messages messageEvent){
        btn_login.setText(messageEvent.getUser_phone());
        EventBus.getDefault().unregister(this);
    }
}
