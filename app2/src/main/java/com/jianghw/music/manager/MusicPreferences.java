package com.jianghw.music.manager;

import com.google.gson.Gson;
import com.jianghw.music.bean.SongDetailBean;
import com.jianghw.music.contant.MContant;
import com.jianghw.music.xutil.SharePreferUtils;

/**
 * @Description: </b>常量集合<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class MusicPreferences {


    public static SongDetailBean songDetailBean;

    public static void saveLastSong(SongDetailBean songDetailBean) {
        SharePreferUtils.setString(MContant.MString.LAST_PLAY_SONG,new Gson().toJson(songDetailBean));
    }

    public static void saveLastSongListType(int type) {
        SharePreferUtils.setInteger(MContant.MString.LAST_SONG_TYPE,type);
    }

    public static void saveLastAlbID(int id) {
        SharePreferUtils.setInteger(MContant.MString.LAST_SONG_ALB_ID,id);
    }

    public static void saveLastPosition(int currentPlaylistNum) {
        SharePreferUtils.setInteger(MContant.MString.LAST_SONG_POSITION,currentPlaylistNum);
    }

    public static void saveLastPath(String path) {
        SharePreferUtils.setString(MContant.MString.LAST_SONG_PATH,path);
    }
}
