package com.studio.visitsukabumi.api.v1.belanja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface BelanjaService {
    /**
     * get all belanja
     */
    @GET("belanja")
    Call<List<BelanjaModel>>
    getAll();

    /**
     * get belanja by id
     */
    @GET("belanja/{id}")
    Call<List<BelanjaModel>>
    getById(@Path("id") String id);
}
