package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.dashboard.mvp.DashboardModel;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public interface DashboardPresenter extends BasePresenter {
    interface DashboardView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            OPEN_MENU, ERROR
        }

        void showState(ViewState state);

        DashboardModel doRetrieveModel();

        void showError();
    }

    void presentState(DashboardView.ViewState state);
}
