package demo.com.demo01.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.FootListData;
import demo.com.demo01.bean.SearchData;
import demo.com.demo01.core.home_list_xiangqing.activity.Adapter_GoodsXiangqing;

/**
 * 作者: Wang on 2019/1/3 11:51
 * 寄语：加油！相信自己可以！！！
 */

public class FootRecyclerViewAdapter extends RecyclerView.Adapter<FootRecyclerViewAdapter.ViewHolder> {

    private List<FootListData> list;
    private Context context;
    private int id;

    public FootRecyclerViewAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void setList(List<FootListData> alist) {
        if (alist != null) {
            list.addAll(alist);
        }
    }
    public void deleteList( ) {
            list.clear();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.myfoot_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FootListData footListData = list.get(i);
        viewHolder.image.setImageURI(Uri.parse(footListData.getMasterPic()));
        viewHolder.price.setText("¥"+footListData.getPrice());
        viewHolder.title.setText(footListData.getCommodityName());
        viewHolder.yishou.setText("已售"+footListData.getBrowseNum()+"件");
        id = footListData.getCommodityId();
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Adapter_GoodsXiangqing.class);
//                intent.putExtra("id",id+"");
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeAll() {
        list.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView image;
        private final TextView title;
        private final TextView price;
        private final TextView yishou;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foot_image);
            title = itemView.findViewById(R.id.foot_x_title);
            yishou = itemView.findViewById(R.id.foot_x_yishou);
            price = itemView.findViewById(R.id.foot_x_price);
        }
    }
}
