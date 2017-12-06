package com.zuckerteam.fragment.dattenchobe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.zuckerteam.adapter.TenHayCuaBeAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.TenHayCuaBe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dung Ali on 8/2/2017.
 */

public class FragmentTenBeGai extends Fragment{
    String tableName = "TenBeGai";
    ExpandableListView expListViewTenBeGai;
    ArrayList<String> tenDaiDien;
    HashMap<String,ArrayList<TenHayCuaBe>> tenHayCuaBeHashMap;
    TenHayCuaBeAdapter adapter;

    DatabaseClass database;
    ArrayList<TenHayCuaBe> tenHayCuaBes;
    int lastPositionOpened=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ten_be_gai,container,false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
        addEvents();
    }

    private void addControls() {
        expListViewTenBeGai = getActivity().findViewById(R.id.expListViewTenBeGai);
        tenHayCuaBes = new ArrayList<TenHayCuaBe>();
        tenDaiDien = new ArrayList<String>();
        tenHayCuaBeHashMap = new HashMap<String,ArrayList<TenHayCuaBe>>();
        layDuLieu();
        adapter = new TenHayCuaBeAdapter(getActivity(),tenDaiDien,tenHayCuaBeHashMap);
        expListViewTenBeGai.setAdapter(adapter);
    }

    private void layDuLieu() {
        database = new DatabaseClass(getActivity());
        tenHayCuaBes.addAll(database.getData(tableName));

        for (int i=0;i<tenHayCuaBes.size();i++){
            ArrayList<TenHayCuaBe> arr = new ArrayList<>();
            tenDaiDien.add(tenHayCuaBes.get(i).getTenDaiDien());
            arr.add(tenHayCuaBes.get(i));
            tenHayCuaBeHashMap.put(tenDaiDien.get(i),arr);
        }
    }

    private void addEvents() {
        expListViewTenBeGai.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastPositionOpened!=groupPosition){
                    expListViewTenBeGai.collapseGroup(lastPositionOpened);
                }
                lastPositionOpened=groupPosition;
            }
        });

        expListViewTenBeGai.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        expListViewTenBeGai.collapseGroup(lastPositionOpened);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
