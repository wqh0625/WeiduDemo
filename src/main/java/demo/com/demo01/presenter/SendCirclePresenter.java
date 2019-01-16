package demo.com.demo01.presenter;

import java.io.File;
import java.util.ArrayList;
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


public class SendCirclePresenter extends BasePresenter {
    public SendCirclePresenter(DataCall consumer) {
        super(consumer);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NewWorkHttp.getNetWorks().create(IRequest.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("commodityId",(String) args[2]);// 商品ID
        builder.addFormDataPart("content",(String)args[3]);//文字内容
        List<Object> list = (List<Object>) args[4];// 图片集合
        if (list .size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image",file.getName(),RequestBody.create(MediaType.parse("multipart/octet-stream"),file));
            }
        }

        return iRequest.sendCircle((int)args[0],(String)args[1],builder.build());
    }
}
