package com.cjy.jianghw.app.data;

import android.support.annotation.NonNull;

import com.cjy.jianghw.app.base.BaseTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * This variable has package local visibility so it can be accessed from tests.
     * 这个变量包当地能见度,因此它可以访问测试。
     */
    Map<String, BaseTask> mCachedTasks;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested.
     * 标志着缓存无效,强迫一个更新下次数据请求。
     */
    boolean mCacheIsDirty = false;

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

    /**
     * 标记数据库缓存操作
     */
    public void refreshTask() {
        mCacheIsDirty = true;
    }

    @Override
    public void getTask(@NonNull final LoadTasksCallback callback) {
        checkNotNull(callback);
        // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onTasksLoading(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getTasksFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mTasksLocalDataSource.getTask(new LoadTasksCallback() {
                @Override
                public void onTasksLoading(List<BaseTask> tasks) {
                    refreshCache(tasks);
                    callback.onTasksLoading(new ArrayList<>(mCachedTasks.values()));
                }

                @Override
                public void onDataNotAvaliable() {
                    getTasksFromRemoteDataSource(callback);
                }
            });
        }
    }

    /**
     * 重新标记缓存
     *
     * @param tasks
     */
    private void refreshCache(List<BaseTask> tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (BaseTask task : tasks) {
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback) {
        mTasksRemoteDataSource.getTask(new LoadTasksCallback() {
            @Override
            public void onTasksLoading(List<BaseTask> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                callback.onTasksLoading(new ArrayList<>(mCachedTasks.values()));
            }

            @Override
            public void onDataNotAvaliable() {
                callback.onDataNotAvaliable();
            }
        });
    }

    private void refreshLocalDataSource(List<BaseTask> tasks) {
        mTasksLocalDataSource.deleteAllTasks();
        for (BaseTask task : tasks) {
            mTasksLocalDataSource.saveTasks(task);
        }
    }

    @Override
    public void completeTask(BaseTask task) {
        checkNotNull(task);
        mTasksRemoteDataSource.completeTask(task);
        mTasksLocalDataSource.completeTask(task);

        BaseTask completedTask = new BaseTask(task.getTitle(), task.getDescription(), task.getId(), true);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), completedTask);
    }

    @Override
    public void deleteAllTasks() {
        mTasksRemoteDataSource.deleteAllTasks();
        mTasksLocalDataSource.deleteAllTasks();

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
    }

    @Override
    public void saveTasks(BaseTask task) {
        checkNotNull(task);
        mTasksRemoteDataSource.saveTasks(task);
        mTasksLocalDataSource.saveTasks(task);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }
}
