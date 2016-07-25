package com.studio.visitsukabumi.presentation.presenters;

import com.studio.visitsukabumi.presentation.base.BasePresenter;
import com.studio.visitsukabumi.presentation.base.BaseView;
import com.studio.visitsukabumi.ui.objek_wisata.detail.mvp.DetailModel;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public interface DetailObjekWisataPresenter extends BasePresenter {
    interface DetailObjekWisataView extends BaseView {
        enum ViewState {
            IDLE, LOADING,
            SHOW_DETAILS,
            ERROR
        }

        void showState(ViewState state);

        DetailModel doRetrieveModel();

        void showError();
    }

    void presentState(DetailObjekWisataView.ViewState state);
}
