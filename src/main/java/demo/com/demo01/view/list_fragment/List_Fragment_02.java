package demo.com.demo01.view.list_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.benadapter.Ben_list2_AdapterRec;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.DanCancelPresenter;
import demo.com.demo01.presenter.DingdanPresenter;

/**
 * 作者: Wang on 2019/1/5 11:11
 * 寄语：加油！相信自己可以！！！
 */

public class List_Fragment_02 extends Fragment implements Ben_list2_AdapterRec.CancelDan {
    private LoginDataDao dao;
    private List<LoginData> loginDatalist;

    public LoginData user;
    private Unbinder unbinder;

//    @BindView(R.id.allorder_txt_dindan)
//    TextView dingdanhao;// 订单号
//    @BindView(R.id.allorder_txt_date)
//    TextView allorder_txt_date;// 创建日期
    @BindView(R.id.list_frag_02_rec)
    RecyclerView recy;// 列表
//    @BindView(R.id.allorder_txt_sum)
//    TextView subText;// 数量

    private DingdanPresenter dingdanPresenter;
    private Ben_list2_AdapterRec adapterRec;
    private DanCancelPresenter danCancelPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_02_buju, container, false);
        unbinder = ButterKnife.bind(this, view);
        getUser();// 得到登录数据
        danCancelPresenter = new DanCancelPresenter(new canDan());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
        // 适配器
        adapterRec = new Ben_list2_AdapterRec(getActivity());
        adapterRec.setCancelDan(this);
        // 设置适配器
        recy.setAdapter(adapterRec);
        // p 层
        dingdanPresenter = new DingdanPresenter(new danGet());
        // 调用p层
//        dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 1, 1, 15);

        // 取消订单
        return view;
    }

    @Override
    public void cancel(String orderId) {
//        danCancelPresenter.requestNet((int)user.getUserId(),user.getSessionId(),orderId);
    }
    // 删除订单
    class canDan implements DataCall<Result>{
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000") ) {
                Toast.makeText(getContext(), ""+data.getMessage(), Toast.LENGTH_SHORT).show();
                dingdanPresenter.requestNet((int) user.getUserId(), user.getSessionId(), 1, 1, 15);
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    //
    class danGet implements DataCall<Result<List<DingdanData>>> {
        @Override
        public void success(Result<List<DingdanData>> data) {
            if (data.getStatus().equals("0000")) {
      Toast.makeText(getContext(), "" + data.getOrderList().size(), Toast.LENGTH_SHORT).show();
//                adapterRec.clear();
                adapterRec.setList(data.getOrderList());
                adapterRec.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
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
        unbinder.unbind();
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
}
