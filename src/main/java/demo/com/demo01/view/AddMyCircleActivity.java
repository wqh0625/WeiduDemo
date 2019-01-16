package demo.com.demo01.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.Result;
import demo.com.demo01.core.adapter.ImageAdapter;
import demo.com.demo01.core.base.BaseActivity;
import demo.com.demo01.core.exception.ApiException;
import demo.com.demo01.core.http.DataCall;
import demo.com.demo01.core.utils.StringUtils;
import demo.com.demo01.presenter.SendCirclePresenter;

/**
 * 作者: Wang on 2019/1/12 14:51
 * 寄语：加油！相信自己可以！！！
 */

public class AddMyCircleActivity extends BaseActivity {

    private SendCirclePresenter sendCirclePresenter;
    @BindView(R.id.bo_text)
    EditText text;
    @BindView(R.id.bo_image_list)
    RecyclerView mImageList;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcircle);
        ButterKnife.bind(this);
        sendCirclePresenter = new SendCirclePresenter(new sendQ());

        imageAdapter = new ImageAdapter(this);
        imageAdapter.setSign(1);
        imageAdapter.add(R.drawable.psb);

        mImageList.setLayoutManager(new GridLayoutManager(this, 3));
        mImageList.setAdapter(imageAdapter);

    }

    // 发布圈子
    @OnClick(R.id.send)
    void send() {
        sendCirclePresenter.requestNet((int) user.getUserId(), user.getSessionId(), "1", text.getText().toString().trim(), imageAdapter.getList());
    }

    // 得到反应
    class sendQ implements DataCall<Result> {

        @Override
        public void success(Result data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(AddMyCircleActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        public void fail(ApiException a) {
            Toast.makeText(AddMyCircleActivity.this, "发布失败！！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null,requestCode,data);
            if (!StringUtils.isEmpty(filePath)) {
                imageAdapter.add(filePath);
                imageAdapter.notifyDataSetChanged();
//                Bitmap bitmap = UIUtils.decodeFile(new File(filePath),200);
//                mImage.setImageBitmap(bitmap);
            }
        }

    }
    // 返回
    @OnClick(R.id.back)
    void back() {
        finish();
    }
}
