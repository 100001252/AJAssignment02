/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.*;
import view.*;
import helper.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author XC8184
 */
public abstract class Vehicle implements Comparable<Vehicle> {

    private static int id_autoGen = 0;
    private int delay = 0;
    private boolean isRouteToGo;
    private String name;

    private String imgName;
    private String imgNormal;
    private String imgBreak;
    private String img40Zone;
    // Where the vehicle is.
    private Location location;
    // Where the vehicle is headed.
    private int speed = 50;//initial speed for all car is 50km/hr

    private int timeInSecond = 0;

    private ArrayList<MdVehicleAction> lstVehicleAction;
    private boolean isParked = false;
    private boolean is40ZoneArea = false;
    private int distanceFromOrigin;//calc
    private int distanceToFrontCar = 0;
    private int distanceToBehindCar = 0;
    private int distanceInEachRow = 700;

    private int cumulitivedecMan = 0;
    private int cumulitivedecAuto = 0;
    private int cumulitivebreakMan = 0;
    private int cumulitivebrakAuto = 0;

    private int color;//calc

    public Vehicle(Location location, String imageName, String carName, boolean goRoute, int delayToStart, int InitialSpeed) {
        //temperarily
        if (InitialSpeed > 0) {
            this.speed = InitialSpeed;
        }
        this.delay = delayToStart;
        this.isRouteToGo = goRoute;
        this.distanceFromOrigin = (location.getX());
        //temperoarily
        this.lstVehicleAction = new ArrayList<MdVehicleAction>();
        this.imgName = imageName;
        this.imgBreak = imageName;
        this.imgNormal = imageName;
        this.name = carName;

        if (location == null) {
            throw new NullPointerException("location");
        }

        this.location = location;

    }

    public Vehicle(Location location, String imageName, String carName) {
        //temperarily

        this.distanceFromOrigin = (location.getX());
        //temperoarily
        this.lstVehicleAction = new ArrayList<MdVehicleAction>();
        this.imgName = imageName;
        this.imgBreak = imageName;
        this.imgNormal = imageName;
        this.name = carName;

        if (location == null) {
            throw new NullPointerException("location");
        }

        this.location = location;

    }

    /**
     * when process finish this function calculate number of break for this car
     * based on arraylist of mdCarAction
     */
    public void calcNumberOfBreaks() {
        for (MdVehicleAction va : this.getLstVehicleAction()) {
            if (va.getAbbriviation().equals("dec") && va.getActionedBy().equals("user")) {
                this.setCumulitivedecMan(this.getCumulitivedecMan() + 1);

            } else if (va.getAbbriviation().equals("dec") && va.getActionedBy().equals("auto")) {
                this.setCumulitivedecAuto(this.getCumulitivedecAuto() + 1);

            } else if (va.getAbbriviation().equals("break") && va.getActionedBy().equals("auto")) {
                this.setCumulitivebrakAuto(this.getCumulitivebrakAuto() + 1);
            } else if (va.getAbbriviation().equals("break") && va.getActionedBy().equals("user")) {
                this.setCumulitivebreakMan(this.getCumulitivebreakMan() + 1);
            }

        }
    }

    /**
     * this function define duration of move in animation javafx
     *
     * @return
     */
    public int convertSpeedToDuration() {

        return (300 - this.speed) * 100;
    }

    /**
     * help to convert speed to rat in order to use in javafx
     *
     * @return
     */
    public double convertSpeedToRate() {
        double rateOfSpeed = 0;
        switch (this.speed) {
            case 150:
                rateOfSpeed = 1;
                break;
            case 140:
                rateOfSpeed = 0.95;
                break;
            case 130:
                rateOfSpeed = 0.90;
                break;
            case 120:
                rateOfSpeed = 0.85;
                break;
            case 110:
                rateOfSpeed = 0.80;
                break;
            case 100:
                rateOfSpeed = 0.75;
                break;
            case 90:
                rateOfSpeed = 0.70;
                break;
            case 80:
                rateOfSpeed = 0.65;
                break;
            case 70:
                rateOfSpeed = 0.60;
                break;
            case 60:
                rateOfSpeed = 0.55;
                break;
            case 50:
                rateOfSpeed = 0.50;
                break;
            case 40:
                rateOfSpeed = 0.4;
                break;
            case 30:
                rateOfSpeed = 0.3;
                break;
            case 20:
                rateOfSpeed = 0.2;
                break;
            case 10:
                rateOfSpeed = 0.1;
                break;
            case 0:
                rateOfSpeed = 0.0;
                break;
            default:
                rateOfSpeed = 1;

        }
        return rateOfSpeed;
    }

