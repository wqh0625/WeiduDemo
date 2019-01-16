package demo.com.demo01.core.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import demo.com.demo01.R;
import demo.com.demo01.bean.TopBean;

/**
 * 作者: Wang on 2019/1/6 15:11
 * 寄语：加油！相信自己可以！！！
 */


public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private List<TopBean> list;
     OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public TopAdapter( ) {

        this.list = new ArrayList<>();
    }

    public interface OnClick{
         void onClick(String id,String name);
    }



    public void setList(List<TopBean> list) {
        if (list != null) {
            this.list.addAll(list);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.pop_top, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.viewById.setText(list.get(i).getName());

        // 点击传ID
        viewHolder.viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(list.get(i).getId()+"",list.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView viewById;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewById = itemView.findViewById(R.id.pop_top_text);
        }
    }
}
