package home.model;

import MyUtils.ApiService;
import bean.HomeBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class UserHomeModel implements IHomeModel {

    HomeBean.DataBean list = new HomeBean.DataBean();
    private OnFinish onFinish;

    public interface OnFinish{
        void OnFinishListener(HomeBean.DataBean list);
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        final Observable<HomeBean> home = apiService.getHome();

        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        list = homeBean.getData();
                        onFinish.OnFinishListener(list);
                    }
                });
    }
}
