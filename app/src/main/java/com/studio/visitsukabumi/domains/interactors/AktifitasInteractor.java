package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class AktifitasInteractor implements Interactor {
    APICallListener listener;

    public AktifitasInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.AKTIFITAS_GET_ALL;
        Call<List<AktifitasModel>> call = APICallManager.getInstance().aktifitasManager.getAll();
        call.enqueue(new Callback<List<AktifitasModel>>() {
            @Override
            public void onResponse(Call<List<AktifitasModel>> call, Response<List<AktifitasModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AktifitasModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.AKTIFITAS_GET_ID;
        Call<List<AktifitasModel>> call = APICallManager.getInstance().aktifitasManager.getById(id);
        call.enqueue(new Callback<List<AktifitasModel>>() {
            @Override
            public void onResponse(Call<List<AktifitasModel>> call, Response<List<AktifitasModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AktifitasModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
