package mine.model;

import android.util.Log;

import java.util.Map;

import MyUtils.ApiService;
import bean.LoginBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class MineModel implements IMineModel {

    String str;
    String str2;

    LoginBean list = new LoginBean();

    private OnLoginFinish onfinish;

    public interface OnLoginFinish{
        void OnLoginFinishListen(String str,String msg);
    }

    public void setOnfinish(OnLoginFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getUrl(String url, Map<String,String> mmap) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<LoginBean> login = apiService.getLogin("user/login",mmap);

        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        str = loginBean.getCode();
                        str2 = loginBean.getMsg();
                        onfinish.OnLoginFinishListen(str,str2);
                        Log.d( "onNext","++++++++++++++++++++++++++++++++++++++++"+str);
                    }
                });

    }
}
