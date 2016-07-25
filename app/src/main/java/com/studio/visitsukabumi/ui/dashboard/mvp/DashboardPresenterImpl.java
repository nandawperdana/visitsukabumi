package com.studio.visitsukabumi.ui.dashboard.mvp;

import android.util.Log;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;
import com.studio.visitsukabumi.domains.interactors.UnggulanInteractor;
import com.studio.visitsukabumi.presentation.presenters.DashboardPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class DashboardPresenterImpl implements DashboardPresenter, APICallListener {
    DashboardView mView;
    UnggulanInteractor mInteractor;

    public DashboardPresenterImpl(DashboardView view) {
        this.mView = view;
        this.mInteractor = new UnggulanInteractor(this);
    }

    @Override
    public void presentState(DashboardView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(DashboardView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(DashboardView.ViewState.LOADING);
                break;
            case LOAD_UNGGULAN:
                presentState(DashboardView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_UNGGULAN:
                mView.showState(DashboardView.ViewState.SHOW_UNGGULAN);
                break;
            case OPEN_MENU:
                mView.showState(DashboardView.ViewState.OPEN_MENU);
                break;
            case ERROR:
                mView.showState(DashboardView.ViewState.ERROR);
                break;
        }

        Log.i("App", "state " + state.toString());
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
        Log.i("App", "response " + responseModels.size());
        switch (route) {
            case UNGGULAN_GET_ALL:
                List<UnggulanModel> subResponseModels = (List<UnggulanModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListUnggulanModel().addAll(subResponseModels);
//                mView.doRetrieveModel().setListUnggulanModel(subResponseModels);

                presentState(DashboardView.ViewState.SHOW_UNGGULAN);
                break;
            case AKOMODASI_GET_ID:
                mView.doRetrieveModel().setListUnggulanModel((List<UnggulanModel>) (UnggulanModel) responseModels);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        switch (route) {
            case UNGGULAN_GET_ALL:
                mView.showError();
                break;
            case AKOMODASI_GET_ID:
                mView.showError();
                break;
        }
    }
}
