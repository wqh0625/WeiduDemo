package demo.com.demo01.core.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: Wang on 2018/12/28 19:51
 * 寄语：加油！相信自己可以！！！
 */

public class NewWorkHttp {
    private static NewWorkHttp netWork;
    private static final String BSAE_URL = "http://172.17.8.100/small/";
//    String BSAE_URL = "http://169.254.101.220:8080/small/";
    private Retrofit retrofit;

    private NewWorkHttp() {
        init();
    }

    public static NewWorkHttp getNetWorks(){
        if (netWork == null) {
            netWork = new NewWorkHttp();
        }
        return netWork;
    }

    private void init() {
        // 拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // 设置拦截器
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 设置okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10,TimeUnit.MINUTES)
                .readTimeout(10,TimeUnit.MINUTES)
                .writeTimeout(10,TimeUnit.MINUTES)
                .build();
        // 初始化 Retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BSAE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }

}
