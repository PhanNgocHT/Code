package com.zuckerteam.wepview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.zuckerteam.model.ChiTietTinTucMeVaBe;
import com.zuckerteam.model.TinTucYeuThich;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class WepViewActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ImageButton btnTroVe;
    private TextView tvTieuDe;
    private ImageButton btnYeuThich;
    private ImageButton btnChiaSe;
    private ImageButton btnCaiDat;
    private LinearLayout lnDisconnectInternet;
    private Button btnThuLai;
    WebView wvContent;
    ChiTietTinTucMeVaBe tinTuc;
    String duongDanTinTuc = "";
    Snackbar snackbar;
    Connectivity connectivity;
    TinTucYeuThich tinTucYeuThich;
    String thanTinTucHtml = "";
    DatabaseClass databaseClass;
    ArrayList<TinTucYeuThich> tinTucYeuThiches;
    boolean isYeuThich = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wep_view);
        setUpActionBar();
        addControls();
        checkInternet();
        addEvents();

    }

    private void checkInternet() {
        connectivity = new Connectivity(this);
        if (connectivity.isConnected()) {
            wvContent.setVisibility(View.VISIBLE);
            lnDisconnectInternet.setVisibility(View.INVISIBLE);

            Intent intent = getIntent();
            tinTuc = (ChiTietTinTucMeVaBe) intent.getSerializableExtra(Constant.KEY_TIN_TUC_ME_VA_BE);
            duongDanTinTuc = tinTuc.getDuongDanTinTuc();
            String tieuDeChuyenMuc = intent.getStringExtra(Constant.keyTieuDeChuyenMuc);
            tvTieuDe.setText(tieuDeChuyenMuc);
            wvContent.setWebViewClient(new WebViewClient());
            wvContent.loadUrl(duongDanTinTuc);
            kiemTraYeuThich();
        } else {
            wvContent.setVisibility(View.INVISIBLE);
            lnDisconnectInternet.setVisibility(View.VISIBLE);
        }
    }

    private void setUpActionBar() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        btnYeuThich = (ImageButton) findViewById(R.id.btnYeuThich);
        btnChiaSe = (ImageButton) findViewById(R.id.btnChiaSe);
        btnCaiDat = (ImageButton) findViewById(R.id.btnCaiDat);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        setSupportActionBar(toolbar);
    }

    private void addControls() {
        lnDisconnectInternet = (LinearLayout) findViewById(R.id.lnDisconnectInternet);
        btnThuLai = (Button) findViewById(R.id.btnThuLai);
        wvContent = (WebView) findViewById(R.id.wvContent);
    }

    private void kiemTraYeuThich() {
        if (connectivity.isConnected()) {
            databaseClass = new DatabaseClass(this);
            tinTucYeuThiches = databaseClass.getData(Constant.TABLE_TIN_TUC_YEU_THICH);
            for (int i = 0; i < tinTucYeuThiches.size(); i++) {
                if (tinTucYeuThiches.get(i).getDuongDanTinTuc().equals(tinTuc.getDuongDanTinTuc())) {
                    btnYeuThich.setImageResource(R.drawable.ic_yeu_thich);
                    isYeuThich = true;
                    break;
                }
            }
        }
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnYeuThich.setOnClickListener(this);
        btnChiaSe.setOnClickListener(this);
        btnCaiDat.setOnClickListener(this);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternet();
            }
        });
        settingWebView();
    }

    private void settingWebView() {
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
                onBackPressed();
                break;
            case R.id.btnCaiDat:
                //handleCaiDat();
                break;
            case R.id.btnYeuThich:
                handleYeuThich();
                break;
            case R.id.btnChiaSe:
                handleChiaSe();
                break;
            default:
                break;
        }
    }

    private void handleChiaSe() {
        if (connectivity.isConnected()) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = tinTuc.getDuongDanTinTuc();
            //String shareSub = "Send By Dung Zucker";
            //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Chia sẻ với"));
        }
    }

    private void handleYeuThich() {
        if (connectivity.isConnected()) {
            if (isYeuThich == false) {
                btnYeuThich.setImageResource(R.drawable.ic_yeu_thich);
                DownloadHtml downloadHtml = new DownloadHtml(handler);
                downloadHtml.execute(tinTuc.getDuongDanTinTuc());
                Snackbar.make(coordinatorLayout, "Đã yêu thích", Snackbar.LENGTH_LONG).show();
                isYeuThich = true;
            } else if (isYeuThich == true) {
                tinTucYeuThiches = databaseClass.getData(Constant.TABLE_TIN_TUC_YEU_THICH);
                for (TinTucYeuThich tag : tinTucYeuThiches) {
                    if (tinTuc.getDuongDanTinTuc()
                            .equals(tag.getDuongDanTinTuc())) {
                        tinTucYeuThich = tag;
                        break;
                    }
                }
                databaseClass.deleteData(tinTucYeuThich, Constant.TABLE_TIN_TUC_YEU_THICH);
                btnYeuThich.setImageResource(R.drawable.ic_khong_yeu_thich);
                Snackbar.make(coordinatorLayout, "Đã bỏ yêu thích", Snackbar.LENGTH_LONG).show();
                isYeuThich = false;
            }
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == Constant.WHAT_DOWNLOAD_HTML) {
                thanTinTucHtml = (String) message.obj;
                String tieuDe = tinTuc.getTieuDeTinTuc();
                String moTa = tinTuc.getMoTaTinTuc();
                String anh = tinTuc.getAnhTinTuc();
                String than = (String) message.obj;
                String duongDan = tinTuc.getDuongDanTinTuc();
                tinTucYeuThich = new TinTucYeuThich(null, tieuDe, anh, moTa, than, duongDan);

                databaseClass.insertData(tinTucYeuThich, Constant.TABLE_TIN_TUC_YEU_THICH);
            }
            return false;
        }
    });

    @Override
    public void onBackPressed() {
        if (connectivity.isConnected()) {
            if (snackbar != null && snackbar.isShown())
                snackbar.dismiss();
            else if (wvContent != null && wvContent.canGoBack())
                wvContent.goBack();
            else
                super.onBackPressed();
        } else
            super.onBackPressed();
    }
}
