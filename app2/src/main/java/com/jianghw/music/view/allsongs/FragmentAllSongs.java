package com.jianghw.music.view.allsongs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jianghw.music.contant.MContant;
import com.jianghw.music.data.DataLoader;
import com.jianghw.music.data.Injection;
import com.jianghw.music.data.MusicTask;
import com.jianghw.music.data.Repository;
import com.jianghw.music.view.MusicFilterType;

import java.util.List;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class FragmentAllSongs extends Fragment implements IAllSongsContract.IAllSongsView {

    private Context mContext;
    private AllSongsPresenter allSongsPresenter;

    public static FragmentAllSongs newInstance() {
        return new FragmentAllSongs();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //注入本地库和远程库
        Repository repository = Injection.provideRepository(mContext.getApplicationContext());
        allSongsPresenter = new AllSongsPresenter(
                new DataLoader(mContext.getApplicationContext(), repository),
                getLoaderManager(),
                repository,
                this);

        if (savedInstanceState != null) {
            MusicFilterType currentFiltering =
                    (MusicFilterType) savedInstanceState.getSerializable(MContant.MString.CURRENT_FILTERING_KEY);
            allSongsPresenter.setFiltering(currentFiltering);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        allSongsPresenter.initStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(MContant.MString.CURRENT_FILTERING_KEY, allSongsPresenter.getFiltering());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setLoadingIndicator(boolean b) {

    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showNoActiveTasks() {

    }

    @Override
    public void showNoCompletedTasks() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showActiveFilterLabel() {

    }

    @Override
    public void showCompletedFilterLabel() {

    }

    @Override
    public void showAllFilterLabel() {

    }

    @Override
    public void showTasks(List<MusicTask> tasksToDisplay) {

    }
}
