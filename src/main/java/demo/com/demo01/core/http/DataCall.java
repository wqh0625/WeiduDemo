package demo.com.demo01.core.http;

import demo.com.demo01.core.exception.ApiException;

/**
 * 作者: Wang on 2018/12/30 13:33
 * 寄语：加油！相信自己可以！！！
 */


public interface DataCall<T> {
    void success(T data);
    void fail (ApiException a);
}
