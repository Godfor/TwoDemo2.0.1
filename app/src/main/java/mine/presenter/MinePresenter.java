package mine.presenter;

import java.util.Map;

import mine.model.MineModel;
import mine.view.IMineView;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class MinePresenter implements MineModel.OnLoginFinish {
    private final IMineView userview;
    private final MineModel userModel;

    public MinePresenter(IMineView userview) {
        this.userview = userview;
        this.userModel = new MineModel();
        userModel.setOnfinish(this);
    }

    public void getUrl(String url, Map<String, String> mmap){
        userModel.getUrl(url,mmap);
    }

    @Override
    public void OnLoginFinishListen(String str,String msg) {
        if(str.equals("0")){
            userview.onSuccess(msg);
        }else{
            userview.onFailed(msg);
        }
    }
}
