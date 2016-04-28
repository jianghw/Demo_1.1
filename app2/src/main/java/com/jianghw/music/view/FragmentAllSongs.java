package com.jianghw.music.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jianghw.music.data.DataLoader;
import com.jianghw.music.data.Injection;
import com.jianghw.music.view.allsongs.IAllSongsContract;

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
    public void onResume() {
        super.onResume();

        if(allSongsPresenter==null){
            allSongsPresenter = new AllSongsPresenter(
                    new DataLoader(mContext.getApplicationContext()),
                    getActivity().getSupportLoaderManager(),
                    Injection.provideRepository(mContext.getApplicationContext()),
                    this);
        }
    }
}
