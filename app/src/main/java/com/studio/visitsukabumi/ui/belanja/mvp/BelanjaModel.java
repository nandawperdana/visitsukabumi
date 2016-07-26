package com.studio.visitsukabumi.ui.belanja.mvp;

import com.studio.visitsukabumi.ui.belanja.fragment.MallFragment;
import com.studio.visitsukabumi.ui.belanja.fragment.OlehFragment;
import com.studio.visitsukabumi.ui.belanja.fragment.PasarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class BelanjaModel {
    public static final String TAG_PASAR = "Pasar Tradisional";
    public static final String TAG_OLEH = "Oleh-oleh";
    public static final String TAG_MAL = "Mal";
    private int page;
    private int fragmentSelected;

    private PasarFragment pasarFragment;
    private OlehFragment olehFragment;
    private MallFragment mallFragment;

    private List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanja;
    private List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaPasar;
    private List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaOleh;
    private List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaMall;

    public BelanjaModel() {
        this.listBelanja = new ArrayList<>();
        this.listBelanjaMall = new ArrayList<>();
        this.listBelanjaOleh = new ArrayList<>();
        this.listBelanjaPasar = new ArrayList<>();
    }

    public List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> getListBelanja() {
        return listBelanja;
    }

    public void setListBelanja(List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanja) {
        this.listBelanja = listBelanja;
    }

    public List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> getListBelanjaPasar() {
        return listBelanjaPasar;
    }

    public void setListBelanjaPasar(List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaPasar) {
        this.listBelanjaPasar = listBelanjaPasar;
    }

    public List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> getListBelanjaOleh() {
        return listBelanjaOleh;
    }

    public void setListBelanjaOleh(List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaOleh) {
        this.listBelanjaOleh = listBelanjaOleh;
    }

    public List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> getListBelanjaMall() {
        return listBelanjaMall;
    }

    public void setListBelanjaMall(List<com.studio.visitsukabumi.api.v1.belanja.BelanjaModel> listBelanjaMall) {
        this.listBelanjaMall = listBelanjaMall;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public PasarFragment getPasarFragment() {
        return pasarFragment;
    }

    public void setPasarFragment(PasarFragment pasarFragment) {
        this.pasarFragment = pasarFragment;
    }

    public OlehFragment getOlehFragment() {
        return olehFragment;
    }

    public void setOlehFragment(OlehFragment olehFragment) {
        this.olehFragment = olehFragment;
    }

    public MallFragment getMallFragment() {
        return mallFragment;
    }

    public void setMallFragment(MallFragment mallFragment) {
        this.mallFragment = mallFragment;
    }
}
