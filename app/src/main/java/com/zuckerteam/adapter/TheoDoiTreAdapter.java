package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.Utilitis;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.sotaytiemphong.BenhTiemPhong;
import com.zuckerteam.model.sotaytiemphong.LichTiemPhong;
import com.zuckerteam.model.sotaytiemphong.TreTiemPhong;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/29/2017.
 */

public class TheoDoiTreAdapter extends RecyclerView.Adapter<TheoDoiTreAdapter.ViewHolder> {

    public Context context;
    private ArrayList<TreTiemPhong> treTiemPhongs;
    private OnClickItemPopupMenu onClickItemPopupMenu;
    private String tuoiCuaTre;
    private DatabaseClass database;
    private String tableLichTiemPhong = "LichTiemPhong";
    private String tableBenhTiemPhong = "BenhTiemPhong";
    private String thoiGianTiemPhong = "";
    private ArrayList<LichTiemPhong> lichTiemPhongs;
    private ArrayList<BenhTiemPhong> benhTiemPhongs;
    private String gioiTinhTre;

    public TheoDoiTreAdapter(Context context, ArrayList<TreTiemPhong> treTiemPhongs) {
        this.context = context;
        this.treTiemPhongs = treTiemPhongs;
    }

    public void setOnClickItemPopupMenu(OnClickItemPopupMenu onClickItemPopupMenu) {
        this.onClickItemPopupMenu = onClickItemPopupMenu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.item_theo_doi_tre, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TreTiemPhong treTiemPhong = treTiemPhongs.get(position);
        String tenCuaTre = treTiemPhong.getTenTre();
        String ngaySinh = String.valueOf(treTiemPhong.getNgaySinhTre());
        String thangSinh = String.valueOf(treTiemPhong.getThangSinhTre());
        String namSinh = String.valueOf(treTiemPhong.getNamSinhTre());
        String ngayThangNamSinh = ngaySinh + "-" + thangSinh + "-" + namSinh;
        tuoiCuaTre = tinhTuoi(treTiemPhong.getNgaySinhTre()
                , treTiemPhong.getThangSinhTre()
                , treTiemPhong.getNamSinhTre());
        gioiTinhTre = treTiemPhong.getGioiTinhTre();

        holder.tvTenCuaTre.setText(tenCuaTre);
        holder.tvNgayThangNamSinhCuaTre.setText(ngayThangNamSinh);
        holder.tvTuoiCuaTre.setText(tuoiCuaTre);
        holder.tvGioiTinhCuaTre.setText(gioiTinhTre);

        if (gioiTinhTre.toUpperCase().equals("NAM")) {
            holder.imgAnhDaiDien.setImageResource(R.drawable.icon_con_trai);
        } else if (gioiTinhTre.toUpperCase().equals("NỮ")) {
            holder.imgAnhDaiDien.setImageResource(R.drawable.icon_con_gai);
        }

        layDuLieu(holder);
        addEvents(holder);
    }


