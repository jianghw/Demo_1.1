package com.jianghw.music.view;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import com.jianghw.music.data.DataLoader;
import com.jianghw.music.data.Repository;
import com.jianghw.music.view.allsongs.IAllSongsContract;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class AllSongsPresenter implements IAllSongsContract.IAllSongsPresenter {
    private final IAllSongsContract.IAllSongsView mFragment;

    public AllSongsPresenter(@NonNull DataLoader dataLoader, @NonNull LoaderManager supportLoaderManager,
                             @NonNull Repository repository, @NonNull IAllSongsContract.IAllSongsView iAllSongsView) {
        this.mFragment = iAllSongsView;

    }
}
