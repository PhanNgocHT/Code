package com.zuckerteam.fragment.sotaytiemphong;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.adapter.SoTayTiemPhongAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.sotaytiemphong.BenhTiemPhong;
import com.zuckerteam.model.sotaytiemphong.LichTiemPhong;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dung Ali on 8/29/2017.
 */

public class FragmentLichTiemPhong extends Fragment {
    private static final String TAG = "LichTiemPhong";
    private ExpandableListView expListViewSoTayTiemPhong;
    private ArrayList<String> thoiGianTiemPhongs;
    private HashMap<String, ArrayList<String>> tenBenhTiemPhongs;
    private DatabaseClass database;
    private String tableLichTiemPhong = "LichTiemPhong";
    private String tableBenhTiemPhong = "BenhTiemPhong";
    private ArrayList<LichTiemPhong> lichTiemPhongs;
    private ArrayList<BenhTiemPhong> benhTiemPhongs;
    private SoTayTiemPhongAdapter soTayTiemPhongAdapter;
    int lastGroupOpened = 0;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment_lich_tiem_phong,container,false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
        addEvents();
    }
    private void addControls() {
        expListViewSoTayTiemPhong = (ExpandableListView) getActivity().findViewById(R.id.expListViewSotayTiemPhong);
        layDuLieu();
        soTayTiemPhongAdapter = new SoTayTiemPhongAdapter(getActivity(), thoiGianTiemPhongs, tenBenhTiemPhongs);
        expListViewSoTayTiemPhong.setAdapter(soTayTiemPhongAdapter);
    }

    private void layDuLieu() {
        database = new DatabaseClass(getActivity());
        lichTiemPhongs = new ArrayList<>();
        lichTiemPhongs.addAll(database.getData(tableLichTiemPhong));
        benhTiemPhongs = new ArrayList<>();
        benhTiemPhongs.addAll(database.getData(tableBenhTiemPhong));
        thoiGianTiemPhongs = new ArrayList<String>();
        tenBenhTiemPhongs = new HashMap<String, ArrayList<String>>();
        String thoi_gian;
        String ten_benh;
        //Thêm các group và ExpandleListview
        for (int i = 0; i < lichTiemPhongs.size(); i++) {
            thoi_gian = lichTiemPhongs.get(i).getThoiGianTiemPhong();
            if (!thoiGianTiemPhongs.contains(thoi_gian))
                thoiGianTiemPhongs.add(thoi_gian);
        }

        //Thêm các children vào từng group
        for (int i = 0; i < thoiGianTiemPhongs.size(); i++) {
            ArrayList<String> arrTenBenh = new ArrayList<>();
            String key = thoiGianTiemPhongs.get(i);
            for (int j = 0; j < lichTiemPhongs.size(); j++) {
                thoi_gian = lichTiemPhongs.get(j).getThoiGianTiemPhong();
                ten_benh = lichTiemPhongs.get(j).getTenBenhTiemPhong();
                if (thoiGianTiemPhongs.get(i).equals(thoi_gian)) {
                    arrTenBenh.add(ten_benh);
                }
            }
            tenBenhTiemPhongs.put(key, arrTenBenh);
        }


    }

    private void addEvents() {
        expListViewSoTayTiemPhong.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView
                    , View view, int groupPosition, int childrenPosition, long l) {
                handleClickChildrenItem(groupPosition, childrenPosition);
                return false;
            }
        });

        expListViewSoTayTiemPhong.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastGroupOpened != groupPosition) {
                    expListViewSoTayTiemPhong.collapseGroup(lastGroupOpened);
                }
                lastGroupOpened = groupPosition;
            }
        });
    }

    private void handleClickChildrenItem(int groupPosition, int childrenPosition) {
        String ten_benh = tenBenhTiemPhongs.get(thoiGianTiemPhongs.get(groupPosition)).get(childrenPosition);
        for (int i = 0; i < benhTiemPhongs.size(); i++) {
            if (benhTiemPhongs.get(i).getTenBenhTiemPhong().equals(ten_benh)) {
                String mo_ta_benh_tiem_phong = benhTiemPhongs.get(i).getMoTaBenhTiemPhong();
                handleHienThiMoTaBenhTiemPhong(ten_benh, mo_ta_benh_tiem_phong);
                continue;
            }
        }
    }

    private void handleHienThiMoTaBenhTiemPhong(String tenBenhTiemPhong, String moTaBenhTiemPhong) {
        dialog = new Dialog(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_mo_ta_benh_tiem_phong, null, false);
        TextView tvTenBenhTiemPhong = v.findViewById(R.id.tvTenBenhTiemPhong);
        TextView tvMoTaBenhTiemPhong = v.findViewById(R.id.tvMoTaBenhTiemPhong);
        tvTenBenhTiemPhong.setText(tenBenhTiemPhong);
        tvMoTaBenhTiemPhong.setText(moTaBenhTiemPhong);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
