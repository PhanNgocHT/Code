package com.zuckerteam.model;

import java.io.Serializable;

/**
 * Created by Dung Ali on 9/18/2017.
 */

public class Music implements Serializable {
    String songName;
    String songSinger;
    String timeSong;
    String linkPage;
    String linkAudioOnLine;
    String linkAudioOffline;

    public Music() {
    }

    public Music(String nameSong, String linkAudioOnLine) {
        this.songName = nameSong;
        this.linkAudioOnLine = linkAudioOnLine;
    }

    public Music(String nameSong, String singerSong, String timeSong, String linkPage, String linkAudioOnLine) {
        this.songName = nameSong;
        this.songSinger = singerSong;
        this.timeSong = timeSong;
        this.linkPage = linkPage;
        this.linkAudioOnLine = linkAudioOnLine;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger;
    }

    public String getTimeSong() {
        return timeSong;
    }

    public void setTimeSong(String timeSong) {
        this.timeSong = timeSong;
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(String linkPage) {
        this.linkPage = linkPage;
    }

    public String getLinkAudioOnLine() {
        return linkAudioOnLine;
    }

    public void setLinkAudioOnLine(String linkAudioOnLine) {
        this.linkAudioOnLine = linkAudioOnLine;
    }

    public String getLinkAudioOffline() {
        return linkAudioOffline;
    }

    public void setLinkAudioOffline(String linkAudioOffline) {
        this.linkAudioOffline = linkAudioOffline;
    }
}
