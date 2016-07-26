package com.studio.visitsukabumi.ui.seni_budaya.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel;
import com.studio.visitsukabumi.domains.interactors.SeniBudayaInteractor;
import com.studio.visitsukabumi.presentation.presenters.SeniBudayaPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class SeniBudayaPresenterImpl implements SeniBudayaPresenter, APICallListener {
    SeniBudayaView mView;
    SeniBudayaInteractor mInteractor;

    public SeniBudayaPresenterImpl(SeniBudayaView mView) {
        this.mView = mView;
        this.mInteractor = new SeniBudayaInteractor(this);
    }

    @Override
    public void presentState(SeniBudayaView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(SeniBudayaView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(SeniBudayaView.ViewState.LOADING);
                break;
            case LOAD_SENIBUDAYA:
                presentState(SeniBudayaView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_SENIBUDAYA:
                mView.showState(SeniBudayaView.ViewState.SHOW_SENIBUDAYA);
                break;
            case ERROR:
                mView.showState(SeniBudayaView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel) {
        List<com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel> subResponseModels = null;
        switch (route) {
            case SENIBUDAYA_GET_ALL:
                break;
            case SENIBUDAYA_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel> subResponseModels = null;
        switch (route) {
            case SENIBUDAYA_GET_ALL:
                subResponseModels = (List<SeniBudayaModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(SeniBudayaView.ViewState.SHOW_SENIBUDAYA);
                break;
            case SENIBUDAYA_GET_ID:
                subResponseModels = (List<SeniBudayaModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListItems().addAll(subResponseModels);

                presentState(SeniBudayaView.ViewState.SHOW_SENIBUDAYA);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(SeniBudayaView.ViewState.IDLE);
        switch (route) {
            case SENIBUDAYA_GET_ALL:
                presentState(SeniBudayaView.ViewState.ERROR);
                break;
            case SENIBUDAYA_GET_ID:
                presentState(SeniBudayaView.ViewState.ERROR);
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
