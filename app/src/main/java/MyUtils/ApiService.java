package MyUtils;

import java.util.Map;

import bean.CLass_LeftRVBean;
import bean.Class_rightRVBean;
import bean.HomeBean;
import bean.LoginBean;
import bean.NewUserBean;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public interface ApiService {
    @GET("umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Observable<HomeBean> getHome();

//    @GET("user/login")
//    Observable<LoginBean> getLogin();

    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap Map<String,String> map);

    @POST
    Observable<NewUserBean> getNewUser(@Url String url,@QueryMap Map<String,String> map);

    @GET("mobile/index.php?act=goods_class")
    Observable<CLass_LeftRVBean> getLeft();

    @POST
    Observable<CLass_LeftRVBean> getRight(@Url String url,@QueryMap Map<String,String> map);

    @POST
    Observable<Class_rightRVBean> getrightjson(@Url String url);

}
