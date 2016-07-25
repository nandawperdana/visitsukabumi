package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.akomodasi.detail.mvp.DetailModel;
import com.studio.visitsukabumi.ui.akomodasi.mvp.AkomodasiModel;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public interface DetailAkomodasiPresenter extends BasePresenter {
    interface DetailAkomodasiView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            SHOW_DETAILS,
            ERROR
        }

        void showState(ViewState state);

        DetailModel doRetrieveModel();

        void showError();
    }

    void presentState(DetailAkomodasiView.ViewState state);
}
