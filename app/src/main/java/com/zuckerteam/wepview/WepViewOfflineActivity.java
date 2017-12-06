package com.zuckerteam.wepview;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.connectivity.Connectivity;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.TinTucYeuThich;

import java.util.ArrayList;

public class WepViewOfflineActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    Toolbar toolbar;
    TextView tvTieuDe;
    ImageButton btnTroVe, btnYeuThich, btnChiaSe, btnCaiDat;
    WebView wvContent;
    Connectivity connectivity;
    TinTucYeuThich tinTucYeuThich;
    boolean statusYeuThich = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wep_view_offline);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setUpToolbar();
        addControls();
        addEvents();
    }

    private void setUpToolbar() {
        toolbar = new Toolbar(this);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText("Yêu thích");
        btnChiaSe = (ImageButton) findViewById(R.id.btnChiaSe);
        btnYeuThich = (ImageButton) findViewById(R.id.btnYeuThich);
        btnYeuThich.setImageResource(R.drawable.ic_yeu_thich);
        btnCaiDat = (ImageButton) findViewById(R.id.btnCaiDat);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        wvContent = (WebView) findViewById(R.id.wvContent);
        doDuLieu();
    }

    private void doDuLieu() {
        connectivity = new Connectivity(this);
        Intent intent = getIntent();
        tinTucYeuThich = (TinTucYeuThich) intent.getSerializableExtra(Constant.KEY_TIN_TUC_OFFLINE);
        wvContent.setWebViewClient(new WebViewClient());
        /*if (connectivity.isConnected()) {
            String duongDan = tinTucYeuThich.getDuongDanTinTuc();
            wvContent.loadUrl(duongDan);
        } else {*/
        //tvTieuDeTinTuc.setText(tinTucYeuThich.getTieuDeTinTuc());
        //tvMoTaTinTuc.setText(tinTucYeuThich.getMoTaTinTuc());
        String thanTinTucHtml = tinTucYeuThich.getThanTinTucHtml();
        wvContent.loadData(thanTinTucHtml, "text/html", "UTF-8");
        //}
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnYeuThich.setOnClickListener(this);
        btnChiaSe.setOnClickListener(this);
        btnCaiDat.setOnClickListener(this);
        settingWepView();
    }

    private void settingWepView() {
        WebSettings settings = wvContent.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        //load được tất cả định dạng ngay cả video ,
        //nhấn được các button chức năng trong trang wep
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTroVe:
                handleTroVe();
                break;
            case R.id.btnYeuThich:
                handleYeuThich();
                break;
            case R.id.btnChiaSe:
                handleChiaSe();
                break;
            case R.id.btnCaiDat:
                handleCaiDat();
                break;
            default:
                break;
        }
    }

    private void handleTroVe() {
        onBackPressed();
    }

    private void handleYeuThich() {
        DatabaseClass databaseClass = new DatabaseClass(this);
        if(statusYeuThich==true) {
            ArrayList<TinTucYeuThich>tinTucYeuThiches = databaseClass.getData(Constant.TABLE_TIN_TUC_YEU_THICH);
            for (TinTucYeuThich tag : tinTucYeuThiches) {
                if (tinTucYeuThich.getDuongDanTinTuc()
                        .equals(tag.getDuongDanTinTuc())) {  
                    tinTucYeuThich = tag;
                    break;
                }
            }
            btnYeuThich.setImageResource(R.drawable.ic_khong_yeu_thich);
            databaseClass.deleteData(tinTucYeuThich,Constant.TABLE_TIN_TUC_YEU_THICH);
            Snackbar.make(linearLayout, "Đã xóa yêu thích", Snackbar.LENGTH_SHORT).show();
            statusYeuThich=false;
        }else if(statusYeuThich==false){
            btnYeuThich.setImageResource(R.drawable.ic_yeu_thich);
            databaseClass.insertData(tinTucYeuThich,Constant.TABLE_TIN_TUC_YEU_THICH);
            Snackbar.make(linearLayout, "Đã yêu thích", Snackbar.LENGTH_SHORT).show();
            statusYeuThich=true;
        }
    }

    private void handleChiaSe() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = tinTucYeuThich.getDuongDanTinTuc();
        String shareSub = "Send By Dung Zucker";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    private void handleCaiDat() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
