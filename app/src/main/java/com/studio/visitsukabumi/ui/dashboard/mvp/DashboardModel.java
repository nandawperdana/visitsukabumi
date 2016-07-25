package com.studio.visitsukabumi.ui.dashboard.mvp;

import com.studio.visitsukabumi.api.v1.unggulan.UnggulanModel;
import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandawperdana on 7/13/2016.
 */
public class DashboardModel {
    Enums.Menu menu;

    // class
    UnggulanModel unggulanModel;
    List<UnggulanModel> listUnggulanModel;

    public DashboardModel() {
        this.listUnggulanModel = new ArrayList<>();
    }

    public Enums.Menu getMenu() {
        return menu;
    }

    public void setMenu(Enums.Menu menu) {
        this.menu = menu;
    }

    public UnggulanModel getUnggulanModel() {
        return unggulanModel;
    }

    public void setUnggulanModel(UnggulanModel unggulanModel) {
        this.unggulanModel = unggulanModel;
    }

    public List<UnggulanModel> getListUnggulanModel() {
        return listUnggulanModel;
    }

    public void setListUnggulanModel(List<UnggulanModel> listUnggulanModel) {
        this.listUnggulanModel = listUnggulanModel;
    }

    public static class Menu {
        private Enums.Menu id;
        private String name;
        private int image;

        public Menu(Enums.Menu id, String name, int image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        public Enums.Menu getId() {
            return id;
        }

        public void setId(Enums.Menu id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
