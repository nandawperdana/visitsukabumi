package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class UnggulanInteractor implements Interactor {
    APICallListener listener;

    public UnggulanInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.UNGGULAN_GET_ALL;
        Call<List<UnggulanModel>> call = APICallManager.getInstance().unggulanManager.getAll();
        call.enqueue(new Callback<List<UnggulanModel>>() {
            @Override
            public void onResponse(Call<List<UnggulanModel>> call, Response<List<UnggulanModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<UnggulanModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.AKOMODASI_GET_ID;
        Call<List<UnggulanModel>> call = APICallManager.getInstance().unggulanManager.getById(id);
        call.enqueue(new Callback<List<UnggulanModel>>() {
            @Override
            public void onResponse(Call<List<UnggulanModel>> call, Response<List<UnggulanModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<UnggulanModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
