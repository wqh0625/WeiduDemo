package demo.com.demo01.core.utils;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import java.io.File;

/**
 * 作者: Wang on 2019/1/1 13:34
 * 寄语：加油！相信自己可以！！！
 */


public class MyApp extends Application {
    private static int MAX_MEM = 50 * ByteConstants.MB;
    private static Context context;
    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "d0101");

    public static Context getContext() {
        return context;



    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化Bugly
        CrashReport.initCrashReport(this, "19a0112879", false);
        Fresco.initialize(this, getConfigureCaches(this));

        /*
         *  注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
         *
        */
        UMConfigure.init(this,   UMConfigure.DEVICE_TYPE_PHONE,   "5c3e9eaef1f5561771001175");
    }

    private ImagePipelineConfig getConfigureCaches(Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEM,// 内存缓存中总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中图片的最大数量。
                MAX_MEM,// 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                Integer.MAX_VALUE,// 内存缓存中准备清除的总图片的最大数量。
                Integer.MAX_VALUE);// 内存缓存中单个图片的最大大小。

        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(this).setBaseDirectoryPath(file)
                        .build());// 设置自定义缓存目录
        builder.setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams);
        return builder.build();
    }

}
