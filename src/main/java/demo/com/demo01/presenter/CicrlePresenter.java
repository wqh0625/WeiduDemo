package demo.com.demo01.presenter;

import java.util.List;

import demo.com.demo01.bean.CircleData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2018/12/28 20:09
 * 寄语：加油！相信自己可以！！！
 */


public class CicrlePresenter extends BasePresenter {
    public CicrlePresenter(DataCall consumer) {
        super(consumer);
    }

    int page = 1;

    public int getPage() {
        return page;
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRegister = NewWorkHttp.getNetWorks().create(IRequest.class);

        boolean arg = (boolean) args[2];
        if (arg) {
            page=1;
        }else{
            page++;
        }
        Observable<Result<List<CircleData>>> bannerData = iRegister.circleData((int) args[0], (String) args[1], page, 20);
//        Observable<Result<List<CircleData>>> bannerData = iRegister.getqzzz(1,page, 5);
        return bannerData;
    }
}
