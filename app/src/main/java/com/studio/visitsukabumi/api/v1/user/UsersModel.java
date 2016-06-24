package com.studio.visitsukabumi.api.v1.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.visitsukabumi.api.RootResponseModel;

import java.io.Serializable;

/**
 * Created by NwP.
 */
public class UsersModel extends RootResponseModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("tempatLahir")
    @Expose
    private String tempatLahir;
    @SerializedName("tglLahir")
    @Expose
    private String tglLahir;
    @SerializedName("imgUsrFileName")
    @Expose
    private String imgUsrFileName;
    @SerializedName("imgUsrFilePath")
    @Expose
    private String imgUsrFilePath;

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
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The tempatLahir
     */
    public String getTempatLahir() {
        return tempatLahir;
    }

    /**
     * @param tempatLahir The tempatLahir
     */
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    /**
     * @return The tglLahir
     */
    public String getTglLahir() {
        return tglLahir;
    }

    /**
     * @param tglLahir The tglLahir
     */
    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    /**
     * @return The imgUsrFileName
     */
    public String getImgUsrFileName() {
        return imgUsrFileName;
    }

    /**
     * @param imgUsrFileName The imgUsrFileName
     */
    public void setImgUsrFileName(String imgUsrFileName) {
        this.imgUsrFileName = imgUsrFileName;
    }

    /**
     * @return The imgUsrFilePath
     */
    public String getImgUsrFilePath() {
        return imgUsrFilePath;
    }

    /**
     * @param imgUsrFilePath The imgUsrFilePath
     */
    public void setImgUsrFilePath(String imgUsrFilePath) {
        this.imgUsrFilePath = imgUsrFilePath;
    }
}
