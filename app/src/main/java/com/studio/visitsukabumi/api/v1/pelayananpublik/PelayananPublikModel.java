package com.studio.visitsukabumi.api.v1.pelayananpublik;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.visitsukabumi.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by NwP.
 */
public class PelayananPublikModel extends RootResponseModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("foto_url")
    @Expose
    private String fotoUrl;

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
     * @return The alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat The alamat
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return The jenis
     */
    public String getJenis() {
        return jenis;
    }

    /**
     * @param jenis The jenis
     */
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    /**
     * @return The keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan The keterangan
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
}
