package com.zuckerteam.asyntask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zuckerteam.Constant;
import com.zuckerteam.model.Music;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Dung Ali on 9/18/2017.
 */

public class GetLinkMusic extends AsyncTask<String, Void, Music> {

    private Handler handler;

    public GetLinkMusic(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Music doInBackground(String... urls) {
        Music music = new Music();
        String linkPage = urls[0];
        try {
            Document document = Jsoup.connect(linkPage).get();
            Element song_name_tag = document.getElementsByTag("h1").get(0);
            String song_name = song_name_tag.text();
            Element singer_tag = document.getElementsByTag("h2").get(0);
            String singer = singer_tag.text();
            Element audio_tag = document.getElementsByTag("audio").get(0);
            Element source_tag = audio_tag.getElementsByTag("source").get(0);
            String link_audio = source_tag.attr("src");
            Log.d("1", "doInBackground: " +song_name+"\n"+singer+"\n"+ link_audio);

            music.setLinkAudioOnLine(link_audio);
            music.setSongName(song_name);
            return music;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return music;
    }

    @Override
    protected void onPostExecute(Music music) {
        super.onPostExecute(music);
        Message message = new Message();
        message.what = Constant.WHAT_GET_LINK_AUDIO;
        message.obj = music;
        handler.sendMessage(message);
    }
}

