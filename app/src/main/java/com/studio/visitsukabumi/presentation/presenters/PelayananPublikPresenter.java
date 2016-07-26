package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.pelayanan_publik.mvp.PelayananPublikModel;

/**
 * Created by NwP.
 */
public interface PelayananPublikPresenter extends BasePresenter {
    interface PelayananPublikView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_RUMAHSAKIT, SHOW_RUMAHSAKIT, LOAD_PUSKESMAS, SHOW_PUSKESMAS, LOAD_POLISI, SHOW_POLISI,
            LOAD_MASJID, SHOW_MASJID, LOAD_PELAYANANPUBLIK, SHOW_PELAYANANPUBLIK,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        PelayananPublikModel doRetrieveModel();

        void showError();
    }

    void presentState(PelayananPublikView.ViewState state);
}
