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
    private Timer timer ;
    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {
            sec++;
            // // System.out.println(sec);
        }
    };

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void pause() {
        this.timer.cancel();
    }
    
    public void resume() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            sec++;
            // // System.out.println(sec);
        }
    }, 1000, 1000);
    }
    
    public MdTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(myTask, 1000, 1000);

    }

    /**
     * @return the sec
     */
    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

}
