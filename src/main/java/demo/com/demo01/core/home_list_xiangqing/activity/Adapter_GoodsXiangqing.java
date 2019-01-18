package demo.com.demo01.core.home_list_xiangqing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.AddDataToCar;
import demo.com.demo01.bean.CommentList;
import demo.com.demo01.bean.CreateOrderData;
import demo.com.demo01.bean.GoodsByIdData;
import demo.com.demo01.bean.MyAddressData;
import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.CommentListAdapter;
import demo.com.demo01.core.adapter.ViewPageDetailsBannerAdapter;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.presenter.AddCarPresenter;
import demo.com.demo01.presenter.CommentListPresenter;
import demo.com.demo01.presenter.CreateOrderPresenter;
import demo.com.demo01.presenter.GetMyAddressListPresenter;
import demo.com.demo01.presenter.QueryCarPresenter;
import demo.com.demo01.presenter.ShowGoodsByIdPresenter;
import demo.com.demo01.view.HomeActivity;
import demo.com.demo01.view.SearchActivity;

/**
 * 作者: Wang on 2019/1/1 14:53
 * 寄语：加油！相信自己可以！！！
 */

public class Adapter_GoodsXiangqing extends BaseActivity {

    private ShowGoodsByIdPresenter presenter;

    @BindView(R.id.details_textview_sold)
    TextView soldNum;
    @BindView(R.id.details_textview_title)
    TextView title;
    @BindView(R.id.details_textview_sprice)
    TextView price;
    @BindView(R.id.details_textview_Weight)
    TextView weight;
    @BindView(R.id.details_viewpager_show)
    ViewPager viewPager;
    @BindView(R.id.details_scroll_changecolor)
    IdeaScrollView details_scroll_changecolor;
    @BindView(R.id.details_relative_changer)
    RelativeLayout details_relative_changer;
    @BindView(R.id.details_relat_changecolor)
    RelativeLayout details_relat_changecolor;
    @BindView(R.id.details_text_goods)
    TextView details_text_goods;
    @BindView(R.id.details_text_details)
    TextView details_text_details;
    @BindView(R.id.details_text_comments)
    TextView details_text_comments;
    @BindView(R.id.details_textview_describe)
    TextView details_textview_describe;
    @BindView(R.id.details_Image_details)
    SimpleDraweeView details_Image_details;
    @BindView(R.id.details_Image_describe)
    SimpleDraweeView details_Image_describe;
    @BindView(R.id.details_textview_comments)
    TextView details_textview_comments;
    @BindView(R.id.details_recview_comments)
    RecyclerView details_recview_comments;
    private CommentListPresenter commentListPresenter;
    private CommentListAdapter commentListAdapter;
    private String id;
    private AddCarPresenter addCarPresenter;
    private QueryCarPresenter queryCarPresenter;
    private List<AddDataToCar> list;
    private  List<MyAddressData> lista;
    private String w;
    private CreateOrderPresenter createOrderPresenter;
    private List<CreateOrderData> createDan;
    private GetMyAddressListPresenter getMyAddressListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter1_onclick_goodsbyid);
        Fresco.initialize(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        w = intent.getStringExtra("w");
//        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        list = new ArrayList<>();
        lista = new ArrayList<>();
        getMyAddressListPresenter = new GetMyAddressListPresenter(new MyList());
        getMyAddressListPresenter.requestNet((int) user.getUserId(), user.getSessionId());

        queryCat();
        // 创建订单
        createOrderPresenter = new CreateOrderPresenter(new createOrder());
        // 实例化p层
        presenter = new ShowGoodsByIdPresenter(new GoodsP());
//        int integer = Integer.parseInt(id);
        presenter.requestNet((int) user.getUserId(), user.getSessionId(), id);

        details_scroll_changecolor.setScrollviewListener(new IdeaScrollView.ScrollviewListener() {
            @Override
            public void OnScrollChange(IdeaScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    //标题显示
                    details_relative_changer.setVisibility(View.GONE);
                    //设置标题所在的背景颜色为透明
                    details_relat_changecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    details_relative_changer.setVisibility(View.VISIBLE);
                    //获取ScrollView想下滑动,图片消失部分的比例
                    float scale = (float) t / 200;
                    //根据比例,让标题的颜色由浅入深
                    float alpha = 0 * scale;
                    //设置标题布局的颜色
                    details_relat_changecolor.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
                    //*************************************
                }
                if (0 < t && t < 700) {
                    details_text_goods.setBackgroundColor(Color.RED);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.RED);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.RED);
                }
            }

        });

        commentListPresenter = new CommentListPresenter(new CommmList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        details_recview_comments.setLayoutManager(linearLayoutManager);
        commentListAdapter = new CommentListAdapter(this);
        details_recview_comments.setAdapter(commentListAdapter);
        createDan = new ArrayList<>();
        // 添加购物车
        addCarPresenter = new AddCarPresenter(new addCar());

        commentListPresenter.requestNet(id, 1, 5);
    }

    private void queryCat() {
        queryCarPresenter = new QueryCarPresenter(new quertCarDataaa());
        queryCarPresenter.requestNet((int) user.getUserId(), user.getSessionId());
    }

    class quertCarDataaa implements DataCall<Result<List<QueryCarData>>> {
        @Override
        public void success(Result<List<QueryCarData>> data) {
            if (data.getStatus().equals("0000")) {
                for (int i = 0; i < data.getResult().size(); i++) {
                    AddDataToCar addDataToCar = new AddDataToCar();
                    addDataToCar.setCommodityId(data.getResult().get(i).getCommodityId());
                    addDataToCar.setCount(data.getResult().get(i).getCount());
                    list.add(addDataToCar);
                }
            }
        }

        @Override
        public void fail(ApiException a) {
        }
    }

    private double prices;

    class GoodsP implements DataCall<Result<GoodsByIdData>> {
        private ViewPageDetailsBannerAdapter viewPageDetailsBannerAdapter;
        private int count;

        @Override
        public void success(Result<GoodsByIdData> data) {
            // 向适配器添加数据
            GoodsByIdData result = data.getResult();
            String trim = result.getPicture().trim();
            int weights = result.getWeight();//重量
            String categoryName = result.getCategoryName();//类别名称
            String commodityName = result.getCommodityName();//商品名称
            String describe = result.getDescribe();//描述
            String details = result.getDetails();//商品详情html
            int commentNum = result.getCommentNum();//数量
            //价格
            prices = result.getPrice();
            soldNum.setText(commentNum + "");
            title.setText(commodityName);
            details_textview_describe.setText(describe);
            weight.setText(weights + "");
            price.setText("¥ " + prices + "");
            String[] split = trim.split(",");
            details_Image_details.setImageURI(split[0]);
            details_Image_describe.setImageURI(split[1]);
            viewPageDetailsBannerAdapter = new ViewPageDetailsBannerAdapter(Adapter_GoodsXiangqing.this, split);
            count = viewPageDetailsBannerAdapter.getCount();
            viewPager.setAdapter(viewPageDetailsBannerAdapter);
        }

        @Override
        public void fail(ApiException a) {

        }

    }

    @OnClick(R.id.details_image_return)
    void back() {
          finish();
    }

    //评论列表
    class CommmList implements DataCall<Result<List<CommentList>>> {
        @Override
        public void success(Result<List<CommentList>> data) {
            List<CommentList> result = data.getResult();
            if (result.size() == 0) {
                details_textview_comments.setVisibility(View.VISIBLE);
                return;
            } else {
                details_textview_comments.setVisibility(View.GONE);
            }
            commentListAdapter.setDataList(result);
            commentListAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(Adapter_GoodsXiangqing.this, "失败了", Toast.LENGTH_SHORT).show();
        }
    }


    // 添加购物车
    @OnClick(R.id.btn_add_cart)
    void addToCar() {
//        Toast.makeText(this, "添加"+id, Toast.LENGTH_SHORT).show();
        AddDataToCar addDataToCar = new AddDataToCar();
        addDataToCar.setCommodityId(Integer.valueOf(id));
        addDataToCar.setCount(1);
        list.add(addDataToCar);
        String s = new Gson().toJson(list);
//        Log.v("addToCar-----", s);

        addCarPresenter.requestNet((int) user.getUserId(), user.getSessionId(), s);
    }


    class addCar implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(Adapter_GoodsXiangqing.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    // 创建订单
    @OnClick(R.id.btn_add_buy)
    void addBuy() {
         int idddddd = 0;

        // 调用添加订单接口
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getWhetherDefault() == 1) {
                idddddd = lista.get(i).getId();
//                Toast.makeText(this, "sss"+idddddd, Toast.LENGTH_SHORT).show();
//                Log.v("----默认", idddddd + "");
            }
        }
        CreateOrderData createOrderData = new CreateOrderData();
        createOrderData.setAmount(1);
        Integer integer = Integer.valueOf(id);
        createOrderData.setCommodityId(integer);
        createDan.add(createOrderData);
        String toJson = new Gson().toJson(createDan);
//        Log.v("buy",toJson+idddddd);
        createOrderPresenter.requestNet((int) user.getUserId(), user.getSessionId(), toJson, prices, idddddd);
    }

    class createOrder implements DataCall<Result> {
        @Override
        public void success(Result data) {
            Toast.makeText(Adapter_GoodsXiangqing.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            if (data.getStatus().equals("0000")) {
                Toast.makeText(Adapter_GoodsXiangqing.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(Adapter_GoodsXiangqing.this, "创建失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unBind();// 防治内存泄露
        commentListPresenter.unBind();
        createOrderPresenter.unBind();
    }



    class MyList implements DataCall<Result<List<MyAddressData>>> {
        @Override
        public void success(Result<List<MyAddressData>> data) {
//            Toast.makeText(ShowMyAddressActivity.this, "" + data.getResult().size(), Toast.LENGTH_SHORT).show();
            if (data.getStatus().equals("0000")) {
                for (int i = 0; i < data.getResult().size(); i++) {
                    Log.v("收货地址-----", "" + data.getResult().get(i).toString());
                }
                lista.addAll(data.getResult());
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(Adapter_GoodsXiangqing.this, "" + a.getCode() + a.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
