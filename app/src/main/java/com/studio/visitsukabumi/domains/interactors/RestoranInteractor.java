package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.restoran.RestoranModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class RestoranInteractor implements Interactor {
    APICallListener listener;

    public RestoranInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.RESTORAN_GET_ALL;
        Call<List<RestoranModel>> call = APICallManager.getInstance().restoranManager.getAll();
        call.enqueue(new Callback<List<RestoranModel>>() {
            @Override
            public void onResponse(Call<List<RestoranModel>> call, Response<List<RestoranModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<RestoranModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.RESTORAN_GET_ID;
        Call<List<RestoranModel>> call = APICallManager.getInstance().restoranManager.getById(id);
        call.enqueue(new Callback<List<RestoranModel>>() {
            @Override
            public void onResponse(Call<List<RestoranModel>> call, Response<List<RestoranModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<RestoranModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
