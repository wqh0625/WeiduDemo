package demo.com.demo01.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/4 14:20
 * 寄语：加油！相信自己可以！！！
 */

public class AddCarPresenter extends BasePresenter {
//    public static void main(String[] args) {
//        int[] a ={1,2,3,2,4,3};
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < a.length; i++) {
//            if (!list.contains(a[i])) {
//                list.add(a[i]);
//            }
//        }
//        System.out.println(""+list);
//    }

    public AddCarPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRegister = NewWorkHttp.getNetWorks().create(IRequest.class);
        Observable<Result> bannerData = iRegister.addCarGoods((int) args[0], (String) args[1],(String) args[2]);
        return bannerData;
    }
}
