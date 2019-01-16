package demo.com.demo01.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.presenter.PayPresenter;

/**
 * 作者: Wang on 2019/1/13 19:18
 * 寄语：加油！相信自己可以！！！
 */


public class PayActivity extends BaseActivity {

    private Unbinder bind;

    @BindView(R.id.mbt_zf)
    Button button;
    private PayPresenter payPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        bind = ButterKnife.bind(this);
        final Intent intent = getIntent();
        final String orderId = intent.getStringExtra("orderId");
        double orderPrice = intent.getDoubleExtra("orderPrice", 0);

        payPresenter = new PayPresenter(new payP());
        button.setText("余额支付" + orderPrice + "元");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPresenter.requestNet((int) user.getUserId(), user.getSessionId(), orderId, 1);
            }
        });

    }

    class payP implements DataCall<Result> {
        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {

                Toast.makeText(PayActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void fail(ApiException a) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        payPresenter.unBind();
    }
}
