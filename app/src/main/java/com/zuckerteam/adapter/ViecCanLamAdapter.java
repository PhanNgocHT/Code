package com.zuckerteam.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.ViecCanLam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dung Ali on 8/11/2017.
 */

public class ViecCanLamAdapter extends ArrayAdapter<ViecCanLam> {
    Context context;
    ArrayList<ViecCanLam> viecCanLams;
    public ViecCanLamAdapter(@NonNull Context context, @NonNull ArrayList<ViecCanLam> viecCanLams) {
        super(context,android.R.layout.simple_list_item_1,viecCanLams);
        this.context=context;
        this.viecCanLams=viecCanLams;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(v==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_viec_can_lam,parent,false);
            viewHolder.tvTenViecCanLam = v.findViewById(R.id.tvTenViecCanLam);
            viewHolder.tvNoiDungViecCanLam = v.findViewById(R.id.tvNoiDungViecCanLam);

            v.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) v.getTag();
        }
        ViecCanLam viecCanLam = viecCanLams.get(position);
        viewHolder.tvTenViecCanLam.setText(viecCanLam.getTenViecCanLam());
        viewHolder.tvNoiDungViecCanLam.setText(viecCanLam.getNoiDungViecCanLam());

        return v;
    }

    class ViewHolder{
        TextView tvTenViecCanLam;
        TextView tvNoiDungViecCanLam;
    }
}
