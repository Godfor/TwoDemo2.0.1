package home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bawei.wangyiming.twodemo_201.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import adapter.MyHomeXRecyclerAdapter;
import api.API;
import bean.HomeBean;
import home.presenter.HomePresenter;
import home.view.IHomeView;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class Fragment_Home extends Fragment implements IHomeView {

    View view;

    XRecyclerView xRecyclerView;
    ImageView nav_top_zxing;
    ImageView nav_top_talk;
    EditText nav_top_search;

    HomePresenter homePresenter;
    //TextView cstv;

    MyHomeXRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_home,null);

        initView();

        return view;
    }
    public void initView(){
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.home_xrecycler);
        nav_top_zxing = (ImageView) view.findViewById(R.id.nav_top_zxing);
        nav_top_talk = (ImageView) view.findViewById(R.id.nav_top_talk);
        nav_top_search = (EditText) view.findViewById(R.id.nav_top_search);
        //cstv = (TextView) home.view.findViewById(R.id.cstv);
        homePresenter = new HomePresenter(this);
        homePresenter.getUrl(API.API_HOME);

        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }



    @Override
    public void getJson(HomeBean.DataBean list) {
        adapter = new MyHomeXRecyclerAdapter(getActivity(),list);
        xRecyclerView.setAdapter(adapter);
    }
}
