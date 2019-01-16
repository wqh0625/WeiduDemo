package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import demo.com.demo01.view.SearchActivity;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/6 15:10
 * 寄语：加油！相信自己可以！！！
 */


public class BotSearchPresenter extends BasePresenter {

    public BotSearchPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        return NewWorkHttp.getNetWorks().create(IRequest.class).getdataCategory((String) args[0],(String) args[1],(String) args[2]);
    }
}
