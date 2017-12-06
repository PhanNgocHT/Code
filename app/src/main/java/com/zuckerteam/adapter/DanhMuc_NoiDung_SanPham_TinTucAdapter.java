package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.danhmuc_noidung_sanpham_tintuc.DanhMuc_NoiDung_SanPham_TinTuc;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/1/2017.
 */

/**
 * Lớp Adapter dùng chung cho các item chuyên mục
 * Danh mục
 * Nội Dung
 * Sản phẩm
 * Tin tức mẹ và bé
 */

public class DanhMuc_NoiDung_SanPham_TinTucAdapter extends RecyclerView.Adapter<DanhMuc_NoiDung_SanPham_TinTucAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<DanhMuc_NoiDung_SanPham_TinTuc> danhMucs;
    private int layoutResource;
    private onItemClick onItemClick;

    public DanhMuc_NoiDung_SanPham_TinTucAdapter(Context context, ArrayList<DanhMuc_NoiDung_SanPham_TinTuc> danhMucs, int layoutResource) {
        this.context = context;
        this.danhMucs = danhMucs;
        this.layoutResource = layoutResource;
    }

    public void setOnItemClick(DanhMuc_NoiDung_SanPham_TinTucAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(layoutResource, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DanhMuc_NoiDung_SanPham_TinTuc danhMuc = danhMucs.get(position);
        holder.imgAnhDanhMuc.setImageResource(danhMuc.getAnhDanhMuc());
        holder.tvTenDanhMuc.setText(danhMuc.getTenDanhMuc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClicked(holder.getAdapterPosition(), danhMuc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhMucs.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAnhDanhMuc;
        private TextView tvTenDanhMuc;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAnhDanhMuc = itemView.findViewById(R.id.imgAnhDanhMuc);
            tvTenDanhMuc = itemView.findViewById(R.id.tvTenDanhMuc);
        }
    }

    public interface onItemClick {
        //Biến object sau này được dùng bên hàm MainActivity để phần biệt
        //xem đối tượng click là thuộc danh mục nội dung hay sản phẩm
        void onItemClicked(int position, DanhMuc_NoiDung_SanPham_TinTuc object);
    }
}
