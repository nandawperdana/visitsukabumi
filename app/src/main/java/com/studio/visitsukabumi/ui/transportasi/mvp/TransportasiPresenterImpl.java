package com.studio.visitsukabumi.ui.transportasi.mvp;

import android.util.Log;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel;
import com.studio.visitsukabumi.domains.interactors.TransportasiInteractor;
import com.studio.visitsukabumi.presentation.presenters.TransportasiPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class TransportasiPresenterImpl implements TransportasiPresenter, APICallListener {
    TransportasiPresenter.TransportasiView mView;
    TransportasiInteractor mInteractor;

    public TransportasiPresenterImpl(TransportasiPresenter.TransportasiView mView) {
        this.mView = mView;
        this.mInteractor = new TransportasiInteractor(this);
    }

    @Override
    public void presentState(TransportasiView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(TransportasiView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(TransportasiView.ViewState.LOADING);
                break;
            case LOAD_TERMINAL:
                presentState(TransportasiView.ViewState.LOADING);
                presentState(TransportasiView.ViewState.SHOW_TERMINAL);
                break;
            case SHOW_TERMINAL:
                mView.showState(TransportasiView.ViewState.SHOW_TERMINAL);
                break;
            case LOAD_STASIUN:
                presentState(TransportasiView.ViewState.LOADING);
                presentState(TransportasiView.ViewState.SHOW_STASIUN);
                break;
            case SHOW_STASIUN:
                mView.showState(TransportasiView.ViewState.SHOW_STASIUN);
                break;
            case LOAD_PELABUHAN:
                presentState(TransportasiView.ViewState.LOADING);
                presentState(TransportasiView.ViewState.SHOW_PELABUHAN);
                break;
            case SHOW_PELABUHAN:
                mView.showState(TransportasiView.ViewState.SHOW_PELABUHAN);
                break;
            case LOAD_TRANSPORTASI:
                presentState(TransportasiView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_TRANSPORTASI:
                mView.showState(TransportasiView.ViewState.SHOW_TRANSPORTASI);
                break;
            case ERROR:
                mView.showState(TransportasiView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel) {
        switch (route) {
            case TRANSPORTASI_GET_ALL:
                break;
            case TRANSPORTASI_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<TransportasiModel> subResponseModels = null;
        Log.i("Transportasi", "response " + responseModels.size());
        switch (route) {
            case TRANSPORTASI_GET_ALL:
                subResponseModels = (List<TransportasiModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListTransportasi().addAll(subResponseModels);

                presentState(TransportasiView.ViewState.SHOW_TRANSPORTASI);
                break;
            case TRANSPORTASI_GET_ID:
                subResponseModels = (List<TransportasiModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListTransportasi().addAll(subResponseModels);

                presentState(TransportasiView.ViewState.SHOW_TRANSPORTASI);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(TransportasiView.ViewState.IDLE);
        switch (route) {
            case TRANSPORTASI_GET_ALL:
                presentState(TransportasiView.ViewState.ERROR);
                break;
            case TRANSPORTASI_GET_ID:
                presentState(TransportasiView.ViewState.ERROR);
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
