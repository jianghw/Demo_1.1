package com.cjy.jianghw.app.custom;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class ScrollChildSwipeRefreshLayout extends SwipeRefreshLayout {
    private View mScrollUpChild;

    public ScrollChildSwipeRefreshLayout(Context context) {
        super(context);
    }

    public ScrollChildSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollUpChild(View view) {
        mScrollUpChild = view;
    }

    /**
     * 当所含子控件view 不为null时
     *
     * @return -1
     */
    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpChild != null) {
            // Check if this view can be scrolled vertically in a certain direction.
            return ViewCompat.canScrollVertically(mScrollUpChild, -1);
        }
        return super.canChildScrollUp();
    }
}
