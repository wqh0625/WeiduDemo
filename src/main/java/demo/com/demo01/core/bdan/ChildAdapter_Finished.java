package demo.com.demo01.core.bdan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;

public class ChildAdapter_Finished extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;

    public ChildAdapter_Finished(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<DingdanData> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_finished_child, viewGroup, false);
        return new Vh_WaitPay_Child(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof Vh_WaitPay_Child) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addMsg(List<DingdanData> detailList) {
        if (detailList != null) {
            list.addAll(detailList);
        }
    }

    class Vh_WaitPay_Child extends RecyclerView.ViewHolder {


        private final SimpleDraweeView mSDv_finished_child;
        private final TextView mTv_name_finished_child;
        private final TextView mTv_price_finished_child;

        public Vh_WaitPay_Child(@NonNull View itemView) {
            super(itemView);

            mSDv_finished_child = itemView.findViewById(R.id.mSDv_Finished_Child);
            mTv_name_finished_child = itemView.findViewById(R.id.mTv_Name_Finished_Child);
            mTv_price_finished_child = itemView.findViewById(R.id.mTv_Price_Finished_Child);


        }
    }

}
