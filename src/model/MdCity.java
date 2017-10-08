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

/**
 *
 * @author XC8184
 */
public class MdCity implements Runnable {

    private ArrayList<MdCar> lstCar;
    private ArrayList<MdSchoolSign> lstSchoolSign;
    private String carToControl;

    public MdCity() {
        lstCar = new ArrayList<>();
        lstSchoolSign = new ArrayList<>();

    }

    /**
     * update location of each car based on their location in javafx
     *
     * @param carName
     * @param xVal
     * @param yVal
     */
    public void updateCarLocation(String carName, double xVal, double yVal) {
        try {
            MdCar carobj = this.getCarByName(carName);
            Location oldLocation = carobj.getLocation();
            this.getCarByName(carName).setLocation(new Location(xVal, yVal));
            // this.getCarByName(carName).setLocation(new Location(carobj.getLocation().getX() + carobj.getSpeed() / 10, 0));
            Double dist = distanceBetweenLocation(oldLocation, new Location(xVal, yVal));

            if (this.getCarByName(carName).getDistanceFromOrigin() > 2650 && this.getCarByName(carName).getDistanceFromOrigin() < 2660) {
                this.getCarByName(carName).setDistanceFromOrigin(0);
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
    public synchronized void stopAccidentOnRoute() {
        try {

            for (MdCar objCar : (ArrayList<MdCar>) this.getLstCar().clone()) {
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

}
