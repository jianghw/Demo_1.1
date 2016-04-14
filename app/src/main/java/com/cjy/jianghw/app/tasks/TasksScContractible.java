package com.cjy.jianghw.app.tasks;

import com.cjy.jianghw.app.base.BaseView;
import com.cjy.jianghw.app.base.BasePresenter;

/**
 * <b>@Description:</b>
 * This specifies the contract between the view and the presenter<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/14 0014<br/>
 */
public interface TasksScContractible {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
