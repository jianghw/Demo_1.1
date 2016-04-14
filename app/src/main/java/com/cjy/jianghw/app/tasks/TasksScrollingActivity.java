package com.cjy.jianghw.app.tasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cjy.jianghw.app.R;
import com.cjy.jianghw.app.util.ActivityUtils;

public class TasksScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        TasksScFragment tsFragment =
                (TasksScFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(tsFragment==null){
            tsFragment= TasksScFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),tsFragment,R.id.contentFrame);
        }
    }
}
