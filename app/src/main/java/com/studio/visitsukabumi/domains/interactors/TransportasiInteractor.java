package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class TransportasiInteractor implements Interactor {
    APICallListener listener;

    public TransportasiInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.TRANSPORTASI_GET_ALL;
        Call<List<TransportasiModel>> call = APICallManager.getInstance().transportasiManager.getAll();
        call.enqueue(new Callback<List<TransportasiModel>>() {
            @Override
            public void onResponse(Call<List<TransportasiModel>> call, Response<List<TransportasiModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<TransportasiModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.TRANSPORTASI_GET_ID;
        Call<List<TransportasiModel>> call = APICallManager.getInstance().transportasiManager.getById(id);
        call.enqueue(new Callback<List<TransportasiModel>>() {
            @Override
            public void onResponse(Call<List<TransportasiModel>> call, Response<List<TransportasiModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<TransportasiModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
