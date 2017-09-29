/* class model car each class car is comming from here and each car is a vehicle (for future extention e.g different type of car)
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.*;

import helper.*;

/**
 *
 * @author XC8184
 */
public class MdCar extends Vehicle {

    public MdCar(Location location, String imgName, String carName, boolean isGoRoute, int delayToStart, int initialSpeed) {
        super(location, imgName, carName, isGoRoute, delayToStart, initialSpeed);

    }

    public MdCar(Location location, String imgName, String carName) {
        super(location, imgName, carName);

    }

}
