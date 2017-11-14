package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.wangyiming.twodemo_201.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import bean.HomeBean;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    Context context;
    ArrayList<HomeBean.DataBean.DefaultGoodsListBean> list;

    public MyRecycleAdapter(Context context, ArrayList<HomeBean.DataBean.DefaultGoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_tuijian,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecycleAdapter.MyViewHolder holder, int position) {
        holder.content.setText(list.get(position).getGoods_name());

        Uri imageuri4 = Uri.parse(list.get(position).getGoods_img());
        holder.head.setImageURI(imageuri4);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView head;
        TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            head = (SimpleDraweeView) itemView.findViewById(R.id.home_tuijian_head);
            content = (TextView) itemView.findViewById(R.id.home_tuijian_content);
        }
    }

}
