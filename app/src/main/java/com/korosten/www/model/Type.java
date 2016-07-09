package com.korosten.www.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vitaliy.herasymchuk on 7/9/16.
 */
public class Type {

    private int id;
    private String slug;
    private String title;
    @SerializedName("post_count")
    private int postsCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    @Override
    public String toString() {
        return "Type{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (id != type.id) return false;
        if (postsCount != type.postsCount) return false;
        if (slug != null ? !slug.equals(type.slug) : type.slug != null) return false;
        return title != null ? title.equals(type.title) : type.title == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + postsCount;
        return result;
    }
}
