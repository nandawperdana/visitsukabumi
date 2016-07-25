package com.studio.visitsukabumi.ui.objek_wisata.detail.mvp;

import com.studio.visitsukabumi.presentation.presenters.DetailObjekWisataPresenter;

/**
 * Created by NwP.
 */
public class DetailObjekWisataPresenterImpl implements DetailObjekWisataPresenter {
    DetailObjekWisataView mView;

    public DetailObjekWisataPresenterImpl(DetailObjekWisataView mView) {
        this.mView = mView;
    }

    @Override
    public void presentState(DetailObjekWisataView.ViewState state) {
        switch (state) {
            case IDLE:
                presentState(DetailObjekWisataView.ViewState.IDLE);
                break;
            case LOADING:
                presentState(DetailObjekWisataView.ViewState.LOADING);
                break;
            case SHOW_DETAILS:
                mView.showState(DetailObjekWisataView.ViewState.SHOW_DETAILS);
                break;
            case ERROR:
                mView.showState(DetailObjekWisataView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }
}
