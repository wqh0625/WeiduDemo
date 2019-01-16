package demo.com.demo01.core.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.demo01.R;
import demo.com.demo01.bean.MyAddressData;

/**
 * 作者: Wang on 2019/1/5 18:02
 * 寄语：加油！相信自己可以！！！
 */


public class MyAddressListAdapter extends BaseAdapter {

    private List<MyAddressData> list;
    private Context context;

    public void setList(List<MyAddressData> list) {
        if (list != null) {
            this.list = list;
        }
    }

    public MyAddressListAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.myaddress_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.address_item_name);
            viewHolder.phone = convertView.findViewById(R.id.address_item_phone);
            viewHolder.address = convertView.findViewById(R.id.address_item_address);
            viewHolder.radioButton = convertView.findViewById(R.id.address_item_rbt);
            viewHolder.delete = convertView.findViewById(R.id.address_item_delete_Btn);
            viewHolder.update = convertView.findViewById(R.id.address_item_update_Btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyAddressData myAddressData = list.get(position);
        viewHolder.name.setText(myAddressData.getRealName());
        viewHolder.phone.setText(myAddressData.getPhone());
        viewHolder.address.setText(myAddressData.getAddress());

        if (myAddressData.getWhetherDefault() == 1) {
            viewHolder.radioButton.setChecked(true);
        } else {
            viewHolder.radioButton.setChecked(false);
        }
//            // 点击选中 则调用接口
//            if (viewHolder.radioButton.isChecked()) {
//            Toast.makeText(context, "已设为默认收货地址", Toast.LENGTH_SHORT).show();
//            } else {
//                viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    Toast.makeText(context, "设置为默认地址", Toast.LENGTH_SHORT).show();
//                    }
//                });


        // 点击删除 调用删除接口 删除收货地址


        // 点击修改 调用修改接口 修改收货地址

        return convertView;
    }

    private class ViewHolder {
        TextView name;
        TextView phone;
        TextView address;
        CheckBox radioButton;
        Button update;
        Button delete;
    }
}
