package com.zuckerteam.mevabe.main;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zuckerteam.mevabe.R;

public class ThoiTietActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_tiet);
        setUpActionBar();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.thoi_tiet);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
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
