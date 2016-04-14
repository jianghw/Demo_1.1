package com.cjy.jianghw.app.tasks;

import android.support.annotation.NonNull;

import com.cjy.jianghw.app.data.TasksRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/14<br/>
 */
public class TasksScPresenter implements TasksScContractible.Presenter {

    private final TasksRepository mRepository;
    private final TasksScContractible.View mTasksView;
    private TasksFilterType mCurrentFileter = TasksFilterType.ALL_TASKS;//

    public TasksScPresenter(@NonNull TasksRepository tasksRepository,
                            @NonNull TasksScContractible.View view) {
        mRepository = checkNotNull(tasksRepository, "Repository cannot be null!");
        mTasksView = checkNotNull(view, "View cannot be null!");
        mTasksView.setPresenter(this);
    }

    @Override
    public void initStart() {

    }

    /**
     * @param tasksFilterType Can be {@link TasksFilterType#ALL_TASKS},
     *                        {@link TasksFilterType#COMPLETED_TASKS},
     *                        or{@link TasksFilterType#ACTIVIE_TASKS}
     */
    public void setFiltering(TasksFilterType tasksFilterType) {
        mCurrentFileter = tasksFilterType;
    }

    public TasksFilterType getFiltering() {
        return mCurrentFileter;
    }
}
