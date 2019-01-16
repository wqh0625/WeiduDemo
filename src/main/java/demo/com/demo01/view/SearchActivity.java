package demo.com.demo01.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.BottonBean;
import demo.com.demo01.bean.Result;
import demo.com.demo01.bean.SearchData;
import demo.com.demo01.bean.TopBean;
import demo.com.demo01.core.adapter.BottonAdapter;
import demo.com.demo01.core.adapter.TopAdapter;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.adapter.SearchActivity_XRecyclerViewAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;

import demo.com.demo01.presenter.BotSearchPresenter;
import demo.com.demo01.presenter.BottonPresenter;
import demo.com.demo01.presenter.PopPresenter;
import demo.com.demo01.presenter.SearchActivityPresenter;

/**
 * 作者: Wang on 2019/1/3 11:39
 * 寄语：加油！相信自己可以！！！
 */


public class SearchActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    private List<SearchData> result;
    @BindView(R.id.search_edtext_search)
    EditText search_edittext;
    SearchActivityPresenter searchActivityPresenter;
    SearchActivity_XRecyclerViewAdapter adapter;
    @BindView(R.id.search_xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.search_none_textview)
    TextView none;
    @BindView(R.id.search_no_data)
    LinearLayout nodata;
    @BindView(R.id.mymeau)
    ImageView pop;
    private String key = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        result = new ArrayList<>();
        searchActivityPresenter = new SearchActivityPresenter(new mySearch());
        adapter = new SearchActivity_XRecyclerViewAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setAdapter(adapter);

        initPop();
    }

    private GridLayoutManager gridLayoutManager;
    private View view;
    private TopAdapter topAdapter;
    private BottonAdapter bottonAdapter;
    private BottonPresenter bottonPresenter;
    private BotSearchPresenter botSearchPresenter;
    private PopupWindow popupWindow;

    private void initPop() {
        view = View.inflate(SearchActivity.this, R.layout.pop, null);
        //搜索p
        //popwindow头  p层
        PopPresenter popPresenter = new PopPresenter(new Top());
        //popwindow尾  p层
        bottonPresenter = new BottonPresenter(new Botton());
        //popwindow尾搜索  p层
        botSearchPresenter = new BotSearchPresenter(new BotSearch());

        gridLayoutManager = new GridLayoutManager(this, 2);

        //popwindow头
        RecyclerView topRecycler = view.findViewById(R.id.meau_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRecycler.setLayoutManager(linearLayoutManager);
        topAdapter = new TopAdapter();
        topRecycler.setAdapter(topAdapter);
        topRecycler.setBackgroundColor(0x88000000);
        //popwindow尾
        RecyclerView bottonRecycler = view.findViewById(R.id.meau_2);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        bottonRecycler.setLayoutManager(linearLayoutManager1);
        bottonAdapter = new BottonAdapter();
        bottonRecycler.setAdapter(bottonAdapter);
        bottonRecycler.setBackgroundColor(0x77000000);
        //头部点击事件
        topAdapter.setOnClick(new TopAdapter.OnClick() {
            @Override
            public void onClick(String id, String name) {
//                key = name;
                bottonPresenter.requestNet(id);
            }
        });
        //尾部点击事件
        bottonAdapter.setOnClickk(new BottonAdapter.onClickk() {

            @Override
            public void click(String id, String name) {
                key = name;
                popupWindow.dismiss();
                botSearchPresenter.requestNet(id, "1", "10");
//                nodata.setVisibility(View.GONE);
//                xRecyclerView.setVisibility(View.VISIBLE);
//                searchActivityPresenter.requestNet(name, true);// 调用网络
                xRecyclerView.refresh();
            }
        });

        popPresenter.requestNet();// 得到第一的名字
        bottonPresenter.requestNet(String.valueOf(1001002));
    }

    /**
     * popwindow 为布局搜索成功接口
     */
    private class BotSearch implements DataCall<Result> {
        @Override
        public void success(Result data) {
            List<SearchData> result = (List<SearchData>) data.getResult();
            if (data.getStatus().equals("0000")) {
                adapter.removeAll();
                adapter.setList(result);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * popwindow 尾布局成功接口
     */
    private class Botton implements DataCall<Result> {
        @Override
        public void success(Result data) {
            List<BottonBean> result = (List<BottonBean>) data.getResult();
            bottonAdapter.remove();
            adapter.deleteList();
            bottonAdapter.setList(result);
            bottonAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    class Top implements DataCall<Result> {
        @Override
        public void success(Result data) {
            List<TopBean> result = (List<TopBean>) data.getResult();
            Log.e("qqqqqqqqqqq", "-----------------" + result.get(0).getName());
            topAdapter.setList(result);
            adapter.deleteList();
            topAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @OnClick(R.id.search_btn)
    void searchData() {
        key="";
        key = search_edittext.getText().toString();
        if (key.equals("")) {
//            Toast.makeText(this, "输入框不可为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        searchActivityPresenter.requestNet(key, true);
    }

    @OnClick(R.id.mymeau)
    void tu() {
        popupWindow = new PopupWindow(view, 800, 200,
                true);
//        int height = getWindowManager().getDefaultDisplay().getHeight();
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.showAsDropDown(pop);
    }

    @Override
    public void onRefresh() {
        if (searchActivityPresenter.isRuning()) {
            xRecyclerView.refreshComplete();
            return;
        }
//        page=1;
        xRecyclerView.refreshComplete();
        searchActivityPresenter.requestNet(key, true);// 调用网络
    }

    @Override
    public void onLoadMore() {
        if (searchActivityPresenter.isRuning()) {
            xRecyclerView.loadMoreComplete();
            return;
        }
//        page++;   暂时不加加 有BUG
        xRecyclerView.loadMoreComplete();
        searchActivityPresenter.requestNet(key, false);// 调用网络
    }

    class mySearch implements DataCall<Result<List<SearchData>>> {
        @Override
        public void success(Result<List<SearchData>> data) {
            none.setVisibility(View.GONE);
            xRecyclerView.setVisibility(View.VISIBLE);

            xRecyclerView.loadMoreComplete();
            xRecyclerView.refreshComplete();
            result.addAll(data.getResult());
            adapter.deleteList();
            adapter.setList(result);
            adapter.notifyDataSetChanged();

            if (data.getResult().size() == 0) {
                nodata.setVisibility(View.VISIBLE);
//                xRecyclerView.setVisibility(View.VISIBLE);
//                adapter.deleteList();
                none.setVisibility(View.VISIBLE);
                Toast.makeText(SearchActivity.this, "没有更多数据了！", Toast.LENGTH_SHORT).show();

                return;
            } else {
                nodata.setVisibility(View.GONE);
                xRecyclerView.setVisibility(View.VISIBLE);
//                adapter.deleteList();
            }

        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(SearchActivity.this, "没有更多数据了！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchActivityPresenter.unBind();
        bottonPresenter.unBind();
    }
}
