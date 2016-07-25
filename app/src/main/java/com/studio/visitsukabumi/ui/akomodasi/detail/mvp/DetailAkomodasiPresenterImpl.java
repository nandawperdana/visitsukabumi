package com.studio.visitsukabumi.ui.akomodasi.detail.mvp;

import com.studio.visitsukabumi.presentation.presenters.DetailAkomodasiPresenter;

/**
 * Created by NwP.
 */
public class DetailAkomodasiPresenterImpl implements DetailAkomodasiPresenter {
    DetailAkomodasiView mView;

    public DetailAkomodasiPresenterImpl(DetailAkomodasiView mView) {
        this.mView = mView;
    }

    @Override
    public void presentState(DetailAkomodasiView.ViewState state) {
        switch (state) {
            case IDLE:
                presentState(DetailAkomodasiView.ViewState.IDLE);
                break;
            case LOADING:
                presentState(DetailAkomodasiView.ViewState.LOADING);
                break;
            case SHOW_DETAILS:
                mView.showState(DetailAkomodasiView.ViewState.SHOW_DETAILS);
                break;
            case ERROR:
                mView.showState(DetailAkomodasiView.ViewState.ERROR);
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
