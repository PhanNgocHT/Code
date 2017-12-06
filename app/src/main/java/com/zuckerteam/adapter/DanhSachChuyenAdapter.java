package com.zuckerteam.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.KeChuyenChoBe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dung Ali on 9/16/2017.
 */

public class DanhSachChuyenAdapter<String> extends ArrayAdapter {
    Context context;
    ArrayList<String> danhSachChuyens;

    public DanhSachChuyenAdapter(@NonNull Context context, @NonNull ArrayList<String> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.context = context;
        this.danhSachChuyens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_listview_danh_sach_chuyen, parent, false);
            holder.tvTenChuyen = v.findViewById(R.id.tvTenChuyen);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        String tenChuyen = danhSachChuyens.get(position);
        holder.tvTenChuyen.setText(tenChuyen+"");
        return v;
    }

    public class ViewHolder {
        TextView tvTenChuyen;
    }
}
