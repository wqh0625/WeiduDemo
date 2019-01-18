package demo.com.demo01.view.my_fragment_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.demo01.R;
import demo.com.demo01.bean.FootListData;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.FootRecyclerViewAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.GetMyFootListPresenter;

/**
 * 作者: Wang on 2019/1/2 18:53
 * 寄语：加油！相信自己可以！！！
 */

public class ShowMyFootActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    //    private LoginDataDao dao;
//    private List<LoginData> loginDatalist;
//    public LoginData user;
    @BindView(R.id.foot_rec)
    XRecyclerView recyclerView;
    private GetMyFootListPresenter getMyFootListPresenter;
    private FootRecyclerViewAdapter footRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_myfoot);
        ButterKnife.bind(this);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //adapter
        // 得到登录的信息
//        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDataDao.TABLENAME);
//        dao = daoSession.getLoginDataDao();
//        loginDatalist = dao.loadAll();
//        for (int i = 0; i < loginDatalist.size(); i++) {
//            if (loginDatalist.get(i).getStatus() == 1) {
//                user = loginDatalist.get(i);
//                return;
//            }
//        }
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(this);
        getMyFootListPresenter = new GetMyFootListPresenter(new footGet());

        footRecyclerViewAdapter = new FootRecyclerViewAdapter(this);
        recyclerView.setAdapter(footRecyclerViewAdapter);
        recyclerView.refresh();
    }

    @Override
    public void onRefresh() {
        if (getMyFootListPresenter.isRuning()) {
            recyclerView.refreshComplete();
            return;
        }
//        footRecyclerViewAdapter.deleteList();
        getMyFootListPresenter.requestNet((int) user.getUserId(), user.getSessionId(), true);
    }

    @Override
    public void onLoadMore() {
        if (getMyFootListPresenter.isRuning()) {
            recyclerView.loadMoreComplete();
            return;
        }
        getMyFootListPresenter.requestNet((int) user.getUserId(), user.getSessionId(), false);
    }

    class footGet implements DataCall<Result<List<FootListData>>> {
        @Override
        public void success(Result<List<FootListData>> data) {
            if (data.getStatus().equals("0000")) {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
                Toast.makeText(ShowMyFootActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                footRecyclerViewAdapter.setList(data.getResult());
                footRecyclerViewAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(ShowMyFootActivity.this, "foot" + a.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMyFootListPresenter.unBind();
    }
}
