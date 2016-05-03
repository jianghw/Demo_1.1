package com.jianghw.music.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.jianghw.music.xutil.NullExUtils;

import java.util.List;

/**
 * @Description: </b>
 * Custom {@link android.content.Loader} for a list of {@link MusicTask},
 * using the{@link Repository} as its source.
 * This Loader is a {@link AsyncTaskLoader} so it queries the data asynchronously.<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class DataLoader extends AsyncTaskLoader<List<MusicTask>> {

    private Repository mRepository;

    public DataLoader(Context context, @NonNull Repository repository) {
        super(context);
        mRepository = NullExUtils.checkNotNull(repository);
    }

    @Override
    public List<MusicTask> loadInBackground() {
        return  mRepository.getTasks();
    }
}
