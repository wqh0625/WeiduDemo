package demo.com.demo01.core.bdan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.view.CommentActivity;

public class ChildAdapter_WaitTalk extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;

    public ChildAdapter_WaitTalk(Context mContext) {
        this.mContext = mContext;
    }

    private ArrayList<DingdanData.DetailListBean> list = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waittalk_child, viewGroup, false);
        return new Vh_WaitTalk_Child(inflate1);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof Vh_WaitTalk_Child) {
            DingdanData.DetailListBean detailListBean = list.get(i) ;
            ((Vh_WaitTalk_Child) viewHolder).mBt_toTalk_child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,CommentActivity.class));
                }
            });
            String[] split = detailListBean.getCommodityPic().split(",");
            ((Vh_WaitTalk_Child) viewHolder).mSDv_waitTalk_child.setImageURI(Uri.parse(split[0]));
            ((Vh_WaitTalk_Child) viewHolder).mTv_name_waitTalk_child.setText(detailListBean.getCommodityName());
            ((Vh_WaitTalk_Child) viewHolder).mTv_price_waitTalk_child.setText(String.valueOf("¥ "+detailListBean.getCommodityPrice())+"0");

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

    class Vh_WaitTalk_Child extends RecyclerView.ViewHolder {


        private final SimpleDraweeView mSDv_waitTalk_child;
        private final TextView mTv_name_waitTalk_child;
        private final TextView mTv_price_waitTalk_child;
        private final Button mBt_toTalk_child;

        public Vh_WaitTalk_Child(@NonNull View itemView) {
            super(itemView);


            mSDv_waitTalk_child = itemView.findViewById(R.id.mSDv_WaitTalk_Child);
            mTv_name_waitTalk_child = itemView.findViewById(R.id.mTv_Name_WaitTalk_Child);
            mTv_price_waitTalk_child = itemView.findViewById(R.id.mTv_Price_WaitTalk_Child);
            mBt_toTalk_child = itemView.findViewById(R.id.mBt_ToTalk_Child);

        }
    }

}
