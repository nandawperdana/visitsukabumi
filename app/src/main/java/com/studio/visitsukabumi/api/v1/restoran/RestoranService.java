package com.studio.visitsukabumi.api.v1.restoran;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface RestoranService {
    /**
     * get all restoran
     */
    @GET("restoran")
    Call<List<RestoranModel>>
    getAll();

    /**
     * get senibudaya by id
     */
    @GET("restoran/{id}")
    Call<List<RestoranModel>>
    getById(@Path("id") String id);
}
