package com.studio.visitsukabumi.ui.belanja.mvp;

import android.util.Log;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.belanja.BelanjaModel;
import com.studio.visitsukabumi.domains.interactors.BelanjaInteractor;
import com.studio.visitsukabumi.presentation.presenters.BelanjaPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class BelanjaPresenterImpl implements BelanjaPresenter, APICallListener {
    BelanjaView mView;
    BelanjaInteractor mInteractor;

    public BelanjaPresenterImpl(BelanjaView mView) {
        this.mView = mView;
        this.mInteractor = new BelanjaInteractor(this);
    }

    @Override
    public void presentState(BelanjaView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(BelanjaView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(BelanjaView.ViewState.LOADING);
                break;
            case LOAD_ITEM:
                presentState(BelanjaView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case SHOW_ITEM:
                mView.showState(BelanjaView.ViewState.SHOW_ITEM);
                break;
            case LOAD_PASAR:
                presentState(BelanjaView.ViewState.LOADING);
                presentState(BelanjaView.ViewState.SHOW_PASAR);
                break;
            case SHOW_PASAR:
                mView.showState(BelanjaView.ViewState.SHOW_PASAR);
                break;
            case LOAD_OLEHOLEH:
                presentState(BelanjaView.ViewState.LOADING);
                presentState(BelanjaView.ViewState.SHOW_OLEHOLEH);
                break;
            case SHOW_OLEHOLEH:
                mView.showState(BelanjaView.ViewState.SHOW_OLEHOLEH);
                break;
            case LOAD_MALL:
                presentState(BelanjaView.ViewState.LOADING);
                presentState(BelanjaView.ViewState.SHOW_MALL);
                break;
            case SHOW_MALL:
                mView.showState(BelanjaView.ViewState.SHOW_MALL);
                break;
            case ERROR:
                mView.showState(BelanjaView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel) {
        switch (route) {
            case BELANJA_GET_ALL:
                break;
            case BELANJA_GET_ID:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> subResponseModels = null;
        Log.i("Belanja", "response " + responseModels.size());
        switch (route) {
            case BELANJA_GET_ALL:
                subResponseModels = (List<BelanjaModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListBelanja().addAll(subResponseModels);

                presentState(BelanjaView.ViewState.SHOW_ITEM);
                break;
            case BELANJA_GET_ID:
                subResponseModels = (List<BelanjaModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListBelanja().addAll(subResponseModels);

                presentState(BelanjaView.ViewState.SHOW_ITEM);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(BelanjaView.ViewState.IDLE);
        switch (route) {
            case BELANJA_GET_ALL:
                break;
            case BELANJA_GET_ID:
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
