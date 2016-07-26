package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class SeniBudayaInteractor implements Interactor {
    APICallListener listener;

    public SeniBudayaInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.SENIBUDAYA_GET_ALL;
        Call<List<SeniBudayaModel>> call = APICallManager.getInstance().seniBudayaManager.getAll();
        call.enqueue(new Callback<List<SeniBudayaModel>>() {
            @Override
            public void onResponse(Call<List<SeniBudayaModel>> call, Response<List<SeniBudayaModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<SeniBudayaModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.SENIBUDAYA_GET_ID;
        Call<List<SeniBudayaModel>> call = APICallManager.getInstance().seniBudayaManager.getById(id);
        call.enqueue(new Callback<List<SeniBudayaModel>>() {
            @Override
            public void onResponse(Call<List<SeniBudayaModel>> call, Response<List<SeniBudayaModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<SeniBudayaModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
