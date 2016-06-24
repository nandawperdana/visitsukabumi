package com.studio.visitsukabumi.ui.splash.mvp;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.presentation.presenters.SplashPresenter;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by NwP.
 */
public class SplashPresenterImpl implements SplashPresenter, APICallListener {
    SplashView mView;

    public SplashPresenterImpl(SplashView mView) {
        this.mView = mView;
    }

    @Override
    public void presentState(SplashView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(SplashView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(SplashView.ViewState.LOADING);
                break;
            case SHOW_SPLASH:
                mView.showState(SplashView.ViewState.SHOW_SPLASH);
                break;
            case OPEN_LOGIN:
                mView.showState(SplashView.ViewState.OPEN_LOGIN);
                break;
            case ERROR:
                mView.showState(SplashView.ViewState.ERROR);
                break;
        }
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
