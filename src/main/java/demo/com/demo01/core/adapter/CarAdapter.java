package demo.com.demo01.core.adapter;




import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.core.utils.AddSubLayout;

/**
 * 作者: Wang on 2019/1/8 14:19
 * 寄语：加油！相信自己可以！！！
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.Myholder> {
    Context context;
    List<QueryCarData> mList = new ArrayList<>();

    List<QueryCarData> huidiao= new ArrayList<>();
    public void clear() {
        this.mList .clear();
    }

    //计算总价
    private TotalPriceListener totalPriceListener;

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.car_item_adapter, null);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarAdapter.Myholder myholder, final int i) {
        final QueryCarData queryShopCar = mList.get(i);
        String commodityName = queryShopCar.getCommodityName();
        int count = queryShopCar.getCount();
        String pic = queryShopCar.getPic();
        double price = queryShopCar.getPrice();
        myholder.image.setImageURI(pic);
        myholder.textPrice.setText("￥" + price);
        myholder.text.setText(commodityName);
        //添加数量
        myholder.addSubLayout.setCount(queryShopCar.getCount());
        //点击设置数量刷新价格
        myholder.addSubLayout.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                queryShopCar.setCount(count);
                calculatePrice();
            }
        });
        //
        if (myholder.cartGoodsCheck.isChecked()) {
            huidiao.add(mList.get(i));
        }
        myholder.cartGoodsCheck.setChecked(queryShopCar.isIscheck());
        //设置点击商品列表cb价格回调
        myholder.cartGoodsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                queryShopCar.setIscheck(isChecked);
                calculatePrice();
            }
        });

        calculatePrice();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<QueryCarData> result) {
        if (result != null) {
            mList.addAll(result);
        }

    }
    //全部选中
    public void checkAll(boolean isCheck) {
        for (int i = 0; i < mList.size(); i++) {//循环的商家
            QueryCarData queryShopCar = mList.get(i);
            queryShopCar.setIscheck(isCheck);
        }
        notifyDataSetChanged();
        //刷新价格
        calculatePrice();
    }

    //价格计算
    private void calculatePrice() {
        double totalPrice = 0;
        for (int i = 0; i < mList.size(); i++) {//循环的商家
            QueryCarData queryShopCar = mList.get(i);
            boolean ischeck = queryShopCar.isIscheck();
            if (ischeck) {
                totalPrice = totalPrice + queryShopCar.getPrice() * queryShopCar.getCount();
//                huidiao.add(mList.get(i));
            }

        }
        if (totalPriceListener != null)
            totalPriceListener.totalPrice(totalPrice,huidiao);
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private CheckBox cartGoodsCheck;
        private SimpleDraweeView image;
        private TextView text;
        private TextView textPrice;
        private AddSubLayout addSubLayout;


        public Myholder(@NonNull View itemView) {
            super(itemView);
            cartGoodsCheck = itemView.findViewById(R.id.cart_goods_check);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            textPrice = itemView.findViewById(R.id.text_price);
            addSubLayout = itemView.findViewById(R.id.add_sub_layout);
        }
    }

    //接口回调给Activity页面显示价格刷新
    public interface TotalPriceListener {
        void totalPrice(double totalPrice,List<QueryCarData> list);
    }
}

