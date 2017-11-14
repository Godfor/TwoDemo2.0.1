package mine.presenter;

import java.util.Map;

import mine.model.NewUserModel;
import mine.view.INewView;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class NewUserPresenter implements NewUserModel.OnNewUserFinish {
    private final INewView userview;
    private final NewUserModel usermodel;

    public NewUserPresenter(INewView userview) {
        this.userview = userview;
        this.usermodel = new NewUserModel();
        usermodel.setOnfinish(this);
    }

    public void getUrl(String url, Map<String,String> map){
        usermodel.getUrl(url,map);
    }

    @Override
    public void OnFinishListener(String str,String msg) {
        if(str.equals("0")){
            userview.onSuccess(msg);
        }else{
            userview.onFailed(msg);
        }
    }
}
