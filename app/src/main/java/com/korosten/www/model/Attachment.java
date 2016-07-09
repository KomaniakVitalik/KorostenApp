package com.korosten.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy.herasymchuk on 7/8/16.
 */
public class Attachment {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("parent")
    @Expose
    private int parent;
    @SerializedName("mime_type")
    @Expose
    private String mimeType;

    @SerializedName("images")
    @Expose
    private ImageTypes imageTypes;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return The parent
     */
    public int getParent() {
        return parent;
    }

    /**
     * @param parent The parent
     */
    public void setParent(int parent) {
        this.parent = parent;
    }

    /**
     * @return The mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType The mime_type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public ImageTypes getImageTypes() {
        return imageTypes;
    }

    public void setImageTypes(ImageTypes imageTypes) {
        this.imageTypes = imageTypes;
    }
}
