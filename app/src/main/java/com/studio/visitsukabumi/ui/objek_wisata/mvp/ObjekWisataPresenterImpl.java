package com.studio.visitsukabumi.ui.objek_wisata.mvp;

import android.util.Log;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel;
import com.studio.visitsukabumi.domains.interactors.ObjekWisataInteractor;
import com.studio.visitsukabumi.presentation.presenters.ObjekWisataPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class ObjekWisataPresenterImpl implements ObjekWisataPresenter, APICallListener {
    ObjekWisataView mView;
    ObjekWisataInteractor mInteractor;

    public ObjekWisataPresenterImpl(ObjekWisataView view) {
        this.mView = view;
        this.mInteractor = new ObjekWisataInteractor(this);
    }

    @Override
    public void presentState(ObjekWisataView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(ObjekWisataView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(ObjekWisataView.ViewState.LOADING);
                break;
            case SHOW_ITEMS:
                mView.showState(ObjekWisataView.ViewState.SHOW_ITEMS);
                break;
            case LOAD_OBJEK_WISATA:
                presentState(ObjekWisataView.ViewState.LOADING);
                mInteractor.callAPIGetAll();
                break;
            case LOAD_RIMBA:
                presentState(ObjekWisataView.ViewState.LOADING);
                presentState(ObjekWisataView.ViewState.SHOW_RIMBA);
                break;
            case SHOW_RIMBA:
                mView.showState(ObjekWisataView.ViewState.SHOW_RIMBA);
                break;
            case LOAD_GUNUNG:
                presentState(ObjekWisataView.ViewState.LOADING);
                presentState(ObjekWisataView.ViewState.SHOW_GUNUNG);
                break;
            case SHOW_GUNUNG:
                mView.showState(ObjekWisataView.ViewState.SHOW_GUNUNG);
                break;
            case LOAD_LAUTPANTAI:
                presentState(ObjekWisataView.ViewState.LOADING);
                presentState(ObjekWisataView.ViewState.SHOW_LAUTPANTAI);
                break;
            case SHOW_LAUTPANTAI:
                mView.showState(ObjekWisataView.ViewState.SHOW_LAUTPANTAI);
                break;
            case SHOW_SUNGAI:
                mView.showState(ObjekWisataView.ViewState.SHOW_SUNGAI);
                break;
            case LOAD_SUNGAI:
                presentState(ObjekWisataView.ViewState.LOADING);
                presentState(ObjekWisataView.ViewState.SHOW_SUNGAI);
                break;
            case ERROR:
                mView.showState(ObjekWisataView.ViewState.ERROR);
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

        Log.i("Objek Wisata", "response " + responseModel.toString());
        switch (route) {
            case OBJEKWISATA_GET_ALL:
                mView.doRetrieveModel().setObjekWisataModel((ObjekWisataModel) responseModel);
                presentState(ObjekWisataView.ViewState.SHOW_ITEMS);
                break;
            case OBJEKWISATA_GET_ID:
                mView.doRetrieveModel().setObjekWisataModel((ObjekWisataModel) responseModel);
                presentState(ObjekWisataView.ViewState.SHOW_ITEMS);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
        List<ObjekWisataModel> subResponseModels = null;
        Log.i("Objek Wisata", "response " + responseModels.size());
        switch (route) {
            case OBJEKWISATA_GET_ALL:
                subResponseModels = (List<ObjekWisataModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListObjekWisata().addAll(subResponseModels);

                presentState(ObjekWisataView.ViewState.SHOW_ITEMS);
                break;
            case OBJEKWISATA_GET_ID:
                subResponseModels = (List<ObjekWisataModel>) (List<?>) responseModels;
                mView.doRetrieveModel().getListObjekWisata().addAll(subResponseModels);

                presentState(ObjekWisataView.ViewState.SHOW_ITEMS);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        switch (route) {
            case OBJEKWISATA_GET_ALL:
                mView.showError();
                break;
            case OBJEKWISATA_GET_ID:
                mView.showError();
                break;
        }
    }
}
