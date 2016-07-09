package com.korosten.www.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy.herasymchuk on 7/7/16.
 */
public class KorostenResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("count_total")
    @Expose
    private int countTotal;
    @SerializedName("pages")
    @Expose
    private int pages;
    @SerializedName("posts")
    @Expose
    private List<Post> posts = new ArrayList<>();

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return The countTotal
     */
    public int getCountTotal() {
        return countTotal;
    }

    /**
     * @param countTotal The count_total
     */
    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    /**
     * @return The pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * @param pages The pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
