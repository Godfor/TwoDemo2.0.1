package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.wangyiming.twodemo_201.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import bean.HomeBean;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class MyHomeXRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    HomeBean.DataBean list;

    ArrayList bannerlist;

    public MyHomeXRecyclerAdapter(Context context, HomeBean.DataBean list) {
        this.context = context;
        this.list = list;
    }
    private enum Item_Type{
        Type1,Type2,Type3,Type4
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == Item_Type.Type1.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem_banner,null);
            MyViewHolderA viewholder = new MyViewHolderA(view);
            return viewholder;
        }else if(viewType == Item_Type.Type2.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem_nav,null);
            MyViewHolderB viewholder = new MyViewHolderB(view);
            return viewholder;
        }else if(viewType == Item_Type.Type3.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem_remai,null);
            MyViewHolderC viewholder = new MyViewHolderC(view);
            return viewholder;
        }else if(viewType == Item_Type.Type4.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.homeitem_tuijian,null);
            MyViewHolderD viewholder = new MyViewHolderD(view);
            return viewholder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolderA){
            bannerlist = new ArrayList();
            for(int i=0; i<list.getAd1().size();i++){
                String ad1s = list.getAd1().get(i).getImage();
                bannerlist.add(ad1s);
            }
            //设置图片加载器
            ((MyViewHolderA) holder).mbanner.setImageLoader(new GlideImageLoader());
            ((MyViewHolderA) holder).mbanner.setImages(bannerlist);
            ((MyViewHolderA) holder).mbanner.start();
        }else if(holder instanceof MyViewHolderB){

            ArrayList<String> list_text = new ArrayList<String>();
            ArrayList<String> list_headurl = new ArrayList<String>();


            for(int i = 0; i < list.getAd5().size();i++){
                list_text.add(i,list.getAd5().get(i).getTitle());
                list_headurl.add(i,list.getAd5().get(i).getImage());
            }

            Uri imageuri1 = Uri.parse(list_headurl.get(0));
            Uri imageuri2 = Uri.parse(list_headurl.get(1));
            Uri imageuri3 = Uri.parse(list_headurl.get(2));
            Uri imageuri4 = Uri.parse(list_headurl.get(3));



            ((MyViewHolderB) holder).home_mrqd_text.setText(list_text.get(0));
            ((MyViewHolderB) holder).home_mrqd_head.setImageURI(imageuri1);


            ((MyViewHolderB) holder).home_jfsc_text.setText(list_text.get(1));
            ((MyViewHolderB) holder).home_jfsc_head.setImageURI(imageuri2);

            ((MyViewHolderB) holder).home_dhzq_text.setText(list_text.get(2));
            ((MyViewHolderB) holder).home_dhzq_head.setImageURI(imageuri3);

            ((MyViewHolderB) holder).home_zwcx_text.setText(list_text.get(3));
            ((MyViewHolderB) holder).home_zwcx_head.setImageURI(imageuri4);


        }else if(holder instanceof MyViewHolderC){
            ((MyViewHolderC)holder).horRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
            ArrayList<HomeBean.DataBean.SubjectsBean> horrecycle_list = (ArrayList<HomeBean.DataBean.SubjectsBean>) list.getSubjects();

            MyHorRecycleAdapter horrecyadapter = new MyHorRecycleAdapter(context,horrecycle_list);
            ((MyViewHolderC)holder).horRecyclerView.setAdapter(horrecyadapter);
        }else if(holder instanceof MyViewHolderD){

            ((MyViewHolderD)holder).mRecyclerView.setNestedScrollingEnabled(false);
            //设置布局管理器
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            ((MyViewHolderD)holder).mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));

            ArrayList<HomeBean.DataBean.DefaultGoodsListBean> lists = (ArrayList<HomeBean.DataBean.DefaultGoodsListBean>) list.getDefaultGoodsList();


            MyRecycleAdapter adapter = new MyRecycleAdapter(context,lists);
            ((MyViewHolderD)holder).mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return Item_Type.Type1.ordinal();
        }else if(position == 1){
            return Item_Type.Type2.ordinal();
        }else if(position ==2){
            return Item_Type.Type3.ordinal();
        }else if(position == 3){
            return Item_Type.Type4.ordinal();
        }
        return -1;
    }

    class MyViewHolderA extends RecyclerView.ViewHolder{
        Banner mbanner;
        public MyViewHolderA(View itemView) {
            super(itemView);
            mbanner= (Banner) itemView.findViewById(R.id.home_banner);
        }
    }

    class MyViewHolderB  extends RecyclerView.ViewHolder{
        TextView home_mrqd_text,home_jfsc_text,home_dhzq_text,home_zwcx_text;
        SimpleDraweeView home_mrqd_head,home_jfsc_head,home_dhzq_head,home_zwcx_head;
        public MyViewHolderB(View itemView) {
            super(itemView);
            home_mrqd_text = (TextView) itemView.findViewById(R.id.home_mrqd_text);
            home_jfsc_text = (TextView) itemView.findViewById(R.id.home_jfsc_text);
            home_dhzq_text = (TextView) itemView.findViewById(R.id.home_dhzq_text);
            home_zwcx_text = (TextView) itemView.findViewById(R.id.home_zwcx_text);

            home_mrqd_head = (SimpleDraweeView) itemView.findViewById(R.id.home_mrqd_head);
            home_jfsc_head = (SimpleDraweeView) itemView.findViewById(R.id.home_jfsc_head);
            home_dhzq_head = (SimpleDraweeView) itemView.findViewById(R.id.home_dhzq_head);
            home_zwcx_head = (SimpleDraweeView) itemView.findViewById(R.id.home_zwcx_head);
        }
    }

    class MyViewHolderC extends RecyclerView.ViewHolder{
        RecyclerView horRecyclerView;
        public MyViewHolderC(View itemView) {
            super(itemView);
            horRecyclerView = (RecyclerView) itemView.findViewById(R.id.home_onehor_recyclerview);
        }
    }

    class MyViewHolderD extends RecyclerView.ViewHolder{
        private RecyclerView mRecyclerView;
        public MyViewHolderD(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.home_recyclerview);
        }
    }
}
