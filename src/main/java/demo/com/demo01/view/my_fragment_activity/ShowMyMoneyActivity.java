package demo.com.demo01.view.my_fragment_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.com.demo01.R;
import demo.com.demo01.bean.MoneyData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.MyMoneyAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.utils.ToDate;
import demo.com.demo01.presenter.GetMyMoneyPresenter;

/**
 * 作者: Wang on 2019/1/2 18:53
 * 寄语：加油！相信自己可以！！！
 */


public class ShowMyMoneyActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    private GetMyMoneyPresenter getMyMoneyPresenter;
    private TextView te;

    XRecyclerView xRecyclerView;
    private MyMoneyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_mymoney);
        xRecyclerView = findViewById(R.id.list_Money);
//        listView = findViewById(R.id.list_Money);
        te = findViewById(R.id.sheng_Yue);
        getMyMoneyPresenter = new GetMyMoneyPresenter(new getMoney());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        xRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyMoneyAdapter(this);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.refresh();
    }

    @Override
    public void onRefresh() {
        if (getMyMoneyPresenter.isRuning()) {
            xRecyclerView.refreshComplete();
            return;
        }
        getMyMoneyPresenter.requestNet((int) user.getUserId(), user.getSessionId(), true);
    }

    @Override
    public void onLoadMore() {
        if (getMyMoneyPresenter.isRuning()) {
            xRecyclerView.loadMoreComplete();
            return;
        }
        getMyMoneyPresenter.requestNet((int) user.getUserId(), user.getSessionId(), false);
    }
    class getMoney implements DataCall<Result<MoneyData>> {
        @Override
        public void success(Result<MoneyData> data) {
            xRecyclerView.loadMoreComplete();
            xRecyclerView.refreshComplete();
            if (data.getStatus().equals("0000")) {
                Toast.makeText(ShowMyMoneyActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                MoneyData result = data.getResult();
                adapter.clear();
                adapter.setData(result.getDetailList());
                te.setText(result.getBalance() + "");
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void fail(ApiException a) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMyMoneyPresenter.unBind();
    }
}
