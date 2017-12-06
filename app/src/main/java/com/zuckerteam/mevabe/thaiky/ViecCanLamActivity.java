package com.zuckerteam.mevabe.thaiky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.ViecCanLamAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.thaiky.ViecCanLam;

import java.util.ArrayList;

public class ViecCanLamActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageButton btnTroVe;
    TextView tvTieuDe;
    ListView lvViecCanLam;
    ArrayList<ViecCanLam> viecCanLams;
    ViecCanLamAdapter adapter;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viec_can_lam);
        setUpToolbar();
        addControls();
        addEvents();
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Việc Cần Làm");
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        lvViecCanLam = (ListView) findViewById(R.id.lvViecCanLam);
        viecCanLams = new ArrayList<>();
        databaseClass = new DatabaseClass(this);
        viecCanLams.addAll(databaseClass.getData(Constant.TABLE_VIEC_CAN_LAM));
        adapter = new ViecCanLamAdapter(this, viecCanLams);
        lvViecCanLam.setAdapter(adapter);
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
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
