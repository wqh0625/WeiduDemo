package demo.com.demo01.core.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.CircleData;
import demo.com.demo01.core.utils.RecyclerGridView;
import demo.com.demo01.core.utils.SpacingItemDecoration;
import demo.com.demo01.core.utils.StringUtils;
import demo.com.demo01.core.utils.ToDate;

/**
 * 作者: Wang on 2019/1/1 15:54
 * 寄语：加油！相信自己可以！！！
 */


public class Circle_RecyclerViewAdapter extends RecyclerView.Adapter<Circle_RecyclerViewAdapter.ViewHolder> {
    private FragmentActivity context;
    private List<CircleData> list;
    public DZInter dzInter;
    private int lieNum = 1;

    public void setDzInter(DZInter dzInter) {
        this.dzInter = dzInter;
    }

    public Circle_RecyclerViewAdapter(FragmentActivity context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void clearList() {
        list.clear();
    }

    public interface DZInter {
        void onclick(int i,CircleData circleData);
    }

    public void setList(List<CircleData> alist) {
        if (alist != null) {
            list.addAll(alist);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.circle_recyclerview_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

            final CircleData circleData = list.get(i);
            viewHolder.title.setText(circleData.getNickName() + "");//
            viewHolder.litterImage.setImageURI(Uri.parse(circleData.getHeadPic()) + "");//头像
            viewHolder.wenzi.setText(circleData.getContent());
            String timedate = ToDate.timedate(circleData.getCreateTime());
            viewHolder.qz_date.setText(timedate + "");
            viewHolder.number.setText(circleData.getGreatNum() + "");
            if (circleData.getWhetherGreat() == 2) {
                viewHolder.prise.setImageResource(R.drawable.common_btn_prise_n);
            } else {
                viewHolder.prise.setImageResource(R.drawable.common_btn_prise_s);
            }
            // 图片
            if (StringUtils.isEmpty(circleData.getImage())) {
                viewHolder.gridView.setVisibility(View.GONE);
            } else {
                viewHolder.gridView.setVisibility(View.VISIBLE);
                String[] split = circleData.getImage().split(",");
                if (split.length == 1) {
                    lieNum = 1;
                } else if (split.length == 2 || split.length == 4) {
                    lieNum = 2;
                } else {
                    lieNum = 3;
                }
                viewHolder.gridLayoutManager.setSpanCount(lieNum);// 设置列数
                List<Object> list = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    list.add(split[j]);
                }
                viewHolder.imageAdapter.clear();// 清空集合
                viewHolder.imageAdapter.addAll(list);// 添加图片
                viewHolder.imageAdapter.notifyDataSetChanged();
            }

            // 点赞
            viewHolder.prise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dzInter.onclick(i,list.get(i));
                }
            });

        }

        @Override
        public int getItemCount () {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {


            TextView qz_date;
            RecyclerGridView gridView;
            SimpleDraweeView litterImage;
            TextView wenzi;
            TextView number;
            TextView title;
            ImageView prise;
            ImageAdapter imageAdapter;
            GridLayoutManager gridLayoutManager;

            public ViewHolder(View itemView) {
                super(itemView);
                qz_date = itemView.findViewById(R.id.qz_date);
                gridView = itemView.findViewById(R.id.qz_image);
                litterImage = itemView.findViewById(R.id.qz_titleImage);
                wenzi = itemView.findViewById(R.id.qz_text);
                number = itemView.findViewById(R.id.qz_number);
                title = itemView.findViewById(R.id.qz_title);
                prise = itemView.findViewById(R.id.qz_prise);

                imageAdapter = new ImageAdapter(context);// 适配器
                int size = context.getResources().getDimensionPixelSize(R.dimen.dp_10);// 条目间的间距
                gridView.addItemDecoration(new SpacingItemDecoration(size));// 添加间距
                gridLayoutManager = new GridLayoutManager(context, lieNum);//
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                gridView.setLayoutManager(gridLayoutManager);
                gridView.setAdapter(imageAdapter);//设置适配器

            }
        }
    }
