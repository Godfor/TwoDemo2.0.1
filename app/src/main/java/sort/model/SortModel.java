package sort.model;

import java.util.ArrayList;
import java.util.Map;

import MyUtils.ApiService;
import bean.CLass_LeftRVBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class SortModel implements ISortModel {

    ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list = new ArrayList<>();

    private OnLeftFinish onfinish;
    private OnRightFinish onRightFinish;

    public interface OnLeftFinish{
        void OnLeftFinishLinsener(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list);
    }

    public void setOnfinish(OnLeftFinish onfinish) {
        this.onfinish = onfinish;
    }

    public interface OnRightFinish{
        void onFinishLinsenter(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list);
    }

    public void setOnRightFinish(OnRightFinish onRightFinish) {
        this.onRightFinish = onRightFinish;
    }

    @Override
    public void getUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<CLass_LeftRVBean> left = apiService.getLeft();

        left.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CLass_LeftRVBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CLass_LeftRVBean cLass_leftRVBean) {
                        list = (ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean>) cLass_leftRVBean.getDatas().getClass_list();
                        onfinish.OnLeftFinishLinsener(list);
                    }
                });

    }

    @Override
    public void getRightUrl(String url, Map<String, String> mmap) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);

        Observable<CLass_LeftRVBean> right = apiService.getRight("mobile/index.php?act=goods_class", mmap);

        right.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CLass_LeftRVBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CLass_LeftRVBean classListBean) {
                        list = (ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean>) classListBean.getDatas().getClass_list();

                        onRightFinish.onFinishLinsenter(list);
                    }
                });

    }
}
