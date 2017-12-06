package com.zuckerteam.mevabe.tintucmavabe;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.ChiTietTinTucMeVaBeAdapter;
import com.zuckerteam.asyntask.ChiTietTinTucMeVaBeAsynTask;
import com.zuckerteam.connectivity.Connectivity;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.ChiTietTinTucMeVaBe;
import com.zuckerteam.wepview.WepViewActivity;

import java.util.ArrayList;
import java.util.Collection;

public class KinhNghiemDiDeActivity extends AppCompatActivity
        implements ChiTietTinTucMeVaBeAdapter.OnItemClick, View.OnClickListener {
    private static final String TIEU_DE = "Kinh nghiệm sinh con";
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private RecyclerView rcvKinhNghiemDiDe;
    private LinearLayout lnDisconnectInternet;
    private ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes;
    private ChiTietTinTucMeVaBeAdapter chiTietTinTucMeVaBeAdapter;
    private ChiTietTinTucMeVaBeAsynTask chiTietTinTucMeVaBeAsynTask;
    private Connectivity connectivity;
    private Button btnThuLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinh_nghiem_di_de);
        setUpActionBar();
        addControls();
        addEvents();
        checkInternet();
    }

    private void checkInternet() {
        connectivity = new Connectivity(this);
        if(connectivity.isConnected()){
            rcvKinhNghiemDiDe.setVisibility(View.VISIBLE);
            lnDisconnectInternet.setVisibility(View.INVISIBLE);
            getData();
            chiTietTinTucMeVaBeAdapter = new ChiTietTinTucMeVaBeAdapter(this, chiTietTinTucMeVaBes);
            chiTietTinTucMeVaBeAdapter.setOnItemClick(this);
            rcvKinhNghiemDiDe.setAdapter(chiTietTinTucMeVaBeAdapter);
        }else {
            rcvKinhNghiemDiDe.setVisibility(View.INVISIBLE);
            lnDisconnectInternet.setVisibility(View.VISIBLE);
        }
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe.setText(R.string.kinh_nghiem_sinh_con);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        rcvKinhNghiemDiDe = (RecyclerView) findViewById(R.id.rcvKinhNghiemDiDe);
        rcvKinhNghiemDiDe.setLayoutManager(new LinearLayoutManager(this));
        lnDisconnectInternet = (LinearLayout) findViewById(R.id.lnDisconnectInternet);
        btnThuLai = (Button) findViewById(R.id.btnThuLai);
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternet();
            }
        });
    }

    private void getData() {
        String link = "http://afamily.vn/me-va-be/chuyen-da-khong-dau.htm";
        chiTietTinTucMeVaBes = new ArrayList<>();
        chiTietTinTucMeVaBeAsynTask = new ChiTietTinTucMeVaBeAsynTask(handler,this);
        chiTietTinTucMeVaBeAsynTask.execute(link);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == ChiTietTinTucMeVaBeAsynTask.WHAT_ON_POST_EXECUTE_TIN_TUC_ME_VA_BE) {
                chiTietTinTucMeVaBes.addAll((Collection<? extends ChiTietTinTucMeVaBe>) msg.obj);
                chiTietTinTucMeVaBeAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, WepViewActivity.class);
        intent.putExtra(Constant.KEY_TIN_TUC_ME_VA_BE, chiTietTinTucMeVaBes.get(position));
        intent.putExtra(Constant.keyTieuDeChuyenMuc,"Kinh nghiệm sinh con");
        startActivity(intent);
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
