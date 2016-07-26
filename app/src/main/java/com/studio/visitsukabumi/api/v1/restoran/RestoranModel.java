package com.studio.visitsukabumi.api.v1.restoran;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.visitsukabumi.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by NwP.
 */
public class RestoranModel extends RootResponseModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("namaPemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("jmlMeja")
    @Expose
    private String jmlMeja;
    @SerializedName("jmlKursi")
    @Expose
    private String jmlKursi;
    @SerializedName("hidangan")
    @Expose
    private String hidangan;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("foto_url")
    @Expose
    private String fotoUrl;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

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
     * @return The namaPemilik
     */
    public String getNamaPemilik() {
        return namaPemilik;
    }

    /**
     * @param namaPemilik The namaPemilik
     */
    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    /**
     * @return The jmlMeja
     */
    public String getJmlMeja() {
        return jmlMeja;
    }

    /**
     * @param jmlMeja The jmlMeja
     */
    public void setJmlMeja(String jmlMeja) {
        this.jmlMeja = jmlMeja;
    }

    /**
     * @return The jmlKursi
     */
    public String getJmlKursi() {
        return jmlKursi;
    }

    /**
     * @param jmlKursi The jmlKursi
     */
    public void setJmlKursi(String jmlKursi) {
        this.jmlKursi = jmlKursi;
    }

    /**
     * @return The hidangan
     */
    public String getHidangan() {
        return hidangan;
    }

    /**
     * @param hidangan The hidangan
     */
    public void setHidangan(String hidangan) {
        this.hidangan = hidangan;
    }

    /**
     * @return The telepon
     */
    public String getTelepon() {
        return telepon;
    }

    /**
     * @param telepon The telepon
     */
    public void setTelepon(String telepon) {
        this.telepon = telepon;
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
}
