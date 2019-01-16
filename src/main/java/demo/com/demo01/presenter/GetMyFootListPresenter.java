package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/5 17:21
 * 寄语：加油！相信自己可以！！！
 */


public class GetMyFootListPresenter extends BasePresenter {
    int page;

    public GetMyFootListPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NewWorkHttp.getNetWorks().create(IRequest.class);
        boolean is = (boolean) args[2];
        if (is) {
            page = 1;
        } else {
            page++;
        }
        return iRequest.getFootList(
                (int) args[0],
                (String) args[1],
                page,
                20
        );
    }
}
