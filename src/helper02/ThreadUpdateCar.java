/* this class has been used in javafx controller for efficient comparison
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper02;

import helper.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MdCity;

/**
 *
 * @author XC8184
 */
public class ThreadUpdateCar extends Thread {

    private String carName;
    private double xvalue;
    private double yvalue;
    private MdCity mdcity;

    public ThreadUpdateCar(MdCity mdcity, String carName, double xval, double yval) {

        this.carName = carName;
        this.xvalue = xval;
        this.yvalue = yval;
        this.mdcity = mdcity;
    }

    /**
     * as we extend thread we need this call a function that check all cars
     * inside city and slow down or stop them if the car infront is slower than
     * them
     */
    public void run() {
        try {
            this.mdcity.updateCarLocation(carName, xvalue, yvalue);
            Thread.sleep(1000);

        } catch (Exception ex) {
            Logger.getLogger(ThreadUpdateCar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
