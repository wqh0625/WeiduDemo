package demo.com.demo01.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.RegisterData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.utils.MD5Utils;
import demo.com.demo01.presenter.RegisterPresenter;

/**
 * 作者: Wang on 2018/12/28 19:47
 * 寄语：加油！相信自己可以！！！
 */


public class RegisterActivity extends AppCompatActivity {
    EditText eMobile;
    EditText ePwd;
    EditText eMa;
    int i = 60;
    @BindView(R.id.register_getMa)
    TextView ma;
    private RegisterPresenter presenter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {

                i--;
                if (i == 0) {
                    ma.setText("获取验证码");
                    return;
                }
                ma.setText(i + "s");
                handler.sendEmptyMessageDelayed(100, 1000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        eMobile = findViewById(R.id.register_mobile);
        ePwd = findViewById(R.id.register_password);
        eMa = findViewById(R.id.register_ma);
        ePwd.setInputType(129);
        presenter = new RegisterPresenter(new RegisterCall());
    }

    // 点击注册按钮
    @OnClick({R.id.register_btn, R.id.register_getMa, R.id.register_eye, R.id.back_login_activity})
    void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            presenter.requestNet(eMobile.getText().toString(), ePwd.getText().toString());
        } else if (view.getId() == R.id.register_getMa) {
            long l = System.currentTimeMillis();
            String s1 = String.valueOf(l);
            char d = s1.charAt(7);
            char d1 = s1.charAt(8);
            char d2 = s1.charAt(9);
            char d3 = s1.charAt(10);
            char d4 = s1.charAt(11);
            char d5 = s1.charAt(12);
            Toast.makeText(getBaseContext(), "验证码为 :" + d + d1 + d2 + d3 + d4 + d5, Toast.LENGTH_LONG
            ).show();

            handler.sendEmptyMessageDelayed(100, 1000);

        } else if (view.getId() == R.id.register_eye) {
            if (ePwd.getInputType() == 129) {
                ePwd.setInputType(127);
            } else {
                ePwd.setInputType(129);
            }
        } else if (view.getId() == R.id.back_login_activity) {
            finish();// 返回登录页面
        }
    }

    class RegisterCall implements DataCall<Result> {
       @Override
        public void success(Result data) {
            Toast.makeText(getBaseContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(RegisterActivity.this, "注册失败 "+a.getCode(), Toast.LENGTH_SHORT).show();
        }
    }
}
