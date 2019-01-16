package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/5 17:21
 * 寄语：加油！相信自己可以！！！
 */


public class AddMyAddressPresenter extends BasePresenter {
    public AddMyAddressPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NewWorkHttp.getNetWorks().create(IRequest.class);
        return iRequest.addMyAddress(
                (int) args[0]   ,
                (String) args[1],
                (String) args[2],
                (String) args[3],
                (String) args[4],
                (String) args[5] );
    }
}
