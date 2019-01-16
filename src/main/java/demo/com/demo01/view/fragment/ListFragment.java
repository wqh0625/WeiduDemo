package demo.com.demo01.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.bdan.Recycler_AllOrder;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.DanCancelPresenter;
import demo.com.demo01.presenter.DingdanPresenter;
import demo.com.demo01.presenter.TakeGoodsPresenter;
import demo.com.demo01.view.list_fragment.List_Fragment_02;


/**
 * 作者: Wang on 2018/12/30 11:36
 * 寄语：加油！相信自己可以！！！
 */


public class ListFragment extends Fragment implements Recycler_AllOrder.CancelDan {
    private LoginDataDao dao;
    private List<LoginData> loginDatalist;

    public LoginData user;
    private Unbinder bind;
    private DingdanPresenter dingdanPresenter;
    private Recycler_AllOrder recycler_allOrder;
    private RecyclerView rec;
    private DanCancelPresenter danCancelPresenter;
    private TakeGoodsPresenter takeGoodsPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_04, container, false);
        bind = ButterKnife.bind(this, view);
        rec = view.findViewById(R.id.list_fragment);
        getUser();
        // 管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(linearLayoutManager);

        takeGoodsPresenter = new TakeGoodsPresenter(new take());

        dingdanPresenter = new DingdanPresenter(new getDan());
        // 默认查询全部订单
        dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 0, 1, 15);
        danCancelPresenter = new DanCancelPresenter(new canDan());
        recycler_allOrder = new Recycler_AllOrder(getContext());
        recycler_allOrder.setCancelDan(this);
        rec.setAdapter(recycler_allOrder);
        return view;
    }

    @OnClick({R.id.rB1, R.id.rB2, R.id.rB3, R.id.rB4, R.id.rB5})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.rB1:
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 0, 1, 15);
                break;
            case R.id.rB2:
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 1, 1, 15);
                break;
            case R.id.rB3:
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 2, 1, 15);
                break;
            case R.id.rB4:
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 3, 1, 15);
                break;
            case R.id.rB5:
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 4, 1, 15);
                break;
        }
    }


    @Override
    public void take(String orderId) {
        // 收货
        takeGoodsPresenter.requestNet((int) user.getUserId(), user.getSessionId(), orderId);
    }

    class take implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getActivity(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    @Override
    public void cancel(String orderId) {
        // 取消订单
        danCancelPresenter.requestNet((int) user.getUserId(), user.getSessionId(), orderId);
    }

    // 取消订单
    class canDan implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getContext(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 1, 1, 15);
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    // 得到订单数据
    class getDan implements DataCall<Result<List<DingdanData>>> {
        @Override
        public void success(Result<List<DingdanData>> data) {
            if (data.getStatus().equals("0000")) {
                recycler_allOrder.addData(data.getOrderList());
                recycler_allOrder.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        danCancelPresenter.unBind();
        dingdanPresenter.unBind();
        takeGoodsPresenter.unBind();
        recycler_allOrder.unBing();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 0, 1, 15);
    }

    private void getUser() {
        // 得到登录的信息
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();
        loginDatalist = dao.loadAll();
        for (int i = 0; i < loginDatalist.size(); i++) {
            if (loginDatalist.get(i).getStatus() == 1) {
                user = loginDatalist.get(i);
                return;
            }
        }
    }

    void yi() {
        //    private List_Fragment_01 list_fragment_01;
//    private List_Fragment_02 list_fragment_02;
//    private List_Fragment_03 list_fragment_03;
//    private List_Fragment_04 list_fragment_04;
//    private List_Fragment_05 list_fragment_05;

        //    RadioGroup radio;
//    private FragmentManager manager;
        //        list_fragment_01 = new List_Fragment_01();
//        list_fragment_02 = new List_Fragment_02();
//        list_fragment_03 = new List_Fragment_03();
//        list_fragment_04 = new List_Fragment_04();
//        list_fragment_05 = new List_Fragment_05();

//        radio = view.findViewById(R.id.list_list);


//        manager = getChildFragmentManager();
//        FragmentTransaction beginTransaction = manager.beginTransaction();
//        beginTransaction.add(R.id.list_fragment,list_fragment_01).show(list_fragment_01);
//        beginTransaction.add(R.id.list_fragment,list_fragment_02).hide(list_fragment_02);
//        beginTransaction.add(R.id.list_fragment,list_fragment_03).hide(list_fragment_03);
//        beginTransaction.add(R.id.list_fragment,list_fragment_04).hide(list_fragment_04);
//        beginTransaction.add(R.id.list_fragment,list_fragment_05).hide(list_fragment_05);
//        beginTransaction.commit();
//        radio.check(radio.getChildAt(0).getId());
//        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                FragmentTransaction transaction = manager.beginTransaction();
//                switch (checkedId) {
//                    case 6:
//                        transaction.show(list_fragment_01).hide(list_fragment_02).hide(list_fragment_03).hide(list_fragment_04).hide(list_fragment_05).commit();
//                        break;
//                    case 7:
//                        transaction.show(list_fragment_02).hide(list_fragment_01).hide(list_fragment_01).hide(list_fragment_04).hide(list_fragment_05).commit();
//                        break;
//                    case 8:
//                        transaction.show(list_fragment_03).hide(list_fragment_02).hide(list_fragment_01).hide(list_fragment_04).hide(list_fragment_05).commit();
//                        break;
//                    case 9:
//                        transaction.show(list_fragment_04).hide(list_fragment_02).hide(list_fragment_03).hide(list_fragment_01).hide(list_fragment_05).commit();
//                        break;
//                    case 10:
//                        transaction.show(list_fragment_05).hide(list_fragment_02).hide(list_fragment_03).hide(list_fragment_04).hide(list_fragment_01).commit();
//                        break;
//                }
//            }
//        });
    }

}
