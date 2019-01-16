package demo.com.demo01.core.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.MoneyData;
import demo.com.demo01.core.utils.ToDate;

/**
 * 作者: Wang on 2019/1/13 20:40
 * 寄语：加油！相信自己可以！！！
 */


public class MyMoneyAdapter extends RecyclerView.Adapter<MyMoneyAdapter.Vh> {

    private Context context;
    private List<MoneyData.DetailListBean> detailListBeans;

    public MyMoneyAdapter(Context context) {
        this.context = context;
        this.detailListBeans = new ArrayList<>();
    }

    public void setData(List<MoneyData.DetailListBean> detailListBeans) {
        if (detailListBeans != null) {
            this.detailListBeans.addAll(detailListBeans);
        }
    }

    public void clear(){
        detailListBeans.clear();
    }
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.money_list_item, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int i) {
        vh.text.setText("¥" + detailListBeans.get(i).getAmount() + ".00");
        vh.data.setText(ToDate.timedate(detailListBeans.get(i).getCreateTime()) + "    >");
    }

    @Override
    public int getItemCount() {
        return detailListBeans.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        private final TextView text, data;

        public Vh(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.money_text);
            data = itemView.findViewById(R.id.money_data);

        }
    }
}
