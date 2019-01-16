package demo.com.demo01.view.list_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.com.demo01.R;

/**
 * 作者: Wang on 2019/1/5 11:09
 * 寄语：加油！相信自己可以！！！
 */


public class List_Fragment_04 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_04,container,false);
        return view;
    }
}
