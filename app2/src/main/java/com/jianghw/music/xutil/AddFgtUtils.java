package com.jianghw.music.xutil;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
/**
 * @Description: </b>TODO<br/>
 * @Author: </b>jianghw<br>
 * @Since: </b>2016/4/28<br>
 * @See {@link}
 * @Github {@https://github.com/jianghw/Demo_1.1}
 */
public class AddFgtUtils {
    public static void addFragmentOnly(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int layout_id, String tag) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layout_id, fragment, tag);
        transaction.commit();
    }
}
