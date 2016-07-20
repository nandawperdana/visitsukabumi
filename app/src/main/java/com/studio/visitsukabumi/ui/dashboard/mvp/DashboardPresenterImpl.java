package com.studio.visitsukabumi.ui.dashboard.mvp;

import com.studio.visitsukabumi.presentation.presenters.DashboardPresenter;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class DashboardPresenterImpl implements DashboardPresenter {
    DashboardView mView;

    public DashboardPresenterImpl(DashboardView view) {
        this.mView = view;
    }

    @Override
    public void presentState(DashboardView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(DashboardView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(DashboardView.ViewState.LOADING);
                break;
            case OPEN_MENU:
                mView.showState(DashboardView.ViewState.OPEN_MENU);
                break;
            case ERROR:
                mView.showState(DashboardView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
