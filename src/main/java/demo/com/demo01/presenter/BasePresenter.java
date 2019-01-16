package demo.com.demo01.presenter;


import demo.com.demo01.bean.Result;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.exception.CustomException;
import demo.com.demo01.core.exception.ResponseTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 作者: Wang on 2018/12/28 20:02
 * 寄语：加油！相信自己可以！！！
 */


public abstract class BasePresenter {
    private DataCall consumer;
    private boolean runing;

    public BasePresenter(DataCall consumer) {
        this.consumer = consumer;
    }

    public void requestNet(final Object... args) {
        if (runing)
            return;
        runing = true;
        observable(args)
                .compose(ResponseTransformer.handleResult())///
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
//                .subscribeOn(Schedulers.newThread())// 子线程
//                .observeOn(AndroidSchedulers.mainThread())// 主线程
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result o) throws Exception {
                        o.setArgs(args);
                        consumer.success(o);
                        runing = false;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        consumer.fail(CustomException.handleException(throwable));
                        runing = false;
                    }
                });
    }

    protected abstract Observable observable(Object... args);

    public void unBind() {
        this.consumer = null;
    }

    public boolean isRuning() {
        return runing;
    }
}
