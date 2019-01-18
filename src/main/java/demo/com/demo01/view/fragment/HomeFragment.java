package demo.com.demo01.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.BannerData;
import demo.com.demo01.bean.HomeListData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.adapter.Home_RecyclerViewAdapter1;
import demo.com.demo01.core.adapter.Home_RecyclerViewAdapter2;
import demo.com.demo01.core.adapter.Home_RecyclerViewAdapter3;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.presenter.BannerPresenter;
import demo.com.demo01.presenter.HomeListPresenter;
import demo.com.demo01.view.SearchActivity;

/**
 * 作者: Wang on 2018/12/30 11:29
 * 寄语：加油！相信自己可以！！！
 */


public class HomeFragment extends Fragment {
    @BindView(R.id.home_banner)
    MZBannerView mMZBanner;

    private BannerPresenter bannerPresenter;
    List<String> list = new ArrayList<>();

    @BindView(R.id.home_recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.home_recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.home_recyclerView3)
    RecyclerView recyclerView3;

    private Home_RecyclerViewAdapter1 recyclerViewAdapter1;
    private HomeListPresenter homeListPresenter;
    private Home_RecyclerViewAdapter2 home_recyclerViewAdapter2;
    private Home_RecyclerViewAdapter3 home_recyclerViewAdapter3;
    private Unbinder bind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
        Fresco.initialize(getContext());
        bind = ButterKnife.bind(this, view);

        bannerPresenter = new BannerPresenter(new GetBanner());
//        mMZBanner.setImageLoader(new BannerImage());
        // 请求Banner的数据
        bannerPresenter.requestNet();
        // 第一个列表的Adapter
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.setNestedScrollingEnabled(false);//禁止滑动
        recyclerViewAdapter1 = new Home_RecyclerViewAdapter1(getContext());
        recyclerView1.setAdapter(recyclerViewAdapter1);

        homeListPresenter = new HomeListPresenter(new getList());

        // 第二个列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        home_recyclerViewAdapter2 = new Home_RecyclerViewAdapter2(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.setAdapter(home_recyclerViewAdapter2);

        // 第三个列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        home_recyclerViewAdapter3 = new Home_RecyclerViewAdapter3(getContext());
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView3.setLayoutManager(gridLayoutManager);
        recyclerView3.setAdapter(home_recyclerViewAdapter3);
        homeListPresenter.requestNet();
        return view;
    }

    @OnClick({R.id.home_textView_start, R.id.home_search_image})
    void startNewActivitySearch() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

    class getList implements DataCall<Result> {
        @Override
        public void success(Result data) {
            HomeListData result1 = (HomeListData) data.getResult();

            List<HomeListData.RxxpBean.CommodityListBean> rexiao = new ArrayList<>();
            for (int i = 0; i < result1.getRxxp().size(); i++) {
                List<HomeListData.RxxpBean.CommodityListBean> commodityList = result1.getRxxp().get(i).getCommodityList();
                rexiao.addAll(commodityList);
            }
            recyclerViewAdapter1.setList(rexiao);
            recyclerViewAdapter1.notifyDataSetChanged();

            HomeListData result2 = (HomeListData) data.getResult();
            List<HomeListData.MlssBean.CommodityListBeanXX> ml = new ArrayList<>();
            for (int i = 0; i < result2.getMlss().size(); i++) {
                ml.addAll(result2.getMlss().get(i).getCommodityList());
            }
            home_recyclerViewAdapter2.setList(ml);
            home_recyclerViewAdapter2.notifyDataSetChanged();
            HomeListData result3 = (HomeListData) data.getResult();
            List<HomeListData.PzshBean.CommodityListBeanX> pz = new ArrayList<>();
            for (int i = 0; i < result3.getPzsh().size(); i++) {
                pz.addAll(result3.getPzsh().get(i).getCommodityList());
            }
            home_recyclerViewAdapter3.setList(pz);
            home_recyclerViewAdapter3.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(getContext(), "lIST失败" + a.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    class GetBanner implements DataCall<Result<List<BannerData>>> {
        @Override
        public void success(Result<List<BannerData>> data) {
            if (data.getStatus().equals("0000")) {
                List<BannerData> bannerData = data.getResult();
                for (int i = 0; i < bannerData.size(); i++) {
                    list.add(bannerData.get(i).getImageUrl());
                }
                mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
//                mMZBanner.start();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(getContext(), "banner失败" + a.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // 轮播图
    public static class BannerViewHolder implements MZViewHolder<String> {
        private SimpleDraweeView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            mImageView.setImageURI(Uri.parse(s));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
//        mMZBanner.pause();
        bannerPresenter.unBind();
        homeListPresenter.unBind();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.pause();//暂停轮播
    }

}
