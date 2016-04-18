package com.cjy.jianghw.app.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cjy.jianghw.app.R;
import com.cjy.jianghw.app.base.BaseTask;
import com.cjy.jianghw.app.custom.ScrollChildSwipeRefreshLayout;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;


/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class ScrollingFragment extends Fragment implements ScrollingContractible.TaskView {


    private ScrollingContractible.TaskPresenter mTaskPresenter;

    private ScrollingAdapter mListAdapter;

    private TextView mFilteringLabelView;
    private LinearLayout mTasksView;
    private LinearLayout mNoTasksView;
    private ImageView mNoTaskIcon;
    private TextView mNoTaskMainView;
    private TextView mNoTaskAddView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    public static ScrollingFragment newInstance() {
        return new ScrollingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new ScrollingAdapter(new ArrayList<BaseTask>(0), mTasksScItemListener);
    }

    public interface TasksScItemListener {

        void onTaskClick(BaseTask task);

        void onCompleteTaskClick(BaseTask task);

        void onActivateTaskClice(BaseTask task);

    }

    TasksScItemListener mTasksScItemListener = new TasksScItemListener() {
        @Override
        public void onTaskClick(BaseTask task) {
            mTaskPresenter.openTaskDetails(task);
        }

        @Override
        public void onCompleteTaskClick(BaseTask task) {
            mTaskPresenter.completeTask(task);
        }

        @Override
        public void onActivateTaskClice(BaseTask task) {
            mTaskPresenter.activateTask(task);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ts_scrolling, container, false);
        // Set up tasks view
        ListView listView = (ListView) root.findViewById(R.id.lv_tasks_list);
        listView.setAdapter(mListAdapter);
        mFilteringLabelView = (TextView) root.findViewById(R.id.tv_filterLabel);
        mTasksView = (LinearLayout) root.findViewById(R.id.tasksLL);

        // Set up  no tasks view
        mNoTasksView = (LinearLayout) root.findViewById(R.id.llay_noTasks);
        mNoTaskIcon = (ImageView) root.findViewById(R.id.img_noTasks_icon);
        mNoTaskMainView = (TextView) root.findViewById(R.id.tv_noTasks_main);
        mNoTaskAddView = (TextView) root.findViewById(R.id.tv_noTasks_add);
        mNoTaskAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Set up progress indicator
        swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(listView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {//下拉刷新
                mTaskPresenter.initStart();
            }
        });
        //
        setHasOptionsMenu(true);
        return root;
    }

    public void setPresenter(ScrollingContractible.TaskPresenter taskPresenter) {
        mTaskPresenter = Preconditions.checkNotNull(taskPresenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTaskPresenter.initStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        mTaskPresenter.activityResult(requestCode, resultCode);
    }

    @Override
    public void setLoadingIndicator(final boolean flag) {
        if (getView() == null) return;
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(flag);
            }
        });


    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    /**
     * 页面显示数据更新
     *
     * @param listToShow
     */
    @Override
    public void onShowTasks(List<BaseTask> listToShow) {
        //刷新adapter
        mListAdapter.replaceData(listToShow);

        mTasksView.setVisibility(View.VISIBLE);
        mNoTasksView.setVisibility(View.GONE);
    }

    @Override
    public void onShowLoadingError() {
        showMessage(getString(R.string.loading_tasks_error));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    private void showNoTasksViews(String mainText, int iconRes, boolean showAddView) {
        mTasksView.setVisibility(View.GONE);
        mNoTasksView.setVisibility(View.VISIBLE);

        mNoTaskMainView.setText(mainText);
        mNoTaskIcon.setImageDrawable(getResources().getDrawable(iconRes));
        mNoTaskAddView.setVisibility(showAddView ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onShowNoActiveTasks() {
        showNoTasksViews(
                getResources().getString(R.string.no_tasks_active),
                R.drawable.ic_check_circle_24dp,
                false
        );
    }

    @Override
    public void onShowNoCompletedTasks() {
        showNoTasksViews(
                getResources().getString(R.string.no_tasks_completed),
                R.drawable.ic_verified_user_24dp,
                false
        );
    }

    @Override
    public void onShowNoTasks() {
        showNoTasksViews(
                getResources().getString(R.string.no_tasks_all),
                R.drawable.ic_assignment_turned_in_24dp,
                false
        );
    }

    @Override
    public void onShowActiveFilterLabel() {
        mFilteringLabelView.setText(getResources().getString(R.string.label_active));
    }

    @Override
    public void onShowCompletedFilterLabel() {
        mFilteringLabelView.setText(getResources().getString(R.string.label_completed));
    }

    @Override
    public void onShowAllFilterLabel() {
        mFilteringLabelView.setText(getResources().getString(R.string.label_all));
    }

    @Override
    public void onShowAddTask() {
      /*  Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);*/
    }

    @Override
    public void showTaskDetailsUI(String id) {
        // in it's own Activity, since it makes more sense that way and it gives us the flexibility to show some Intent stubbing.
    /*    Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(TaskDetailActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);*/
    }

    @Override
    public void showTaskMarkedComplete() {
        showMessage(getString(R.string.task_marked_complete));
    }
}
