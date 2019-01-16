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

        Fresco.initialize(this, getConfigureCaches(this));
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
