package com.zuckerteam.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.mevabe.thaiky.BienChungThaiKyActivity;
import com.zuckerteam.model.muasam.DoDungSauSinh;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class DoDungSauSinhAdapter extends RecyclerView.Adapter<DoDungSauSinhAdapter.ViewHolder> {
    Context context;
    ArrayList<DoDungSauSinh> doDungSauSinhs;

    public DoDungSauSinhAdapter(Context context, ArrayList<DoDungSauSinh> doDungSauSinhs) {
        this.context = context;
        this.doDungSauSinhs = doDungSauSinhs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_do_dung_sau_sinh, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DoDungSauSinh doDungSauSinh = doDungSauSinhs.get(position);
        holder.tvSoThuTu.setText(doDungSauSinh.getId()+1+"");
        holder.tvTenDoDung.setText(doDungSauSinh.getTenDoDung());
        holder.tvSoLuongDeNghi.setText(doDungSauSinh.getSoLuongDeNghi());
        if(position%2==0){
            holder.itemView.setBackgroundResource(R.color.colorLittePink);
        }else {
            holder.itemView.setBackgroundResource(R.color.colorSomePink);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDoDung = doDungSauSinh.getTenDoDung();
                String congDung = doDungSauSinh.getCongDung();
                createDialog(tenDoDung,congDung);
            }
        });
    }

    private void createDialog(String tenDoDung, String congDung) {
        Dialog dialog = new Dialog(this.context);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.dialog_cong_dung_cua_do_dung, null, false);
        TextView tvTenDoDung = v.findViewById(R.id.tvTenDoDung);
        TextView tvCongDung = v.findViewById(R.id.tvCongDung);
        tvTenDoDung.setText(tenDoDung);
        tvCongDung.setText(congDung);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return doDungSauSinhs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoThuTu;
        TextView tvTenDoDung;
        TextView tvSoLuongDeNghi;

        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
        }

        private void addControls(View itemView) {
            tvSoThuTu = itemView.findViewById(R.id.tvSoThuTu);
            tvTenDoDung = itemView.findViewById(R.id.tvTenDoDung);
            tvSoLuongDeNghi = itemView.findViewById(R.id.tvSoLuongDeNghi);
        }
    }
}
