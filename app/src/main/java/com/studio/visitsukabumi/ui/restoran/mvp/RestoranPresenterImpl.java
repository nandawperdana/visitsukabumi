package com.studio.visitsukabumi.ui.restoran.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.restoran.RestoranModel;
import com.studio.visitsukabumi.domains.interactors.RestoranInteractor;
import com.studio.visitsukabumi.presentation.presenters.RestoranPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class RestoranPresenterImpl implements RestoranPresenter, APICallListener {
    RestoranView mView;
    RestoranInteractor mInteractor;

    public RestoranPresenterImpl(RestoranView mView) {
        this.mView = mView;
        this.mInteractor = new RestoranInteractor(this);
    }

    @Override
    public void presentState(RestoranView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(RestoranView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(RestoranView.ViewState.LOADING);
                break;
            case LOAD_ITEM:
                presentState(RestoranView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_ITEM:
                mView.showState(RestoranView.ViewState.SHOW_ITEM);
                break;
            case LOAD_RESTORAN:
                presentState(RestoranView.ViewState.LOADING);
                presentState(RestoranView.ViewState.SHOW_RESTORAN);
                break;
            case SHOW_RESTORAN:
                mView.showState(RestoranView.ViewState.SHOW_RESTORAN);
                break;
            case LOAD_RUMAHMAKAN:
                presentState(RestoranView.ViewState.LOADING);
                presentState(RestoranView.ViewState.SHOW_RUMAHMAKAN);
                break;
            case SHOW_RUMAHMAKAN:
                mView.showState(RestoranView.ViewState.SHOW_RUMAHMAKAN);
                break;
            case LOAD_KAFE:
                presentState(RestoranView.ViewState.LOADING);
                presentState(RestoranView.ViewState.SHOW_KAFE);
                break;
            case SHOW_KAFE:
                mView.showState(RestoranView.ViewState.SHOW_KAFE);
                break;
            case ERROR:
                mView.showError();
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
        switch (route) {
            case RESTORAN_GET_ALL:
                break;
            case RESTORAN_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> subResponseModels = null;
        switch (route) {
            case RESTORAN_GET_ALL:
                subResponseModels = (List<RestoranModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(RestoranView.ViewState.SHOW_ITEM);
                break;
            case RESTORAN_GET_ID:
                subResponseModels = (List<RestoranModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(RestoranView.ViewState.SHOW_ITEM);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(RestoranView.ViewState.IDLE);
        switch (route) {
            case RESTORAN_GET_ALL:
                presentState(RestoranView.ViewState.ERROR);
                break;
            case RESTORAN_GET_ID:
                presentState(RestoranView.ViewState.ERROR);
                break;
        }
    }
}
