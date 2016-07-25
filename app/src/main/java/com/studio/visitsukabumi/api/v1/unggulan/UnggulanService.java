package com.studio.visitsukabumi.api.v1.unggulan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface UnggulanService {
    /**
     * get all unggulan
     */
    @GET("unggulan")
    Call<List<UnggulanModel>>
    getAll();

    /**
     * get unggulan by id
     */
    @GET("unggulan/{id}")
    Call<List<UnggulanModel>>
    getById(@Path("id") String id);
}
