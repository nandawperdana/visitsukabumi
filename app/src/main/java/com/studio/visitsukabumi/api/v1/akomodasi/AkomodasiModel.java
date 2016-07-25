package com.studio.visitsukabumi.api.v1.akomodasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.visitsukabumi.api.RootResponseModel;

/**
 * Created by NwP.
 */
public class AkomodasiModel extends RootResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("namaPemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("jmlKamar")
    @Expose
    private String jmlKamar;
    @SerializedName("jmlTempatTidur")
    @Expose
    private String jmlTempatTidur;
    @SerializedName("tarif")
    @Expose
    private String tarif;
    @SerializedName("bintang")
    @Expose
    private String bintang;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
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
     * @return The jmlKamar
     */
    public String getJmlKamar() {
        return jmlKamar;
    }

    /**
     * @param jmlKamar The jmlKamar
     */
    public void setJmlKamar(String jmlKamar) {
        this.jmlKamar = jmlKamar;
    }

    /**
     * @return The jmlTempatTidur
     */
    public String getJmlTempatTidur() {
        return jmlTempatTidur;
    }

    /**
     * @param jmlTempatTidur The jmlTempatTidur
     */
    public void setJmlTempatTidur(String jmlTempatTidur) {
        this.jmlTempatTidur = jmlTempatTidur;
    }

    /**
     * @return The tarif
     */
    public String getTarif() {
        return tarif;
    }

    /**
     * @param tarif The tarif
     */
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    /**
     * @return The bintang
     */
    public String getBintang() {
        return bintang;
    }

    /**
     * @param bintang The bintang
     */
    public void setBintang(String bintang) {
        this.bintang = bintang;
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
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return The fasilitas
     */
    public String getFasilitas() {
        return fasilitas;
    }

    /**
     * @param fasilitas The fasilitas
     */
    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
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
