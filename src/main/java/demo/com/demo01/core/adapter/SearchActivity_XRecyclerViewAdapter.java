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
import demo.com.demo01.bean.SearchData;
import demo.com.demo01.core.home_list_xiangqing.activity.Adapter_GoodsXiangqing;

/**
 * 作者: Wang on 2019/1/3 11:51
 * 寄语：加油！相信自己可以！！！
 */

public class SearchActivity_XRecyclerViewAdapter extends RecyclerView.Adapter<SearchActivity_XRecyclerViewAdapter.ViewHolder> {

    private List<SearchData> list;
    private Context context;

    public SearchActivity_XRecyclerViewAdapter(  Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void setList(List<SearchData> alist) {
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
        View view = View.inflate(context, R.layout.search_item222,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SearchData searchData = list.get(i);
        viewHolder.image.setImageURI(Uri.parse(searchData.getMasterPic()));
        viewHolder.price.setText("¥"+searchData.getPrice());
        viewHolder.title.setText(searchData.getCommodityName());
        viewHolder.yishou.setText("已售"+searchData.getSaleNum()+"件");
        final int id = searchData.getCommodityId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Adapter_GoodsXiangqing.class);
                intent.putExtra("id",id+"");
                intent.putExtra("w","2");
                context.startActivity(intent);
            }
        });
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
            image = itemView.findViewById(R.id.search_xrecyclerview_image);
            title = itemView.findViewById(R.id.search_x_title);
            yishou = itemView.findViewById(R.id.search_x_yishou);
            price = itemView.findViewById(R.id.search_x_price);
        }
    }
}
