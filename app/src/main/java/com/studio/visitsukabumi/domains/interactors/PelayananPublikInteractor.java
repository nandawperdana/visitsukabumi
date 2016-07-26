package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class PelayananPublikInteractor implements Interactor {
    APICallListener listener;

    public PelayananPublikInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.PELAYANANPUBLIK_GET_ALL;
        Call<List<PelayananPublikModel>> call = APICallManager.getInstance().pelayananPublikManager.getAll();
        call.enqueue(new Callback<List<PelayananPublikModel>>() {
            @Override
            public void onResponse(Call<List<PelayananPublikModel>> call, Response<List<PelayananPublikModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<PelayananPublikModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.PELAYANANPUBLIK_GET_ID;
        Call<List<PelayananPublikModel>> call = APICallManager.getInstance().pelayananPublikManager.getById(id);
        call.enqueue(new Callback<List<PelayananPublikModel>>() {
            @Override
            public void onResponse(Call<List<PelayananPublikModel>> call, Response<List<PelayananPublikModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<PelayananPublikModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
