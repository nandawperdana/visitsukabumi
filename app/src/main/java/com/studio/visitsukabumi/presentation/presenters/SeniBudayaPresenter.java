package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.seni_budaya.mvp.SeniBudayaModel;

/**
 * Created by NwP.
 */
public interface SeniBudayaPresenter extends BasePresenter {
    interface SeniBudayaView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_SENIBUDAYA, SHOW_SENIBUDAYA,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        SeniBudayaModel doRetrieveModel();

        void showError();
    }

    void presentState(SeniBudayaView.ViewState state);
}
