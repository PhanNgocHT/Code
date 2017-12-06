package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.ChiTietTinTucMeVaBe;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/3/2017.
 * Lớp PagerAdapter dùng chung cho các item chuyên mục
 * Dinh dưỡng bà bầu ,
 * kinh nghiệm đi đẻ ,
 * Chăm sóc trẻ
 * Dạy con
 * Góc hài hước
 */

public class ChiTietTinTucMeVaBeAdapter extends RecyclerView.Adapter<ChiTietTinTucMeVaBeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes;
    private OnItemClick onItemClick;


    public ChiTietTinTucMeVaBeAdapter(Context context, ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes) {
        this.context = context;
        this.chiTietTinTucMeVaBes = chiTietTinTucMeVaBes;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.item_chi_tiet_tin_tuc_me_va_be, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ChiTietTinTucMeVaBe tinTuc = chiTietTinTucMeVaBes.get(position);
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
        return chiTietTinTucMeVaBes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

    public interface OnItemClick {
        void onItemClicked(int position);

        //void onItemLongClicked(int position);
    }


}
