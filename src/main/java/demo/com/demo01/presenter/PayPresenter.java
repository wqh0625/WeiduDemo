package demo.com.demo01.presenter;

import java.io.File;
import java.util.List;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者: Wang on 2019/1/12 16:20
 * 寄语：加油！相信自己可以！！！
 */


public class PayPresenter extends BasePresenter {
    public PayPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {

        return NewWorkHttp.getNetWorks().create(IRequest.class).pay((int)args[0],(String)args[1],(String)args[2],(int)args[3]);
    }
}
