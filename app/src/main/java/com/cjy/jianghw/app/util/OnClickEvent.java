package com.cjy.jianghw.app.util;

import android.view.View;

/**
 * <b>@Description:</b>
 * 防止Button的频繁点击,多次执行点击事件
 * <br/>
 * <b>@Author:</b>
 * jianghw<br/>
 * <b>@Since:</b>
 * 2016/4/24<br/>
 */
public abstract class OnClickEvent implements View.OnClickListener {

    private long lastTime;

    @Override
    public void onClick(View v) {
        if (onDoubClick()) return;
        singleClick(v);
    }

    private boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;
        if (time < 500) flag = true;
        lastTime = System.currentTimeMillis();
        return flag;
    }

    protected abstract void singleClick(View v);
}
