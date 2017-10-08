/* model class to record one single action by user or automatic
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author XC8184
 */
public class MdVehicleAction {

    private static int i = 0;

    /**
     * @return the i
     */
    public static int getI() {
        return i;
    }

    /**
     * @param aI the i to set
     */
    public static void setI(int aI) {
        i = aI;
    }
    private int id;
    private Date actionTime;
    private String actionTimeInString;
    private String carName;
    private String nameFriendly;
    private String abbriviation;
    private String description;
    private String actionedBy;//user or auto

    public MdVehicleAction(String abbr, String nameFriendly, String descriptiton, String actionby, String carName) throws Exception {
        if (!validateAbbriviation(abbr) || !validateActionBy(actionby)) {
            throw new CarRacingException("--oooooopppps: abbr or actionby for vehicle action is not valid");
        }
        this.id = ++i;
        this.carName = carName;
        this.actionTime = new Date();
        this.nameFriendly = nameFriendly;
        this.abbriviation = abbr;
        this.description = descriptiton;
        this.actionedBy = actionby;

    }

    public MdVehicleAction(String abbr, String nameFriendly, String actionby, String carName) throws Exception {
        if (!validateAbbriviation(abbr) || !validateActionBy(actionby)) {
            throw new CarRacingException("--oooooopppps: abbr or actionby for vehicle action is not valid");
        }
        this.id = ++i;
        this.carName = carName;
        this.actionTime = new Date();
        this.nameFriendly = nameFriendly;
        this.abbriviation = abbr;
        this.actionedBy = actionby;

    }

    private boolean validateAbbriviation(String abbr) {
        boolean result = false;
        switch (abbr.toLowerCase()) {
            case "inc"://increase
                result = true;
                break;
            case "dec"://decrease speed
                result = true;
                break;
            case "break"://full stop
                result = true;
                break;
            case "breakstart"://full stop start moving
                result = true;
                break;
        }

        return result;
    }

    private boolean validateActionBy(String actionby) {
        boolean result = false;
        //if(abbr.equals(""))
        switch (actionby.toLowerCase()) {
            case "user":
                result = true;
                break;
            case "auto":
                result = true;
                break;

        }
        return result;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the actionTime
     */
    public Date getActionTime() {
        return actionTime;
    }

    /**
     * @param actionTime the actionTime to set
     */
    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the nameFriendly
     */
    public String getNameFriendly() {
        return nameFriendly;
    }

    /**
     * @param nameFriendly the nameFriendly to set
     */
    public void setNameFriendly(String nameFriendly) {
        this.nameFriendly = nameFriendly;
    }

    /**
     * @return the abbriviation
     */
    public String getAbbriviation() {
        return abbriviation;
    }

    /**
     * @param abbriviation the abbriviation to set
     */
    public void setAbbriviation(String abbriviation) {
        this.abbriviation = abbriviation;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the actionedBy
     */
    public String getActionedBy() {
        return actionedBy;
    }

    /**
     * @param actionedBy the actionedBy to set
     */
    public void setActionedBy(String actionedBy) {
        this.actionedBy = actionedBy;
    }

    /**
     * @return the actionTimeInString
     */
    public String getActionTimeInString() {
        return actionTimeInString;
    }

    /**
     * @param actionTimeInString the actionTimeInString to set
     */
    public void setActionTimeInString(String actionTimeInString) {
        this.actionTimeInString = actionTimeInString;
    }
}
