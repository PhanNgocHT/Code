package com.zuckerteam.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/10/2017.
 */

public class BieuDoThaiKyAdapter extends RecyclerView.Adapter<BieuDoThaiKyAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    ArrayList<BieuDoThaiKy> bieuDoThaiKies;
    int layout;
    OnClick onClick;

    public BieuDoThaiKyAdapter(Context context, ArrayList<BieuDoThaiKy> bieuDoThaiKies,int layout) {
        this.context = context;
        this.bieuDoThaiKies = bieuDoThaiKies;
        this.layout=layout;
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(this.layout, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BieuDoThaiKy bieuDoThaiKy = bieuDoThaiKies.get(position);
        byte[] byteAnh = bieuDoThaiKy.getAnhThaiNhi();
        holder.imgAnhThaiNhi
                .setImageBitmap(BitmapFactory.decodeByteArray(byteAnh, 0, byteAnh.length));
        holder.tvTuanThaiKy.setText(bieuDoThaiKy.getTuanThaiKy());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClicked(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return bieuDoThaiKies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAnhThaiNhi;
        private TextView tvTuanThaiKy;

        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
            addEvents(itemView);
        }

        private void addControls(View itemView) {
            imgAnhThaiNhi = itemView.findViewById(R.id.imgAnhThaiNhi);
            tvTuanThaiKy = itemView.findViewById(R.id.tvTuanThaiKy);
        }

        private void addEvents(View itemView) {

        }

    }

    public interface OnClick {
        void onItemClicked(int position);

    }
}
