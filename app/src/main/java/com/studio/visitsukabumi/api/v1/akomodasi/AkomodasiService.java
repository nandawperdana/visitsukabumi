package com.studio.visitsukabumi.api.v1.akomodasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface AkomodasiService {
    /**
     * get all penginapan
     */
    @GET("penginapan")
    Call<List<AkomodasiModel>>
    getAll();

    /**
     * get penginapan by id
     */
    @GET("penginapan/{id}")
    Call<AkomodasiModel>
    getById(@Path("id") String id);
}
