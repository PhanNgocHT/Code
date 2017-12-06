package com.zuckerteam.fragment.muasam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.DoDungBaBauAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.muasam.DoDungChoBaBau;

import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/14/2017.
 */

public class FragmentDoDungChoBaBau extends Fragment {
    RecyclerView rcvDoDungChoBaBau;
    ArrayList<DoDungChoBaBau> doDungChoBaBaus;
    DoDungBaBauAdapter adapter;
    DatabaseClass database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_do_dung_cho_ba_bau, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
    }

    private void addControls() {
        rcvDoDungChoBaBau = getActivity().findViewById(R.id.rcvDoDungChoBaBau);
        rcvDoDungChoBaBau.setLayoutManager(new LinearLayoutManager(getActivity()));
        layDuLieu();
        adapter = new DoDungBaBauAdapter(getActivity(), doDungChoBaBaus);
        rcvDoDungChoBaBau.setAdapter(adapter);
    }

    private void layDuLieu() {
        doDungChoBaBaus = new ArrayList<>();
        database = new DatabaseClass(getActivity());
        doDungChoBaBaus.addAll(database.getData(Constant.TABLE_DO_DUNG_CHO_BA_BAU));
    }
}
