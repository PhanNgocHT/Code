package com.zuckerteam.mevabe.thaiky;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.BienChungThaiKyAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.BienChungTrongThaiKy;
import com.zuckerteam.model.thaiky.GiaiDoanBienChungTrongThaiKy;

import java.util.ArrayList;
import java.util.HashMap;

public class BienChungThaiKyActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageButton btnTroVe;
    TextView tvTieuDe;
    ExpandableListView expBienChungThaiKy;
    ArrayList<GiaiDoanBienChungTrongThaiKy> giaiDoanBienChungs;
    ArrayList<BienChungTrongThaiKy> bienChungThaiKies;
    BienChungThaiKyAdapter adapter;
    ArrayList<String> tenGianDoans;
    HashMap<String, ArrayList<String>> tenBienChungs;
    DatabaseClass database;
    int lastGroupOpened = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_chung_thai_ky);
        setUpToolbar();
        addControls();
        addEvents();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Biến chứng thai kỳ");
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        expBienChungThaiKy = (ExpandableListView) findViewById(R.id.expBienChungThaiKy);
        layDuLieu();
        adapter = new BienChungThaiKyAdapter(this, tenGianDoans, tenBienChungs);
        expBienChungThaiKy.setAdapter(adapter);
    }

    private void layDuLieu() {
        //lay du lieu
        database = new DatabaseClass(this);
        giaiDoanBienChungs = new ArrayList<>();
        bienChungThaiKies = new ArrayList<>();
        giaiDoanBienChungs.addAll(database.getData(Constant.TABLE_GIAI_DOAN_BIEN_CHUNG_TRONG_THAI_KY));
        bienChungThaiKies.addAll(database.getData(Constant.TABLE_BIEN_TRONG_CHUNG_THAI_KY));
        tenGianDoans = new ArrayList<>();
        tenBienChungs = new HashMap<>();
        //cho du lieu vao expListView
        for (int i = 0; i < giaiDoanBienChungs.size(); i++) {
            if (!tenGianDoans.contains(giaiDoanBienChungs.get(i).getTenGiaiDoan()))
                tenGianDoans.add(giaiDoanBienChungs.get(i).getTenGiaiDoan());
        }
        for (int i = 0; i < tenGianDoans.size(); i++) {
            ArrayList<String> arr = new ArrayList<>();
            for (int j = 0; j < giaiDoanBienChungs.size(); j++) {
                String strGiaiDoan = giaiDoanBienChungs.get(j).getTenGiaiDoan();
                String strBienChung = giaiDoanBienChungs.get(j).getTenBienChung();
                if (strGiaiDoan.equals(tenGianDoans.get(i)))
                    arr.add(strBienChung);
            }
            tenBienChungs.put(tenGianDoans.get(i), arr);
        }
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        expBienChungThaiKy.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastGroupOpened != groupPosition) {
                    expBienChungThaiKy.collapseGroup(lastGroupOpened);
                }
                lastGroupOpened = groupPosition;
            }
        });
        expBienChungThaiKy.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childrenPositon, long l) {
                if (groupPosition != 0 && groupPosition != tenGianDoans.size()-1)
                    handleClickChildren(groupPosition, childrenPositon);
                return false;
            }
        });
    }

    private void handleClickChildren(int groupPosition, int childrenPositon) {
        String tenBienChung = tenBienChungs.get(tenGianDoans.get(groupPosition)).get(childrenPositon);
        String noiDungBienChung = "";
        for (int i = 0; i < bienChungThaiKies.size(); i++) {
            if (tenBienChung.equals(bienChungThaiKies.get(i).getTenBienChung())) {
                noiDungBienChung = bienChungThaiKies.get(i).getNoiDungBienChung();
            }
        }
        Dialog dialog = new Dialog(BienChungThaiKyActivity.this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_noi_dung_bien_chung_trong_thai_ky, null, false);
        TextView tvTenBienChung = v.findViewById(R.id.tvTenBienChung);
        TextView tvNoiDungBienChung = v.findViewById(R.id.tvNoiDungBienChung);
        tvTenBienChung.setText(tenBienChung);
        tvNoiDungBienChung.setText(noiDungBienChung);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        dialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTroVe:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
