package demo.com.demo01.view.my_fragment_activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.view.MainActivity;
import demo.com.demo01.view.makingview.MyMakingView;
import demo.com.demo01.view.makingview.MyMakingViewActivity;

/**
 * 作者: Wang on 2019/1/2 15:53
 * 寄语：加油！相信自己可以！！！
 */


public class ShowMyDataActivity extends BaseActivity {
    @BindView(R.id.my_mydata_myImage)
    SimpleDraweeView image;
    @BindView(R.id.my_mydata_Nickname)
    TextView nickname;
    @BindView(R.id.my_mydata_pwd)
    TextView pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_mydata);
        ButterKnife.bind(this);
        nickname.setText(user.getNickName());
        pwd.setText(user.getPwd() + "");
        image.setImageURI(Uri.parse(user.getHeadPic()));
    }

    @OnClick(R.id.tuichudenglv)
    void on() {
        LoginDataDao loginDataDao = DaoMaster.newDevSession(ShowMyDataActivity.this, LoginDataDao.TABLENAME).getLoginDataDao();
        loginDataDao.delete(user);// 删除用户
        Intent intent = new Intent(ShowMyDataActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);// 清空当前栈 ，并且创建新的栈
        startActivity(intent);// 跳转
    }

    @OnClick(R.id.makingBtn)
    void in() {
        startActivity(new Intent(ShowMyDataActivity.this, MyMakingViewActivity.class));
    }
}
