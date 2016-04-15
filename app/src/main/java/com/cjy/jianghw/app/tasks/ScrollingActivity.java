package com.cjy.jianghw.app.tasks;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cjy.jianghw.app.Injection;
import com.cjy.jianghw.app.R;
import com.cjy.jianghw.app.util.ActivityUtils;

public class ScrollingActivity extends AppCompatActivity {

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";//当前过滤
    private ScrollingPresenter mScrollingPresenter;//对应presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //create view
                    createFragmentView();

                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        if (savedInstanceState != null) {
            EnumFilterType enumFilterType = (EnumFilterType)
                    savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mScrollingPresenter.setFiltering(enumFilterType);
        }
    }

    private void createFragmentView() {
        ScrollingFragment scrollingFragment =
                (ScrollingFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (scrollingFragment == null) {
            scrollingFragment = ScrollingFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), scrollingFragment, R.id.contentFrame);
        }

        //Create the presenter 传入data层 view层
        if (mScrollingPresenter == null) {
            mScrollingPresenter = new ScrollingPresenter(//注射
                    Injection.provideTwoRepository(getApplicationContext()), scrollingFragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putSerializable(CURRENT_FILTERING_KEY, mScrollingPresenter.getFiltering());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
