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

import helper.Location;

/**
 *
 * @author XC8184
 */
public class MdBall {

    //private final String imgBall = ;
    private String name;
    // Where the vehicle is.
    private Location locationStart;
    private Location locationEnd;

    // Where the vehicle is headed.
    private int distanceFromOriginStart;
    private int distanceFromOriginEnd;

    public MdBall(String name, Location startLoc, Location endLoc) {
        this.name = name;
        this.locationStart = startLoc;
        this.locationEnd = endLoc;
        this.distanceFromOriginStart = startLoc.getDistanceFromOrigin();
        this.distanceFromOriginEnd = endLoc.getDistanceFromOrigin();

    }
    //this.loca

}
