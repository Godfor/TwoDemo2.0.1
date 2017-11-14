package home.presenter;

import bean.HomeBean;
import home.model.UserHomeModel;
import home.view.IHomeView;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HomePresenter implements UserHomeModel.OnFinish {
    private final IHomeView userview;
    private final UserHomeModel usermodel;

    public HomePresenter(IHomeView userview) {
        this.userview = userview;
        this.usermodel = new UserHomeModel();
        this.usermodel.setOnFinish(this);
    }
    public void getUrl(String url){
        usermodel.getUrl(url);
    }

    @Override
    public void OnFinishListener(HomeBean.DataBean list) {
        userview.getJson(list);
    }
}
