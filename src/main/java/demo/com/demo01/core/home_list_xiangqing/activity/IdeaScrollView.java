package demo.com.demo01.core.home_list_xiangqing.activity;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.ScrollView;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * date: 2019/1/1.
 * Created by Administrator
 * function:
 */
public class IdeaScrollView extends ScrollView {
    public IdeaScrollView(Context context) {
        this(context,null);
    }

    public IdeaScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IdeaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollviewListener !=null){
            mScrollviewListener.OnScrollChange(this,l,t,oldl,oldt);
        }
    }

    /**
     *
     * 接口回调
     *
     */
    public interface ScrollviewListener{
        void OnScrollChange(IdeaScrollView scrollView,int l, int t, int oldl, int oldt);
    }

    public ScrollviewListener mScrollviewListener;

    public void setScrollviewListener(ScrollviewListener scrollviewListener){
        mScrollviewListener = scrollviewListener;
    }
}
