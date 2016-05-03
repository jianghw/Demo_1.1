package com.jianghw.music.manager;

import com.google.gson.Gson;
import com.jianghw.music.bean.SongDetailBean;
import com.jianghw.music.contant.MContant;
import com.jianghw.music.xutil.SharePreferUtils;

import java.util.ArrayList;

/**
 * @Description: </b>常量集合<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class MusicPreferences {


    public static SongDetailBean songDetailBean;
    public static ArrayList<SongDetailBean> playlist = new ArrayList<>();

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

    /**
     *
     * @param path
     * @return
     */
    public static ArrayList<SongDetailBean> getPlaylist(String path) {
        // Returns the position of the enum constant in the declaration. The first constant has an ordinal value of zero.
        MyMediaController.getInstance().type = PhoneMediaControl.SonLoadForEnum.Musicintent.ordinal();//4
        MyMediaController.getInstance().id = -1;
        MyMediaController.getInstance().currentPlaylistNum = 0;
        MyMediaController.getInstance().path = path;
        playlist = PhoneMediaControl.getInstance().getList(-1, PhoneMediaControl.SonLoadForEnum.Musicintent, path);

        if (playlist != null && !playlist.isEmpty())
            songDetailBean = playlist.get(0);
        return playlist;
    }
}
