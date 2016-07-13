package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public interface AkomodasiPresenter extends BasePresenter {
    interface AkomodasiView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            OPEN_MENU, ERROR
        }

        void showState(ViewState state);

        AkomodasiModel doRetrieveModel();

        void showError();
    }

    void presentState(AkomodasiView.ViewState state);
}
