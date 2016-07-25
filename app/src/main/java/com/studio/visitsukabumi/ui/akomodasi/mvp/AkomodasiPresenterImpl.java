package com.studio.visitsukabumi.ui.akomodasi.mvp;

import android.util.Log;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.domains.interactors.AkomodasiInteractor;
import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class AkomodasiPresenterImpl implements AkomodasiPresenter, APICallListener {
    AkomodasiView mView;
    AkomodasiInteractor mInteractor;

    public AkomodasiPresenterImpl(AkomodasiView view) {
        this.mView = view;
        this.mInteractor = new AkomodasiInteractor(this);
    }

    @Override
    public void presentState(AkomodasiView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(AkomodasiView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(AkomodasiView.ViewState.LOADING);
                break;
            case LOAD_AKOMODASI:
                presentState(AkomodasiView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case LOAD_HOTEL_BINTANG:
                presentState(AkomodasiView.ViewState.LOADING);
                mView.showState(AkomodasiView.ViewState.SHOW_HOTEL_BINTANG);
                break;
            case LOAD_HOTEL_NONBINTANG:
                presentState(AkomodasiView.ViewState.LOADING);
                mView.showState(AkomodasiView.ViewState.SHOW_HOTEL_NONBINTANG);
                break;
            case LOAD_PONDOK:
                presentState(AkomodasiView.ViewState.LOADING);
                mView.showState(AkomodasiView.ViewState.SHOW_PONDOK);
                break;
            case SHOW_ITEMS:
                mView.showState(AkomodasiView.ViewState.SHOW_ITEMS);
                break;
            case ERROR:
                mView.showState(AkomodasiView.ViewState.ERROR);
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
            case AKOMODASI_GET_ALL:
                mView.doRetrieveModel().setAkomodasiModel((AkomodasiModel) responseModel);
                presentState(AkomodasiView.ViewState.SHOW_ITEMS);
                break;
            case AKOMODASI_GET_ID:
                mView.doRetrieveModel().setAkomodasiModel((AkomodasiModel) responseModel);
                presentState(AkomodasiView.ViewState.SHOW_ITEMS);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<AkomodasiModel> subResponseModels = null;
        Log.i("Akomodasi", "response " + responseModels.size());
        switch (route) {
            case AKOMODASI_GET_ALL:
                subResponseModels = (List<AkomodasiModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListAkomodasiModel().addAll(subResponseModels);

                presentState(AkomodasiView.ViewState.SHOW_ITEMS);
                break;
            case AKOMODASI_GET_ID:
                subResponseModels = (List<AkomodasiModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListAkomodasiModel().addAll(subResponseModels);

                presentState(AkomodasiView.ViewState.SHOW_ITEMS);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        switch (route) {
            case AKOMODASI_GET_ALL:
                mView.showError();
                break;
            case AKOMODASI_GET_ID:
                mView.showError();
                break;
        }
    }
}
