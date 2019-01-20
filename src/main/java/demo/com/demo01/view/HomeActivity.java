package demo.com.demo01.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import demo.com.demo01.R;
import demo.com.demo01.view.fragment.CircleFragment;
import demo.com.demo01.view.fragment.HomeFragment;
import demo.com.demo01.view.fragment.ListFragment;
import demo.com.demo01.view.fragment.MyFragment;
import demo.com.demo01.view.fragment.ShoppingFragment;

/**
 * 作者: Wang on 2018/12/29 15:58
 * 寄语：加油！相信自己可以！！！
 */


public class HomeActivity extends AppCompatActivity {

    private RadioGroup radio;



    private FragmentManager manager;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        radio = findViewById(R.id.mmyRadio);


        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, new HomeFragment());
        transaction.commit();

        radio.check(radio.getChildAt(0).getId());
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.mmyRB1:
                         transaction.replace(R.id.frameLayout,new HomeFragment());
                        break;
                    case R.id.mmyRB2:
                        transaction.replace(R.id.frameLayout,new CircleFragment());

                        break;
                    case R.id.mmyRB3:
                        transaction.replace(R.id.frameLayout,new ShoppingFragment());

                        break;
                    case R.id.mmyRB4:
                        transaction.replace(R.id.frameLayout,new ListFragment());

                        break;
                    case R.id.mmyRB5:
                        transaction.replace(R.id.frameLayout,new MyFragment());
                        break;
                }
                transaction.commit();
            }
        });

//        fragmentTransaction.add(R.id.frameLayout,homeFragment).show(homeFragment);
//        fragmentTransaction.add(R.id.frameLayout,circleFragment).hide(circleFragment);
//        fragmentTransaction.add(R.id.frameLayout,shoppingFragment).hide(shoppingFragment);
//        fragmentTransaction.add(R.id.frameLayout,listFragment).hide(listFragment);
//        fragmentTransaction.add(R.id.frameLayout,myFragment).hide(myFragment);
//        fragmentTransaction.commit();
//
//        radio.check(radio.getChildAt(0).getId());
//        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                FragmentTransaction transaction = manager.beginTransaction();
//                switch (checkedId){
//                    case R.id.mmyRB1:
//                        transaction.show(homeFragment).hide(myFragment).hide(listFragment).hide(shoppingFragment).hide(circleFragment).commit();
//                        break;
//                    case R.id.mmyRB2:
//                        transaction.show(circleFragment).hide(homeFragment).hide(listFragment).hide(shoppingFragment).hide(myFragment).commit();
//                        break;
//                    case R.id.mmyRB3:
//                        transaction.show(shoppingFragment).hide(myFragment).hide(homeFragment).hide(listFragment).hide(circleFragment).commit();
//                        break;
//                    case R.id.mmyRB4:
//                        transaction.show(listFragment).hide(myFragment).hide(shoppingFragment).hide(homeFragment).hide(circleFragment).commit();
//                        break;
//                    case R.id.mmyRB5:
//                        transaction.show(myFragment).hide(circleFragment).hide(listFragment).hide(shoppingFragment).hide(homeFragment).commit();
//                        break;
//                }
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
