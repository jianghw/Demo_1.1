package com.cjy.jianghw.app.base;

import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class BaseTask {

    private final String mId;
    @Nullable
    private final String mTitle;
    @Nullable
    private final String mDescription;
    private final boolean mCompleted;//是否完成

    public BaseTask(@Nullable String title, @Nullable String description) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = false;
    }
}
