/**
 * Created by vitaliy.komaniak on 9/2/15
 */

package com.korosten.www.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Serializable {

    public Place(){}

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String description;

    @Expose
    private Integer distance;
    @Expose
    private Double latitude;
    @Expose
    private Double longtitude;
    @Expose
    private String address;
    @Expose
    private String location;


    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Place withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Place withName(String name) {
        this.name = name;
        return this;
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

    public Place withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Place withDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    /**
     * @return The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Place withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * @return The longtitude
     */
    public Double getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude The longtitude
     */
    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Place withLongtitude(Double longtitude) {
        this.longtitude = longtitude;
        return this;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public Place withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public Place withLocation(String location) {
        this.location = location;
        return this;
    }
}