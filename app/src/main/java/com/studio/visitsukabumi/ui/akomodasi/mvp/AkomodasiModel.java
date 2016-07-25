package com.studio.visitsukabumi.ui.akomodasi.mvp;

import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelNonBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.fragment.PondokFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class AkomodasiModel {
    private List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModel;
    private List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelBintang;
    private List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelNonBintang;
    private List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelPondok;
    private com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel akomodasiModel;

    private int page = 0;
    private int fragmentSelected;

    private HotelBintangFragment hotelBintangFragment;
    private HotelNonBintangFragment hotelNonBintangFragment;
    private PondokFragment pondokFragment;

    private AkomodasiPresenter.AkomodasiView.ScreenState screenState;

    public static final String TAG_HOTEL_BINTANG = "Hotel Bintang";
    public static final String TAG_HOTEL_NON_BINTANG = "Hotel Non Bintang";
    public static final String TAG_PONDOK = "Pondok Wisata";
    public static final String TAG_PENGINAPAN = "Penginapan Remaja";

    public AkomodasiModel() {
        this.listAkomodasiModel = new ArrayList<>();
        this.listAkomodasiModelBintang = new ArrayList<>();
        this.listAkomodasiModelNonBintang = new ArrayList<>();
        this.listAkomodasiModelPondok = new ArrayList<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getFragmentSelected() {
        return fragmentSelected;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public HotelBintangFragment getHotelBintangFragment() {
        return hotelBintangFragment;
    }

    public void setHotelBintangFragment(HotelBintangFragment hotelBintangFragment) {
        this.hotelBintangFragment = hotelBintangFragment;
    }

    public HotelNonBintangFragment getHotelNonBintangFragment() {
        return hotelNonBintangFragment;
    }

    public void setHotelNonBintangFragment(HotelNonBintangFragment hotelNonBintangFragment) {
        this.hotelNonBintangFragment = hotelNonBintangFragment;
    }

    public PondokFragment getPondokFragment() {
        return pondokFragment;
    }

    public void setPondokFragment(PondokFragment pondokFragment) {
        this.pondokFragment = pondokFragment;
    }

    public AkomodasiPresenter.AkomodasiView.ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(AkomodasiPresenter.AkomodasiView.ScreenState screenState) {
        this.screenState = screenState;
    }

    public List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> getListAkomodasiModel() {
        return listAkomodasiModel;
    }

    public void setListAkomodasiModel(List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModel) {
        this.listAkomodasiModel = listAkomodasiModel;
    }

    public com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel getAkomodasiModel() {
        return akomodasiModel;
    }

    public void setAkomodasiModel(com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel akomodasiModel) {
        this.akomodasiModel = akomodasiModel;
    }

    public List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> getListAkomodasiModelBintang() {
        return listAkomodasiModelBintang;
    }

    public void setListAkomodasiModelBintang(List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelBintang) {
        this.listAkomodasiModelBintang = listAkomodasiModelBintang;
    }

    public List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> getListAkomodasiModelNonBintang() {
        return listAkomodasiModelNonBintang;
    }

    public void setListAkomodasiModelNonBintang(List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelNonBintang) {
        this.listAkomodasiModelNonBintang = listAkomodasiModelNonBintang;
    }

    public List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> getListAkomodasiModelPondok() {
        return listAkomodasiModelPondok;
    }

    public void setListAkomodasiModelPondok(List<com.studio.visitsukabumi.api.v1.akomodasi.AkomodasiModel> listAkomodasiModelPondok) {
        this.listAkomodasiModelPondok = listAkomodasiModelPondok;
    }

    public static class ItemModel implements Serializable {
        private String nama;
        private float rating;
        private String imageURL;

        public ItemModel(String nama, float rating, String imageURL) {
            this.nama = nama;
            this.rating = rating;
            this.imageURL = imageURL;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }
}
