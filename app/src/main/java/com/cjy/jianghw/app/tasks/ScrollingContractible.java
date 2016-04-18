package com.cjy.jianghw.app.tasks;

import com.cjy.jianghw.app.base.BaseTask;
import com.cjy.jianghw.app.base.BaseView;
import com.cjy.jianghw.app.base.BasePresenter;

import java.util.List;

/**
 * <b>@Description:</b>
 * This specifies the contract between the view and the presenter<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public interface ScrollingContractible {

    interface TaskView extends BaseView<TaskPresenter> {
        /**
         * 加载指示器
         *
         * @param b
         */
        void setLoadingIndicator(boolean b);

        /**
         * Return true if the fragment is currently added to its activity.
         */
        boolean isActive();

        void onShowTasks(List<BaseTask> listToShow);

        void onShowLoadingError();

        void onShowNoActiveTasks();

        void onShowNoCompletedTasks();

        void onShowNoTasks();

        void onShowActiveFilterLabel();

        void onShowCompletedFilterLabel();

        void onShowAllFilterLabel();

        void onShowAddTask();

        void showTaskDetailsUI(String id);

        void showTaskMarkedComplete();
    }

    interface TaskPresenter extends BasePresenter {

        void activityResult(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        /**
         * 添加新的事件
         */
        void addNewTask();

        void openTaskDetails(BaseTask task);

        void completeTask(BaseTask task);

        void activateTask(BaseTask task);
    }
}
