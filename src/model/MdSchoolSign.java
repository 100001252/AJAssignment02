/* class to locate school sign into mdcity
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
public class MdSchoolSign implements Comparable<MdSchoolSign> {

    private final String imgSchoolStart;
    private final String imgSchoolEnd;

    private String name;
    // Where the vehicle is.
    private Location locationSchoolStart;
    private Location locationSchoolEnd;

    // Where the vehicle is headed.
    private int distanceFromOriginSchoolStart;
    private int distanceFromOriginSchoolEnd;
    private int lengthOfarea;

    public MdSchoolSign(String name, Location startLoc, Location endLoc) {
        this.imgSchoolStart = "school_zone_start.png";
        this.imgSchoolEnd = "school_zone_end.PNG";
        this.name = name;
        this.locationSchoolStart = startLoc;
        this.locationSchoolEnd = endLoc;
        this.distanceFromOriginSchoolStart = startLoc.getDistanceFromOrigin();
        this.distanceFromOriginSchoolEnd = endLoc.getDistanceFromOrigin();
        this.lengthOfarea = startLoc.calcDistanceBetweenTwoLocation(endLoc);
        //temperarily
    }

    /**
     * method to access endTime
     *
     * @param
     * @return endtime of booking
     */
    public int compareTo(MdSchoolSign objMdschoolSign) {
        //sort by distance from origin
        int distanceCompare = this.getDistanceFromOriginSchoolStart() - objMdschoolSign.getDistanceFromOriginSchoolStart();
        return distanceCompare;
    }

    public String toString() {
//
//        String str = "my name=" + this.name + " | myimgName=" + this.imgName + " | normalimg=" + this.imgNormal + "| breakimg=" + this.imgBreak + ""
//                + "| location(" + Integer.toString(this.location.getX()) + "," + Integer.toString(this.location.getY()) + ")"
//                + "| speed=" + this.speed + "| timeinsecond=" + this.timeInSecond + "|distanceFromOrg=" + this.distanceFromOrigin;
//        return str;
        return "sfddas";

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
     * @return the imgSchoolStart
     */
    public String getImgSchoolStart() {
        return imgSchoolStart;
    }

    /**
     * @return the imgSchoolEnd
     */
    public String getImgSchoolEnd() {
        return imgSchoolEnd;
    }

    /**
     * @return the locationSchoolStart
     */
    public Location getLocationSchoolStart() {
        return locationSchoolStart;
    }

    /**
     * @param locationSchoolStart the locationSchoolStart to set
     */
    public void setLocationSchoolStart(Location locationSchoolStart) {
        this.locationSchoolStart = locationSchoolStart;
    }

    /**
     * @return the locationSchoolEnd
     */
    public Location getLocationSchoolEnd() {
        return locationSchoolEnd;
    }

    /**
     * @param locationSchoolEnd the locationSchoolEnd to set
     */
    public void setLocationSchoolEnd(Location locationSchoolEnd) {
        this.locationSchoolEnd = locationSchoolEnd;
    }

    /**
     * @return the distanceFromOriginSchoolStart
     */
    public int getDistanceFromOriginSchoolStart() {
        return distanceFromOriginSchoolStart;
    }

    /**
     * @param distanceFromOriginSchoolStart the distanceFromOriginSchoolStart to
     * set
     */
    public void setDistanceFromOriginSchoolStart(int distanceFromOriginSchoolStart) {
        this.distanceFromOriginSchoolStart = distanceFromOriginSchoolStart;
    }

    /**
     * @return the distanceFromOriginSchoolEnd
     */
    public int getDistanceFromOriginSchoolEnd() {
        return distanceFromOriginSchoolEnd;
    }

    /**
     * @param distanceFromOriginSchoolEnd the distanceFromOriginSchoolEnd to set
     */
    public void setDistanceFromOriginSchoolEnd(int distanceFromOriginSchoolEnd) {
        this.distanceFromOriginSchoolEnd = distanceFromOriginSchoolEnd;
    }

}
