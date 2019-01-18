package demo.com.demo01.view.makingview;

/**
 * 作者: Wang on 2019/1/17 11:04
 * 寄语：加油！相信自己可以！！！
 */


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class CricleView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Paint mPaint;
    private int value;

    public CricleView(Context context) {
        super(context);
        init();
    }

    public CricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        animator.setIntValues(0, 200);
        animator.addUpdateListener(this);
        animator.start();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(255 - (int) (value * (255.0 / 200)));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, value, mPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (int) (value * (150.0 / 200)), mPaint);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        value = (int) animation.getAnimatedValue();
        invalidate();
    }
}
