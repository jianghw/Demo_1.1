package com.cjy.jianghw.app.tasks;

import android.support.annotation.NonNull;

import com.cjy.jianghw.app.base.BaseTask;
import com.cjy.jianghw.app.data.DataSourceible;
import com.cjy.jianghw.app.data.ScrollingRepository;
import com.cjy.jianghw.app.util.EspressoIDResource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>中间层实现方法类<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/14<br/>
 */
public class ScrollingPresenter implements ScrollingContractible.TaskPresenter {

    private final ScrollingRepository mRepository;
    private final ScrollingContractible.TaskView mTasksView;

    private EnumFilterType mCurrentEnumFilter = EnumFilterType.ALL_TASKS;//默认不过滤
    private boolean mFirstLoad = true;//初始化时网路加载

    public ScrollingPresenter(@NonNull ScrollingRepository scrollingRepository,
                              @NonNull ScrollingContractible.TaskView view) {
        mRepository = checkNotNull(scrollingRepository, "Repository cannot be null!");
        mTasksView = checkNotNull(view, "TaskView cannot be null!");

        mTasksView.setPresenter(this);
    }

    /**
     * Sets the current task filtering type. 设置当前事务处理类型
     *
     * @param enumFilterType Can be {@link EnumFilterType#ALL_TASKS},
     *                       {@link EnumFilterType#COMPLETED_TASKS},
     *                       or{@link EnumFilterType#ACTIVE_TASKS}
     */
    public void setFiltering(EnumFilterType enumFilterType) {
        mCurrentEnumFilter = enumFilterType;
    }

    public EnumFilterType getFiltering() {
        return mCurrentEnumFilter;
    }

    @Override
    public void initStart() {
        loadTasks(false);
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        // 简化示例:一个网络重新加载将被迫在第一次加载。
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    /**
     * 控制刷新数据及加载图标的显示
     *
     * @param forceUpdate   Pass in true to refresh the data in the {@link com.cjy.jianghw.app.data.DataSourceible}
     * @param showLoadingUI Pass in true to display a loading icon in the UI 加载图标
     */
    private void loadTasks(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {//第一次加载时 网络层
            mTasksView.setLoadingIndicator(true);
        }
        //数据层操作刷新
        if (forceUpdate) {
            mRepository.refreshTask();
        }
        //The network request might be handled in a different thread
        //so make sure Espresso knows that the app is busy until the response is handled.
        EspressoIDResource.increment();//App is busy until further notice 自增1

        mRepository.getTask(new DataSourceible.LoadTasksCallback() {
            @Override
            public void onTasksLoading(List<BaseTask> tasks) {
                //用于显示的事务
                List<BaseTask> listToShow = new ArrayList<>();
                for (BaseTask baseTask : tasks) {
                    switch (mCurrentEnumFilter) {
                        case ALL_TASKS:
                            listToShow.add(baseTask);
                            break;
                        case ACTIVE_TASKS:
                            if (baseTask.isActive()) listToShow.add(baseTask);
                            break;
                        case COMPLETED_TASKS:
                            if (baseTask.isCompleted()) listToShow.add(baseTask);
                            break;
                        default:
                            listToShow.add(baseTask);
                            break;
                    }
                }
                // The view may not be able to handle UI updates anymore
                // 视图可能无法处理UI更新了
                if (!mTasksView.isActive()) return;
                if (showLoadingUI) mTasksView.setLoadingIndicator(false);

                onProcessTasks(listToShow);
            }

            @Override
            public void onDataNotAvaliable() {
                if (!mTasksView.isActive()) return;
                mTasksView.onShowLoadingError();
            }
        });

    }

    private void onProcessTasks(List<BaseTask> listToShow) {
        if (listToShow.isEmpty()) {
            //Show a message indicating there are no tasks for that filter type.
            //显示一条消息说明没有任务,滤波器类型。
            onProcessEmptyTasks();
        } else {
            // Show the list of tasks 显示事件
            mTasksView.onShowTasks(listToShow);
            // Set the filter label's text.
            onShowFilterLabel();
        }
    }

    private void onProcessEmptyTasks() {
        switch (mCurrentEnumFilter) {
            case ACTIVE_TASKS:
                mTasksView.onShowNoActiveTasks();
                break;
            case COMPLETED_TASKS:
                mTasksView.onShowNoCompletedTasks();
                break;
            default:
                mTasksView.onShowNoTasks();
                break;
        }
    }

    private void onShowFilterLabel() {
        switch (mCurrentEnumFilter) {
            case ACTIVE_TASKS:
                mTasksView.onShowActiveFilterLabel();
                break;
            case COMPLETED_TASKS:
                mTasksView.onShowCompletedFilterLabel();
                break;
            default:
                mTasksView.onShowAllFilterLabel();
                break;
        }
    }

    @Override
    public void activityResult(int requestCode, int resultCode) {

    }

    @Override
    public void addNewTask() {
        mTasksView.onShowAddTask();
    }

    @Override
    public void openTaskDetails(BaseTask task) {
        checkNotNull(task, "requestedTask cannot be null!");
        mTasksView.showTaskDetailsUI(task.getId());
    }

    @Override
    public void completeTask(BaseTask task) {
        checkNotNull(task, "completedTask cannot be null!");
        mRepository.completeTask(task);
        mTasksView.showTaskMarkedComplete();
        loadTasks(false, false);
    }

    @Override
    public void activateTask(BaseTask task) {

    }
}
