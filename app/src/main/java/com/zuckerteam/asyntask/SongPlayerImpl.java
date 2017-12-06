package com.zuckerteam.asyntask;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.zuckerteam.Constant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 9/20/2017.
 */

public class SongPlayerImpl implements SongPlayer {
    MediaPlayer mediaPlayer;
    ArrayList<File> files;
    int state;
    int pos = 0;
    GetPositionSong getPositionSong;

    public SongPlayerImpl(ArrayList<File> files,GetPositionSong getPositionSong) {
        this.state = Constant.STATE_IDLE;
        this.files = files;
        this.getPositionSong=getPositionSong;
    }

    @Override
    public void play(final Context context, final int position) {
        pos = position;
        if (state == Constant.STATE_IDLE) {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                } else {
                    mediaPlayer.reset();
                    //mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.setDataSource(context, Uri.parse(files.get(position).getPath()));
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        state = Constant.STATE_PLAYING;
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        try {
                            pos = (pos + 1) % files.size();
                            getPositionSong.getPosition(pos);
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource(context, Uri.parse(files.get(pos).getPath()));
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            stop();
            play(context, position);
        }
    }

    @Override
    public void pause() {
        if (state == Constant.STATE_PLAYING) {
            mediaPlayer.pause();
            state = Constant.STATE_PAUSED;
        }
    }

    @Override
    public void resume() {
        if (state == Constant.STATE_PAUSED) {
            mediaPlayer.start();
            state = Constant.STATE_PLAYING;
        }
    }

    @Override
    public void stop() {
        if (state == Constant.STATE_PAUSED ||
                state == Constant.STATE_PLAYING) {
            //mediaPlayer.pause();
            //mediaPlayer = null;
            state = Constant.STATE_IDLE;
        }

    }

    public int getDuration(){
        return mediaPlayer.getDuration();
    }

    public interface GetPositionSong{
        void getPosition(int pos);
    }

}
