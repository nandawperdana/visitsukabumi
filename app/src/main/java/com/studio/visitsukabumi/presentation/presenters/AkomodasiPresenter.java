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
            LOAD_HOTEL_BINTANG, SHOW_HOTEL_BINTANG, LOAD_HOTEL_NONBINTANG, SHOW_HOTEL_NONBINTANG,
            OPEN_MENU, OPEN_DETAIL, LOAD_AKOMODASI, SHOW_ITEMS, SHOW_PONDOK, LOAD_PONDOK, ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        AkomodasiModel doRetrieveModel();

        void showError();
    }

    void presentState(AkomodasiView.ViewState state);
}
