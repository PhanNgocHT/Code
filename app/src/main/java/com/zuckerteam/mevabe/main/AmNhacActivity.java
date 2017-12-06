package com.zuckerteam.mevabe.main;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuckerteam.Constant;
import com.zuckerteam.adapter.FileAdapter;
import com.zuckerteam.asyntask.DownloadFileAudio;
import com.zuckerteam.asyntask.GetLinkMusic;
import com.zuckerteam.asyntask.SongPlayerImpl;
import com.zuckerteam.connectivity.Connectivity;
import com.zuckerteam.database.FileManager;
import com.zuckerteam.media.MediaService;
import com.zuckerteam.mevabe.R;
import com.zuckerteam.model.Music;

import java.io.File;
import java.util.ArrayList;

public class AmNhacActivity extends AppCompatActivity implements View.OnClickListener, FileAdapter.OnClickItem, SongPlayerImpl.GetPositionSong {
    private Toolbar toolbar;
    private TextView tvTieuDe;
    private ImageButton btnTroVe;
    private Connectivity connectivity;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView rcvMusic;
    private ArrayList<Music> musics;
    private FileAdapter adapter;
    private ArrayList<String> linkPages;
    private FileManager fileManager = new FileManager();
    private ArrayList<File> files = new ArrayList<>();
    private SongPlayerImpl songPlayer;
    private Dialog dialog;
    private TextView tvTaiNhac;
    private LinearLayout lnPlayMusic;
    private ImageButton btnDiskMusic, btnPrevious, btnPlayPause, btnNext;
    private TextView tvSongName;
    private Animation rotateAnimation;
    private int positionPlayed = -1;
    private boolean tagPlayPause = false;
    private Button btnThuLai;
    private LinearLayout lnDisconnectInternet;
    private LinearLayout lnConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_nhac);
        musics = new ArrayList<>();
        setUpActionBar();
        addControls();
        layDuLieu();
        addEvents();
        songPlayer = new SongPlayerImpl(files, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(Constant.ACTION_PLAY));
        /*SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        positionPlayed = preferences.getInt(Constant.POSITON_PLAY, -1);
        if (positionPlayed != -1) {
            tagPlayPause = preferences.getBoolean(Constant.IS_PLAYING, false);
            String songName = files.get(positionPlayed).getName().replace(".mp3", "");
            lnPlayMusic.setVisibility(View.VISIBLE);
            tvSongName.setText(songName);
            setBackGroundButtonPlay(tagPlayPause);

        }*/
    }

    private void layDuLieu() {
        File f = new File(Constant.pathFileAudio);
        if (!f.exists() || f.listFiles().length == 0) {
            connectivity = new Connectivity(this);
            checkInternet();
            //dialog.dismiss();
        } else {
            files.addAll(fileManager.getListFileMusic(f));

            //adapter.notifyDataSetChanged();
        }
    }

    private void checkInternet(){
        if (connectivity.isConnected()) {
            lnConnected.setVisibility(View.VISIBLE);
                /*rcvMusic.setVisibility(View.VISIBLE);
                lnPlayMusic.setVisibility(View.INVISIBLE);*/
            lnDisconnectInternet.setVisibility(View.INVISIBLE);
            setUpDialog();
            getLinkAudio();
        } else {
            lnConnected.setVisibility(View.INVISIBLE);
                /*rcvMusic.setVisibility(View.INVISIBLE);
                lnPlayMusic.setVisibility(View.INVISIBLE);*/
            lnDisconnectInternet.setVisibility(View.VISIBLE);
        }
    }


    private void addControls() {
        lnDisconnectInternet = (LinearLayout) findViewById(R.id.lnDisconnectInternet);
        lnConnected = (LinearLayout) findViewById(R.id.lnConnected);
        btnThuLai = (Button) findViewById(R.id.btnThuLai);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        rcvMusic = (RecyclerView) findViewById(R.id.rcvMusic);
        rcvMusic.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FileAdapter(this, files, this);
        rcvMusic.setAdapter(adapter);

        lnPlayMusic = (LinearLayout) findViewById(R.id.lnPlayMusic);
        btnDiskMusic = (ImageButton) findViewById(R.id.btnDiskMusic);
        tvSongName = (TextView) findViewById(R.id.tvSongName);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPlayPause = (ImageButton) findViewById(R.id.btnPlayPause);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        lnPlayMusic.setVisibility(View.INVISIBLE);

        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_disk_music);
    }

    private void setUpDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_load_nhac, rcvMusic, false);
        tvTaiNhac = v.findViewById(R.id.tvTaiNhac);
        dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        dialog.setContentView(v);
        dialog.show();
    }

    private void getLinkAudio() {
        linkPages = new ArrayList<>();
        //linkPages.add("http://nhacpro.net/bai-hat/beautiful-in-white-shane-filan.3mv.html");
        linkPages.add("http://nhacpro.net/bai-hat/a-town-with-an-ocean-various-artists.7tur.html");
        //linkPages.add("http://nhacpro.net/bai-hat/shining-the-morning-various-artists.1w0f.html");
        //linkPages.add("http://nhacpro.net/bai-hat/tay-luong-nu-quoc-various-artists.rmz.html");
        //linkPages.add("http://nhacpro.net/bai-hat/why-not-me-enrique-iglesias.3mi.html");
        linkPages.add("http://nhacpro.net/bai-hat/july-my-soul-various-artists.ttc.html");
        //linkPages.add("http://nhacpro.net/bai-hat/hope-you-the-daydream.3r0k.html");
        //linkPages.add("http://nhacpro.net/bai-hat/because-i-love-you-shakin-stevens.cws.html");
        linkPages.add("http://nhacpro.net/bai-hat/betrayal-yao-si-ting.f5e.html");
        linkPages.add("http://nhacpro.net/bai-hat/in-love-july.vze.html");
        linkPages.add("http://nhacpro.net/bai-hat/autumn-july.5xw5.html");
        linkPages.add("http://nhacpro.net/bai-hat/somewhere-july.1fxw.html");
        linkPages.add("http://nhacpro.net/bai-hat/beyond-the-memory-july.7iau.html");
        //linkPages.add("http://nhacpro.net/bai-hat/endless-love-blue.9ctf.html");
        linkPages.add("http://nhacpro.net/bai-hat/sundial-dreams-kevin-kern.3xbe.html");
        //linkPages.add("http://nhacpro.net/bai-hat/the-moment-kenny-g.koo.html");
        linkPages.add("http://nhacpro.net/bai-hat/kiss-the-rain-yiruma.dnx.html");
        linkPages.add("http://nhacpro.net/bai-hat/in-the-wind-july.3f73.html");
        for (int i = 0; i < linkPages.size(); i++) {
            GetLinkMusic a = new GetLinkMusic(handler);
            a.execute(linkPages.get(i));
        }

    }

    private void downloadAudioMusic(Music music) {
        DownloadFileAudio downloadAsynTask = new DownloadFileAudio(handler);
        downloadAsynTask.execute(music.getLinkAudioOnLine(), music.getSongName());
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnPlayPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnThuLai.setOnClickListener(this);
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        btnTroVe = (ImageButton) findViewById(R.id.btnTroVe);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(R.string.nhac_cho_ba_bau);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTroVe:
                onBackPressed();
                break;
            case R.id.btnPrevious:
                songPlayer.stop();
                handlePrevious();
                break;
            case R.id.btnPlayPause:
                handlePlayPause();
                break;
            case R.id.btnNext:
                songPlayer.stop();
                handleNext();
                break;
            case R.id.btnThuLai:
                checkInternet();
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == Constant.WHAT_GET_LINK_AUDIO) {
                Music music = (Music) msg.obj;
                downloadAudioMusic(music);
                musics.add(music);
            } else if (msg.what == Constant.WHAT_DOWNLOAD_FILE_AUDIO) {
                String pathAudio = (String) msg.obj;
                files.add(new File(pathAudio));
                if (files.size() == linkPages.size()) {
                    tvTaiNhac.setText("Đã tải xong");
                    dialog.dismiss();
                    //Snackbar.make(coordinatorLayout, "Đã tải xong", Snackbar.LENGTH_LONG).show();
                } else
                    tvTaiNhac.setText("Đang tải " + files.size() * 100 / linkPages.size() + " %");
                adapter.notifyDataSetChanged();
            }
            return false;
        }
    });

    @Override
    public void onClicked(int position) {
        File fileAudio = files.get(position);
        String songName = fileAudio.getName().replace(".mp3", "");
        //songPlayer.play(MainActivity.this, position);
        lnPlayMusic.setVisibility(View.VISIBLE);
        tvSongName.setText(songName);
        rotateAnimation.setDuration(5000);
        btnDiskMusic.startAnimation(rotateAnimation);

        positionPlayed = position;
        tagPlayPause = true;
        setBackGroundButtonPlay(true);
        playMusicWithService(positionPlayed);

    }

    private void handlePrevious() {
        positionPlayed = (files.size() + positionPlayed - 1) % files.size();
        File fileAudio = files.get(positionPlayed);
        String songName = fileAudio.getName().replace(".mp3", "");
        //songPlayer.play(this, positionPlayed);
        tvSongName.setText(songName);
        rotateAnimation.setDuration(5000);
        tagPlayPause = true;
        setBackGroundButtonPlay(tagPlayPause);
        playMusicWithService(positionPlayed);
    }

    private void handlePlayPause() {
        if (tagPlayPause == false) {
            songPlayer.resume();
            tagPlayPause = true;
            pauseMusicWithService(true);
        } else if (tagPlayPause == true) {
            songPlayer.pause();
            tagPlayPause = false;
            pauseMusicWithService(false);
        }
        setBackGroundButtonPlay(tagPlayPause);

    }

    private void handleNext() {
        positionPlayed = (positionPlayed + 1) % files.size();
        File fileAudio = files.get(positionPlayed);
        String songName = fileAudio.getName().replace(".mp3", "");
        //songPlayer.play(this, positionPlayed);
        tvSongName.setText(songName);
        rotateAnimation.setDuration(5000);
        tagPlayPause = true;
        setBackGroundButtonPlay(tagPlayPause);
        playMusicWithService(positionPlayed);
    }

    private void setBackGroundButtonPlay(boolean isPlay) {
        if (isPlay == false) {
            btnPlayPause.setBackgroundResource(R.drawable.ic_pause);
            rotateAnimation.cancel();
        } else if (isPlay == true) {
            btnPlayPause.setBackgroundResource(R.drawable.ic_play);
            btnDiskMusic.startAnimation(rotateAnimation);
        }
    }

    @Override
    public void getPosition(int pos) {
        tvSongName.setText(files.get(pos).getName().replace(".mp3", ""));
        positionPlayed = pos;
    }

    private void playMusicWithService(int position) {
        positionPlayed = position;
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction(Constant.PLAY);
        intent.putExtra(Constant.POSITION_SONG, position);
        //intent.putExtra(Constant.URI,files.get(position).getPath());
        //intent.putExtra(Constant.SONG_NAME,songName);
        intent.putExtra(Constant.ARRAYLIST_FILE, files);
        startService(intent);
    }

    private void pauseMusicWithService(boolean isPlaying) {
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction(Constant.PAUSE);
        intent.putExtra(Constant.IS_PLAYING, isPlaying);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //songPlayer.pause();
        //tagPlayPause = false;
       /* SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.IS_PLAYING, tagPlayPause);
        editor.putInt(Constant.POSITON_PLAY, positionPlayed);
        editor.commit();*/
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        /*SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.IS_PLAYING, tagPlayPause);
        editor.putInt(Constant.POSITON_PLAY,-1);
        editor.commit();*/
        super.onDestroy();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Constant.ACTION_PLAY) {
                positionPlayed = intent.getIntExtra(Constant.POSITION_SONG, 0);
                tvSongName.setText(files.get(positionPlayed).getName().replace(".mp3", ""));
            }
        }
    };
}
