package demo.com.demo01.view.my_fragment_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.MyAddressData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.adapter.MyAddressListAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.presenter.GetMyAddressListPresenter;

/**
 * 作者: Wang on 2019/1/2 18:53
 * 寄语：加油！相信自己可以！！！
 */

public class ShowMyAddressActivity extends BaseActivity {

    private GetMyAddressListPresenter getMyAddressListPresenter;
    ListView listView;
    private MyAddressListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_myaddress);
        ButterKnife.bind(this);
        listView = findViewById(R.id.my_myaddress_List);
        adapter = new MyAddressListAdapter(this);


        getMyAddressListPresenter = new GetMyAddressListPresenter(new MyList());
        getMyAddressListPresenter.requestNet(552, user.getSessionId());
        listView.setAdapter(adapter);
    }

    class MyList implements DataCall<Result<List<MyAddressData>>> {
        @Override
        public void success(Result<List<MyAddressData>> data) {
//            Toast.makeText(ShowMyAddressActivity.this, "" + data.getResult().size(), Toast.LENGTH_SHORT).show();
            if (data.getStatus().equals("0000") ) {
                for (int i = 0; i < data.getResult().size(); i++) {
                    Log.v("收货地址-----", "" + data.getResult().get(i).toString());
                }
                adapter.setList(data.getResult());
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(ShowMyAddressActivity.this, "" + a.getCode() + a.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.my_myaddress_AddAddress_Btn)
    void OnClick() {
//        Toast.makeText(this, "跳转至新增收货地址Activity", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ShowMyAddressActivity.this, AddMyAddressActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMyAddressListPresenter.unBind();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMyAddressListPresenter.requestNet(552, user.getSessionId());
    }
}
