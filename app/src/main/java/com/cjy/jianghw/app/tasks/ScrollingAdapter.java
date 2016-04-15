package com.cjy.jianghw.app.tasks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cjy.jianghw.app.base.BaseTask;

import java.util.ArrayList;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class ScrollingAdapter extends BaseAdapter{

    public ScrollingAdapter(ArrayList<BaseTask> baseTasks,
                            ScrollingFragment.TasksScItemListener tasksScItemListener) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
