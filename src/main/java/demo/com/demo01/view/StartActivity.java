package demo.com.demo01.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;

/**
 * 作者: Wang on 2018/12/29 16:57
 * 寄语：加油！相信自己可以！！！
 */


public class StartActivity extends AppCompatActivity {
    private LoginDataDao dao;
    @BindView(R.id.tg)
    TextView tg;
    private int i = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                i--;
                if (i == 0) {
                    List<LoginData> list = dao.loadAll();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getStatus() == 1) {
//                            Toast.makeText(StartActivity.this, "跳转进主页面", Toast.LENGTH_SHORT).show();
                            Log.e("------登录数据",list.get(i).toString());
                            // 跳转到主页面
                            startActivity(new Intent(StartActivity.this, HomeActivity.class));
                            finish();
                            return;
                        }
                    }
                    // 跳转到主页面
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    handler.removeMessages(1);
                    finish();
                    return;
                }
                tg.setText("跳过" + i);
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();

        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @OnClick(R.id.tg)
    void on() {
        List<LoginData> list = dao.loadAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus() == 1) {
//                Toast.makeText(StartActivity.this, "跳转进主页面", Toast.LENGTH_SHORT).show();
                // 跳转到主页面
                startActivity(new Intent(StartActivity.this, HomeActivity.class));
                handler.removeMessages(1);
                finish();
                return;

            }
        }
        // 跳转到主页面
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        handler.removeMessages(1);
        finish();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
}
