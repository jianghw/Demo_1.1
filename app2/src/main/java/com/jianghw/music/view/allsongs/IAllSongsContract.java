package com.jianghw.music.view.allsongs;

import com.jianghw.music.base.IBasePresenter;
import com.jianghw.music.base.IBaseView;
import com.jianghw.music.data.MusicTask;
import com.jianghw.music.view.MusicFilterType;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public interface IAllSongsContract {

    interface IAllSongsView extends IBaseView<IAllSongsPresenter> {
        /**
         * 加载指示器
         *
         * @param b
         */
        void setLoadingIndicator(boolean b);

        /**
         * 显示错误
         */
        void showLoadingTasksError();

        /**
         * 提示显示没有~~
         */
        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showNoTasks();

        /**
         * 提示显示当前的过滤标签
         */
        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        /**
         * 成功显示listview数据
         *
         * @param tasksToDisplay
         */
        void showTasks(List<MusicTask> tasksToDisplay);
    }

    interface IAllSongsPresenter extends IBasePresenter {

        Serializable getFiltering();

        void setFiltering(MusicFilterType currentFiltering);

        void initStart();
    }
}
