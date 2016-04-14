package com.cjy.jianghw.app.data;

import com.cjy.jianghw.app.base.BaseTask;

import java.util.List;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/14<br/>
 */
public interface TasksDatable {

    interface LoadTasksCallback {
        void onTasksLoading(List<BaseTask> tasks);

        void onDataNotAvaliable();
    }

    interface  getTasksCallback{
        void onTasksLoading(List<BaseTask> tasks);

        void onDataNotAvaliable();
    }
}
