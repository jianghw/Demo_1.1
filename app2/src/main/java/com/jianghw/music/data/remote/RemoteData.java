package com.jianghw.music.data.remote;

import android.support.annotation.Nullable;

import com.jianghw.music.data.IDataSource;
import com.jianghw.music.data.MusicTask;

import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class RemoteData implements IDataSource{

    private static RemoteData INSTANCE = null;

    public static RemoteData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteData();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public List<MusicTask> getTasks() {
        return null;
    }
}
