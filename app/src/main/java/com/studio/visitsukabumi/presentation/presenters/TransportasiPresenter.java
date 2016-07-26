package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.transportasi.mvp.TransportasiModel;

/**
 * Created by NwP.
 */
public interface TransportasiPresenter extends BasePresenter {
    interface TransportasiView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_TERMINAL, SHOW_TERMINAL, LOAD_STASIUN, SHOW_STASIUN, LOAD_PELABUHAN, SHOW_PELABUHAN,
            LOAD_TRANSPORTASI, SHOW_TRANSPORTASI,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        TransportasiModel doRetrieveModel();

        void showError();
    }

    void presentState(TransportasiView.ViewState state);
}
