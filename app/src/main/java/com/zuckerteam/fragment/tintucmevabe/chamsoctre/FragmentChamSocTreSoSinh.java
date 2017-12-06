package com.zuckerteam.fragment.tintucmevabe.chamsoctre;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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

public class FragmentChamSocTreSoSinh extends Fragment implements ChiTietTinTucMeVaBeAdapter.OnItemClick {
    public static String TIEU_DE = "Chăm sóc trẻ sơ sinh";
    private LinearLayout lnDisconnectInternet;
    private RecyclerView rcvChamSocTreSoSinh;
    private ArrayList<ChiTietTinTucMeVaBe> chiTietTinTucMeVaBes;
    private ChiTietTinTucMeVaBeAdapter chiTietTinTucMeVaBeAdapter;
    private ChiTietTinTucMeVaBeAsynTask asynTask;
    private Button btnThuLai;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View  v = inflater.inflate(R.layout.fragment_cham_soc_tre_so_sinh, container, false);
        rcvChamSocTreSoSinh = v.findViewById(R.id.rcvChamSocTreSoSinh);
        rcvChamSocTreSoSinh.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        if (connectivity.isConnected()) {
            rcvChamSocTreSoSinh.setVisibility(View.VISIBLE);
            lnDisconnectInternet.setVisibility(View.INVISIBLE);
            layDuLieu();
            chiTietTinTucMeVaBeAdapter = new ChiTietTinTucMeVaBeAdapter(getActivity(), chiTietTinTucMeVaBes);
            chiTietTinTucMeVaBeAdapter.setOnItemClick(this);
            rcvChamSocTreSoSinh.setAdapter(chiTietTinTucMeVaBeAdapter);
        }else {
            lnDisconnectInternet.setVisibility(View.VISIBLE);
            rcvChamSocTreSoSinh.setVisibility(View.INVISIBLE);
        }
    }

    private void layDuLieu() {
        String link = "http://afamily.vn/me-va-be/cham-soc-tre-so-sinh.htm";
        chiTietTinTucMeVaBes = new ArrayList<>();
        asynTask = new ChiTietTinTucMeVaBeAsynTask(handler, getActivity());
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
        intent.putExtra(Constant.keyTieuDeChuyenMuc, "Chăm sóc trẻ sơ sinh");
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
