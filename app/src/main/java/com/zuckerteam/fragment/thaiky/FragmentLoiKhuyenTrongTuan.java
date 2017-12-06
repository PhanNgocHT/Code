package com.zuckerteam.fragment.thaiky;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.mevabe.thaiky.BieuDoThaiKyActivity;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;

/**
 * Created by Dung Ali on 8/11/2017.
 */

public class FragmentLoiKhuyenTrongTuan extends Fragment {
    TextView tvLoiKhuyenTrongTuan;
    String loiKhuyenTrongTuan;
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_loi_khuyen_trong_tuan,container,false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        layDuLieu();
        addControls();
        addEvents();
    }

    private void layDuLieu() {
        Intent intent = getActivity().getIntent();
        BieuDoThaiKy bieuDoThaiKy = (BieuDoThaiKy) intent.getSerializableExtra(Constant.KEY_BIEU_DO_THAI_KY);
        loiKhuyenTrongTuan = bieuDoThaiKy.getLoiKhuyenTrongTuan();
    }

    private void addControls() {
        tvLoiKhuyenTrongTuan = (TextView) getActivity().findViewById(R.id.tvLoiKhuyenTrongTuan);
        tvLoiKhuyenTrongTuan.setText(loiKhuyenTrongTuan);
    }

    private void addEvents() {
    }

}
