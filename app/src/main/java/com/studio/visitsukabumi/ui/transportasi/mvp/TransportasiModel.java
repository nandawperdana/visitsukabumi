package com.studio.visitsukabumi.ui.transportasi.mvp;

import com.studio.visitsukabumi.ui.transportasi.fragment.PelabuhanFragment;
import com.studio.visitsukabumi.ui.transportasi.fragment.StasiunFragment;
import com.studio.visitsukabumi.ui.transportasi.fragment.TerminalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class TransportasiModel {
    public static final String TAG_TERMINAL = "Terminal";
    public static final String TAG_STASIUN = "Stasiun";
    public static final String TAG_PELABUHAN = "Pelabuhan";

    private List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiTerminal;
    private List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiStasiun;
    private List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiPelabuhan;
    private List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasi;

    private TerminalFragment terminalFragment;
    private StasiunFragment stasiunFragment;
    private PelabuhanFragment pelabuhanFragment;

    private int page;
    private int fragmentSelected;

    public TransportasiModel() {
        this.listTransportasi = new ArrayList<>();
        this.listTransportasiPelabuhan = new ArrayList<>();
        this.listTransportasiStasiun = new ArrayList<>();
        this.listTransportasiTerminal = new ArrayList<>();
    }

    public List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> getListTransportasiTerminal() {
        return listTransportasiTerminal;
    }

    public void setListTransportasiTerminal(List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiTerminal) {
        this.listTransportasiTerminal = listTransportasiTerminal;
    }

    public List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> getListTransportasiStasiun() {
        return listTransportasiStasiun;
    }

    public void setListTransportasiStasiun(List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiStasiun) {
        this.listTransportasiStasiun = listTransportasiStasiun;
    }

    public List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> getListTransportasiPelabuhan() {
        return listTransportasiPelabuhan;
    }

    public void setListTransportasiPelabuhan(List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasiPelabuhan) {
        this.listTransportasiPelabuhan = listTransportasiPelabuhan;
    }

    public List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> getListTransportasi() {
        return listTransportasi;
    }

    public void setListTransportasi(List<com.studio.visitsukabumi.api.v1.transportasi.TransportasiModel> listTransportasi) {
        this.listTransportasi = listTransportasi;
    }

    public TerminalFragment getTerminalFragment() {
        return terminalFragment;
    }

    public void setTerminalFragment(TerminalFragment terminalFragment) {
        this.terminalFragment = terminalFragment;
    }

    public StasiunFragment getStasiunFragment() {
        return stasiunFragment;
    }

    public void setStasiunFragment(StasiunFragment stasiunFragment) {
        this.stasiunFragment = stasiunFragment;
    }

    public PelabuhanFragment getPelabuhanFragment() {
        return pelabuhanFragment;
    }

    public void setPelabuhanFragment(PelabuhanFragment pelabuhanFragment) {
        this.pelabuhanFragment = pelabuhanFragment;
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
}
