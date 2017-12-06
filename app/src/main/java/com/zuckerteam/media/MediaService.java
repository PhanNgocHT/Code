package com.zuckerteam.media;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;

import com.zuckerteam.Constant;
import com.zuckerteam.mevabe.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/22/2017.
 */

public class MediaService extends IntentService {
    private MediaPlayer mediaPlayer;
    private int state;
    private ArrayList<File> files;
    private int pos = 0;

    public MediaService() {
        super("Dung");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        state = Constant.STATE_IDLE;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return Service.START_STICKY;
        }
        switch (intent.getAction()) {
            case Constant.PLAY:
                handlePlay(intent);
                return Service.START_STICKY;
            case Constant.PAUSE:
                boolean isPlaying = intent.getBooleanExtra(Constant.IS_PLAYING, true);

                handlePause(isPlaying);
                return Service.START_STICKY;
            case Constant.BROADCAST_ACTION_PREVIOUS:
                //previous
                pos = (files.size() + pos - 1) % files.size();
                startSong(Uri.parse(files.get(pos).getPath()));
                return Service.START_STICKY;
            case Constant.BROADCAST_ACTION_PLAY_PAUSE:
                //play , pause
                if (mediaPlayer.isPlaying()) {
                    handlePause(false);
                } else {
                    handlePause(true);
                }
                return Service.START_STICKY;
            case Constant.BROADCAST_ACTION_NEXT:
                //next
                pos = (files.size() + pos + 1) % files.size();
                startSong(Uri.parse(files.get(pos).getPath()));

                return Service.START_STICKY;
            default:
                return Service.START_STICKY;

        }
    }

    private void handlePlay(Intent intent) {
        int positionSong = intent.getIntExtra(Constant.POSITION_SONG, 0);
        if (mediaPlayer == null) {
            files = (ArrayList<File>) intent.getSerializableExtra(Constant.ARRAYLIST_FILE);
        }
        String uri = files.get(positionSong).getPath();
        String songName = files.get(positionSong).getName().replace(".mp3", "");
        if (uri != null) {
            startSong(Uri.parse(uri));
            startForegroundMediaService(songName);
        }
        pos = positionSong;
    }

    private void startSong(Uri uri) {

        try {
            if (mediaPlayer == null)
                mediaPlayer = new MediaPlayer();
            else {
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    publishResults(pos);
                    state = Constant.STATE_PLAYING;
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    try {
                        pos = (pos + 1) % files.size();
                        publishResults(pos);
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(MediaService.this, Uri.parse(files.get(pos).getPath()));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        startForegroundMediaService(files.get(pos).getName().replace(".mp3", ""));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            startForegroundMediaService(files.get(pos).getName().replace(".mp3", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePause(boolean isPlaying) {
        if (isPlaying) {
            mediaPlayer.start();
        } else if (!isPlaying)
            mediaPlayer.pause();
        startForegroundMediaService(files.get(pos).getName().replace(".mp3", ""));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        pos = intent.getIntExtra(Constant.POSITION_SONG, 0);
    }

    private void publishResults(int position) {
        Intent intent = new Intent();
        intent.setAction(Constant.ACTION_PLAY);
        intent.putExtra(Constant.POSITION_SONG, position);
        sendBroadcast(intent);
    }


    private void startForegroundMediaService(String songName) {

        //Dùng pendingIntent vì cần chờ xem bao h thì thực hiện lệnh nhấn nút.
        Intent stopIntent = new Intent(this, MediaService.class);
        stopIntent.setAction(Constant.SERVICE_ACTION_STOP);
        PendingIntent pendingIntentStop = PendingIntent
                .getService(this, 0, stopIntent, 0);//bật service
        Intent previousIntent = new Intent(this, MediaService.class);
        previousIntent.setAction(Constant.BROADCAST_ACTION_PREVIOUS);
        PendingIntent pendingIntentPrevious = PendingIntent.getService(this, 0, previousIntent, 0);

        Intent playPauseIntent = new Intent(this, MediaService.class);
        playPauseIntent.setAction(Constant.BROADCAST_ACTION_PLAY_PAUSE);
        PendingIntent pendingIntentPausePlay = PendingIntent.getService(this, 0, playPauseIntent, 0);

        Intent nextIntent = new Intent(this, MediaService.class);
        nextIntent.setAction(Constant.BROADCAST_ACTION_NEXT);
        PendingIntent pendingIntentNext = PendingIntent.getService(this, 0, nextIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_disk_music)
                .setStyle(new NotificationCompat.MediaStyle())//chuyên
                .addAction(android.R.drawable.ic_media_previous, "", pendingIntentPrevious)
                .addAction(iconPausePlay(mediaPlayer.isPlaying()), "", pendingIntentPausePlay)
                .addAction(android.R.drawable.ic_media_next, "", pendingIntentNext)
                //.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", pendingIntentStop)
                //.setCustomContentView(remoteViews)
                .setContentTitle(songName)
                //.setContentText("Justin bieber")
                /*.setContentIntent(pendingIntentPrevious)
                .setContentIntent(pendingIntentPausePlay)
                .setContentIntent(pendingIntentNext)*/
                .setAutoCancel(false)//Không vuốt được.
                .setWhen(System.currentTimeMillis())//Trả về thời gian tạo notification.
                .setOngoing(true)//Nhấn clear notification cũng không clear được.
                .build();


        startForeground(Constant.NOTIFICATION_ID, notification);

    }

    private int iconPausePlay(boolean isPlaying) {
        if (isPlaying) {
            return android.R.drawable.ic_media_pause;
        }
        return android.R.drawable.ic_media_pause;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
