package demo.com.demo01.core.bdan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.benadapter.Ben_list2_AdapterRec;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.presenter.TakeGoodsPresenter;
import demo.com.demo01.view.PayActivity;

public class Recycler_AllOrder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    public Recycler_AllOrder(Context context) {
        this.context = context;
    }
    private List<DingdanData> list = new ArrayList<>();
    public CancelDan cancelDan;

    public void setCancelDan( CancelDan cancelDan) {
        this.cancelDan = cancelDan;
    }
    public interface CancelDan{
        void cancel(String orderId);
        void take(String orderId);
    }
    //多条目加载
    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getOrderStatus() == 1) {
            //全部订单
            return 1;
        } else if (list.get(position).getOrderStatus() == 2) {
            //待付款
            return 2;
        } else if (list.get(position).getOrderStatus() == 3) {
            //待收货
            return 3;
        } else if (list.get(position).getOrderStatus() == 9) {
            //待评价
            return 4;
        }else {
            //待完成
            return 10;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                View inflate0 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waitpay, viewGroup, false);
                return new Vh_WaitPay(inflate0);
            case 1:
                View inflate1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waitpay, viewGroup, false);
                return new Vh_WaitPay(inflate1);
            case 2:
                View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waittake, viewGroup, false);
                return new Vh_WaitTake(inflate2);
            case 3:
                View inflate3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waittalk, viewGroup, false);
                return new Vh_WaitTalk(inflate3);
            case 4:
                View inflate4 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_finished, viewGroup, false);
                return new Vh_Finished(inflate4);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof Vh_WaitTake) {

            ((Vh_WaitTake) viewHolder).mTv_time_waitTake.setText("2019-01-08 12:55");
            ((Vh_WaitTake) viewHolder).mBt_toTake_waitTake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "确认收货", Toast.LENGTH_SHORT).show();
                    cancelDan.take(list.get(i).getOrderId());
                }
            });
            ((Vh_WaitTake) viewHolder).mTv_eamil_waitTake.setText("派件公司 "+list.get(i).getExpressCompName());
            ((Vh_WaitTake) viewHolder).mTv_orderNum_waitTake.setText(list.get(i).getOrderId());
