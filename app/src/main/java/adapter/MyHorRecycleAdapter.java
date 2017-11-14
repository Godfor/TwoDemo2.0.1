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

public class MyHorRecycleAdapter extends RecyclerView.Adapter<MyHorRecycleAdapter.HorViewHolder> {

    Context context;
    ArrayList<HomeBean.DataBean.SubjectsBean> list;

    public MyHorRecycleAdapter(Context context, ArrayList<HomeBean.DataBean.SubjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HorViewHolder viewHolder = new HorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_horrecyclerview,parent,false));


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HorViewHolder holder, int position) {
        holder.text.setText(list.get(position).getDetail());
        Uri imageuri1 = Uri.parse(list.get(position).getDescImage());
        holder.head.setImageURI(imageuri1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView head;
        TextView text;

        public HorViewHolder(View itemView) {
            super(itemView);
            head = (SimpleDraweeView) itemView.findViewById(R.id.home_onehorrecycler_head);
            text = (TextView) itemView.findViewById(R.id.home_onehorcycle_text);
        }
    }

}
