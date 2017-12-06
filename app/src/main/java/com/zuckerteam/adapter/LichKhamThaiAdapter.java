package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.lich_kham_thai.LichKhamThai;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class LichKhamThaiAdapter extends RecyclerView.Adapter<LichKhamThaiAdapter.ViewHolder> {
    Context context;
    ArrayList<LichKhamThai> lichKhamThais;

    public LichKhamThaiAdapter(Context context, ArrayList<LichKhamThai> lichKhamThais) {
        this.context = context;
        this.lichKhamThais = lichKhamThais;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_lich_kham_thai, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LichKhamThai lichKhamThai = lichKhamThais.get(position);
        String lan_kham = lichKhamThai.getLanKham();
        String tuan_kham = lichKhamThai.getTuanKham();
        String lan_kham_thai = lan_kham + ": " + tuan_kham;
        String noi_dung_kham = lichKhamThai.getNoiDungKham();
        if (lichKhamThai.getId()==1) {
            holder.tvLanKhamThai.setText(lan_kham);
        } else {
            holder.tvLanKhamThai.setText(lan_kham_thai);
        }
        holder.tvNoiDungKham.setText(noi_dung_kham);
    }

    @Override
    public int getItemCount() {
        return lichKhamThais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLanKhamThai;
        private TextView tvNoiDungKham;

        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
        }

        private void addControls(View itemView) {
            tvLanKhamThai = itemView.findViewById(R.id.tvLanKhamThai);
            tvNoiDungKham = itemView.findViewById(R.id.tvNoiDungKham);
        }
    }


}
