package com.zuckerteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dung Ali on 8/8/2017.
 */

public class SoTayTiemPhongAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> thoiGianTiemPhongs;
    private HashMap<String, ArrayList<String>> tenBenhTiemPhongs;

    public SoTayTiemPhongAdapter(Context context,
                                 ArrayList<String> thoiGianTiemPhongs,
                                 HashMap<String, ArrayList<String>> tenBenhTiemPhongs) {
        this.context = context;
        this.thoiGianTiemPhongs = thoiGianTiemPhongs;
        this.tenBenhTiemPhongs = tenBenhTiemPhongs;
    }

    @Override
    public int getGroupCount() {
        return thoiGianTiemPhongs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return tenBenhTiemPhongs.get(thoiGianTiemPhongs.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return thoiGianTiemPhongs.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childrenPosition)
    {
        return tenBenhTiemPhongs.get(thoiGianTiemPhongs.get(groupPosition)).get(childrenPosition);
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
        String thoi_gian_tiem_phong = (String) getGroup(groupPosition);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_parent_so_tay_tiem_phong,null);
        TextView tvThoiGianTiemPhong = v.findViewById(R.id.tvThoiGianTiemPhong);
        tvThoiGianTiemPhong.setText(thoi_gian_tiem_phong);
        return v;
    }

    @Override
    public View getChildView(int childrenPositon, int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String ten_benh_tiem_phong = (String) getChild(childrenPositon,groupPosition);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_children_so_tay_tiem_phong,null);
        TextView tvTenBenhTiemPhong = v.findViewById(R.id.tvTenBenhTiemPhong);
        tvTenBenhTiemPhong.setText(ten_benh_tiem_phong);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
