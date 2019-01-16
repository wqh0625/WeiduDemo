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
import demo.com.demo01.bean.CommentList;
import demo.com.demo01.core.utils.RecyclerGridView;
import demo.com.demo01.core.utils.SpacingItemDecoration;
import demo.com.demo01.core.utils.StringUtils;
import demo.com.demo01.core.utils.ToDate;

/**
 * 作者: Wang on 2019/1/6 19:58
 * 寄语：加油！相信自己可以！！！
 */


public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    private List<CommentList> dataList;
    private FragmentActivity context;

    public CommentListAdapter(FragmentActivity context) {
        this.dataList = new ArrayList<>();
        this.context = context;
    }

    public void setDataList(List<CommentList> dataList) {
        if (dataList != null) {
            this.dataList.addAll(dataList);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.circle_recyclerview_item, null);
        return new CommentListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CommentList circleData = dataList.get(i);
        viewHolder.title.setText(circleData.getNickName() + "");//
        viewHolder.litterImage.setImageURI(Uri.parse(circleData.getHeadPic()) + "");//头像
        viewHolder.wenzi.setText(circleData.getContent());
        String timedate = ToDate.timedate(circleData.getCreateTime());
        viewHolder.qz_date.setText(timedate + "");

        viewHolder.number.setVisibility(View.GONE);
        viewHolder.image.setVisibility(View.GONE);
        // 图片
        int lieNum;
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
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView qz_date;
        RecyclerGridView gridView;
        SimpleDraweeView litterImage;
        TextView wenzi;
        TextView number;
        TextView title;
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
            image = itemView.findViewById(R.id.qz_prise);

            imageAdapter = new ImageAdapter(context);// 适配器
            int size = context.getResources().getDimensionPixelSize(R.dimen.dp_10);// 条目间的间距
            gridView.addItemDecoration(new SpacingItemDecoration(size));// 添加间距
            gridLayoutManager = new GridLayoutManager(context, 2);//
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);//设置适配器

        }
    }
}
