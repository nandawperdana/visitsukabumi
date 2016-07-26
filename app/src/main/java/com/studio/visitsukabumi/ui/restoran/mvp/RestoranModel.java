package com.studio.visitsukabumi.ui.restoran.mvp;

import com.studio.visitsukabumi.ui.restoran.fragment.KafeFragment;
import com.studio.visitsukabumi.ui.restoran.fragment.RestoranFragment;
import com.studio.visitsukabumi.ui.restoran.fragment.RumahMakanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class RestoranModel {
    public static final String TAG_RESTORAN = "Restoran";
    public static final String TAG_RUMAHMAKAN = "Rumah Makan";
    public static final String TAG_KAFE = "Kafe";
    private int page;
    private int fragmentSelected;

    private List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItems;
    private List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemRumahMakan;
    private List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemRestoran;
    private List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemKafe;

    private RumahMakanFragment rumahMakanFragment;
    private KafeFragment kafeFragment;
    private RestoranFragment restoranFragment;

    public RestoranModel() {
        this.listItems = new ArrayList<>();
        this.listItemRumahMakan = new ArrayList<>();
        this.listItemRestoran = new ArrayList<>();
        this.listItemKafe = new ArrayList<>();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> getListItems() {
        return listItems;
    }

    public void setListItems(List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItems) {
        this.listItems = listItems;
    }

    public List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> getListItemRumahMakan() {
        return listItemRumahMakan;
    }

    public void setListItemRumahMakan(List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemRumahMakan) {
        this.listItemRumahMakan = listItemRumahMakan;
    }

    public List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> getListItemRestoran() {
        return listItemRestoran;
    }

    public void setListItemRestoran(List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemRestoran) {
        this.listItemRestoran = listItemRestoran;
    }

    public List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> getListItemKafe() {
        return listItemKafe;
    }

    public void setListItemKafe(List<com.studio.visitsukabumi.api.v1.restoran.RestoranModel> listItemKafe) {
        this.listItemKafe = listItemKafe;
    }

    public RumahMakanFragment getRumahMakanFragment() {
        return rumahMakanFragment;
    }

    public void setRumahMakanFragment(RumahMakanFragment rumahMakanFragment) {
        this.rumahMakanFragment = rumahMakanFragment;
    }

    public KafeFragment getKafeFragment() {
        return kafeFragment;
    }

    public void setKafeFragment(KafeFragment kafeFragment) {
        this.kafeFragment = kafeFragment;
    }

    public RestoranFragment getRestoranFragment() {
        return restoranFragment;
    }

    public void setRestoranFragment(RestoranFragment restoranFragment) {
        this.restoranFragment = restoranFragment;
    }
}
