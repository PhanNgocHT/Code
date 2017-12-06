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
 * Created by Dung Ali on 8/11/2017.
 */

public class BienChungThaiKyAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String> giaiDoanBienChungs;
    HashMap<String,ArrayList<String>> tenBienChungs;

    public BienChungThaiKyAdapter(Context context, ArrayList<String> giaiDoanBienChungs, HashMap<String, ArrayList<String>> tenBienChung) {
        this.context = context;
        this.giaiDoanBienChungs = giaiDoanBienChungs;
        this.tenBienChungs = tenBienChung;
    }

    @Override
    public int getGroupCount() {
        return giaiDoanBienChungs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return tenBienChungs.get(giaiDoanBienChungs.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return giaiDoanBienChungs.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childrenPosition) {
        return tenBienChungs.get(giaiDoanBienChungs.get(groupPosition)).get(childrenPosition);
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
        String giai_doan_bien_chung = (String) getGroup(groupPosition);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.item_parent_bien_chung_thai_ky,viewGroup,false);
        TextView tvGiaiDoanBienChung = view.findViewById(R.id.tvGiaiDoanBienChung);
        tvGiaiDoanBienChung.setText(giai_doan_bien_chung);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childrenPosition, boolean b, View view, ViewGroup viewGroup) {
        String ten_bien_chung = (String) getChild(groupPosition,childrenPosition);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.item_children_bien_chung_thai_ky,viewGroup,false);
        TextView tvTenBienChung = view.findViewById(R.id.tvTenBienChung);
        tvTenBienChung.setText(ten_bien_chung);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
