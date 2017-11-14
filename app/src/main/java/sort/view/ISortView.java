package sort.view;

import java.util.ArrayList;

import bean.CLass_LeftRVBean;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public interface ISortView {
    void getJson(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list);
    void getTypeData(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list);
}
