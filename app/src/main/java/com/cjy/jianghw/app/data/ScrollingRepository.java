package com.cjy.jianghw.app.data;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/14<br/>
 */
public class ScrollingRepository implements DataSourceible {

    private static ScrollingRepository INSTANCE;
    private final DataSourceible mTasksRemoteDataSource;
    private final DataSourceible mTasksLocalDataSource;

    public ScrollingRepository(@NonNull DataSourceible tasksRemoteDataSource,
                               @NonNull DataSourceible tasksLocalDataSource) {
        mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link ScrollingRepository} instance
     */
    public static ScrollingRepository getInstance(DataSourceible tasksRemoteDataSource,//远程数据
                                                  DataSourceible tasksLocalDataSource) {//本地数据
        if (INSTANCE == null) {
            INSTANCE = new ScrollingRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    public void refreshTask() {

    }

    @Override
    public void getTask(@NonNull LoadTasksCallback callback) {

    }
}
