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

    /**
     * Use this constructor to create a new active Task.
     *
     * @param title
     * @param description
     */
    public BaseTask(@Nullable String title, @Nullable String description) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = false;
    }

    /**
     * 复制或创建一个新的任务 活动的
     *
     * @param id
     * @param title
     * @param description
     */
    public BaseTask(String id, @Nullable String title, @Nullable String description) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = false;
    }

    /**
     *指定一个已完成的任务,如果任务已经有一个id(复制另一个任务）
     * @param title
     * @param description
     * @param completed
     */
    public BaseTask(@Nullable String title, @Nullable String description,boolean completed) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    public BaseTask(String id, @Nullable String title, @Nullable String description,boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return (mTitle == null || "".equals(mTitle)) &&
                (mDescription == null || "".equals(mDescription));
    }

    @Override
    public String toString() {
        return "BaseTask{" +
                "mId='" + mId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mCompleted=" + mCompleted +
                '}';
    }

    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getTitleForList() {
        if (mTitle != null && !mTitle.equals("")) {
            return mTitle;
        } else {
            return mDescription;
        }
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + (mCompleted ? 1 : 0);
        return result;
    }


}
