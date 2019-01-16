package demo.com.demo01.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.demo01.R;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LoginDataDao dao;
    Button login_btn;
    EditText mMobile;
    EditText mPassword;
    LoginPresenter presenter;
    TextView start;
    private SharedPreferences sp;
    private LoginData loginData;
    private String pwd;

    @BindView(R.id.check_remeber)
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPassword = findViewById(R.id.login_password);
        mMobile = findViewById(R.id.login_mobile);
        login_btn = findViewById(R.id.login_btn);
//        checkBox = findViewById(R.id.check_remeber);
        mPassword.setInputType(129);
        presenter = new LoginPresenter(new Call());// 得到p层
        login_btn.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        start = findViewById(R.id.start_register);
        start.setOnClickListener(this);
        findViewById(R.id.eye).setOnClickListener(this);
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();
        sp = getSharedPreferences("share", Context.MODE_PRIVATE);

        loginData = new LoginData();
        boolean check = sp.getBoolean("check", false);
        List<LoginData> list = dao.loadAll();
        for (int i = 0; i < list.size(); i++) {
            loginData = list.get(i);
        }
//        Toast.makeText(this, ""+list.size(), Toast.LENGTH_SHORT).show();
        if (check) {
            String name = sp.getString("name", "");
            String pwd = sp.getString("pwd", "");
            checkBox.setChecked(true);
            mPassword.setText(pwd);
            mMobile.setText(name);
//            Toast.makeText(this, "选中", Toast.LENGTH_SHORT).show();
            return;
        }else{
            sp.edit().clear();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                //得到输入框数据 调用P层 进行网络判断
                String phone = mMobile.getText().toString();
                String pw = mPassword.getText().toString();
                if (phone.length() < 11 || pw.length() < 6) {
                    Toast.makeText(this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (checkBox.isChecked()) {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("name",phone);
                    edit.putString("pwd",pw);
                    edit.putBoolean("check",checkBox.isChecked());
                    edit.commit();
                }

                // 调用登录接口
                presenter.requestNet(phone,  pw );
                break;
            case R.id.start_register:
                // 进入注册页面
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.eye:
                if (mPassword.getInputType() == 129) {
                    mPassword.setInputType(127);
                } else {
                    mPassword.setInputType(129);
                }
                break;
//            case R.id.check_remeber:
//
//                break;
        }

    }

    class Call implements DataCall<Result> {

        @Override
        public void success(Result data) {
            Toast.makeText(getBaseContext(), "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            // 存入数据库
            if (data.getStatus().equals("0000")) {
                LoginData loginData = (LoginData) data.getResult();
                loginData.setStatus(1);//设置唯一标识
                loginData.setPwd(mPassword.getText().toString().trim());
                long insert = dao.insertOrReplace(loginData);
                Log.e("---llll", insert + "");
                // 跳转到主页面
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
                return;
            }

        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
