package com.cjy.jianghw.app.tasks;

import com.cjy.jianghw.app.base.BaseView;
import com.cjy.jianghw.app.base.BasePresenter;

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
    }

    interface TaskPresenter extends BasePresenter {

        void activityResult(int requestCode, int resultCode);

        void loadTasks(boolean forceUpdate);

        /**
         * 添加新的事件
         */
        void addNewTask();
    }
}
