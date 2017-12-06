package com.zuckerteam.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.KeChuyenChoBe;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/16/2017.
 */

public class CustomKeChuyenChoBeViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<KeChuyenChoBe> keChuyenChoBes;
    private LayoutInflater layoutInflater;

    public CustomKeChuyenChoBeViewPagerAdapter(Context context,ArrayList<KeChuyenChoBe> keChuyenChoBes) {
        this.keChuyenChoBes = keChuyenChoBes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return keChuyenChoBes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.item_viewpager_noi_dung_chuyen,container,false);
        TextView tvTenChuyen = item_view.findViewById(R.id.tvTenChuyen);
        TextView tvNoiDungChuyen = item_view.findViewById(R.id.tvNoiDungChuyen);
        TextView tvBaiHocCuaChuyen = item_view.findViewById(R.id.tvBaiHocCuaChuyen);

        KeChuyenChoBe keChuyenChoBe = keChuyenChoBes.get(position);
        tvTenChuyen.setText(keChuyenChoBe.getTenChuyen());
        tvNoiDungChuyen.setText(keChuyenChoBe.getNoiDungChuyen());
        tvBaiHocCuaChuyen.setText(keChuyenChoBe.getBaiHocCuaChuyen());

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
