package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.splash.mvp.SplashModel;

/**
 * Created by NwP.
 */
public interface SplashPresenter {
    interface SplashView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            SHOW_SPLASH,
            OPEN_LOGIN, OPEN_DASHBOARD, ERROR
        }

        void showState(ViewState state);

        SplashModel doRetrieveModel();

        void showToast(String message);

        void showError();
    }

    void presentState(SplashView.ViewState state);

}
