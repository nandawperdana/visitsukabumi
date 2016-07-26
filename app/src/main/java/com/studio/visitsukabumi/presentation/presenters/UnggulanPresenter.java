package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.unggulan.mvp.UnggulanModel;

/**
 * Created by NwP.
 */
public interface UnggulanPresenter extends BasePresenter {
    interface UnggulanView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_ITEM, SHOW_ITEM,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        UnggulanModel doRetrieveModel();

        void showError();
    }

    void presentState(UnggulanView.ViewState state);

}
