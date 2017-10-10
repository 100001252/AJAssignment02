/* this class has been used in javafx controller for efficient comparison
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper02;

import helper.*;
import javafx.animation.Timeline;
import model.MdCity;

/**
 *
 * @author XC8184
 */
public class ThreadGeneral extends Thread {

    private MdCity mdCity;
    private Timeline timeline;

    public ThreadGeneral(MdCity mdcity, Timeline tl) {

        this.mdCity = mdcity;
        this.timeline = tl;
        this.timeline.play();
    }

    /**
     * as we extend thread we need this call a function that check all cars
     * inside city and slow down or stop them if the car infront is slower than
     * them
     */
    public void run() {
        this.mdCity.stopAccidentOnRoute();
    }

}
