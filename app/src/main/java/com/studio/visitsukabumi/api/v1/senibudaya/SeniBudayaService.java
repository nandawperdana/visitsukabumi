package com.studio.visitsukabumi.api.v1.senibudaya;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface SeniBudayaService {
    /**
     * get all senibudaya
     */
    @GET("senibudaya")
    Call<List<SeniBudayaModel>>
    getAll();

    /**
     * get senibudaya by id
     */
    @GET("senibudaya/{id}")
    Call<List<SeniBudayaModel>>
    getById(@Path("id") String id);
}
