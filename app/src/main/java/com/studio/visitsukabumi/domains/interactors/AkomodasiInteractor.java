package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class AkomodasiInteractor implements Interactor {
    APICallListener listener;

    public AkomodasiInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.AKOMODASI_GET_ALL;
        Call<List<AkomodasiModel>> call = APICallManager.getInstance().akomodasiManager.getAll();
        call.enqueue(new Callback<List<AkomodasiModel>>() {
            @Override
            public void onResponse(Call<List<AkomodasiModel>> call, Response<List<AkomodasiModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AkomodasiModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.AKOMODASI_GET_ID;
        Call<AkomodasiModel> call = APICallManager.getInstance().akomodasiManager.getById(id);
        call.enqueue(new Callback<AkomodasiModel>() {
            @Override
            public void onResponse(Call<AkomodasiModel> call, Response<AkomodasiModel> response) {
                listener.onAPICallSucceed(route, response.body());
            }

            @Override
            public void onFailure(Call<AkomodasiModel> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
