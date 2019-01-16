package demo.com.demo01.core.adapter.benadapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.core.utils.AddSubLayout;

/**
 * 作者: Wang on 2019/1/10 10:36
 * 寄语：加油！相信自己可以！！！
 */


public class Ben_list2_item_Adapter extends RecyclerView.Adapter<Ben_list2_item_Adapter.ViewHolder> {
    private FragmentActivity context;
    private List<DingdanData.DetailListBean> listBeans;

    public Ben_list2_item_Adapter(FragmentActivity context) {
        listBeans = new ArrayList<>();
        this.context = context;
    }

    public void setListBeans(List<DingdanData.DetailListBean> listBeans) {
        if (listBeans != null) {
//            this.listBeans.clear();
            this.listBeans=listBeans;
//            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.list_fragment_02_buju_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DingdanData.DetailListBean detailListBean = listBeans.get(i);

        viewHolder.title.setText(detailListBean.getCommodityName());
        String commodityPic = detailListBean.getCommodityPic();
        String[] split = commodityPic.split(",");
        viewHolder.image.setImageURI(Uri.parse(split[0]));
        viewHolder.add.setCount(detailListBean.getCommodityCount());
        viewHolder.add.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                detailListBean.setCommodityCount(count);
            }
        });
        viewHolder.price.setText("¥ "+detailListBean.getCommodityPrice()*detailListBean.getCommodityCount());
    }


    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public void clear() {
        listBeans.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView image;
        private final AddSubLayout add;
        private final TextView price,title;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.order_sdv_image);
            add = itemView.findViewById(R.id.order_txt_add);
            price = itemView.findViewById(R.id.order_txt_price);
            title = itemView.findViewById(R.id.order_txt_title);
        }
    }
}
