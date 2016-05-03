package com.jianghw.music.bean;

import android.text.TextUtils;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class SongDetailBean {

    public int id;
    public int album_id;//专辑
    public String artist;//艺术家
    public String title;
    public String display_name;//显示名字
    public String duration;
    public String path;
    public float audioProgress = 0.0f;//播放进度
    public int audioProgressSec = 0;//第二个播放进度


    public SongDetailBean(int _id, int album_id,
                          String _artist, String _title, String _path, String _display_name, String _duration) {
        this.id = _id;
        this.album_id = album_id;
        this.artist = _artist;
        this.title = _title;
        this.path = _path;
        this.display_name = _display_name;
        this.duration = TextUtils.isEmpty(_duration) ? "0" : String.valueOf((Long.valueOf(_duration) / 1000));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getAudioProgress() {
        return audioProgress;
    }

    public void setAudioProgress(float audioProgress) {
        this.audioProgress = audioProgress;
    }

    public int getAudioProgressSec() {
        return audioProgressSec;
    }

    public void setAudioProgressSec(int audioProgressSec) {
        this.audioProgressSec = audioProgressSec;
    }
}
