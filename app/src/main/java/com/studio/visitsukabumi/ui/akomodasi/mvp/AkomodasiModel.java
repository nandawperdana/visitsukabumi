package com.studio.visitsukabumi.ui.akomodasi.mvp;

import com.studio.visitsukabumi.presentation.presenters.AkomodasiPresenter;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelBintangFragment;
import com.studio.visitsukabumi.ui.akomodasi.fragment.HotelNonBintangFragment;

import java.io.Serializable;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class AkomodasiModel {
    private int page = 0;
    private int fragmentSelected;

    private HotelBintangFragment hotelBintangFragment;
    private HotelNonBintangFragment hotelNonBintangFragment;

    private AkomodasiPresenter.AkomodasiView.ScreenState screenState;

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

    public AkomodasiPresenter.AkomodasiView.ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(AkomodasiPresenter.AkomodasiView.ScreenState screenState) {
        this.screenState = screenState;
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
