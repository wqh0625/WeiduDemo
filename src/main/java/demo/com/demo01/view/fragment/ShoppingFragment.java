package demo.com.demo01.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.CarAdapter;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.QueryCarPresenter;

/**
 * 作者: Wang on 2018/12/30 11:36
 * 寄语：加油！相信自己可以！！！
 */


public class ShoppingFragment extends Fragment {
    private LoginDataDao dao;
    private List<LoginData> loginDatalist;
    public LoginData user;
    private QueryCarPresenter queryCarPresenter;
    private Unbinder bind;
    private List<QueryCarData> result = new ArrayList<>();

    private List<QueryCarData> addOrderList = new ArrayList<>();
    double allPrice = 0;
    @BindView(R.id.car_check_all)
    CheckBox carCheckAll;
    @BindView(R.id.car_swiperecycler)
    SwipeMenuRecyclerView mSwipeRecyclerView;
    private CarAdapter carAdapter;
    @BindView(R.id.car_tv_heji)
    TextView carTvHeji;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_03, container, false);
        bind = ButterKnife.bind(this, view);
        getUser();
        carAdapter = new CarAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mSwipeRecyclerView.setLayoutManager(linearLayoutManager);

        queryCarPresenter = new QueryCarPresenter(new queryCar());
        queryCarPresenter.requestNet((int) user.getUserId(), user.getSessionId());

        mSwipeRecyclerView.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem swipeMenuItem = new SwipeMenuItem(getContext());
                swipeMenuItem
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)//设置高，这里使用match_parent，就是与item的高相同
                        .setWidth(60)//设置宽
                        .setText("删除")
//                        .setHeight(200)
//                        .setTextColor(00000000)
                        .setBackground(R.drawable.pick);
                rightMenu.addMenuItem(swipeMenuItem);//设置右边的侧滑
//                swipeMenuItem.set
            }
        });
        mSwipeRecyclerView.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int position) {
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。0是左，右是1，暂时没有用到
                //int adapterPosition = menuBridge.get; // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                Toast.makeText(getContext(), "删除" + position, Toast.LENGTH_SHORT).show();

//                result.remove(position);
//                carAdapter.notifyDataSetChanged();
            }
        });
        mSwipeRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                //Toast.makeText(getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mSwipeRecyclerView.setAdapter(carAdapter);
        /**
         * carCheckAll点击全选
         */
        carCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carAdapter.checkAll(isChecked);
            }
        });
        //carShowAdapter.setTotalPriceListener(this);
        carAdapter.setTotalPriceListener(new CarAdapter.TotalPriceListener() {
            @Override
            public void totalPrice(double totalPrice,List<QueryCarData> list) {
                allPrice = totalPrice;
                addOrderList.addAll(list);
                carTvHeji.setText("合计：" + String.valueOf(totalPrice));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        queryCarPresenter.requestNet((int) user.getUserId(), user.getSessionId());
    }

    class queryCar implements DataCall<Result<List<QueryCarData>>> {

        @Override
        public void success(Result<List<QueryCarData>> data) {
            Log.v("ccccccccccc", data.getResult().size() + "");
            if (data.getStatus().equals("0000")) {
                carAdapter.clear();// 清空
                result = data.getResult();
                carAdapter.addAll(result);
                carAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(getContext(), "查询失败", Toast.LENGTH_SHORT).show();
        }
    }

    // 得到登录信息
    private void getUser() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();
        loginDatalist = dao.loadAll();
        for (int i = 0; i < loginDatalist.size(); i++) {
            if (loginDatalist.get(i).getStatus() == 1) {
                user = loginDatalist.get(i);
                Log.v("登录数据--------======", user.toString());
                return;
            }
        }
    }

    @OnClick(R.id.car_tv_qujiesuan)
    void on() {
        Toast.makeText(getActivity(), ""+addOrderList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        queryCarPresenter.unBind();
    }
}
