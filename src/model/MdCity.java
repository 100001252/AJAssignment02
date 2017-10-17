/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.*;

import helper.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author XC8184
 */
public class MdCity implements Runnable {

    private ArrayList<MdCar> lstCar;
    private ArrayList<MdSchoolSign> lstSchoolSign;
    private ArrayList<MdStopSign> lstStopSign;
    private ArrayList<MdTrafficLight> lstTrafficLight;
    private String carToControl;

    public MdCity() {
        lstCar = new ArrayList<>();
        lstSchoolSign = new ArrayList<MdSchoolSign>();
        lstStopSign = new ArrayList<MdStopSign>();
        lstTrafficLight = new ArrayList<MdTrafficLight>();

    }

    /**
     * update location of each car based on their location in javafx
     *
     * @param carName
     * @param xVal
     * @param yVal
     */
    public synchronized void updateCarLocation(String carName, double xVal, double yVal, int oneCycle, int setto) {
        try {
            MdCar carobj = this.getCarByName(carName);
            Location oldLocation = carobj.getLocation();
            this.getCarByName(carName).setLocation(new Location(xVal, yVal));
            // this.getCarByName(carName).setLocation(new Location(carobj.getLocation().getX() + carobj.getSpeed() / 10, 0));
            Double dist = distanceBetweenLocation(oldLocation, new Location(xVal, yVal));

            // if (this.getCarByName(carName).getDistanceFromOrigin() > 2650 && this.getCarByName(carName).getDistanceFromOrigin() < 2660) {
            if (this.getCarByName(carName).getDistanceFromOrigin() > oneCycle && this.getCarByName(carName).getDistanceFromOrigin() < (oneCycle + 20)) {
                this.getCarByName(carName).setDistanceFromOrigin(setto);
            } else {
                this.getCarByName(carName).setDistanceFromOrigin(this.getCarByName(carName).getDistanceFromOrigin() + dist.intValue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * add car to arraylist of car for mdcity
     *
     * @param carobj
     */
    public void addCar(MdCar carobj) throws Exception {
        for (MdCar lstobj : this.getLstCar()) {
            if (lstobj.getName().equals(carobj.getName())) {
                DebugLog.appendData2("errrorrr2349080kj98723r>>>> car name exist");
                throw new CarRacingException("Car name " + carobj.getName() + " already exist in our record ");
            }
        }
        lstCar.add(carobj);
    }

    /**
     * add schoolsign to mdcity
     *
     * @param schoolsignObj
     */
    public void addSchoolSign(MdSchoolSign schoolsignObj) throws Exception {
        for (MdSchoolSign scObj : this.getLstSchoolSign()) {
            if (scObj.getName().equals(schoolsignObj.getName())) {
                DebugLog.appendData2("errrroo23214hkjasdf987rrrr>>>>>   schoolSign");
                throw new CarRacingException("this schoolsign name (" + schoolsignObj.getName() + ") already exist in our record");
            }
        }

        lstSchoolSign.add(schoolsignObj);
    }

    /**
     * add schoolsign to mdcity
     *
     * @param schoolsignObj
     */
    public void addStopSign(MdStopSign stObj) throws Exception {
        for (MdStopSign scObj : this.getLstStopSign()) {
            if (scObj.getName().equals(stObj.getName())) {
                DebugLog.appendData2("errrroo23214hkjasdf987rrrr>>>>>   schoolSign");
                throw new CarRacingException("this schoolsign name (" + stObj.getName() + ") already exist in our record");
            }
        }
        this.getLstStopSign().add(stObj);
    }

    public void addTrafficLight(MdTrafficLight tfObj) throws Exception {
        for (MdTrafficLight trafficobj : this.getLstTrafficLight()) {
            if (tfObj.getName().equals(trafficobj.getName())) {
                throw new CarRacingException("this Traffic sign name (" + tfObj.getName() + ") already exist in our record");
            }
        }
        this.getLstTrafficLight().add(tfObj);

    }

    /**
     * find a car by carname (carname is unique feature in mdcity)
     *
     * @param name
     * @return
     * @throws Exception
     */
    public MdCar getCarByName(String name) throws Exception {
        for (MdCar c : getLstCar()) {
            if (c.getName().equals(name)) {
                return c;
            }

        }
        throw new CarRacingException("there is no car with this name");

    }

    /**
     * get school sign by name(we can have many school sign by each school sign
     * should be uniuqe)
     *
     * @param name
     * @return
     * @throws Exception
     */
    public MdSchoolSign getSchoolSignByName(String name) throws Exception {
        for (MdSchoolSign sc : this.getLstSchoolSign()) {
            if (sc.getName().equals(name)) {
                return sc;
            }

        }
        throw new CarRacingException("there is no car with this name");

    }

    /**
     * method to sort all cars by distance from origin to figure out their
     * distance from each other
     *
     * @param
     * @return sorted car arraylist
     */
    public ArrayList<MdCar> getLstCar() {
        Collections.sort(this.lstCar);
        return this.lstCar;
    }

    public ArrayList<MdSchoolSign> getLstSchoolSign() {
        Collections.sort(this.lstSchoolSign);
        return this.lstSchoolSign;
    }

    /**
     * as we might have many different locations inside mdcity this method
     * return distance between each two point
     *
     * @param loc1
     * @param loc2
     * @return
     */
    public static double distanceBetweenLocation(Location loc1, Location loc2) {
        double result = 0;
        double part1 = Math.pow((loc1.getX() - loc2.getX()), 2);
        double part2 = Math.pow((loc1.getY() - loc2.getY()), 2);
        result = Math.sqrt(part1 + part2);
        return result;
    }

    /**
     * this method check distance between each pair of cars if distance is less
     * than 60 and the car in front is slowwer it decrease car behind to same
     * speed as the car infront. also it does fullstop if car infront is stopped
     *
     */
    public synchronized void stopAccidentOnRouteForSingleCar(String carname) {
        try {

            //for (MdCar objCar : (ArrayList<MdCar>) this.getLstCar().clone()) {
            MdCar objCar = this.getCarByName(carname);
            //----------------manage 40zone area
            for (MdSchoolSign sd : this.getLstSchoolSign()) {

                if ((sd.getLocationSchoolStart().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 50) {
                    objCar.setIs40ZoneArea(true);
                }
                if ((sd.getLocationSchoolEnd().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 50) {
                    objCar.setIs40ZoneArea(false);
                }
            }

            if (objCar.isIs40ZoneArea() && objCar.getSpeed() > 40) {
                objCar.setImgName("car-pink.png");
                // objCar.setSpeedFor40Zone("auto"); in 40 zone we wont set speed automatically
            }
            if (!objCar.isIs40ZoneArea() && objCar.getImgName().equals("car-pink.png")) {
                objCar.setImgName(objCar.getImgNormal());
            }

            //----------------------------------------------------------objcar is the one that we check
            //******check distance from infront car if its speed is less than me
            MdCar c = null;
            int index = this.getLstCar().indexOf(objCar);
            if ((index - 1) < this.getLstCar().size() && (index - 1) > -1) {
                c = this.getLstCar().get(index - 1);
            } else {
                c = this.getLstCar().get(this.getLstCar().size() - 1);
            }
            double distanceBetweenCars = distanceBetweenLocation(c.getLocation(), objCar.getLocation());
            if (distanceBetweenCars < 40 && objCar.isIsRouteToGo() == c.isIsRouteToGo()) {
                //it means they are very close that is why we stop the car in front
                if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.getSpeed() < c.getSpeed()) {
                    c.setIsParked(true, new MdVehicleAction("break", "break full stop", "because cars are very close", "auto", c.getName())); //car c is behind and objcar has speed less than c

                }

            }
            if (distanceBetweenCars < 60 && objCar.isIsRouteToGo() == c.isIsRouteToGo()) {

                if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.getSpeed() < c.getSpeed()) {
                    c.setSpeed(objCar.getSpeed(), new MdVehicleAction("dec", "decrease speed", "because of close distance", "auto", c.getName())); //car c is behind and objcar has speed less than c

                }
                //***********if front car is in fullstop condition
                if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.isIsParked()) {
                    c.setIsParked(true, new MdVehicleAction("break", "break full stop", "because car infront stoped", "auto", c.getName()));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * this method check distance between each pair of cars if distance is less
     * than 60 and the car in front is slowwer it decrease car behind to same
     * speed as the car infront. also it does fullstop if car infront is stopped
     *
     */
    public synchronized void stopAccidentOnRoute() {
        try {

            for (MdCar objCar : (ArrayList<MdCar>) this.getLstCar().clone()) {
                //---------------------------------------------------------------------stop sign
                for (MdStopSign st : this.getLstStopSign()) {
                    int index = objCar.getLstVehicleAction().size();
                    Date now = new Date();
                    if ((st.getLocation().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 100) {
                        //if last time that they stop passed is in less than 10 seconds from now
                        Date actionDate = objCar.getLstVehicleAction().get(index - 1).getActionTime();
                        String abr = objCar.getLstVehicleAction().get(index - 1).getAbbriviation();
                        long seconds = (now.getTime() - actionDate.getTime()) / 1000;
                        if (seconds < 4 && abr.equals("break")) // System.out.println("close to traffic lighttttt");
                        //objCar.setIs40ZoneArea(true);
                        {
                            objCar.setIs40ZoneArea(false);
                        } else {
                            objCar.setIs40ZoneArea(true);
                        }
                    }
                    if ((st.getLocation().calcDistanceBetweenTwoLocation(objCar.getLocation())) > 100) {
                        //objCar.setIs40ZoneArea(false);
                        objCar.setIs40ZoneArea(false);
                    }
                }

                //---------------------------------------------------------------------traffic light
                for (MdTrafficLight tf : this.getLstTrafficLight()) {
                    if ((tf.getLocation().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 100 && tf.getStatus() == 2) {
                        // System.out.println("close to traffic lighttttt");
                        objCar.setIs40ZoneArea(true);
                    }
                    if ((tf.getLocation().calcDistanceBetweenTwoLocation(objCar.getLocation())) > 100) {
                        objCar.setIs40ZoneArea(false);
                    }
                }

                //----------------manage 40zone area
                for (MdSchoolSign sd : this.getLstSchoolSign()) {

                    if ((sd.getLocationSchoolStart().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 50) {
                        objCar.setIs40ZoneArea(true);
                    }
                    if ((sd.getLocationSchoolEnd().calcDistanceBetweenTwoLocation(objCar.getLocation())) < 50) {
                        objCar.setIs40ZoneArea(false);
                    }
                }

                if (objCar.isIs40ZoneArea() && objCar.getSpeed() > 40) {
                    objCar.setImgName("car-pink.png");
                    // objCar.setSpeedFor40Zone("auto"); in 40 zone we wont set speed automatically
                }
                if (!objCar.isIs40ZoneArea() && objCar.getImgName().equals("car-pink.png")) {
                    objCar.setImgName(objCar.getImgNormal());
                }

                //----------------------------------------------------------objcar is the one that we check
                //******check distance from infront car if its speed is less than me
                MdCar c = null;
                int index = this.getLstCar().indexOf(objCar);
                if ((index - 1) < this.getLstCar().size() && (index - 1) > -1) {
                    c = this.getLstCar().get(index - 1);
                } else {
                    c = this.getLstCar().get(this.getLstCar().size() - 1);
                }
                double distanceBetweenCars = distanceBetweenLocation(c.getLocation(), objCar.getLocation());
                ///---------------------------------------------------------------------------set car speed if that is close
                if (distanceBetweenCars < 700 && objCar.isIsRouteToGo() == c.isIsRouteToGo()) {

                    if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.getSpeed() < c.getSpeed()) {
                        c.setSpeedofCarInFront(objCar.getSpeed());
                    }
                    //c.setSpeed(objCar.getSpeed(), new MdVehicleAction("dec", "decrease speed", "because of close distance", "auto", c.getName())); //car c is behind and objcar has speed less than c

                }

                if (distanceBetweenCars < 40 && objCar.isIsRouteToGo() == c.isIsRouteToGo()) {
                    //it means they are very close that is why we stop the car in front
                    if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.getSpeed() < c.getSpeed()) {
                        c.setSpeed(c.getSpeedofCarInFront(), new MdVehicleAction("break", "break full stop step01 slowdown", "because cars are very close", "auto", c.getName()));
                        c.setIsParked(true, new MdVehicleAction("break", "break full stop step02 full stop", "because cars are very close", "auto", c.getName())); //car c is behind and objcar has speed less than c

                    }

                }
                if (distanceBetweenCars < 80 && objCar.isIsRouteToGo() == c.isIsRouteToGo()) {

                    if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.getSpeed() < c.getSpeed()) {
                        c.setSpeed(c.getSpeedofCarInFront(), new MdVehicleAction("dec", "decrease speed", "because of close distance", "auto", c.getName())); //car c is behind and objcar has speed less than c

                    }
                    //***********if front car is in fullstop condition
                    if (objCar.getDistanceFromOrigin() > c.getDistanceFromOrigin() && objCar.isIsParked()) {
                        c.setIsParked(true, new MdVehicleAction("break", "break full stop", "because car infront stoped", "auto", c.getName()));
                    }

                }
            }//----------------------------------------------------------endobjcar is the one that we check
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * this function has been used in swing to stop cars accident
     *
     */
    public synchronized void stopAccident() {
        try {
            // System.out.println();
            for (MdCar objCar : (ArrayList<MdCar>) this.getLstCar().clone()) {
                MdCar c = null;
                int index = this.getLstCar().indexOf(objCar);
                if ((index + 1) < this.getLstCar().size()) {
                    c = this.getLstCar().get(index + 1);
                } else {
                    c = this.getLstCar().get(0);
                }

                int distanceBetweencars = objCar.getDistanceFromOrigin() - c.getDistanceFromOrigin();
                objCar.setDistanceToFrontCar(distanceBetweencars);
                if (distanceBetweencars > -10 && distanceBetweencars < 0 && objCar.getSpeed() > 0) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 10 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -20 && distanceBetweencars < -10 && objCar.getSpeed() > 0) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 20 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -30 && distanceBetweencars < -20 && objCar.getSpeed() > 0) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 30 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -40 && distanceBetweencars < -30 && objCar.getSpeed() > 0) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 40 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -50 && distanceBetweencars < -40 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 50 m has less speed", "auto", objCar.getName()));

                }
                if (distanceBetweencars > -60 && distanceBetweencars < -50 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.setSpeed(c.getSpeed() - 10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 60 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -60 && distanceBetweencars < -50 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.decreaseSpeed(10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 60 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -100 && distanceBetweencars < -20 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.decreaseSpeed(10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 100 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -200 && distanceBetweencars < -20 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.decreaseSpeed(10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 200 m has less speed", "auto", objCar.getName()));
                }
                if (distanceBetweencars > -300 && distanceBetweencars < -20 && objCar.getSpeed() > c.getSpeed()) {
                    objCar.decreaseSpeed(10, new MdVehicleAction("dec", "decrease Speed", "because car infront in 300 m has less speed", "auto", objCar.getName()));
                }

            }
//            this.setLstCar(listCar2);
        } catch (Exception ex) {
            ex.printStackTrace();
            // System.out.println("123123mymessage" + ex.getMessage());
        }

    }

    /**
     * it has been used in swing this function is in order to move on the path
     * that is a rectangular shape with (0,0), (0,500), (900,550), (950,100)
     * corners
     */
    public synchronized void moveOnPath1() {

        try {

            for (MdCar c : (ArrayList<MdCar>) this.getLstCar().clone()) {
                c.setImgName("taxi.jpg");

                c.setLocation(new Location(c.getLocation().getX() + (c.getSpeed() / 10), c.getLocation().getY()));
                c.setDistanceFromOrigin(c.getLocation().getX() + (c.getLocation().getY() == 200 ? c.getDistanceInEachRow() : 0));
                if (c.getLocation().getX() > c.getDistanceInEachRow() && c.getLocation().getY() == 100) {
                    c.setLocation(new Location(0, 200));
                }
                if (c.getLocation().getX() > c.getDistanceInEachRow() && c.getLocation().getY() == 200) {
                    c.setLocation(new Location(0, 100));
                }
            }
        } catch (Exception e) {
        }

    }

    public synchronized void move() {

        try {
            // System.out.println();
            for (MdCar c : (ArrayList<MdCar>) this.getLstCar().clone()) {
                c.setImgName("taxi.jpg");
                c.setLocation(new Location(c.getLocation().getX() + (c.getSpeed() / 10), c.getLocation().getY()));
                c.setDistanceFromOrigin(c.getLocation().getX() + (c.getLocation().getY() == 200 ? c.getDistanceInEachRow() : 0));
                if (c.getLocation().getX() > c.getDistanceInEachRow() && c.getLocation().getY() == 100) {
                    c.setLocation(new Location(0, 200));
                }
                if (c.getLocation().getX() > c.getDistanceInEachRow() && c.getLocation().getY() == 200) {
                    c.setLocation(new Location(0, 100));
                }
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void run() {//

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param lstCar the lstCar to set
     */
    public void setLstCar(ArrayList<MdCar> lstCar) {
        this.lstCar = lstCar;
    }

    /**
     * @return the carToControl
     */
    public String getCarToControl() {
        return carToControl;
    }

    /**
     * @param carToControl the carToControl to set
     */
    public void setCarToControl(String carToControl) {
        this.carToControl = carToControl;
    }

    /**
     * @return the lstStopSign
     */
    public ArrayList<MdStopSign> getLstStopSign() {
        return lstStopSign;
    }

    /**
     * @param lstStopSign the lstStopSign to set
     */
    public void setLstStopSign(ArrayList<MdStopSign> lstStopSign) {
        this.lstStopSign = lstStopSign;
    }

    /**
     * @return the lstTrafficLight
     */
    public ArrayList<MdTrafficLight> getLstTrafficLight() {
        return lstTrafficLight;
    }

    /**
     * @param lstTrafficLight the lstTrafficLight to set
     */
    public void setLstTrafficLight(ArrayList<MdTrafficLight> lstTrafficLight) {
        this.lstTrafficLight = lstTrafficLight;
    }

}
