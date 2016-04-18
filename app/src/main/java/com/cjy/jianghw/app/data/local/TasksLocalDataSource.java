package com.cjy.jianghw.app.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cjy.jianghw.app.base.BaseTask;
import com.cjy.jianghw.app.data.DataSourceible;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/15 0015<br/>
 */
public class TasksLocalDataSource implements DataSourceible {
    private static TasksLocalDataSource INSTANCE;
    private final TasksDbHelper mDbHelper;

    public TasksLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new TasksDbHelper(context);
    }

    public static TasksLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getTask(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void completeTask(@NonNull BaseTask task) {

    }

    @Override
    public void deleteAllTasks() {
        //删除数据
    }

    @Override
    public void saveTasks(BaseTask task) {
        //保存数据
    }
}
