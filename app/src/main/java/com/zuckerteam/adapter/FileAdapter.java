package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/23/2017.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder>{
    private Context context;
    private ArrayList<File> files;
    private OnClickItem onClickItem;

    public FileAdapter(Context context, ArrayList<File> files, OnClickItem onClickItem) {
        this.context = context;
        this.files = files;
        this.onClickItem = onClickItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_music,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        File file = files.get(position);
        holder.tvSoThuTu.setText(position+1+"");
        holder.tvSongName.setText(file.getName().replace(".mp3",""));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoThuTu;
        TextView tvSongName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSoThuTu = itemView.findViewById(R.id.tvSoThuTu);
            tvSongName = itemView.findViewById(R.id.tvSongName);
        }
    }

    public interface OnClickItem{
        void onClicked(int position);
    }
}