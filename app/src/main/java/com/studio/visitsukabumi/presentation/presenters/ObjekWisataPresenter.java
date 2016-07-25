package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.objek_wisata.mvp.ObjekWisataModel;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public interface ObjekWisataPresenter extends BasePresenter {
    interface ObjekWisataView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            LOAD_RIMBA, SHOW_RIMBA, LOAD_GUNUNG, SHOW_GUNUNG,
            LOAD_LAUTPANTAI, SHOW_LAUTPANTAI, SHOW_SUNGAI, LOAD_SUNGAI, SHOW_ITEMS, LOAD_OBJEK_WISATA, ERROR
        }

        enum ScreenState {
            SCREEN_BLANK, SCREEN_NOT_BLANK
        }

        void showState(ViewState state);

        ObjekWisataModel doRetrieveModel();

        void showError();
    }

    void presentState(ObjekWisataView.ViewState state);
}
