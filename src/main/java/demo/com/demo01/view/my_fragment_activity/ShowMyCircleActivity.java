package demo.com.demo01.view.my_fragment_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.demo01.R;
import demo.com.demo01.bean.MyCircleData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.adapter.MyCircleRecylerViewAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.presenter.MyCirclePresenter;

/**
 * 作者: Wang on 2019/1/2 18:53
 * 寄语：加油！相信自己可以！！！
 */


public class ShowMyCircleActivity extends BaseActivity {

    @BindView(R.id.mycircle_recycler)
    RecyclerView recyclerView;
    private MyCirclePresenter myCirclePresenter;
    private MyCircleRecylerViewAdapter myCircleRecylerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_myquanzi);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // p层
        myCirclePresenter = new MyCirclePresenter(new myGetCircle());
        // 适配器
        myCircleRecylerViewAdapter = new MyCircleRecylerViewAdapter(this);
        recyclerView.setAdapter(myCircleRecylerViewAdapter);
        myCirclePresenter.requestNet((int)user.getUserId(),user.getSessionId(),1,5);
    }

    class myGetCircle implements DataCall<Result<List<MyCircleData>>>{
        @Override
        public void success(Result<List<MyCircleData>> data) {

            if (data.getStatus().equals("0000")) {
                myCircleRecylerViewAdapter.setDataList(data.getResult());
                Log.v("wdqz",data.getResult().toString());
                myCircleRecylerViewAdapter.notifyDataSetChanged();
                if (myCircleRecylerViewAdapter.getItemCount() == 0) {
                    Toast.makeText(ShowMyCircleActivity.this, "您还未发布过圈子 快去发布吧！" , Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myCirclePresenter.unBind();
    }
}