    void layDuLieu(ViewHolder holder) {
        database = new DatabaseClass(context);
        lichTiemPhongs = new ArrayList<>();
        lichTiemPhongs.addAll(database.getData(tableLichTiemPhong));
        benhTiemPhongs = new ArrayList<>();
        benhTiemPhongs.addAll(database.getData(tableBenhTiemPhong));
        int tuoiCuaTre = tinhSoThangCuaMotChuoiDuaVao(holder.tvTuoiCuaTre.getText().toString());
        //SetText cho tvThoiGianTiemPhong
        for (int i = 0; i < lichTiemPhongs.size(); i++) {
            int thoiGian = tinhSoThangCuaMotChuoiDuaVao(lichTiemPhongs.get(i).getThoiGianTiemPhong());
            if (thoiGian > tuoiCuaTre) {
                String strThoiGian = lichTiemPhongs.get(i).getThoiGianTiemPhong().trim();
                if (gioiTinhTre.toLowerCase().equals("nam")) {
                    if (strThoiGian.equals("9 Tuổi") ||
                            strThoiGian.equals("9 Tuổi 2 Tháng") ||
                            strThoiGian.equals("9 Tuổi 6 Tháng") ||
                            strThoiGian.equals("10 Tuổi 2 Tháng") ||
                            strThoiGian.equals("10 Tuổi 6 Tháng"))
                        continue;
                }
                thoiGianTiemPhong=strThoiGian;
                holder.tvThoiGianTiemPhong.setText(strThoiGian);
                break;
            }
        }

        //SetText cho tvDanhSachBenhTiemPhong
        String strDanhSachBenhTiemPhong="";
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < lichTiemPhongs.size(); j++) {
                String thoi_gian = lichTiemPhongs.get(j).getThoiGianTiemPhong().trim();
                String ten_benh = lichTiemPhongs.get(j).getTenBenhTiemPhong().trim();
                if (thoiGianTiemPhong.equals(thoi_gian)) {
                    strDanhSachBenhTiemPhong+=ten_benh+"\n";
                }
            }
        }
        holder.tvDanhSachBenhTiemPhong.setText(strDanhSachBenhTiemPhong);

    }

    private void addEvents(final ViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_xoa_item_tre_theo_doi,popupMenu.getMenu());
                popupMenu.setGravity(Gravity.END);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onClickItemPopupMenu.onClickItemXoa(holder.getAdapterPosition());
                        return true;
                    }
                });
                return true;
            }
        });

    }

    private int tinhSoThangCuaMotChuoiDuaVao(String tuoi) {
        int tongSoThang = 0;
        String[] list = tuoi.trim().split(" ");
        for (int i = 0; i < list.length; i++) {
            String regex = list[i].trim().toLowerCase();
            if (regex.equals("tháng")) {
                int soThangLe = Integer.parseInt(list[i - 1].toString().trim());
                tongSoThang += soThangLe;
            } else if (regex.equals("tuổi")) {
                tongSoThang += 12 * (Integer.parseInt(list[i - 1].toString().trim()));
            }
        }
        return tongSoThang;
    }

    private String tinhTuoi(int ngaySinh, int thangSinh, int namSinh) {
        String tuoi = "";
        int ngayHienTai = Constant.NGAY_HIEN_TAI;
        int thangHienTai = Constant.THANG_HIEN_TAI;
        int namHienTai = Constant.NAM_HIEN_TAI;

        int soNam = 0;
        if (thangHienTai > thangSinh) {
            soNam = namHienTai - namSinh;
        } else if (thangHienTai < thangSinh) {
            soNam = namHienTai - namSinh - 1;
        } else if (thangHienTai == thangSinh) {
            if (ngayHienTai >= ngaySinh) {
                soNam = namHienTai - namSinh;
            } else if (ngayHienTai < ngaySinh) {
                soNam = namHienTai - namSinh - 1;
            }
        }
        int soThang = 0;
        if (thangHienTai > thangSinh) {
            if (ngayHienTai >= ngaySinh) {
                soThang = thangHienTai - thangSinh;
            } else if (ngayHienTai < ngaySinh) {
                soThang = thangHienTai - thangSinh - 1;
            }
        } else if (thangHienTai < thangSinh) {
            if (ngayHienTai >= ngaySinh) {
                soThang = thangHienTai + 12 - thangSinh;
            } else if (ngayHienTai < ngaySinh) {
                soThang = thangHienTai + 12 - thangSinh - 1;
            }
        } else if (thangHienTai == thangSinh) {
            if (ngayHienTai >= ngaySinh) {
                soThang = thangHienTai - thangSinh;
            } else if (ngayHienTai < ngaySinh) {
                soThang = thangHienTai + 12 - thangSinh - 1;
            }
        }
        int soNgayTrongThangTruocThangHienTai = Utilitis.soNgayTrongMotThang(thangHienTai - 1, namSinh);
        int soNgay = ngayHienTai >= ngaySinh ? ngayHienTai - ngaySinh : (ngayHienTai + soNgayTrongThangTruocThangHienTai - ngaySinh);

        if (soNam <= 1) {
            if (soNam == 0) {
                if (soThang == 0) {
                    tuoi = soNgay + " ngày";
                } else if (soThang > 0) {
                    tuoi = soThang + " tháng";
                }
            } else if (soNam > 0) {
                if (soThang == 0 && soNgay == 0) {
                    tuoi = soNam * 12 + " tháng";
                } else {
                    tuoi = soNam * 12 + soThang + " tháng";
                }
            }
        } else if (soNam >= 2) {
            if (soNam == 2 && soThang == 0) {
                if (soNgay == 0) {
                    tuoi = soNam * 12 + " tháng";
                } else if (soNgay > 0) {
                    tuoi = soNam * 12 + " tháng";
                }
            } else {
                if (soThang == 0) {
                    if (soNgay == 0) {
                        tuoi = soNam + " tuổi ";
                    } else if (soNgay > 0) {
                        tuoi = soNam + " tuổi ";
                    }
                } else if (soThang > 0) {
                    tuoi = soNam + " tuổi " + soThang + " tháng";
                }
            }
        }
        return tuoi;
    }

    @Override
    public int getItemCount() {
        return treTiemPhongs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhDaiDien;
        TextView tvTenCuaTre;
        TextView tvTuoiCuaTre;
        TextView tvNgayThangNamSinhCuaTre;
        TextView tvGioiTinhCuaTre;
        TextView tvThoiGianTiemPhong;
        TextView tvDanhSachBenhTiemPhong;

        public ViewHolder(View itemView) {
            super(itemView);
            addControls(itemView);
        }

        private void addControls(View itemView) {
            imgAnhDaiDien = itemView.findViewById(R.id.imgAnhDaiDienCuaTre);
            tvTenCuaTre = itemView.findViewById(R.id.tvTenCuaTre);
            tvTuoiCuaTre = itemView.findViewById(R.id.tvTuoiCuaTre);
            tvNgayThangNamSinhCuaTre = itemView.findViewById(R.id.tvNgayThangNamSinh);
            tvGioiTinhCuaTre = itemView.findViewById(R.id.tvGioiTinhCuaTre);
            tvThoiGianTiemPhong = itemView.findViewById(R.id.tvThoiGianTiemPhong);
            tvDanhSachBenhTiemPhong = itemView.findViewById(R.id.tvDanhSachBenhTiemPhong);

        }

    }

    public interface OnClickItemPopupMenu {
        void onClickItemXoa(int position);
    }


}
