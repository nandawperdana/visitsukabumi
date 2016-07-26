package com.studio.visitsukabumi.utils.adapter;

/**
 * Created by NwP.
 */
public class RowListItem {
    private String title;
    private String subTitle;
    private String image;

    public RowListItem(String title, String subTitle, String image) {
        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
