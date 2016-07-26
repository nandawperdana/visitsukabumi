package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.belanja.BelanjaModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class BelanjaInteractor implements Interactor {
    APICallListener listener;

    public BelanjaInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.BELANJA_GET_ALL;
        Call<List<BelanjaModel>> call = APICallManager.getInstance().belanjaManager.getAll();
        call.enqueue(new Callback<List<BelanjaModel>>() {
            @Override
            public void onResponse(Call<List<BelanjaModel>> call, Response<List<BelanjaModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<BelanjaModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.BELANJA_GET_ID;
        Call<List<BelanjaModel>> call = APICallManager.getInstance().belanjaManager.getById(id);
        call.enqueue(new Callback<List<BelanjaModel>>() {
            @Override
            public void onResponse(Call<List<BelanjaModel>> call, Response<List<BelanjaModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<BelanjaModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
