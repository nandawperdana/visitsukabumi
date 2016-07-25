package com.studio.visitsukabumi.ui.objek_wisata.mvp;

import com.studio.visitsukabumi.presentation.presenters.ObjekWisataPresenter;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.GunungFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.LautPantaiFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.RimbaFragment;
import com.studio.visitsukabumi.ui.objek_wisata.fragment.SungaiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class ObjekWisataModel {
    private List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekWisata;
    private List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekRimba;
    private List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekGunung;
    private List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekLautPantai;
    private List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekSungai;
    private com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel objekWisataModel;

    private int page = 0;
    private int fragmentSelected;

    private RimbaFragment rimbaFragment;
    private LautPantaiFragment lautPantaiFragment;
    private GunungFragment gunungFragment;
    private SungaiFragment sungaiFragment;

    private ObjekWisataPresenter.ObjekWisataView.ScreenState screenState;

    public static final String TAG_RIMBA = "Rimba";
    public static final String TAG_LAUT_DAN_PANTAI = "Laut dan Pantai";
    public static final String TAG_GUNUNG = "Gunung";
    public static final String TAG_SUNGAI = "Sungai";

    public ObjekWisataModel() {
        this.listObjekWisata = new ArrayList<>();
        this.listObjekRimba = new ArrayList<>();
        this.listObjekGunung = new ArrayList<>();
        this.listObjekLautPantai = new ArrayList<>();
        this.listObjekSungai = new ArrayList<>();
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

    public ObjekWisataPresenter.ObjekWisataView.ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(ObjekWisataPresenter.ObjekWisataView.ScreenState screenState) {
        this.screenState = screenState;
    }

    public List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> getListObjekWisata() {
        return listObjekWisata;
    }

    public void setListObjekWisata(List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekWisata) {
        this.listObjekWisata = listObjekWisata;
    }

    public com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel getObjekWisataModel() {
        return objekWisataModel;
    }

    public void setObjekWisataModel(com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel objekWisataModel) {
        this.objekWisataModel = objekWisataModel;
    }

    public List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> getListObjekRimba() {
        return listObjekRimba;
    }

    public void setListObjekRimba(List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekRimba) {
        this.listObjekRimba = listObjekRimba;
    }

    public List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> getListObjekGunung() {
        return listObjekGunung;
    }

    public void setListObjekGunung(List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekGunung) {
        this.listObjekGunung = listObjekGunung;
    }

    public List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> getListObjekSungai() {
        return listObjekSungai;
    }

    public void setListObjekSungai(List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekSungai) {
        this.listObjekSungai = listObjekSungai;
    }

    public List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> getListObjekLautPantai() {
        return listObjekLautPantai;
    }

    public void setListObjekLautPantai(List<com.studio.visitsukabumi.api.v1.objekwisata.ObjekWisataModel> listObjekLautPantai) {
        this.listObjekLautPantai = listObjekLautPantai;
    }

    public RimbaFragment getRimbaFragment() {
        return rimbaFragment;
    }

    public void setRimbaFragment(RimbaFragment rimbaFragment) {
        this.rimbaFragment = rimbaFragment;
    }

    public LautPantaiFragment getLautPantaiFragment() {
        return lautPantaiFragment;
    }

    public void setLautPantaiFragment(LautPantaiFragment lautPantaiFragment) {
        this.lautPantaiFragment = lautPantaiFragment;
    }

    public GunungFragment getGunungFragment() {
        return gunungFragment;
    }

    public void setGunungFragment(GunungFragment gunungFragment) {
        this.gunungFragment = gunungFragment;
    }

    public SungaiFragment getSungaiFragment() {
        return sungaiFragment;
    }

    public void setSungaiFragment(SungaiFragment sungaiFragment) {
        this.sungaiFragment = sungaiFragment;
    }
}
