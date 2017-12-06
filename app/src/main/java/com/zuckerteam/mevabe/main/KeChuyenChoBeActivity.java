package com.zuckerteam.mevabe.main;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.CustomKeChuyenChoBeViewPagerAdapter;
import com.zuckerteam.adapter.DanhSachChuyenAdapter;
import com.zuckerteam.database.DatabaseClass;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.KeChuyenChoBe;

import java.util.ArrayList;
import java.util.Arrays;

public class KeChuyenChoBeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private LinearLayout lnNavigation;
    private ActionBarDrawerToggle toggle;
    private ActionBar actionBar;
    private EditText edtTimKiemChuyen;
    private ListView lvKeChuyenChoBe;
    private DanhSachChuyenAdapter<String> danhSachChuyenAdapter;
    private ArrayList<String> danhSachChuyens;
    private ImageButton btnXoa;
    private ViewPager vpKeChuyenChoBe;
    private ArrayList<KeChuyenChoBe> keChuyenChoBes;
    private CustomKeChuyenChoBeViewPagerAdapter pagerAdapter;
    private DatabaseClass database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_chuyen_cho_be);
        setUpActionBar();
        addDrawerLayout();
        addControls();
        addEvents();
    }

    private void setUpActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.close_navigation_ke_chuyen);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void addDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lnNavigation = (LinearLayout) findViewById(R.id.lnNavigation);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_navigation_ke_chuyen, R.string.close_navigation_ke_chuyen) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(R.string.open_navigation_ke_chuyen);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                InputMethodManager imm = (InputMethodManager)getSystemService(KeChuyenChoBeActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtTimKiemChuyen.getWindowToken(), 0);
                actionBar.setTitle(R.string.close_navigation_ke_chuyen);
            }
        };
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggle.syncState();
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        lnNavigation = (LinearLayout) findViewById(R.id.lnNavigation);
        edtTimKiemChuyen = (EditText) findViewById(R.id.edtTimKiemChuyen);
        lvKeChuyenChoBe = (ListView) findViewById(R.id.lvKeChuyenChoBe);
        btnXoa = (ImageButton) findViewById(R.id.btnXoa);
        vpKeChuyenChoBe = (ViewPager) findViewById(R.id.vpKeChuyenChoBe);
        layDuLieu();
        danhSachChuyenAdapter = new DanhSachChuyenAdapter<>(KeChuyenChoBeActivity.this, danhSachChuyens);
        lvKeChuyenChoBe.setAdapter(danhSachChuyenAdapter);
        pagerAdapter = new CustomKeChuyenChoBeViewPagerAdapter(this, keChuyenChoBes);
        vpKeChuyenChoBe.setAdapter(pagerAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("Chuyện vừa đọc",MODE_PRIVATE);
        int currentStory = sharedPreferences.getInt("Current Story",0);
        vpKeChuyenChoBe.setCurrentItem(currentStory);
    }

    private void layDuLieu() {
        database = new DatabaseClass(this);
        keChuyenChoBes = new ArrayList<>();
        keChuyenChoBes.addAll(database.getData(Constant.TABLE_KE_CHUYEN_CHO_BE));
        //Lấy danh sách tên các truyện
        danhSachChuyens = new ArrayList<>();
        for (KeChuyenChoBe tag : keChuyenChoBes) {
            danhSachChuyens.add(tag.getTenChuyen());
        }
    }

    private void addEvents() {
        lvKeChuyenChoBe.setOnItemClickListener(this);
        btnXoa.setOnClickListener(this);
        edtTimKiemChuyen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().equals("")) {
                    searchContacts(s.toString());
                } else {
                    danhSachChuyens.clear();
                    for (KeChuyenChoBe tag : keChuyenChoBes) {
                        danhSachChuyens.add(tag.getTenChuyen());
                    }
                    danhSachChuyenAdapter = new DanhSachChuyenAdapter<>(KeChuyenChoBeActivity.this,danhSachChuyens);
                    lvKeChuyenChoBe.setAdapter(danhSachChuyenAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void searchContacts(String s) {
        danhSachChuyens.clear();
        ArrayList<String> ds = new ArrayList<>();
        for (KeChuyenChoBe tag : keChuyenChoBes) {
            ds.add(tag.getTenChuyen());
        }
        for(String str:ds){
            if(str.toLowerCase().contains(s.toLowerCase()))
                danhSachChuyens.add(str);
        }
        danhSachChuyenAdapter.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(lnNavigation)) {
            drawerLayout.closeDrawer(lnNavigation);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Tắt keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtTimKiemChuyen.getWindowToken(), 0);
        drawerLayout.closeDrawer(lnNavigation);
        //Lấy position khi đang tìm kiếm.
        String str =  danhSachChuyens.get(position);
        for (int i=0;i<keChuyenChoBes.size();i++){
            if(keChuyenChoBes.get(i).getTenChuyen().contains(str)){
                position=i;
                break;
            }

        }
        vpKeChuyenChoBe.setCurrentItem(position,true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnXoa:
                edtTimKiemChuyen.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("Chuyện vừa đọc",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Current Story",vpKeChuyenChoBe.getCurrentItem());
        editor.commit();
    }
}