    /**
     * javafx duration
     *
     * @param speed
     * @return
     */
    public int convertanySpeedToDuration(int speed) {
        return speed * 100;
    }

//    /**
//     * in a very specific condition we need a vehicle stop completely because of
//     * big barier
//     */
//    public abstract void breakFullStop();
    public void addVehicleAction(MdVehicleAction mdvAction) {

        this.getLstVehicleAction().add(mdvAction);

    }

    /**
     * in circumstances vehicle needs to decrease speed by 10km/hr
     */
    public void decreaseSpeed(int decreaseVal, MdVehicleAction mdvAction) {
//        if (decreaseVal == 0) {
//            decreaseVal = 10;
//        }

        try {
            if (this.speed - decreaseVal > 0) {
                this.setSpeed(this.speed - decreaseVal, mdvAction);

            }
            // this.imgName = "taxi-break.jpg";
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * this function increase car speed by value that you pass in (just it check
     * that value is not greater than 150)
     *
     * @param incVal
     * @param mdvAction
     */
    public void increaseSpeed(int incVal, MdVehicleAction mdvAction) {
        try {

            if (this.speed > 150) {
                this.speed = 150;
            } else {
                this.setSpeed(this.speed + incVal, mdvAction);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void setSpeedFor40Zone(String ActionBy) throws Exception {
        if (this.isIs40ZoneArea()) {

            if (this.getSpeed() <= 40) {
                //no need action
            } else {
                this.setSpeed(40, new MdVehicleAction("dec", "decrease speed", "due to 40 zone", ActionBy, this.getName()));
            }
            //    objCar.setSpeed(objCar.getSpeed() < 40 ? objCar.getSpeed() : 40);
        }
    }

//    /**
//     * in some circustances that there is no hazard anymore vehcle increase
//     * speed by 10km/hr
//     *
//     */
//    public abstract void increaseSpeed();
    /**
     * @return Where this vehicle is currently located.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the current location.
     *
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location) {
        if (location != null) {
            this.location = location;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id_autoGen
     */
    public static int getId_autoGen() {
        return id_autoGen;
    }

    /**
     * @param aId_autoGen the id_autoGen to set
     */
    public static void setId_autoGen(int aId_autoGen) {
        id_autoGen = aId_autoGen;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the imgName
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * @param imgName the imgName to set
     */
    public void setImgName(String imgName) {

        this.imgName = imgName;
//        this.imgBreak = imgName + "-break";
//        this.setImgNormal(imgName);
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * it convert action date to string for a car so if it exist it find
     * it(because timeline in javafx running for miliiseconds)
     *
     * @param vaction2
     * @return
     */
    public boolean isActionExist(MdVehicleAction vaction2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //Or whatever format fits best your needs.
        boolean result = false;
        for (MdVehicleAction vaction : this.getLstVehicleAction()) {
            if (sdf.format(vaction.getActionTime()).equals(sdf.format(vaction2.getActionTime()))) {
                result = true;
                return result;
            }
        }
        return result;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed, MdVehicleAction mdVehicleAction) throws Exception {
        if (!this.isActionExist(mdVehicleAction)) {
            getLstVehicleAction().add(mdVehicleAction);
        }
        this.speed = speed;

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

    /**
     * in order to have carlist sortabel according to their distance from origin
     * method to access endTime
     *
     * @param
     * @return endtime of booking
     */
    public int compareTo(Vehicle objVehicle) {
        //sort by distance from origin
        int distanceCompare = this.getDistanceFromOrigin() - objVehicle.getDistanceFromOrigin();
        return distanceCompare;
    }

    public String toString() {

        String str = "my name=" + this.name + " | myimgName=" + this.imgName + " | normalimg=" + this.getImgNormal() + "| breakimg=" + this.imgBreak + ""
                + "| location(" + Integer.toString(this.location.getX()) + "," + Integer.toString(this.location.getY()) + ")"
                + "| speed=" + this.speed + "| timeinsecond=" + this.timeInSecond + "|distanceFromOrg=" + this.distanceFromOrigin;
        return str;

    }

    /**
     * @return the distanceToFrontCar
     */
    public int getDistanceToFrontCar() {
        return distanceToFrontCar;
    }

    /**
     * @param distanceToFrontCar the distanceToFrontCar to set
     */
    public void setDistanceToFrontCar(int distanceToFrontCar) {
        this.distanceToFrontCar = distanceToFrontCar;
    }

    /**
     * @return the distanceInEachRow
     */
    public int getDistanceInEachRow() {
        return distanceInEachRow;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * @return the isRouteToGo
     */
    public boolean isIsRouteToGo() {
        return isRouteToGo;
    }

    /**
     * @param isRouteToGo the isRouteToGo to set
     */
    public void setIsRouteToGo(boolean isRouteToGo) {
        this.isRouteToGo = isRouteToGo;
    }

    /**
     * @return the isParked
     */
    public boolean isIsParked() {
        return isParked;
    }

    /**
     * @param isParked the isParked to set
     */
    public void setIsParked(boolean isParked, MdVehicleAction mdVehicleAction) {
        if (!this.isActionExist(mdVehicleAction)) {
            getLstVehicleAction().add(mdVehicleAction);
        }
        this.isParked = isParked;
    }

    /**
     * @return the is40ZoneArea
     */
    public boolean isIs40ZoneArea() {
        return is40ZoneArea;
    }

    /**
     * @param is40ZoneArea the is40ZoneArea to set
     */
    public void setIs40ZoneArea(boolean is40ZoneArea) {
        this.is40ZoneArea = is40ZoneArea;
    }

    /**
     * @return the lstVehicleAction
     */
    public ArrayList<MdVehicleAction> getLstVehicleAction() {
        return lstVehicleAction;
    }

    /**
     * @param lstVehicleAction the lstVehicleAction to set
     */
    public void setLstVehicleAction(ArrayList<MdVehicleAction> lstVehicleAction) {
        this.lstVehicleAction = lstVehicleAction;
    }

    /**
     * @return the cumulitivedecMan
     */
    public int getCumulitivedecMan() {
        return cumulitivedecMan;
    }

    /**
     * @param cumulitivedecMan the cumulitivedecMan to set
     */
    public void setCumulitivedecMan(int cumulitivedecMan) {
        this.cumulitivedecMan = cumulitivedecMan;
    }

    /**
     * @return the cumulitivedecAuto
     */
    public int getCumulitivedecAuto() {
        return cumulitivedecAuto;
    }

    /**
     * @param cumulitivedecAuto the cumulitivedecAuto to set
     */
    public void setCumulitivedecAuto(int cumulitivedecAuto) {
        this.cumulitivedecAuto = cumulitivedecAuto;
    }

    /**
     * @return the cumulitivebreakMan
     */
    public int getCumulitivebreakMan() {
        return cumulitivebreakMan;
    }

    /**
     * @param cumulitivebreakMan the cumulitivebreakMan to set
     */
    public void setCumulitivebreakMan(int cumulitivebreakMan) {
        this.cumulitivebreakMan = cumulitivebreakMan;
    }

    /**
     * @return the cumulitivebrakAuto
     */
    public int getCumulitivebrakAuto() {
        return cumulitivebrakAuto;
    }

    /**
     * @param cumulitivebrakAuto the cumulitivebrakAuto to set
     */
    public void setCumulitivebrakAuto(int cumulitivebrakAuto) {
        this.cumulitivebrakAuto = cumulitivebrakAuto;
    }

    /**
     * @return the imgNormal
     */
    public String getImgNormal() {
        return imgNormal;
    }

    /**
     * @param imgNormal the imgNormal to set
     */
    public void setImgNormal(String imgNormal) {
        this.imgNormal = imgNormal;
    }

}
