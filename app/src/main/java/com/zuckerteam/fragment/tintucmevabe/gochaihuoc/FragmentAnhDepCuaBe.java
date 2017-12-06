package com.zuckerteam.fragment.tintucmevabe.gochaihuoc;

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

public class FragmentAnhDepCuaBe extends Fragment implements ChiTietTinTucMeVaBeAdapter.OnItemClick {
    private static String  TIEU_DE = "Ảnh đẹp của bé";
    private RecyclerView rcvAnhDepCuaBe;
    private LinearLayout lnDisconnectInternet;
    private Button btnThuLai;
    private ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes;
    private ChiTietTinTucMeVaBeAdapter chiTietTinTucMeVaBeAdapter;
    private ChiTietTinTucMeVaBeAsynTask asynTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anh_dep_cua_be, container, false);
        rcvAnhDepCuaBe = v.findViewById(R.id.rcvAnhDepCuaBe);
        rcvAnhDepCuaBe.setLayoutManager(new LinearLayoutManager(getActivity()));
        lnDisconnectInternet = v.findViewById(R.id.lnDisconnectInternet);
        btnThuLai= v.findViewById(R.id.btnThuLai);
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
            lnDisconnectInternet.setVisibility(View.INVISIBLE);
            rcvAnhDepCuaBe.setVisibility(View.VISIBLE);
            layDuLieu();
            chiTietTinTucMeVaBeAdapter = new ChiTietTinTucMeVaBeAdapter(getActivity(), chiTietTinTucMeVaBes);
            chiTietTinTucMeVaBeAdapter.setOnItemClick(this);
            rcvAnhDepCuaBe.setAdapter(chiTietTinTucMeVaBeAdapter);
        }else {
            rcvAnhDepCuaBe.setVisibility(View.INVISIBLE);
            lnDisconnectInternet.setVisibility(View.VISIBLE);
        }
    }


    private void layDuLieu() {
        String link = "http://afamily.vn/me-va-be/anh-dep-cua-be.htm";
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
        intent.putExtra(Constant.keyTieuDeChuyenMuc,"Ảnh đẹp của bé");
        startActivity(intent);
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
