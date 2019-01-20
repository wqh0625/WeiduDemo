package demo.com.demo01.core.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.BottonBean;

/**
 * 作者: Wang on 2019/1/6 15:09
 * 寄语：加油！相信自己可以！！！
 */

public class BottonAdapter extends RecyclerView.Adapter<BottonAdapter.ViewHolder> {
    private List<BottonBean> list;

    private onClickk onClickk;

    public BottonAdapter() {
        this.list = new ArrayList<>();
    }

    public void setOnClickk(BottonAdapter.onClickk onClickk) {
        this.onClickk = onClickk;
    }

    public interface onClickk {
        void click(String id,String name);
    }

    public void setList(List<BottonBean> list) {
        if (list != null) {
            this.list.addAll(list);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.pop_bottom, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.text.setText(list.get(i).getName());
        // 点击传name
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickk.click(list.get(i).getId(),list.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void remove() {
        list.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.pop_bon_text);
        }
    }
}
