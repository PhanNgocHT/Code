package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.muasam.DoDungChoBaBau;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class DoDungBaBauAdapter extends RecyclerView.Adapter<DoDungBaBauAdapter.ViewHolder>{
    Context context;
    ArrayList<DoDungChoBaBau> doDungChoBaBaus;

    public DoDungBaBauAdapter(Context context, ArrayList<DoDungChoBaBau> doDungChoBaBaus) {
        this.context = context;
        this.doDungChoBaBaus = doDungChoBaBaus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_do_dung_cho_ba_bau,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoDungChoBaBau doDungChoBaBau = doDungChoBaBaus.get(position);
        holder.tvTenDoDung.setText(doDungChoBaBau.getTenDoDung());
        holder.tvMoTaDoDung.setText(doDungChoBaBau.getMotaDoDung());
    }

    @Override
    public int getItemCount() {
        return doDungChoBaBaus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDoDung;
        TextView tvMoTaDoDung;
        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
        }

        private void addControls(View itemView) {
            tvTenDoDung = itemView.findViewById(R.id.tvTenDoDung);
            tvMoTaDoDung = itemView.findViewById(R.id.tvMoTaDoDung);
        }
    }
}
