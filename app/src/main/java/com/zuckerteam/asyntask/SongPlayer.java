package com.zuckerteam.asyntask;

import android.content.Context;

import java.io.IOException;

/**
 * Created by Dung Ali on 9/20/2017.
 */

public interface SongPlayer {

    int STATE_IDLE = 1;
    int STATE_PLAYING = 2;
    int STATE_PAUSED = 3;

    void play(Context context, int position) throws IOException;

    void pause();

    void resume();

    void stop();


}
