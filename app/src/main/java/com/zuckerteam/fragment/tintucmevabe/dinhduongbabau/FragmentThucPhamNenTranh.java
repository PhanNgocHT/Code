package com.zuckerteam.fragment.tintucmevabe.dinhduongbabau;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.ChiTietTinTucMeVaBeAdapter;
import com.zuckerteam.asyntask.ChiTietTinTucMeVaBeAsynTask;
import com.zuckerteam.connectivity.Connectivity;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.ChiTietTinTucMeVaBe;
import com.zuckerteam.wepview.WepViewActivity;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dung Ali on 8/2/2017.
 */

public class FragmentThucPhamNenTranh extends Fragment implements ChiTietTinTucMeVaBeAdapter.OnItemClick, ChiTietTinTucMeVaBeAsynTask.GetData {
    private static String  TIEU_DE = "Thực phẩm nên tránh";
    private RecyclerView rcvThucPhamNenTranh;
    private LinearLayout lnDisconnectInternet;
    private Button btnThuLai;
    private ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes;
    private ChiTietTinTucMeVaBeAdapter chiTietTinTucMeVaBeAdapter;
    private ChiTietTinTucMeVaBeAsynTask asynTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thuc_pham_nen_tranh, container, false);
        rcvThucPhamNenTranh = v.findViewById(R.id.rcvThucPhamNenTranh);
        rcvThucPhamNenTranh.setLayoutManager(new LinearLayoutManager(getActivity()));
        lnDisconnectInternet = v.findViewById(R.id.lnDisconnectInternet);
        btnThuLai = v.findViewById(R.id.btnThuLai);
        btnThuLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternet();
            }
        });
        checkInternet();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void checkInternet(){
        Connectivity connectivity = new Connectivity(getActivity());
        if(connectivity.isConnected()){
            rcvThucPhamNenTranh.setVisibility(View.VISIBLE);
            lnDisconnectInternet.setVisibility(View.INVISIBLE);
            layDuLieu();
            chiTietTinTucMeVaBeAdapter = new ChiTietTinTucMeVaBeAdapter(getActivity(), chiTietTinTucMeVaBes);
            chiTietTinTucMeVaBeAdapter.setOnItemClick(this);
            rcvThucPhamNenTranh.setAdapter(chiTietTinTucMeVaBeAdapter);
        }else {
            rcvThucPhamNenTranh.setVisibility(View.INVISIBLE);
            lnDisconnectInternet.setVisibility(View.VISIBLE);
        }
    }

    private void layDuLieu() {
        String link = "http://afamily.vn/me-va-be/thuc-pham-nen-tranh.htm";
        chiTietTinTucMeVaBes = new ArrayList<>();
        asynTask = new ChiTietTinTucMeVaBeAsynTask(handler,getActivity());
        asynTask.execute(link);
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
        Intent intent = new Intent(getActivity(), WepViewActivity.class);
        intent.putExtra(Constant.KEY_TIN_TUC_ME_VA_BE, chiTietTinTucMeVaBes.get(position));
        intent.putExtra(Constant.keyTieuDeChuyenMuc,"Thực phẩm nên tránh");
        startActivity(intent);
    }

    @Override
    public void onSuccess(ArrayList<ChiTietTinTucMeVaBe> tinTucMeVaBes) {
        this.chiTietTinTucMeVaBes.addAll(tinTucMeVaBes);
        chiTietTinTucMeVaBeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
