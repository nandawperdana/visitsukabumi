package com.studio.visitsukabumi.ui.pelayanan_publik.mvp;

import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.MasjidFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.PolisiFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.PuskesmasFragment;
import com.studio.visitsukabumi.ui.pelayanan_publik.fragment.RumahSakitFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class PelayananPublikModel {
    public static final String TAG_RUMAH_SAKIT = "Rumah Sakit";
    public static final String TAG_PUSKESMAS = "Puskesmas";
    public static final String TAG_POLISI = "Kantor Polisi";
    public static final String TAG_MASJID = "Masjid";

    private List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublik;
    private List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikRumahSakit;
    private List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikPolisi;
    private List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikMasjid;
    private List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikPuskesmas;

    private int page;
    private int fragmentSelected;

    private MasjidFragment masjidFragment;
    private RumahSakitFragment rumahSakitFragment;
    private PolisiFragment polisiFragment;
    private PuskesmasFragment puskesmasFragment;

    public PelayananPublikModel() {
        this.listPelayananPublik = new ArrayList<>();
        this.listPelayananPublikMasjid = new ArrayList<>();
        this.listPelayananPublikPolisi = new ArrayList<>();
        this.listPelayananPublikPuskesmas = new ArrayList<>();
        this.listPelayananPublikRumahSakit = new ArrayList<>();
    }

    public int getFragmentSelected() {
        return fragmentSelected;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> getListPelayananPublik() {
        return listPelayananPublik;
    }

    public void setListPelayananPublik(List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublik) {
        this.listPelayananPublik = listPelayananPublik;
    }

    public List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> getListPelayananPublikRumahSakit() {
        return listPelayananPublikRumahSakit;
    }

    public void setListPelayananPublikRumahSakit(List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikRumahSakit) {
        this.listPelayananPublikRumahSakit = listPelayananPublikRumahSakit;
    }

    public List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> getListPelayananPublikPolisi() {
        return listPelayananPublikPolisi;
    }

    public void setListPelayananPublikPolisi(List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikPolisi) {
        this.listPelayananPublikPolisi = listPelayananPublikPolisi;
    }

    public List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> getListPelayananPublikMasjid() {
        return listPelayananPublikMasjid;
    }

    public void setListPelayananPublikMasjid(List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikMasjid) {
        this.listPelayananPublikMasjid = listPelayananPublikMasjid;
    }

    public List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> getListPelayananPublikPuskesmas() {
        return listPelayananPublikPuskesmas;
    }

    public void setListPelayananPublikPuskesmas(List<com.studio.visitsukabumi.api.v1.pelayananpublik.PelayananPublikModel> listPelayananPublikPuskesmas) {
        this.listPelayananPublikPuskesmas = listPelayananPublikPuskesmas;
    }

    public int getPage() {
        return page;
    }

    public MasjidFragment getMasjidFragment() {
        return masjidFragment;
    }

    public void setMasjidFragment(MasjidFragment masjidFragment) {
        this.masjidFragment = masjidFragment;
    }

    public RumahSakitFragment getRumahSakitFragment() {
        return rumahSakitFragment;
    }

    public void setRumahSakitFragment(RumahSakitFragment rumahSakitFragment) {
        this.rumahSakitFragment = rumahSakitFragment;
    }

    public PolisiFragment getPolisiFragment() {
        return polisiFragment;
    }

    public void setPolisiFragment(PolisiFragment polisiFragment) {
        this.polisiFragment = polisiFragment;
    }

    public PuskesmasFragment getPuskesmasFragment() {
        return puskesmasFragment;
    }

    public void setPuskesmasFragment(PuskesmasFragment puskesmasFragment) {
        this.puskesmasFragment = puskesmasFragment;
    }
}
