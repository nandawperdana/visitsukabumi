package com.studio.visitsukabumi.api.v1.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.visitsukabumi.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by NwP.
 */
public class EventModel extends RootResponseModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("foto_url")
    @Expose
    private String fotoUrl;

    public EventModel() {
    }

    public EventModel(Integer id, String nama, String tanggal, String deskripsi, String fotoUrl) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.fotoUrl = fotoUrl;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama The nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return The deskripsi
     */
    public String getDeskripsi() {
        return deskripsi;
    }

    /**
     * @param deskripsi The deskripsi
     */
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    /**
     * @return The fotoUrl
     */
    public String getFotoUrl() {
        return fotoUrl;
    }

    /**
     * @param fotoUrl The foto_url
     */
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
