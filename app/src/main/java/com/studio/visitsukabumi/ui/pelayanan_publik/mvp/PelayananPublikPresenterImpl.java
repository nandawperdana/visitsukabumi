package com.studio.visitsukabumi.ui.pelayanan_publik.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel;
import com.studio.visitsukabumi.domains.interactors.PelayananPublikInteractor;
import com.studio.visitsukabumi.presentation.presenters.PelayananPublikPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class PelayananPublikPresenterImpl implements PelayananPublikPresenter, APICallListener {
    PelayananPublikInteractor mInteractor;
    PelayananPublikView mView;

    public PelayananPublikPresenterImpl(PelayananPublikView mView) {
        this.mView = mView;
        this.mInteractor = new PelayananPublikInteractor(this);
    }

    @Override
    public void presentState(PelayananPublikView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(PelayananPublikView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(PelayananPublikView.ViewState.LOADING);
                break;
            case LOAD_RUMAHSAKIT:
                presentState(PelayananPublikView.ViewState.LOADING);
                presentState(PelayananPublikView.ViewState.SHOW_RUMAHSAKIT);
                break;
            case SHOW_RUMAHSAKIT:
                mView.showState(PelayananPublikView.ViewState.SHOW_RUMAHSAKIT);
                break;
            case LOAD_PUSKESMAS:
                presentState(PelayananPublikView.ViewState.LOADING);
                presentState(PelayananPublikView.ViewState.SHOW_PUSKESMAS);
                break;
            case SHOW_PUSKESMAS:
                mView.showState(PelayananPublikView.ViewState.SHOW_PUSKESMAS);
                break;
            case LOAD_POLISI:
                presentState(PelayananPublikView.ViewState.LOADING);
                presentState(PelayananPublikView.ViewState.SHOW_POLISI);
                break;
            case SHOW_POLISI:
                mView.showState(PelayananPublikView.ViewState.SHOW_POLISI);
                break;
            case LOAD_MASJID:
                presentState(PelayananPublikView.ViewState.LOADING);
                presentState(PelayananPublikView.ViewState.SHOW_MASJID);
                break;
            case SHOW_MASJID:
                mView.showState(PelayananPublikView.ViewState.SHOW_MASJID);
                break;
            case LOAD_PELAYANANPUBLIK:
                presentState(PelayananPublikView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_PELAYANANPUBLIK:
                mView.showState(PelayananPublikView.ViewState.SHOW_PELAYANANPUBLIK);
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
            case PELAYANANPUBLIK_GET_ALL:
                break;
            case PELAYANANPUBLIK_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> subResponseModels = null;
        switch (route) {
            case PELAYANANPUBLIK_GET_ALL:
                subResponseModels = (List<PelayananPublikModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListPelayananPublik().addAll(subResponseModels);

                presentState(PelayananPublikView.ViewState.SHOW_PELAYANANPUBLIK);
                break;
            case PELAYANANPUBLIK_GET_ID:
                subResponseModels = (List<PelayananPublikModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListPelayananPublik().addAll(subResponseModels);

                presentState(PelayananPublikView.ViewState.SHOW_PELAYANANPUBLIK);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(PelayananPublikView.ViewState.IDLE);
        switch (route) {
            case PELAYANANPUBLIK_GET_ALL:
                presentState(PelayananPublikView.ViewState.ERROR);
                break;
            case PELAYANANPUBLIK_GET_ID:
                presentState(PelayananPublikView.ViewState.ERROR);
                break;
        }
    }
}
