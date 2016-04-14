package com.cjy.jianghw.app.tasks;

/**
 * <b>@Description:</b>
 * 枚举类 Used with the filter spinner in the tasks list.<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/14<br/>
 */
public enum TasksFilterType {
    /**
     * Do not filter tasks.
     * 不过滤任务。
     */
    ALL_TASKS,
    /**
     * Filters only the active (not completed yet) tasks.
     * 过滤器只活跃(未完成)的任务。
     */
    ACTIVIE_TASKS,
    /**
     * Filters only the completed tasks.
     * 过滤器只完成任务。
     */
    COMPLETED_TASKS


}
