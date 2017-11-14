package mine.model;

import java.util.Map;

import MyUtils.ApiService;
import bean.NewUserBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class NewUserModel implements INewModel {

    String str;
    String str2;

    private OnNewUserFinish onfinish;

    public interface OnNewUserFinish{
        void OnFinishListener(String str,String msg);
    }

    public void setOnfinish(OnNewUserFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getUrl(String url, Map<String, String> map) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<NewUserBean> newUser = apiService.getNewUser("user/reg", map);

        newUser.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewUserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewUserBean newUserBean) {
                        str = newUserBean.getCode();
                        str2 = newUserBean.getMsg();
                        onfinish.OnFinishListener(str,str2);
                    }
                });

    }
}
