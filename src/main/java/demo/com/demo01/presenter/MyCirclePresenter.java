package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/4 19:42
 * 寄语：加油！相信自己可以！！！
 */


public class MyCirclePresenter extends BasePresenter {
    public MyCirclePresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NewWorkHttp.getNetWorks().create(IRequest.class);
        return iRequest.getMyCircle((int) args[0], (String) args[1], (int) args[2], (int) args[3]);
    }
}
