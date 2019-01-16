package demo.com.demo01.view.my_fragment_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.presenter.AddMyAddressPresenter;

/**
 * 作者: Wang on 2019/1/5 20:45
 * 寄语：加油！相信自己可以！！！
 */


public class AddMyAddressActivity extends BaseActivity {

    @BindView(R.id.addmyaddress_address)
    EditText eadress;
    @BindView(R.id.addmyaddress_shoujianren)
    EditText eren;
    @BindView(R.id.addmyaddress_sjh)
    EditText esjh;
    @BindView(R.id.addmyaddress_youbian)
    EditText eyoubian;
    @BindView(R.id.toBottom)
    TextView jiben;
    private AddMyAddressPresenter addMyAddressPresenter;
    private CityPicker mCP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmyaddressactivity);
        ButterKnife.bind(this);
        addMyAddressPresenter = new AddMyAddressPresenter(new add());
    }

    @OnClick(R.id.addmyaddress_btn)
    void on() {
        String address = eadress.getText().toString();
        String ren = eren.getText().toString();
        String sjh = esjh.getText().toString();
        String youb = eyoubian.getText().toString();
        if (youb.length() > 0 && address.length() > 0 && sjh.length() > 0 && ren.length() > 0) {
            addMyAddressPresenter.requestNet((int) user.getUserId(), user.getSessionId(), ren, sjh, jiben.getText().toString()+address, youb);
        }
    }

    // 点击弹出
    @OnClick(R.id.toBottom)
    void toBotton(){
        mCP = new CityPicker.Builder(AddMyAddressActivity.this)
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];

                jiben.setText(province + city + district);
            }

            @Override
            public void onCancel() {
//                Toast.makeText(AddMyAddressActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });

        // 显示出来
        mCP.show();

    }

    class add implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getMessage().equals("添加成功")) {
                Toast.makeText(AddMyAddressActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void fail(ApiException a) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addMyAddressPresenter.unBind();
    }
}
