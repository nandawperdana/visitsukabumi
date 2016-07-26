package com.studio.visitsukabumi.ui.aktifitas.mvp;

import com.studio.visitsukabumi.ui.aktifitas.fragment.AktifitasFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class AktifitasModel {
    private List<com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel> listItems;
    private int page;
    private int fragmentSelected;

    private AktifitasFragment aktifitasFragment;

    public AktifitasModel() {
        this.listItems = new ArrayList<>();
    }

    public List<com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel> getListItems() {
        return listItems;
    }

    public void setListItems(List<com.studio.visitsukabumi.api.v1.aktifitas.AktifitasModel> listItems) {
        this.listItems = listItems;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public AktifitasFragment getAktifitasFragment() {
        return aktifitasFragment;
    }

    public void setAktifitasFragment(AktifitasFragment aktifitasFragment) {
        this.aktifitasFragment = aktifitasFragment;
    }
}
