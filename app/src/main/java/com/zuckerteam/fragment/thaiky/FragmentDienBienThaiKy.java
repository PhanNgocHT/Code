package com.zuckerteam.fragment.thaiky;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;

/**
 * Created by Dung Ali on 8/11/2017.
 */

public class FragmentDienBienThaiKy extends Fragment {
    private ImageView imgAnhThaiNhi;
    private TextView tvTheTrangThaiPhu;
    private TextView tvTamLyThaiPhu;
    private TextView tvTinhTrangThaiNhi;
    private byte[] anhThaiNhi;
    private String tamCaNguyet;
    private String theTrangThaiPhu;
    private String tamLyThaiPhu;
    private String tinhTrangThaiNhi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dien_bien_thai_ky, container, false);
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
        anhThaiNhi = bieuDoThaiKy.getAnhThaiNhi();
        tamCaNguyet = bieuDoThaiKy.getTamCaNguyet();
        theTrangThaiPhu = bieuDoThaiKy.getTheTrangThaiPhu();
        tamLyThaiPhu = bieuDoThaiKy.getTamLyThaiPhu();
        tinhTrangThaiNhi = bieuDoThaiKy.getTinhTrangThaiNhi();
    }

    private void addControls() {
        imgAnhThaiNhi = getActivity().findViewById(R.id.imgAnhThaiNhi);
        tvTheTrangThaiPhu = getActivity().findViewById(R.id.tvTheTrangThaiPhu);
        tvTamLyThaiPhu = getActivity().findViewById(R.id.tvTamLyThaiPhu);
        tvTinhTrangThaiNhi = getActivity().findViewById(R.id.tvTinhTrangThaiNhi);
        hienThiDuLieu();
    }

    private void hienThiDuLieu() {
        imgAnhThaiNhi.setImageBitmap(BitmapFactory.decodeByteArray(anhThaiNhi, 0, anhThaiNhi.length));
        tvTheTrangThaiPhu.setText(theTrangThaiPhu);
        tvTamLyThaiPhu.setText(tamLyThaiPhu);
        tvTinhTrangThaiNhi.setText(tinhTrangThaiNhi);
    }

    private void addEvents() {

    }
}
