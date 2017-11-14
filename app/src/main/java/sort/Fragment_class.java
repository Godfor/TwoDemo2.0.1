package sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.wangyiming.twodemo_201.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import api.API;
import bean.CLass_LeftRVBean;
import sort.adapter.ClassLeftRVAdapter;
import sort.adapter.ClassRightRVAdapter;
import sort.presenter.SortPresenter;
import sort.view.ISortView;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class Fragment_class extends Fragment implements ISortView {

    View view;

    RecyclerView recycle_left,recycle_right;
    ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> left_list = new ArrayList<>();

    SortPresenter sortPresenter;

    ClassLeftRVAdapter leftadapter;
    ClassRightRVAdapter rightadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_sort,null);

        initview();
        recycle_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_right.setLayoutManager(new LinearLayoutManager(getActivity()));

        sortPresenter.getUrl(API.API_SORT);

        return view;
    }

    public void initview(){
        recycle_left = (RecyclerView) view.findViewById(R.id.class_recycle_left);
        recycle_right = (RecyclerView) view.findViewById(R.id.class_recycle_right);
        sortPresenter = new SortPresenter(this);
    }

    @Override
    public void getJson(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list) {

        Log.d("Main","++++++++++++++++++++++++++++++++++++++++++++++++++"+list.get(0).getGc_name());


        left_list = list;
        leftadapter = new ClassLeftRVAdapter(left_list,getActivity());
        recycle_left.setAdapter(leftadapter);

        leftadapter.setRecycleViewItemClickListener(new ClassLeftRVAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {

                Toast.makeText(getActivity(), "+111111111111111111111111", Toast.LENGTH_SHORT).show();
                
                leftadapter.setTagPosition(position);
                leftadapter.notifyDataSetChanged();
                Map<String,String> mmap = new HashMap<String, String>();
                mmap.put("gc_id",left_list.get(position).getGc_id());

                sortPresenter.getrightUrl(API.API_SORT,mmap);

            }
        });

    }

    @Override
    public void getTypeData(ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list) {
        ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean> list2 = new ArrayList<CLass_LeftRVBean.DatasBean.ClassListBean>();
        list2 = list;
        rightadapter = new ClassRightRVAdapter(list2,getActivity());
        recycle_right.setAdapter(rightadapter);
    }
}
