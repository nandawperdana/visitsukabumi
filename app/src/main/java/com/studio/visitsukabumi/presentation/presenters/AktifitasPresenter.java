package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.aktifitas.mvp.AktifitasModel;

/**
 * Created by NwP.
 */
public interface AktifitasPresenter extends BasePresenter {
    interface AktifitasView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_ITEM, SHOW_ITEM,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        AktifitasModel doRetrieveModel();

        void showError();
    }

    void presentState(AktifitasView.ViewState state);

}
