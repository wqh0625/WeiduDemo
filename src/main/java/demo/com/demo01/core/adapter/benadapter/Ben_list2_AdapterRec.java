package demo.com.demo01.core.adapter.benadapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.core.utils.AddSubLayout;
import demo.com.demo01.core.utils.ToDate;

/**
 * 作者: Wang on 2019/1/8 20:11
 * 寄语：加油！相信自己可以！！！
 */


public class Ben_list2_AdapterRec extends RecyclerView.Adapter<Ben_list2_AdapterRec.ViewHol> {
    private List<DingdanData> list;
    private FragmentActivity context;

    // Item  的适配器
    private Ben_list2_item_Adapter adapter;
    public CancelDan cancelDan;

    public void setCancelDan(CancelDan cancelDan) {
        this.cancelDan = cancelDan;
    }

    public interface CancelDan{
        void cancel(String orderId);
    }
    public Ben_list2_AdapterRec(FragmentActivity context) {
        list = new ArrayList<>();
        this.context = context;

        // Item中的RecyclerView的适配器

    }

    public void setList(List<DingdanData> list) {
        if (list != null) {
//            this.list.clear();
            this.list = list;
//            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public ViewHol onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.ben_lsit2_adapter, null);
        return new ViewHol(view);
    }

    @Override
    public void onBindViewHolder(ViewHol viewHol, final int i) {
        final DingdanData dingdanData = list.get(i);
        adapter = new Ben_list2_item_Adapter(context);
//        adapter.clear();
        // 向Item中的适配器中添加数据
        adapter.setListBeans(dingdanData.getDetailList());
        // 设置布局中的RecyclerView的适配器
        viewHol.rec.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        viewHol.date.setText("2019-01-08");
        viewHol.dingdanhao.setText("" + dingdanData.getOrderId());
        int p = dingdanData.getDetailList().size();// 一共几件
        Log.v("qqqq", dingdanData.getPayAmount() + "");
        Log.v("里面的数据条数", dingdanData.getDetailList().size() + "");
        viewHol.sum.setText("共" + p + "件商品,需支付" + p * dingdanData.getPayAmount() + "元");
        // 列表的管理器
        viewHol.rec.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        viewHol.cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDan.cancel(dingdanData.getOrderId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public class ViewHol extends RecyclerView.ViewHolder {
        private final TextView date, sum, dingdanhao;
        private final RecyclerView rec;
        Button cal, buy;

        public ViewHol(@NonNull View itemView) {
            super(itemView);
            dingdanhao = itemView.findViewById(R.id.list_02allorder_txt_dindan);
            date = itemView.findViewById(R.id.list_02allorder_txt_date);
            sum = itemView.findViewById(R.id.list_02allorder_txt_sum);
            cal = itemView.findViewById(R.id.list_02allorder_btn_quxiao);
            buy = itemView.findViewById(R.id.list_02allorder_btn_buy);

            // 列表
            rec = itemView.findViewById(R.id.list_02allorder_recy_item);
        }
    }
}
