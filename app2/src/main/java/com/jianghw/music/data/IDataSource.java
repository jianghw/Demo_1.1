package com.jianghw.music.data;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public interface IDataSource {

    @Nullable
    List<MusicTask> getTasks();
}
