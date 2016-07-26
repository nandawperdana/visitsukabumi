package com.studio.visitsukabumi.ui.unggulan.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.domains.interactors.UnggulanInteractor;
import com.studio.visitsukabumi.presentation.presenters.UnggulanPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class UnggulanPresenterImpl implements UnggulanPresenter, APICallListener {
    UnggulanView mView;
    UnggulanInteractor mInteractor;

    public UnggulanPresenterImpl(UnggulanView mView) {
        this.mView = mView;
        this.mInteractor = new UnggulanInteractor(this);
    }

    @Override
    public void presentState(UnggulanView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(UnggulanView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(UnggulanView.ViewState.LOADING);
                break;
            case LOAD_ITEM:
                presentState(UnggulanView.ViewState.LOADING);
                presentState(UnggulanView.ViewState.SHOW_ITEM);
                break;
            case SHOW_ITEM:
                mView.showState(UnggulanView.ViewState.SHOW_ITEM);
                break;
            case ERROR:
                mView.showState(UnggulanView.ViewState.ERROR);
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

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {

    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {

    }
}
