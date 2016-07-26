package com.studio.visitsukabumi.ui.seni_budaya.mvp;

import com.studio.visitsukabumi.ui.seni_budaya.fragment.SeniBudayaFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class SeniBudayaModel {
    private int page;
    private int fragmentSelected;

    private List<com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel> listItems;

    private SeniBudayaFragment seniBudayaFragment;

    public SeniBudayaModel() {
        this.listItems = new ArrayList<>();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public List<com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel> getListItems() {
        return listItems;
    }

    public void setListItems(List<com.studio.visitsukabumi.api.v1.senibudaya.SeniBudayaModel> listItems) {
        this.listItems = listItems;
    }

    public SeniBudayaFragment getSeniBudayaFragment() {
        return seniBudayaFragment;
    }

    public void setSeniBudayaFragment(SeniBudayaFragment seniBudayaFragment) {
        this.seniBudayaFragment = seniBudayaFragment;
    }
}
