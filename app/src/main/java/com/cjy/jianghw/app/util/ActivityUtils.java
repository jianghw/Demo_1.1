package com.cjy.jianghw.app.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>
 * This provides methods to help Activities load their UI. <br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class ActivityUtils {
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}.
     * The operation is performed by the {@code fragmentManager}.
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(frameId,fragment);
        transaction.commit();
    }
}
