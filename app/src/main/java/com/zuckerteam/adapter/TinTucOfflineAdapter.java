package com.zuckerteam.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.TinTucYeuThich;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Dung Ali on 8/24/2017.
 */

public class TinTucOfflineAdapter extends RecyclerView.Adapter<TinTucOfflineAdapter.ViewHolder> {

    private Context context;
    ArrayList<TinTucYeuThich> tinTucYeuThiches;
    OnItemClick onItemClick;

    public TinTucOfflineAdapter(Context context, ArrayList<TinTucYeuThich> tinTucYeuThiches) {
        this.context = context;
        this.tinTucYeuThiches = tinTucYeuThiches;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_chi_tiet_tin_tuc_me_va_be,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TinTucYeuThich tinTuc = tinTucYeuThiches.get(position);
        Glide.with(this.context)
                .load(tinTuc.getAnhTinTuc())
                .into(holder.imgAnhTinTuc);
        holder.tvTieuDeTinTuc.setText(tinTuc.getTieuDeTinTuc());
        holder.tvMoTaTinTuc.setText(tinTuc.getMoTaTinTuc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tinTucYeuThiches.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAnhTinTuc;
        TextView tvTieuDeTinTuc;
        TextView tvMoTaTinTuc;

        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
        }

        private void addControls(View itemView) {
            imgAnhTinTuc = itemView.findViewById(R.id.imgAnhTinTuc);
            tvTieuDeTinTuc = itemView.findViewById(R.id.tvTieuDeTinTuc);
            tvMoTaTinTuc = itemView.findViewById(R.id.tvMoTaTinTuc);
        }
    }

    public interface OnItemClick{
       void onItemClicked(int position);
    }



}
