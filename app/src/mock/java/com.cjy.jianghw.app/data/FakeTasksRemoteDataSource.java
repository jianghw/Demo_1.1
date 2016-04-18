package com.cjy.jianghw.app.data;

import android.support.annotation.NonNull;

import com.cjy.jianghw.app.base.BaseTask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/15 0015<br/>
 */
public class FakeTasksRemoteDataSource implements DataSourceible {

    private static FakeTasksRemoteDataSource INSTANCE;
    private static final Map<String, BaseTask> TASKS_SERVICE_DATA = new LinkedHashMap<>();

    public static FakeTasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeTasksRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getTask(@NonNull LoadTasksCallback callback) {
        callback.onTasksLoading(new ArrayList<>(TASKS_SERVICE_DATA.values()));
    }

    @Override
    public void completeTask(@NonNull BaseTask task) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void saveTasks(BaseTask task) {

    }
}
