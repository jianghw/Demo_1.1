package com.jianghw.music.manager;

import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.jianghw.music.MusicApplication;
import com.jianghw.music.bean.SongDetailBean;

import java.util.ArrayList;

/**
 * @Description: </b>手机媒体控制<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/5/3 0003<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class PhoneMediaControl {

    private Cursor mCursor = null;

    public enum SonLoadForEnum {
        All,
        Gener,
        Artis,
        Album,
        Musicintent,//4
        MostPlay,
        Favorite,
        ResecntPlay;

    }

    private static volatile PhoneMediaControl Instance = null;

    public static PhoneMediaControl getInstance() {
        if (Instance == null) {
            synchronized (MyMediaController.class) {
                if (Instance == null) {
                    Instance = new PhoneMediaControl();
                }
            }
        }
        return Instance;
    }

    private final String[] projectionSongs = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION};

    public ArrayList<SongDetailBean> getList(long id, SonLoadForEnum anEnum, String path) {
        String sortOrder = "";//排序次序
        ArrayList<SongDetailBean> songsList = new ArrayList<>();
        switch (anEnum) {
            case All:
                String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
                sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
                mCursor = MusicApplication.applicationContext.getContentResolver().query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        projectionSongs, selection, null, sortOrder);
                songsList=getSongsFromCursor(mCursor);
                break;
            case Gener:
                break;
        }
        return songsList;
    }

    /**
     *
     * @param cursor {@see #getList()}
     * @return
     */
    private ArrayList<SongDetailBean> getSongsFromCursor(Cursor cursor) {
        ArrayList<SongDetailBean> beanArrayList = new ArrayList<>();
        try {
            if (cursor != null && cursor.getCount() >= 1) {
                int _id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);//艺术家
                int album_id = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);//专辑
                int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);//标题
                int data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);//数据文件即指向地址
                int display_name = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);//显示名称
                int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);//持续时间
                while (cursor.moveToNext()) {

                    int ID = cursor.getInt(_id);
                    String ARTIST = cursor.getString(artist);
                    String TITLE = cursor.getString(title);
                    String DISPLAY_NAME = cursor.getString(display_name);
                    String DURATION = cursor.getString(duration);
                    String Path = cursor.getString(data);

                    SongDetailBean mSongDetail = new SongDetailBean(ID, album_id, ARTIST, TITLE, Path, DISPLAY_NAME, DURATION);
                    beanArrayList.add(mSongDetail);
                }
            }
            closeCrs();
        } catch (Exception e) {
            closeCrs();
            e.printStackTrace();
        }
        return beanArrayList;
    }

    private void closeCrs() {
        if (mCursor != null) {
            try {
                mCursor.close();
            } catch (Exception e) {
                Log.e("TAG", e.toString());
            }
        }
    }
}
