package demo.com.demo01.core.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

public class ViewPageDetailsBannerAdapter extends PagerAdapter {
    String[] images;
    Context mContext;
    public ViewPageDetailsBannerAdapter(Context con,String[] imgs){
        mContext = con;
        images = imgs;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);

        simpleDraweeView.setImageURI(images[position]);
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
