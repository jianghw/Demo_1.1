package com.cjy.jianghw.app.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
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


/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public class TasksScFragment extends Fragment implements TasksScContractible.View {


    private TasksScContractible.Presenter mPresenter;
    private TasksScAdapter mListAdapter;
    private TextView mFilteringLabelView;
    private LinearLayout mTasksView;
    private LinearLayout mNoTasksView;
    private ImageView mNoTaskIcon;
    private TextView mNoTaskMainView;
    private TextView mNoTaskAddView;

    public static TasksScFragment newInstance() {
        return new TasksScFragment();
    }

    @Override
    public void setPresenter(TasksScContractible.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksScAdapter(new ArrayList<BaseTask>(0), mTasksScItemListener);
    }

    public interface TasksScItemListener {
        void onTaskClick(BaseTask task);

        void onCompleteTaskClick(BaseTask task);

        void onActivateTaskClice(BaseTask task);
    }

    TasksScItemListener mTasksScItemListener = new TasksScItemListener() {
        @Override
        public void onTaskClick(BaseTask task) {

        }

        @Override
        public void onCompleteTaskClick(BaseTask task) {

        }

        @Override
        public void onActivateTaskClice(BaseTask task) {

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
        ScrollChildSwipeRefreshLayout swipeRefreshLayout =
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

            }
        });
        //
        setHasOptionsMenu(true);
        return root;
    }
}
