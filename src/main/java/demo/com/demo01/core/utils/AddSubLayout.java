package demo.com.demo01.core.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import demo.com.demo01.R;

/**
 * 作者: Wang on 2019/1/8 14:16
 * 寄语：加油！相信自己可以！！！
 */

public class AddSubLayout extends LinearLayout implements View.OnClickListener {


    private TextView mAddBtn,mSubBtn;
    private TextView mNumText;
    private AddSubListener addSubListener;

    public AddSubLayout(Context context) {
        super(context);
        initView();
    }

    public AddSubLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddSubLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        //加载layout布局，第三个参数ViewGroup一定写成this
        View view = View.inflate(getContext(),R.layout.car_add_sub,this);

        mAddBtn = view.findViewById(R.id.btn_add);
        mSubBtn = view.findViewById(R.id.btn_sub);
        mNumText = view.findViewById(R.id.text_number);
        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int number = Integer.parseInt(mNumText.getText().toString());

        switch (v.getId()){
            case R.id.btn_add:
                number++;
                mNumText.setText(number+"");
                break;
            case R.id.btn_sub:
                number--;
                if (number==0){
                    Toast.makeText(getContext(),"数量不能小于0",Toast.LENGTH_LONG).show();
                    return;
                }
                mNumText.setText(number+"");
                break;
        }
        if (addSubListener!=null){
            addSubListener.addSub(number);
        }
    }

    public void setCount(int count) {
        mNumText.setText(count+"");
    }

    public void setAddSubListener(AddSubListener addSubListener) {
        this.addSubListener = addSubListener;
    }

    public interface AddSubListener{
        void addSub(int count);
    }
}
