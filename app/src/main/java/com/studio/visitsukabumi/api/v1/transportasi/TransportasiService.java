package com.studio.visitsukabumi.api.v1.transportasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface TransportasiService {
    /**
     * get all transportasi
     */
    @GET("transportasi")
    Call<List<TransportasiModel>>
    getAll();

    /**
     * get transportasi by id
     */
    @GET("transportasi/{id}")
    Call<List<TransportasiModel>>
    getById(@Path("id") String id);
}
