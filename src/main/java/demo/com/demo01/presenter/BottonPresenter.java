package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/6 15:09
 * 寄语：加油！相信自己可以！！！
 */


public class BottonPresenter extends BasePresenter {
    public BottonPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        return NewWorkHttp.getNetWorks().create(IRequest.class).getTwoCategory((String) args[0]);
    }
}
