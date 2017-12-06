package com.zuckerteam.fragment.muasam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.DoDungSauSinhAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.muasam.DoDungSauSinh;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class FragmentDoDungSauSinh extends Fragment {
    RecyclerView rcvDoDungSauSinh;
    ArrayList<DoDungSauSinh> doDungSauSinhs;
    DoDungSauSinhAdapter adapter;
    DatabaseClass database;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_do_dung_sau_sinh,container,false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
    }

    private void addControls() {
        rcvDoDungSauSinh = getActivity().findViewById(R.id.rcvDoDungSauSinh);
        rcvDoDungSauSinh.setLayoutManager(new LinearLayoutManager(getActivity()));
        layDuLieu();
        adapter = new DoDungSauSinhAdapter(getActivity(),doDungSauSinhs);
        rcvDoDungSauSinh.setAdapter(adapter);
    }

    private void layDuLieu() {
        doDungSauSinhs = new ArrayList<>();
        database = new DatabaseClass(getActivity());
        doDungSauSinhs.addAll(database.getData(Constant.TABLE_DO_DUNG_SAU_SINH));
    }
}
