package com.jianghw.music.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.jianghw.music.contant.MContant;
import com.jianghw.music.data.DataLoader;
import com.jianghw.music.data.MusicTask;
import com.jianghw.music.data.Repository;
import com.jianghw.music.view.allsongs.IAllSongsContract;
import com.jianghw.music.xutil.NullExUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class AllSongsPresenter implements IAllSongsContract.IAllSongsPresenter,
        LoaderManager.LoaderCallbacks<List<MusicTask>> {

    private final IAllSongsContract.IAllSongsView mFragment;
    private final DataLoader mDataLoader;
    private final LoaderManager mLoaderManager;
    private final Repository mRepository;

    private MusicFilterType mCurrentFiltering = MusicFilterType.ALL_TASKS;
    private List<MusicTask> mCurrentTasks;//

    public AllSongsPresenter(@NonNull DataLoader dataLoader, @NonNull LoaderManager loaderManager,
                             @NonNull Repository repository, @NonNull IAllSongsContract.IAllSongsView iAllSongsView) {
        this.mDataLoader = NullExUtils.checkNotNull(dataLoader, "loader cannot be null!");
        this.mLoaderManager = NullExUtils.checkNotNull(loaderManager, "loader manager cannot be null");
        this.mRepository = NullExUtils.checkNotNull(repository, "tasksRepository cannot be null");
        this.mFragment = NullExUtils.checkNotNull(iAllSongsView, "tasksView cannot be null!");
    }


    @Override
    public Serializable getFiltering() {
        return mCurrentFiltering;
    }

    /**
     * Sets the current task filtering type.
     *
     * @param filterType Can be {@link MusicFilterType#ALL_TASKS},
     *                   {@link MusicFilterType#COMPLETED_TASKS},
     *                   or {@link MusicFilterType#ACTIVE_TASKS}
     */
    @Override
    public void setFiltering(MusicFilterType filterType) {
        this.mCurrentFiltering = filterType;
    }

    @Override
    public void initStart() {
        mLoaderManager.initLoader(MContant.MInteger.TASKS_QUERY, null, this);
    }

    /**
     * Activity中的转载器  监听
     *
     * @param id   事件id
     * @param args 信息
     * @return
     */
    @Override
    public Loader<List<MusicTask>> onCreateLoader(int id, Bundle args) {
        mFragment.setLoadingIndicator(true);
        return mDataLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<MusicTask>> loader, List<MusicTask> data) {
        mFragment.setLoadingIndicator(false);

        mCurrentTasks = data;
        if (mCurrentTasks == null) {
            mFragment.showLoadingTasksError();
        } else {
            showFilteredTasks();
        }
    }

    private void showFilteredTasks() {
        List<MusicTask> tasksToDisplay = new ArrayList<>();
        if (mCurrentTasks != null && !mCurrentTasks.isEmpty()) {
            for (MusicTask musicTask : mCurrentTasks) {
                switch (mCurrentFiltering) {
                    case ALL_TASKS:
                        tasksToDisplay.add(musicTask);
                        break;
                    case ACTIVE_TASKS:
                        if (musicTask.isActive())
                            tasksToDisplay.add(musicTask);
                        break;
                    case COMPLETED_TASKS:
                        if (musicTask.isCompleted())
                            tasksToDisplay.add(musicTask);
                        break;
                    default:
                        tasksToDisplay.add(musicTask);
                        break;
                }
            }
        }
        processTasks(tasksToDisplay);
    }

    private void processTasks(List<MusicTask> tasksToDisplay) {
        if (tasksToDisplay.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyTasks();
        } else {
            // Show the list of tasks
            mFragment.showTasks(tasksToDisplay);
            // Set the filter label's text.
            showFilterLabel();
        }
    }

    private void showFilterLabel() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mFragment.showActiveFilterLabel();
                break;
            case COMPLETED_TASKS:
                mFragment.showCompletedFilterLabel();
                break;
            default:
                mFragment.showAllFilterLabel();
                break;
        }
    }

    private void processEmptyTasks() {
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mFragment.showNoActiveTasks();
                break;
            case COMPLETED_TASKS:
                mFragment.showNoCompletedTasks();
                break;
            default:
                mFragment.showNoTasks();
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }
}
