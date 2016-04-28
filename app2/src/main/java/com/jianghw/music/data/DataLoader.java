package com.jianghw.music.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class DataLoader<T> extends AsyncTaskLoader<T> {

    public DataLoader(Context context) {
        super(context);
    }

    @Override
    public T loadInBackground() {
        return null;
    }
}
