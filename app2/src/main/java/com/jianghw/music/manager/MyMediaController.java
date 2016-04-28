package com.jianghw.music.manager;

import android.content.Context;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class MyMediaController {

    private static volatile MyMediaController INSTANCE = null;

    public static MyMediaController getInstance() {
        MyMediaController mediaController = INSTANCE;
        if (mediaController == null) {
            synchronized (MyMediaController.class) {
                mediaController = INSTANCE;
                if (mediaController == null) {
                    INSTANCE = mediaController = new MyMediaController();
                }
            }
        }
        return mediaController;
    }

    public void cleanUpPlayer(Context context, boolean notify, boolean stopService) {
        saveLastPreferences(context);
        notifyAndStopPlay(notify, stopService);
    }

    private void saveLastPreferences(Context context) {
       /* MusicPreferences.saveLastSong(context, getPlayingSongDetail());
        MusicPreferences.saveLastSongListType(context, type);
        MusicPreferences.saveLastAlbID(context, id);
        MusicPreferences.saveLastPosition(context, currentPlaylistNum);
        MusicPreferences.saveLastPath(context, path);*/
    }

    private void notifyAndStopPlay(boolean notify, boolean stopService) {
//        pauseMusicAudio(getPlayingSongDetail());
    }
}
