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

public class FragmentTenBeTrai extends Fragment {
    private ExpandableListView expListViewTenBeTrai;
    private TenHayCuaBeAdapter tenHayCuaBeAdapter;
    private ArrayList<String> tenDaiDien;
    private HashMap<String,ArrayList<TenHayCuaBe>> tenHayCuaBeHashMap;
    private DatabaseClass database;
    private String tableName = "TenBeTrai";
    private ArrayList<TenHayCuaBe> tenHayCuaBes;
    private int lastPositionOpened = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ten_be_trai,container,false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        addControls();
        addEvents();
    }

    private void addControls() {
        expListViewTenBeTrai = getActivity().findViewById(R.id.expListViewTenBeTrai);
        tenDaiDien = new ArrayList<String>();
        tenHayCuaBeHashMap = new HashMap<>();
        layDuLieu();
        tenHayCuaBeAdapter = new TenHayCuaBeAdapter(getActivity(),tenDaiDien,tenHayCuaBeHashMap);
        expListViewTenBeTrai.setAdapter(tenHayCuaBeAdapter);
    }

    private void layDuLieu() {
        tenHayCuaBes = new ArrayList<TenHayCuaBe>();
        database = new DatabaseClass(getActivity());
        database.checkDatabase();
        tenHayCuaBes.addAll(database.getData(tableName));
        for(int i = 0 ; i<tenHayCuaBes.size();i++){
            ArrayList<TenHayCuaBe> arr = new ArrayList<>();
            tenDaiDien.add(tenHayCuaBes.get(i).getTenDaiDien());
            arr.add(tenHayCuaBes.get(i));
            tenHayCuaBeHashMap.put(tenDaiDien.get(i),arr);
        }

    }

    private void addEvents() {
        expListViewTenBeTrai.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastPositionOpened!=groupPosition){
                    expListViewTenBeTrai.collapseGroup(lastPositionOpened);
                }
                lastPositionOpened=groupPosition;
            }
        });

        expListViewTenBeTrai.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        expListViewTenBeTrai.collapseGroup(lastPositionOpened);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
