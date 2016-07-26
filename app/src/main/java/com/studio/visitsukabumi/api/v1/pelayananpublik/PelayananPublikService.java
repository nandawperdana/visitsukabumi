package com.studio.visitsukabumi.api.v1.pelayananpublik;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface PelayananPublikService {
    /**
     * get all pelayananpublik
     */
    @GET("pelayananpublik")
    Call<List<PelayananPublikModel>>
    getAll();

    /**
     * get pelayananpublik by id
     */
    @GET("pelayananpublik/{id}")
    Call<List<PelayananPublikModel>>
    getById(@Path("id") String id);
}
