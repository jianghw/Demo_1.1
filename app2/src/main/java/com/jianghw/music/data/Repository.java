package com.jianghw.music.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jianghw.music.xutil.NullExUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class Repository implements IDataSource{

    private final IDataSource mRemoteData;
    private final IDataSource mLocalData;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable has package local visibility so it can be accessed from tests.
     * 标志着缓存无效,迫使一个下次更新数据请求。这个变量包当地能见度,因此它可以访问测试。
     */
    boolean mCacheIsDirty;
    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, MusicTask> mCachedTasks;

    public Repository(@NonNull IDataSource remoteData,@NonNull IDataSource localData) {
        mRemoteData = NullExUtils.checkNotNull(remoteData);
        mLocalData = NullExUtils.checkNotNull(localData);
    }

    private static Repository INSTANCE = null;
    public static Repository getInstance(IDataSource remoteData,
                                         IDataSource localData) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteData, localData);
        }
        return INSTANCE;
    }

    /**
     *
     * Gets tasks from cache, local data source (SQLite) or remote data source,
     * whichever is available first. This is done synchronously because it's used by the {@link DataLoader},
     * which implements the async mechanism.
     */
    @Nullable
    @Override
    public List<MusicTask> getTasks() {
        List<MusicTask> tasks = null;
        if (!mCacheIsDirty) {
            // Respond immediately with cache if available and not dirty
            if (mCachedTasks != null) {
                tasks = getCachedTasks();
                return tasks;
            } else {
                // Query the local storage if available.
                tasks = mLocalData.getTasks();
            }
        }
        return getCachedTasks();
    }

    public List<MusicTask> getCachedTasks() {
        return mCachedTasks == null ? null : new ArrayList<>(mCachedTasks.values());
    }
}
