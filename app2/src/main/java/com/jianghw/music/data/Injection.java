package com.jianghw.music.data;

import android.content.Context;

import com.jianghw.music.data.local.LocalDataDB;
import com.jianghw.music.data.remote.RemoteData;
import com.jianghw.music.xutil.NullExUtils;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class Injection {
    public static Repository provideRepository(Context applicationContext) {
        NullExUtils.checkNotNull(applicationContext);
        return Repository.getInstance(
                RemoteData.getInstance(),
                LocalDataDB.getInstance(applicationContext));
    }
}
