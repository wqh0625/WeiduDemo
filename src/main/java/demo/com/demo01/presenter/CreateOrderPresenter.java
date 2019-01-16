package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/9 15:36
 * 寄语：加油！相信自己可以！！！
 */


public class CreateOrderPresenter extends BasePresenter {
    public CreateOrderPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        return NewWorkHttp.getNetWorks().create(IRequest.class).createOrder(
                (int)args[0],
                (String)args[1],
                (String)args[2],
                (double)args[3],
                (int)args[4]);
    }
}
