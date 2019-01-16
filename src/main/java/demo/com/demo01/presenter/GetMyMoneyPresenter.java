package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/5 17:21
 * 寄语：加油！相信自己可以！！！
 */


public class GetMyMoneyPresenter extends BasePresenter {
    public GetMyMoneyPresenter(DataCall consumer) {
        super(consumer);
    }

    int count;

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NewWorkHttp.getNetWorks().create(IRequest.class);
        boolean arg = (boolean) args[2];
        if (arg) {
            count = 1;
        } else {
            count++;
        }
        return iRequest.getMoneyList(
                (int) args[0],
                (String) args[1], 1, 10);
    }
}
