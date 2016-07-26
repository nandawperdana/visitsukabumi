package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.belanja.mvp.BelanjaModel;

/**
 * Created by NwP.
 */
public interface BelanjaPresenter extends BasePresenter {
    interface BelanjaView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_ITEM, SHOW_ITEM,
            LOAD_PASAR, SHOW_PASAR,
            LOAD_OLEHOLEH, SHOW_OLEHOLEH, LOAD_MALL, SHOW_MALL,
            ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        BelanjaModel doRetrieveModel();

        void showError();
    }

    void presentState(BelanjaView.ViewState state);

}
