package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.restoran.mvp.RestoranModel;

/**
 * Created by NwP.
 */
public interface RestoranPresenter extends BasePresenter {
    interface RestoranView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_ITEM, SHOW_ITEM,
            LOAD_RESTORAN, SHOW_RESTORAN,
            LOAD_RUMAHMAKAN, SHOW_RUMAHMAKAN, LOAD_KAFE, SHOW_KAFE,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        RestoranModel doRetrieveModel();

        void showError();
    }

    void presentState(RestoranView.ViewState state);

}
