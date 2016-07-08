package com.korosten.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy.herasymchuk on 7/8/16.
 */
public class ImageTypes {

    @SerializedName("full")
    @Expose
    private Image full = new Image();
    @SerializedName("thumbnail")
    @Expose
    private Image thumbnail = new Image();
    @SerializedName("medium")
    @Expose
    private Image medium = new Image();
    @SerializedName("medium_large")
    @Expose
    private Image mediumLarge = new Image();
    @SerializedName("large")
    @Expose
    private Image large = new Image();
    @SerializedName("shop_thumbnail")
    @Expose
    private Image shopThumbnail = new Image();
    @SerializedName("shop_catalog")
    @Expose
    private Image shopCatalog = new Image();
    @SerializedName("shop_single")
    @Expose
    private Image shopSingle = new Image();

    public Image getFull() {
        return full;
    }

    public void setFull(Image full) {
        this.full = full;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Image getMedium() {
        return medium;
    }

    public void setMedium(Image medium) {
        this.medium = medium;
    }

    public Image getMediumLarge() {
        return mediumLarge;
    }

    public void setMediumLarge(Image mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    public Image getLarge() {
        return large;
    }

    public void setLarge(Image large) {
        this.large = large;
    }

    public Image getShopThumbnail() {
        return shopThumbnail;
    }

    public void setShopThumbnail(Image shopThumbnail) {
        this.shopThumbnail = shopThumbnail;
    }

    public Image getShopCatalog() {
        return shopCatalog;
    }

    public void setShopCatalog(Image shopCatalog) {
        this.shopCatalog = shopCatalog;
    }

    public Image getShopSingle() {
        return shopSingle;
    }

    public void setShopSingle(Image shopSingle) {
        this.shopSingle = shopSingle;
    }
}
