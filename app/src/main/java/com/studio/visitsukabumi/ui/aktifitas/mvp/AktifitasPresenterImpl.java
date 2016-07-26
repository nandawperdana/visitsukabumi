package com.studio.visitsukabumi.ui.aktifitas.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel;
import com.studio.visitsukabumi.domains.interactors.AktifitasInteractor;
import com.studio.visitsukabumi.presentation.presenters.AktifitasPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class AktifitasPresenterImpl implements AktifitasPresenter, APICallListener {
    AktifitasView mView;
    AktifitasInteractor mInteractor;

    public AktifitasPresenterImpl(AktifitasView mView) {
        this.mView = mView;
        this.mInteractor = new AktifitasInteractor(this);
    }

    @Override
    public void presentState(AktifitasView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(AktifitasView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(AktifitasView.ViewState.LOADING);
                break;
            case LOAD_ITEM:
                presentState(AktifitasView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_ITEM:
                mView.showState(AktifitasView.ViewState.SHOW_ITEM);
                break;
            case ERROR:
                mView.showState(AktifitasView.ViewState.ERROR);
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
            case AKTIFITAS_GET_ALL:
                break;
            case AKTIFITAS_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel> subResponseModels = null;
        switch (route) {
            case AKTIFITAS_GET_ALL:
                subResponseModels = (List<AktifitasModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(AktifitasView.ViewState.SHOW_ITEM);
                break;
            case AKTIFITAS_GET_ID:
                subResponseModels = (List<AktifitasModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(AktifitasView.ViewState.SHOW_ITEM);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(AktifitasView.ViewState.IDLE);
        switch (route) {
            case AKTIFITAS_GET_ALL:
                presentState(AktifitasView.ViewState.ERROR);
                break;
            case AKTIFITAS_GET_ID:
                presentState(AktifitasView.ViewState.ERROR);
                break;
        }
    }
}
