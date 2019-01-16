package demo.com.demo01.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.HomeListData;
import demo.com.demo01.core.home_list_xiangqing.activity.Adapter_GoodsXiangqing;
import demo.com.demo01.view.StartActivity;

/**
 * 作者: Wang on 2018/12/30 15:57
 * 寄语：加油！相信自己可以！！！
 */


public class Home_RecyclerViewAdapter1 extends RecyclerView.Adapter<Home_RecyclerViewAdapter1.ViewHolder> {
    private List<HomeListData.RxxpBean.CommodityListBean> list;
    private  Context context;

    public Home_RecyclerViewAdapter1(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void setList(List<HomeListData.RxxpBean.CommodityListBean> alist) {
        if (alist != null) {
            list.addAll(alist);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.home_recyclerview_1,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        final HomeListData.RxxpBean.CommodityListBean rxxpBean = list.get(i);

        viewHolder.simpleDraweeView.setImageURI(Uri.parse(rxxpBean.getMasterPic()));
        viewHolder.price.setText(" ¥"+rxxpBean.getPrice());
        viewHolder.title.setText(rxxpBean.getCommodityName());
        final int id = list.get(i).getCommodityId();

        // 点击条目进入展示商品详情
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Adapter_GoodsXiangqing.class);
                intent.putExtra("id",id+"");
                intent.putExtra("w","1");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simpleDraweeView;
        private final TextView price;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.recyclerview1_icon);
            price = itemView.findViewById(R.id.recyclerview1_price);
            title = itemView.findViewById(R.id.recyclerview1_title);
        }
    }
}
