package demo.com.demo01.core.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.MyCircleData;
import demo.com.demo01.core.utils.RecyclerGridView;
import demo.com.demo01.core.utils.SpacingItemDecoration;
import demo.com.demo01.core.utils.StringUtils;
import demo.com.demo01.core.utils.ToDate;

/**
 * 作者: Wang on 2019/1/4 19:48
 * 寄语：加油！相信自己可以！！！
 */


public class MyCircleRecylerViewAdapter extends RecyclerView.Adapter<MyCircleRecylerViewAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<MyCircleData> dataList;

    public void setDataList(List<MyCircleData> dataList) {
        if (dataList != null) {
            this.dataList.addAll(dataList);
        }
    }

    public MyCircleRecylerViewAdapter(FragmentActivity context) {
        this.context = context;
        this.dataList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mycircle_data_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyCircleData myCircleData = dataList.get(i);
        viewHolder.date.setText(ToDate.timedate(myCircleData.getCreateTime()) + "");//发布时间呢
        viewHolder.dznumber.setText(myCircleData.getGreatNum() + "");// 设置点赞数量

        viewHolder.text.setText(myCircleData.getContent()+"");// 设置文字
        viewHolder.prise.setImageResource(R.drawable.common_btn_prise_s);// 设置图片

        if (StringUtils.isEmpty(myCircleData.getImage())) {
            viewHolder.grid.setVisibility(View.GONE);
        } else {
            viewHolder.grid.setVisibility(View.VISIBLE);

            String[] split = myCircleData.getImage().split(",");
            int lieNum;
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
//            viewHolder.imageAdapter.clear();// 清空集合
            viewHolder.imageAdapter.addAll(list);// 添加图片
            viewHolder.imageAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final RecyclerGridView grid;
        private final TextView dznumber;
        private final ImageView prise;
        private final TextView text;
        private final ImageAdapter2 imageAdapter;
        private final GridLayoutManager gridLayoutManager;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.myqz_date);
            dznumber = itemView.findViewById(R.id.myqz_number);
            prise = itemView.findViewById(R.id.myqz_prise);
            text = itemView.findViewById(R.id.myqz_text);
//
            grid = itemView.findViewById(R.id.myqz_gridview);
//
            imageAdapter = new ImageAdapter2(context);
            int size = context.getResources().getDimensionPixelSize(R.dimen.dp_10);// 图片间距
            grid.addItemDecoration(new SpacingItemDecoration(size));
//            grid.setHorizontalSpacing(size);
//            grid.setVerticalSpacing(size);

            gridLayoutManager = new GridLayoutManager(context, 2);
            grid.setLayoutManager(gridLayoutManager);
            grid.setAdapter(imageAdapter);

        }
    }
}
