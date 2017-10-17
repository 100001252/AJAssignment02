/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.*;
import view.*;
import helper.*;
import java.util.ArrayList;

/**
 *
 * @author XC8184
 */
public class MdTrafficLight {

    private String img;
    private final String imgGrean = "tl-g.png";
    private final String imgyellow = "tl-y.png";
    private final String imgred = "tl-r.png";
    private String name;
    // Where the vehicle is.
    private Location location;

    // Where the vehicle is headed.
    private int distanceFromOrigin;

    public MdTrafficLight(String name, Location loc) {
        this.location = loc;
        this.name = name;
        this.img = this.imgGrean;
        this.distanceFromOrigin = loc.getDistanceFromOrigin();

    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the imgGrean
     */
    public String getImgGrean() {
        return imgGrean;
    }

    /**
     * @return the imgyellow
     */
    public String getImgyellow() {
        return imgyellow;
    }

    /**
     * @return the imgred
     */
    public String getImgred() {
        return imgred;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the distanceFromOrigin
     */
    public int getDistanceFromOrigin() {
        return distanceFromOrigin;
    }

    /**
     * @param distanceFromOrigin the distanceFromOrigin to set
     */
    public void setDistanceFromOrigin(int distanceFromOrigin) {
        this.distanceFromOrigin = distanceFromOrigin;
    }

}
