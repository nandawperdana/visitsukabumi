package com.studio.visitsukabumi.api.v1.objekwisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NwP.
 */
public interface ObjekWisataService {
    /**
     * get all objek wisata
     */
    @GET("objekwisata")
    Call<List<ObjekWisataModel>>
    getAll();

    /**
     * get objek wisata by id
     */
    @GET("objekwisata/{id}")
    Call<List<ObjekWisataModel>>
    getById(@Path("id") String id);
}
