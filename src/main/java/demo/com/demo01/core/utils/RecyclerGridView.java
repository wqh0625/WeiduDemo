package demo.com.demo01.core.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.GridView;

public class RecyclerGridView extends RecyclerView {

    public RecyclerGridView(Context context) {
        super(context);
    }

    public RecyclerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}

