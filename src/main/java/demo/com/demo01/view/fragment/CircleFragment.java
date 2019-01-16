package demo.com.demo01.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.CircleData;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.adapter.Circle_RecyclerViewAdapter;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.CicrlePresenter;
import demo.com.demo01.presenter.CircleCancelPresenter;
import demo.com.demo01.presenter.CircleDianZanPresenter;
import demo.com.demo01.view.AddMyCircleActivity;

/**
 * 作者: Wang on 2018/12/30 11:30
 * 寄语：加油！相信自己可以！！！
 */


public class CircleFragment extends Fragment implements Circle_RecyclerViewAdapter.DZInter, XRecyclerView.LoadingListener {
    XRecyclerView recyclerView;
    private Unbinder bind;
    private CicrlePresenter presenter;
    private Circle_RecyclerViewAdapter adapter;
    private LoginData loginData;
    private CircleDianZanPresenter circleDianZanPresenter;
    private int i;
    private CircleCancelPresenter circleCancelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02, container, false);
        Fresco.initialize(getContext());
        bind = ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.quanzi_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // p层
        presenter = new CicrlePresenter(new cireleCall());
        circleDianZanPresenter = new CircleDianZanPresenter(new DZCall());
        adapter = new Circle_RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginDataDao.TABLENAME);
        LoginDataDao loginDataDao = daoSession.getLoginDataDao();
        List<LoginData> list = loginDataDao.loadAll();
        for (int j = 0; j < list.size(); j++) {
            loginData = list.get(j);
        }

        circleCancelPresenter = new CircleCancelPresenter(new canaelDz());
        /// 点赞
        adapter.setDzInter(this);
        recyclerView.setLoadingMoreEnabled(true);// 下拉加载更多
        recyclerView.setLoadingListener(this);
        recyclerView.refresh();
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @OnClick(R.id.toaddCircle)
    void add() {
        getActivity().startActivity(new Intent(getActivity(), AddMyCircleActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        // 页面返回 重新查询
        recyclerView.refresh();
    }

    private List<CircleData> result;

    // 取消点赞
    class canaelDz implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getContext(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                CircleData o = (CircleData) data.getArgs()[3];
                o.setGreatNum(o.getGreatNum() - 1);
                o.setWhetherGreat(2);
                adapter.notifyItemChanged(i + 1);//这个方法返回的并不是adapter所有item的数量，而是当前显示的item个数   所以必须下标+1
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }


    // 点赞
    @Override
    public void onclick(int ai, CircleData circleData) {
        i = ai;
//        Toast.makeText(getContext(), ""+result.size(), Toast.LENGTH_SHORT).show();
//        CircleData circleData = result.get(ai);
        if (circleData.getWhetherGreat() == 1) {
            // 调用取消点赞的接口
            circleCancelPresenter.requestNet((int) loginData.getUserId(), loginData.getSessionId(), circleData.getId(), circleData);
            return;
        }
        // 调用点赞的接口
        circleDianZanPresenter.requestNet((int) loginData.getUserId(), loginData.getSessionId(), circleData.getId(), circleData);
    }

    @Override
    public void onRefresh() {
        if (presenter.isRuning()) {
            recyclerView.refreshComplete();
            recyclerView.loadMoreComplete();
            return;
        }
        recyclerView.refreshComplete();
        presenter.requestNet((int) loginData.getUserId(), loginData.getSessionId(), true);
    }

    @Override
    public void onLoadMore() {
        if (presenter.isRuning()) {
            recyclerView.refreshComplete();
            recyclerView.loadMoreComplete();
            return;
        }
        recyclerView.loadMoreComplete();
        presenter.requestNet((int) loginData.getUserId(), loginData.getSessionId(), false);
    }

    class cireleCall implements DataCall<Result<List<CircleData>>> {

        @Override
        public void success(Result<List<CircleData>> data) {
            // 想适配器中传值
            recyclerView.loadMoreComplete();
            recyclerView.refreshComplete();
            if (presenter.getPage() == 1) {
                adapter.clearList();
            }
            result = data.getResult();
            adapter.setList(result);
            // 刷新适配器
            adapter.notifyDataSetChanged();
//            Toast.makeText(getContext(), "圈子数据：" + data.getStatus(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(getContext(), "圈子" + a.getDisplayMessage() + a.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    class DZCall implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(getContext(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                CircleData circleData = (CircleData) data.getArgs()[3];
                circleData.setGreatNum(circleData.getGreatNum() + 1);
                circleData.setWhetherGreat(1);
                adapter.notifyItemChanged(i + 1);//这个方法返回的并不是adapter所有item的数量，而是当前显示的item个数   所以必须下标+1
            }
        }

        @Override
        public void fail(ApiException a) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        presenter.unBind();
        circleDianZanPresenter.unBind();
        circleCancelPresenter.unBind();
    }
}
