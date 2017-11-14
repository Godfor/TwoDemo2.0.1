package sort.view;

import android.widget.GridView;

import java.util.ArrayList;

import bean.Class_rightRVBean;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public interface ISortJsonView {
    void getrightUser(ArrayList<Class_rightRVBean.DatasBean.ClassListBean> list, GridView gridView);
}
