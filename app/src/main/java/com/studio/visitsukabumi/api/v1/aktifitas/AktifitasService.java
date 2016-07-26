package com.studio.visitsukabumi.api.v1.aktifitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface AktifitasService {
    /**
     * get all aktivitas
     */
    @GET("aktivitas")
    Call<List<AktifitasModel>>
    getAll();

    /**
     * get aktivitas by id
     */
    @GET("aktivitas/{id}")
    Call<List<AktifitasModel>>
    getById(@Path("id") String id);
}
