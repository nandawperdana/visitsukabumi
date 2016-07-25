package com.studio.visitsukabumi.domains.interactors;

import com.studio.visitsukabumi.api.APICallListener;
import com.studio.visitsukabumi.api.APICallManager;
import com.studio.visitsukabumi.api.RootResponseModel;
import com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NwP.
 */
public class ObjekWisataInteractor implements Interactor {
    APICallListener listener;

    public ObjekWisataInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAll() {
        final Enums.APIRoute route = Enums.APIRoute.OBJEKWISATA_GET_ALL;
        Call<List<ObjekWisataModel>> call = APICallManager.getInstance().objekWisataManager.getAll();
        call.enqueue(new Callback<List<ObjekWisataModel>>() {
            @Override
            public void onResponse(Call<List<ObjekWisataModel>> call, Response<List<ObjekWisataModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<ObjekWisataModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetById(String id) {
        final Enums.APIRoute route = Enums.APIRoute.OBJEKWISATA_GET_ID;
        Call<List<ObjekWisataModel>> call = APICallManager.getInstance().objekWisataManager.getById(id);
        call.enqueue(new Callback<List<ObjekWisataModel>>() {
            @Override
            public void onResponse(Call<List<ObjekWisataModel>> call, Response<List<ObjekWisataModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<ObjekWisataModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
