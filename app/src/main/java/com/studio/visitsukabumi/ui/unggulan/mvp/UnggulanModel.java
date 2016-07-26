package com.studio.visitsukabumi.ui.unggulan.mvp;

import com.studio.visitsukabumi.ui.unggulan.fragment.AccessibilityFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.ActivitiesFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AmenitiesFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AncilliaryFragment;
import com.studio.visitsukabumi.ui.unggulan.fragment.AttractionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NwP.
 */
public class UnggulanModel {
    com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel unggulanModel;
    private List<com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel> listUnggulan;
    private int page;
    private int fragmentSelected;

    private AmenitiesFragment amenitiesFragment;
    private AttractionFragment attractionFragment;
    private ActivitiesFragment activitiesFragment;
    private AccessibilityFragment accessibilityFragment;
    private AncilliaryFragment ancilliaryFragment;

    public UnggulanModel() {
        this.unggulanModel = new com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel();
        this.listUnggulan = new ArrayList<>();
    }

    public com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel getUnggulanModel() {
        return unggulanModel;
    }

    public void setUnggulanModel(com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel unggulanModel) {
        this.unggulanModel = unggulanModel;
    }

    public List<com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel> getListUnggulan() {
        return listUnggulan;
    }

    public void setListUnggulan(List<com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel> listUnggulan) {
        this.listUnggulan = listUnggulan;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setFragmentSelected(int fragmentSelected) {
        this.fragmentSelected = fragmentSelected;
    }

    public AmenitiesFragment getAmenitiesFragment() {
        return amenitiesFragment;
    }

    public void setAmenitiesFragment(AmenitiesFragment amenitiesFragment) {
        this.amenitiesFragment = amenitiesFragment;
    }

    public AttractionFragment getAttractionFragment() {
        return attractionFragment;
    }

    public void setAttractionFragment(AttractionFragment attractionFragment) {
        this.attractionFragment = attractionFragment;
    }

    public ActivitiesFragment getActivitiesFragment() {
        return activitiesFragment;
    }

    public void setActivitiesFragment(ActivitiesFragment activitiesFragment) {
        this.activitiesFragment = activitiesFragment;
    }

    public AccessibilityFragment getAccessibilityFragment() {
        return accessibilityFragment;
    }

    public void setAccessibilityFragment(AccessibilityFragment accessibilityFragment) {
        this.accessibilityFragment = accessibilityFragment;
    }

    public AncilliaryFragment getAncilliaryFragment() {
        return ancilliaryFragment;
    }

    public void setAncilliaryFragment(AncilliaryFragment ancilliaryFragment) {
        this.ancilliaryFragment = ancilliaryFragment;
    }
}
