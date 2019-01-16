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
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;

public class ChildAdapter_WaitPay extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;

    public ChildAdapter_WaitPay(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<DingdanData.DetailListBean> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waitpay_child, viewGroup, false);
        return new Vh_WaitPay_Child(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof Vh_WaitPay_Child){
            DingdanData.DetailListBean detailListBean = list.get(i);
            ((Vh_WaitPay_Child) viewHolder).mTv_Price_WaitPayChild.setText("￥"+String.valueOf(detailListBean.getCommodityPrice())+"0");
            String[] split = detailListBean.getCommodityPic().split(",");

            ((Vh_WaitPay_Child) viewHolder).mSDv_WaitPayChild.setImageURI(Uri.parse(split[0]));
            ((Vh_WaitPay_Child) viewHolder).mTv_Name_WaitPayChild.setText(detailListBean.getCommodityName());
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

    class Vh_WaitPay_Child extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mSDv_WaitPayChild;
        private final TextView mTv_Name_WaitPayChild;
        private final TextView mTv_Price_WaitPayChild;
//        private final Button mBt_Red_WaitPayChild;
//        private final Button mBt_Add_WaitPayChild;
//        private final EditText mEt_Num_WaitPayChild;

        public Vh_WaitPay_Child(@NonNull View itemView) {
            super(itemView);

            mSDv_WaitPayChild = itemView.findViewById(R.id.mSDv_WaitPayChild);
            mTv_Name_WaitPayChild = itemView.findViewById(R.id.mTv_Name_WaitPayChild);
            mTv_Price_WaitPayChild = itemView.findViewById(R.id.mTv_Price_WaitPayChild);
//            mBt_Red_WaitPayChild = itemView.findViewById(R.id.mBt_Red_WaitPayChild);
//            mBt_Add_WaitPayChild = itemView.findViewById(R.id.mBt_Add_WaitPayChild);
//            mEt_Num_WaitPayChild = itemView.findViewById(R.id.mEt_Num_WaitPayChild);

        }
    }

}
