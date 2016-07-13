package com.studio.visitsukabumi.ui.akomodasi.mvp;

import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class AkomodasiPresenterImpl implements AkomodasiPresenter {
    AkomodasiView mView;

    public AkomodasiPresenterImpl(AkomodasiView view) {
        this.mView = view;
    }

    @Override
    public void presentState(AkomodasiView.ViewState state) {
        switch (state) {
            case IDLE:
                break;
            case LOADING:
                break;
            case OPEN_MENU:
                break;
            case ERROR:
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
