package com.zuckerteam.fragment.sotaytiemphong;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.Utilitis;
import com.zuckerteam.adapter.TheoDoiTreAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.sotaytiemphong.TreTiemPhong;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/29/2017.
 */

public class FragmentTheoDoiTre extends Fragment implements View.OnClickListener,TheoDoiTreAdapter.OnClickItemPopupMenu, CompoundButton.OnCheckedChangeListener {
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView rcvTheoDoiTre;
    private TextView tvHuongDanTheoDoiTre;
    private TheoDoiTreAdapter adapter;
    private ArrayList<TreTiemPhong> treTiemPhongs;
    private FloatingActionButton btnAdd;
    private DatabaseClass database;
    private ImageView imgAnhDaiDienCuaTre;
    private EditText edtTenCuaTre;
    private EditText edtNgaySinhCuaTre;
    private EditText edtThangSinhCuaTre;
    private EditText edtNamSinhCuaTre;
    private ImageButton btnDongDialog;
    private ImageButton btnHoanThanh;
    private RadioButton radGioiTinhNam;
    private RadioButton radGioiTinhNu;
    private TreTiemPhong treTiemPhong;
    private Dialog dialog;
    int ngayHienTai = Constant.NGAY_HIEN_TAI;
    int thangHienTai = Constant.THANG_HIEN_TAI;
    int namHienTai = Constant.NAM_HIEN_TAI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theo_doi_tre, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
        addEvents();
    }


    private void addControls() {
        coordinatorLayout = getActivity().findViewById(R.id.coordinatorLayout);
        tvHuongDanTheoDoiTre = getActivity().findViewById(R.id.tvHuongDanTheoDoiTre);
        rcvTheoDoiTre = getActivity().findViewById(R.id.rcvTheoDoiTre);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvTheoDoiTre.setLayoutManager(layoutManager);
        layDuLieu();
        adapter = new TheoDoiTreAdapter(getActivity(), treTiemPhongs);
        adapter.notifyDataSetChanged();
        adapter.setOnClickItemPopupMenu(this);
        rcvTheoDoiTre.setAdapter(adapter);
        btnAdd = getActivity().findViewById(R.id.btnAdd);
        if(treTiemPhongs.isEmpty()){
            tvHuongDanTheoDoiTre.setVisibility(View.VISIBLE);
            rcvTheoDoiTre.setVisibility(View.INVISIBLE);
        }else {
            tvHuongDanTheoDoiTre.setVisibility(View.INVISIBLE);
            rcvTheoDoiTre.setVisibility(View.VISIBLE);
        }
    }

    private void layDuLieu() {
        treTiemPhongs = new ArrayList<>();
        database = new DatabaseClass(getActivity());
        treTiemPhongs.addAll(database.getData("TreTiemPhong"));
    }

    private void addEvents() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                handlePressAdd();
                break;
            case R.id.btnDongDialog:
                dialog.dismiss();
                break;
            case R.id.btnHoanThanh:
                handlePressHoanThanh();
                break;
            default:
                break;
        }
    }

    private void handlePressHoanThanh() {
        //TODO : Khi nhấn hoàn thanh thì thêm dữ liệu vào database
        String tenCuaTre = edtTenCuaTre.getText().toString().trim();
        String strNgaySinh = edtNgaySinhCuaTre.getText().toString().trim();
        String strThangSinh = edtThangSinhCuaTre.getText().toString().trim();
        String strNamSinh = edtNamSinhCuaTre.getText().toString().trim();

        String gioiTinh = "";

        if (radGioiTinhNam.isChecked()) {
            gioiTinh = radGioiTinhNam.getText().toString();
        } else if (radGioiTinhNu.isChecked()) {
            gioiTinh = radGioiTinhNu.getText().toString();
        }
        if (tenCuaTre.isEmpty()) {
            Snackbar.make(coordinatorLayout, "Bạn cần điền tên vào", Snackbar.LENGTH_LONG).show();
            return;
        } else if (strNgaySinh.isEmpty() ||
                strThangSinh.isEmpty() ||
                strNamSinh.isEmpty()) {
            Snackbar.make(coordinatorLayout, "Bạn cần điền đầy đủ ngày tháng năm sinh", Snackbar.LENGTH_LONG).show();
            return;
        }
        int ngaySinh = Integer.parseInt(strNgaySinh);
        int thangSinh = Integer.parseInt(strThangSinh);
        int namSinh = Integer.parseInt(strNamSinh);
        if (thangSinh < 1 || thangSinh > 12) {
            Snackbar.make(coordinatorLayout, "Điền tháng trong khoảng từ 1 đến 12 !", Snackbar.LENGTH_LONG).show();
            return;
        } else if (namHienTai - namSinh > 17) {
            Snackbar.make(coordinatorLayout, "Đứa trẻ này đã hết lịch tiêm phòng !", Snackbar.LENGTH_LONG).show();
            return;
        } else if (ngaySinh < 1 || ngaySinh > Utilitis.soNgayTrongMotThang(thangSinh,namSinh)) {
            Snackbar.make(coordinatorLayout, "Không tồn tại ngày này", Snackbar.LENGTH_LONG).show();
            return;
        } else if (namSinh > namHienTai) {
            Snackbar.make(coordinatorLayout, "Ngày tháng năm sinh không phù hợp", Snackbar.LENGTH_LONG).show();
            return;
        }else if(namSinh==namHienTai) {
            if(thangSinh==thangHienTai){
                if(ngaySinh>ngayHienTai){
                    Snackbar.make(coordinatorLayout, "Ngày tháng năm sinh không phù hợp !", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }else if(thangSinh>thangHienTai){
                Snackbar.make(coordinatorLayout, "Ngày tháng năm sinh không phù hợp !", Snackbar.LENGTH_LONG).show();
                return;
            }
        }else if (gioiTinh.isEmpty()) {
            Snackbar.make(coordinatorLayout, "Bạn cần chọn giới tính cho đứa trẻ này !", Snackbar.LENGTH_LONG).show();
            return;
        }

        treTiemPhong = new TreTiemPhong(tenCuaTre, ngaySinh, thangSinh, namSinh, gioiTinh);
        database.insertData(treTiemPhong, "TreTiemPhong");
        treTiemPhongs.clear();
        treTiemPhongs.addAll(database.getData("TreTiemPhong"));
        adapter.notifyDataSetChanged();
        dialog.dismiss();
        if(tvHuongDanTheoDoiTre.isShown()){
            tvHuongDanTheoDoiTre.setVisibility(View.INVISIBLE);
            rcvTheoDoiTre.setVisibility(View.VISIBLE);
        }
    }

    private void handlePressAdd() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_them_tre, null, false);
        imgAnhDaiDienCuaTre = v.findViewById(R.id.imgAnhDaiDienCuaTre);
        edtTenCuaTre = v.findViewById(R.id.edtTenCuaTre);
        edtNgaySinhCuaTre = v.findViewById(R.id.edtNgaySinhCuaTre);
        edtThangSinhCuaTre = v.findViewById(R.id.edtThangSinhCuaTre);
        edtNamSinhCuaTre = v.findViewById(R.id.edtNamSinhCuaTre);
        radGioiTinhNam = v.findViewById(R.id.radGioiTinhNam);
        radGioiTinhNu = v.findViewById(R.id.radGioiTinhNu);
        btnDongDialog = v.findViewById(R.id.btnDongDialog);
        btnHoanThanh = v.findViewById(R.id.btnHoanThanh);

        btnDongDialog.setOnClickListener(this);
        btnHoanThanh.setOnClickListener(this);
        radGioiTinhNam.setOnCheckedChangeListener(this);
        radGioiTinhNu.setOnCheckedChangeListener(this);

        dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onClickItemXoa(final int position) {
            database.deleteData(treTiemPhongs.get(position),"TreTiemPhong");
            treTiemPhongs.clear();
            treTiemPhongs.addAll(database.getData("TreTiemPhong"));
            adapter.notifyDataSetChanged();
            Snackbar.make(coordinatorLayout,"Đã xóa thành công !",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.radGioiTinhNam) {
            if (b == true) {
                imgAnhDaiDienCuaTre.setImageResource(R.drawable.icon_con_trai);
            }
        }else if(compoundButton.getId() == R.id.radGioiTinhNu){
            if(b==true){
                imgAnhDaiDienCuaTre.setImageResource(R.drawable.icon_con_gai);
            }
        }
    }
}
