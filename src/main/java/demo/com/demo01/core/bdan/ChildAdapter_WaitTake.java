package demo.com.demo01.core.bdan;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;

public class ChildAdapter_WaitTake extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    
    private Context mContext;

    public ChildAdapter_WaitTake(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<DingdanData.DetailListBean> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waittake_child, viewGroup, false);
        return new Vh_WaitTake_Child(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof Vh_WaitTake_Child) {
            DingdanData.DetailListBean detailListBean = list.get(i);
            String[] split = detailListBean.getCommodityPic().split(",");
            ((Vh_WaitTake_Child) viewHolder).mSDv_waitTake_child.setImageURI(Uri.parse(split[0]));
            ((Vh_WaitTake_Child) viewHolder).mTv_nAme_waitTake_child.setText(detailListBean.getCommodityName());
            ((Vh_WaitTake_Child) viewHolder).mTv_price_waitTake_child.setText("￥"+String.valueOf(detailListBean.getCommodityPrice())+"0");
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addMsg(List<DingdanData.DetailListBean> detailList) {
        if (detailList != null) {
            list.addAll(detailList);
        }
    }

    class Vh_WaitTake_Child extends RecyclerView.ViewHolder {


        private final SimpleDraweeView mSDv_waitTake_child;
        private final TextView mTv_nAme_waitTake_child;
        private final TextView mTv_price_waitTake_child;

        public Vh_WaitTake_Child(@NonNull View itemView) {
            super(itemView);

            mSDv_waitTake_child = itemView.findViewById(R.id.mSDv_WaitTake_Child);
            mTv_nAme_waitTake_child = itemView.findViewById(R.id.mTv_NAme_WaitTake_Child);
            mTv_price_waitTake_child = itemView.findViewById(R.id.mTv_Price_WaitTake_Child);


        }
    }

}
