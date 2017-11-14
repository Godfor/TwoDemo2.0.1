package sort.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import MyUtils.ApiService;
import bean.Class_rightRVBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class SortUserModel implements ISortUserModel {

    ArrayList<Class_rightRVBean.DatasBean.ClassListBean> list = new ArrayList<>();
    private OnRightUserFinish onfinish;

    public interface OnRightUserFinish{
        void onRightUserFinishLinsenter(ArrayList<Class_rightRVBean.DatasBean.ClassListBean> list);
    }

    public void setOnfinish(OnRightUserFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getUrl(String url, Map<String, String> mmap) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        String s = mmap.get("gc_id");

//        http://169.254.57.17/mobile/index.php?act=goods_class&gc_id=2
//        mobile/index.php?act=goods_class&gc_id=2
        Observable<Class_rightRVBean> right = apiService.getrightjson("mobile/index.php?act=goods_class&gc_id="+s);

        right.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Class_rightRVBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Main","wwwwwwwwwwwwwwwwwwwwww");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Class_rightRVBean class_rightRVBean) {
                        list = (ArrayList<Class_rightRVBean.DatasBean.ClassListBean>) class_rightRVBean.getDatas().getClass_list();
                        Log.d("Main","qqqqqqqqqqqqqqqqqqqqq");
                        onfinish.onRightUserFinishLinsenter(list);
                    }
                });
    }
}
