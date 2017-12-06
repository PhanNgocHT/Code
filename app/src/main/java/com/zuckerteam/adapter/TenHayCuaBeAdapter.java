package com.zuckerteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.TenHayCuaBe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Dung Ali on 8/5/2017.
 */

public class TenHayCuaBeAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String> tenDaiDien;
    HashMap<String,ArrayList<TenHayCuaBe>> tenHayCuaBe;

    public TenHayCuaBeAdapter(Context context, ArrayList<String> tenDaiDien, HashMap<String,ArrayList<TenHayCuaBe>> tenHayCuaBe) {
        this.context = context;
        this.tenDaiDien = tenDaiDien;
        this.tenHayCuaBe = tenHayCuaBe;
    }

    @Override
    public int getGroupCount() {
        return tenDaiDien.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return tenHayCuaBe.get(tenDaiDien.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return tenDaiDien.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childrenPosition) {
        return tenHayCuaBe.get(tenDaiDien.get(groupPosition)).get(childrenPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childrenPosition) {
        return childrenPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String tenDaiDien = (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_parent_dat_ten_cho_be, viewGroup, false);
        TextView tvTenDaiDien = view.findViewById(R.id.tvTenDaiDien);
        tvTenDaiDien.setText(tenDaiDien);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childrenPosition, boolean b, View view, ViewGroup viewGroup) {
        TenHayCuaBe tenHayCuaBe = (TenHayCuaBe) getChild(groupPosition, childrenPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_children_dat_ten_cho_be, viewGroup, false);
        TextView tvYNghiaten = view.findViewById(R.id.tvYNghiaTen);
        TextView tvDanhSachTen = view.findViewById(R.id.tvDanhSachTen);
        tvYNghiaten.setText(tenHayCuaBe.getyNghiaTen());
        tvDanhSachTen.setText(tenHayCuaBe.getDanhSachten());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
