package sort.presenter;

import android.content.Context;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Map;

import bean.Class_rightRVBean;
import sort.adapter.MyGridAdapter;
import sort.model.SortUserModel;
import sort.view.ISortJsonView;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class SortUserPresenter implements SortUserModel.OnRightUserFinish {
    private final ISortJsonView userview;
    private final SortUserModel usermodel;
    GridView gridView;
    Context context;

    MyGridAdapter adapter;

    public SortUserPresenter(ISortJsonView userview) {
        this.userview = userview;
        this.usermodel = new SortUserModel();
        usermodel.setOnfinish(this);
    }

    public void getUrl(String url, Map<String,String> mmap, GridView gridView, Context context){
        this.gridView = gridView;
        this.context = context;
        usermodel.getUrl(url,mmap);

    }

    @Override
    public void onRightUserFinishLinsenter(ArrayList<Class_rightRVBean.DatasBean.ClassListBean> list) {
        //Log.d("Main","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        userview.getrightUser(list,gridView);
//        adapter = new MyGridAdapter(list,context);
//        gridView.setAdapter(adapter);
    }
}
