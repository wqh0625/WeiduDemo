package demo.com.demo01.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.com.demo01.R;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;
import demo.com.demo01.view.my_fragment_activity.ShowMyAddressActivity;
import demo.com.demo01.view.my_fragment_activity.ShowMyCircleActivity;
import demo.com.demo01.view.my_fragment_activity.ShowMyDataActivity;
import demo.com.demo01.view.my_fragment_activity.ShowMyFootActivity;
import demo.com.demo01.view.my_fragment_activity.ShowMyMoneyActivity;

/**
 * 作者: Wang on 2018/12/30 11:36
 * 寄语：加油！相信自己可以！！！
 */


public class MyFragment extends Fragment {
    private Unbinder unbinder;

    @BindView(R.id.my_litterImage)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.my_title)
    TextView title;

    private LoginDataDao dao;
    private LoginData loginData;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_05, container, false);
        unbinder = ButterKnife.bind(this, view);
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();

        List<LoginData> list = dao.loadAll();
        for (int i = 0; i < list.size(); i++) {
            loginData = list.get(i);
        }
        title.setText(loginData.getNickName()+"");
        simpleDraweeView.setImageURI(Uri.parse(loginData.getHeadPic()));
//        File file = new File(Environment.getExternalStorageDirectory() + "psb222.png");
//        Log.e("ffff",file.getAbsolutePath());

        return view;
    }

    @OnClick({R.id.my_geren, R.id.my_zuji, R.id.my_money, R.id.my_address, R.id.my_qz})
    void toActivity(View view) {

        switch (view.getId()) {
            case R.id.my_geren:
                // 展示个人资料
                String s = new Gson().toJson(loginData);
                Intent intent = new Intent(getContext(), ShowMyDataActivity.class);
                intent.putExtra("mydata", s);
                startActivity(intent);
                break;
            case R.id.my_money:
                startActivity(new Intent(getContext(),ShowMyMoneyActivity.class));
                // 我的钱包
                break;
            case R.id.my_address:
                startActivity(new Intent(getContext(),ShowMyAddressActivity.class));
                // 我的地址
                break;
            case R.id.my_qz:
                startActivity(new Intent(getContext(),ShowMyCircleActivity.class));
                // 我的圈子
                break;
            case R.id.my_zuji:
                startActivity(new Intent(getContext(),ShowMyFootActivity.class));
                // 我的足迹
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
