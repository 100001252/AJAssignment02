/* a class to increment time and run drivving test for 5 mins
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author XC8184
 */
public class MdTimer {

    private int sec = 0;
    Timer timer = new Timer();
    TimerTask myTask = new TimerTask() {
        public void run() {
            sec++;
            // // System.out.println(sec);
        }
    };

    public MdTimer() {
        timer.scheduleAtFixedRate(myTask, 1000, 1000);

    }

    /**
     * @return the sec
     */
    public int getSec() {
        return sec;
    }

}
