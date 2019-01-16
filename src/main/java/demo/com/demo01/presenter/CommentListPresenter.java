package demo.com.demo01.presenter;

import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.http.IRequest;
import demo.com.demo01.core.http.NewWorkHttp;
import io.reactivex.Observable;

/**
 * 作者: Wang on 2019/1/6 19:53
 * 寄语：加油！相信自己可以！！！
 */


public class CommentListPresenter extends BasePresenter {
    public CommentListPresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        return NewWorkHttp.getNetWorks().create(IRequest.class).getCommentList((String) args[0],(int)args[1],(int)args[2]);
    }
}
