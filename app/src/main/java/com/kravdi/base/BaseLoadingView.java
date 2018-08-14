package com.kravdi.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface BaseLoadingView extends MvpView {

    void showLoading(int visibility);

    void showErrorToast(String message);

}
