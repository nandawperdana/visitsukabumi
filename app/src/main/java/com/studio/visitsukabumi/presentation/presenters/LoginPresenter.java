package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.login.mvp.LoginModel;

/**
 * Created by nandawperdana on 5/12/2016.
 */
public interface LoginPresenter extends BasePresenter {
    interface LoginView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_LOGIN, SHOW_LOGIN,
            LOAD_LOGIN_FACEBOOK, OPEN_MAIN, ERROR
        }

        void showState(ViewState state);

        LoginModel doRetrieveModel();
    }

    void presentState(LoginView.ViewState state);
}