//            Log.e("TAG", "onBindViewHolder: 订单ID==============="+list.get(i).getOrderId());
            ((Vh_WaitTake) viewHolder).mTv_eamilNum_waitTake.setText("快递单号 "+list.get(i).getExpressSn());
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Vh_WaitTake) viewHolder).mRv_waitTake.setLayoutManager(layoutManager);
            ChildAdapter_WaitTake childAdapter_waitTake = new ChildAdapter_WaitTake(context);
            List<DingdanData.DetailListBean> detailList = list.get(i).getDetailList();
            childAdapter_waitTake.addMsg(detailList);
            ((Vh_WaitTake) viewHolder).mRv_waitTake.setAdapter(childAdapter_waitTake);
        } else if (viewHolder instanceof Vh_WaitPay) {

            ((Vh_WaitPay) viewHolder).mTv_orderNum_waitPay.setText(list.get(i).getOrderId());
            ((Vh_WaitPay) viewHolder).mTv_num_waitPay.setText("共"+String.valueOf(list.get(i).getDetailList().size())+"件商品，需付款");
            String replace = String.valueOf(list.get(i).getPayAmount()).replace("2019011","");
            ((Vh_WaitPay) viewHolder).mTv_price_waitPay.setText(replace);
            ((Vh_WaitPay) viewHolder).mTv_time_waitPay.setText("2019-01-08");
            //Log.e("TAG", "onBindViewHolder: 订单ID==============="+list.get(i).getOrderId() );
            ((Vh_WaitPay) viewHolder).mBt_cleanOrDer_waitPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelDan.cancel(list.get(i).getOrderId());
//                    Toast.makeText(context, "取消订单", Toast.LENGTH_SHORT).show();
                }
            });
            ((Vh_WaitPay) viewHolder).mBt_toPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PayActivity.class);
                    intent.putExtra("orderId",list.get(i).getOrderId());
                    intent.putExtra("orderPrice",list.get(i).getPayAmount());
                    context.startActivity(intent);//
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Vh_WaitPay) viewHolder).mRv_waitPay.setLayoutManager(layoutManager);
            ChildAdapter_WaitPay childAdapter_waitPay = new ChildAdapter_WaitPay(context);
            List<DingdanData.DetailListBean> detailList = list.get(i).getDetailList();
            childAdapter_waitPay.addMsg(detailList);
            ((Vh_WaitPay) viewHolder).mRv_waitPay.setAdapter(childAdapter_waitPay);
        } else if (viewHolder instanceof Vh_WaitTalk) {
            ((Vh_WaitTalk) viewHolder).mTv_orderNum_waitTalk.setText(list.get(i).getOrderId());
            ((Vh_WaitTalk) viewHolder).mIv_more_waitTalk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多——去评论", Toast.LENGTH_SHORT).show();
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Vh_WaitTalk) viewHolder).mRv_waitTalk.setLayoutManager(layoutManager);
            ChildAdapter_WaitTalk childAdapter_waitTalk = new ChildAdapter_WaitTalk(context);
            List<DingdanData.DetailListBean> detailList = list.get(i).getDetailList();
            childAdapter_waitTalk.addMsg(detailList);
            ((Vh_WaitTalk) viewHolder).mRv_waitTalk.setAdapter(childAdapter_waitTalk);
        } else if (viewHolder instanceof Vh_Finished) {

            ((Vh_Finished) viewHolder).mTv_OrderNum_Finished.setText(list.get(i).getOrderId());
            ((Vh_Finished) viewHolder).mTv_Time_Finished.setText("2019-01-08 13:10");
            ((Vh_Finished) viewHolder).mIv_More_Finished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "更多——已完成", Toast.LENGTH_SHORT).show();
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Vh_Finished) viewHolder).mRv_Finished.setLayoutManager(layoutManager);
            ChildAdapter_Finished childAdapter_waitPay = new ChildAdapter_Finished(context);
            childAdapter_waitPay.addMsg(list);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<DingdanData> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
        }
    }
    public void unBing(){
        cancelDan=null;
    }

    //全部订单
    class Vh_AllOrder extends RecyclerView.ViewHolder {

//        private final TextView mTv_time_allOrder;
//        private final TextView mTv_orderNum_allOrder;
//        private final RecyclerView mRv_allorder;

        public Vh_AllOrder(@NonNull View itemView) {
            super(itemView);

//            mTv_time_allOrder = itemView.findViewById(R.id.mTv_Time_AllOrder);
//            mTv_orderNum_allOrder = itemView.findViewById(R.id.mTv_OrderNum_AllOrder);
//            mRv_allorder = itemView.findViewById(R.id.mRv_Allorder);

        }
    }

    //待收货
    class Vh_WaitTake extends RecyclerView.ViewHolder {

        private final TextView mTv_orderNum_waitTake;
        private final TextView mTv_time_waitTake;
        private final TextView mTv_eamil_waitTake;
        private final TextView mTv_eamilNum_waitTake;
        private final RecyclerView mRv_waitTake;
        private final Button mBt_toTake_waitTake;

        public Vh_WaitTake(@NonNull View itemView) {
            super(itemView);
            mTv_orderNum_waitTake = itemView.findViewById(R.id.mTv_OrderNum_WaitTake);
            mTv_time_waitTake = itemView.findViewById(R.id.mTv_Time_WaitTake);
            mTv_eamil_waitTake = itemView.findViewById(R.id.mTv_Eamil_WaitTake);
            mTv_eamilNum_waitTake = itemView.findViewById(R.id.mTv_EamilNum_WaitTake);
            mRv_waitTake = itemView.findViewById(R.id.mRv_WaitTake);
            mBt_toTake_waitTake = itemView.findViewById(R.id.mBt_ToTake_WaitTake);
        }
    }

    //待付款
    class Vh_WaitPay extends RecyclerView.ViewHolder {
        private final TextView mTv_orderNum_waitPay;
        private final TextView mTv_time_waitPay;
        private final RecyclerView mRv_waitPay;
        private final TextView mTv_num_waitPay;
        private final TextView mTv_price_waitPay;
        private final Button mBt_cleanOrDer_waitPay;
        private final Button mBt_toPay;
        public Vh_WaitPay(@NonNull View itemView) {
            super(itemView);
            mTv_orderNum_waitPay = itemView.findViewById(R.id.mTv_OrderNum_WaitPay);
            mTv_time_waitPay = itemView.findViewById(R.id.mTv_Time_WaitPay);
            mRv_waitPay = itemView.findViewById(R.id.mRv_WaitPay);
            mTv_num_waitPay = itemView.findViewById(R.id.mTv_Num_WaitPay);
            mTv_price_waitPay = itemView.findViewById(R.id.mTv_Price_WaitPay);
            mBt_cleanOrDer_waitPay = itemView.findViewById(R.id.mBt_CleanOrDer_WaitPay);
            mBt_toPay = itemView.findViewById(R.id.mBt_ToPay);
        }
    }

    //待评价
    class Vh_WaitTalk extends RecyclerView.ViewHolder {

        private final TextView mTv_orderNum_waitTalk;
        private final ImageView mIv_more_waitTalk;
//        private final TextView mTv_time_waitTalk;
        private final RecyclerView mRv_waitTalk;
        public Vh_WaitTalk(@NonNull View itemView) {
            super(itemView);
            mTv_orderNum_waitTalk = itemView.findViewById(R.id.mTv_OrderNum_WaitTalk);
            mIv_more_waitTalk = itemView.findViewById(R.id.mIv_More_WaitTalk);
//            mTv_time_waitTalk = itemView.findViewById(R.id.mTv_Time_WaitTalk);
            mRv_waitTalk = itemView.findViewById(R.id.mRv_WaitTalk);
        }
    }

    //已完成
    class Vh_Finished extends RecyclerView.ViewHolder {
        private final TextView mTv_OrderNum_Finished;
        private final ImageView mIv_More_Finished;
        private final TextView mTv_Time_Finished;
        private final RecyclerView mRv_Finished;
        public Vh_Finished(@NonNull View itemView) {
            super(itemView);
            mTv_OrderNum_Finished = itemView.findViewById(R.id.mTv_OrderNum_Finished);
            mIv_More_Finished = itemView.findViewById(R.id.mIv_More_Finished);
            mTv_Time_Finished = itemView.findViewById(R.id.mTv_Time_Finished);
            mRv_Finished = itemView.findViewById(R.id.mRv_Finished);
        }
    }
}
