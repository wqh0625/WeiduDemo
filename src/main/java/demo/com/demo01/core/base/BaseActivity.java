package demo.com.demo01.core.base;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.dao.DaoMaster;
import demo.com.demo01.dao.DaoSession;
import demo.com.demo01.dao.LoginDataDao;

/**
 * 作者: Wang on 2019/1/3 13:56
 * 寄语：加油！相信自己可以！！！
 */


public class BaseActivity extends AppCompatActivity {
    private LoginDataDao dao;
    private List<LoginData> loginDatalist;

    public final static int PHOTO = 0;// 相册选取
    public final static int CAMERA = 1;// 拍照
    private static BaseActivity mForegroundActivity = null;
    public LoginData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 得到登录的信息
        DaoSession daoSession = DaoMaster.newDevSession(this, LoginDataDao.TABLENAME);
        dao = daoSession.getLoginDataDao();
        loginDatalist = dao.loadAll();
        for (int i = 0; i < loginDatalist.size(); i++) {
            if (loginDatalist.get(i).getStatus() == 1) {
                user = loginDatalist.get(i);
                return;
            }
        }
        //
    }

    /**
     * 获取当前处于前台的activity
     */
    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */

    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == CAMERA) {
            return fileName;
        } else if (requestCode == PHOTO) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                actualimagecursor.close();
            return img_path;
        }
        return null;
    }
}
