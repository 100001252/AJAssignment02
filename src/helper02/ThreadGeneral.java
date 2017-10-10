/* this class has been used in javafx controller for efficient comparison
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper02;

import helper.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.MdCity;
import model.MdTimer;
import model.MdVehicleAction;

/**
 *
 * @author XC8184
 */
public class ThreadGeneral extends Thread {

    private MdCity mdCity;
    private Timeline timeline;
    private MdTimer mdTimer;
    private PathTransition anim0;
    private String carname;
    private ImageView aCar;

    public ThreadGeneral(MdCity mdcity, MdTimer mdtimerObj, PathTransition anim0, String carname, ImageView aCar) {
        this.mdTimer = mdtimerObj;
        this.mdCity = mdcity;
        this.anim0 = anim0;
        this.aCar = aCar;
        this.carname = carname;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                updataeCarLocation();
                actionListenerCar();
                ThreadStopAccident th = new ThreadStopAccident(mdCity);
                th.run();
                run();
            }
        }));
        this.timeline.play();

    }

    private void updataeCarLocation() {
        if (getMdTimer().getSec() > 3) {
            try {
                mdCity.updateCarLocation(carname, aCar.getX() + aCar.getTranslateX(), aCar.getY() + aCar.getTranslateY());
                System.out.println("c1 x=" + mdCity.getCarByName(carname).getLocation().getX() + "|Y=" + mdCity.getCarByName(carname).getLocation().getY());
                getAnim0().setDelay(Duration.seconds(0));
                ThreadStopAccident ths = new ThreadStopAccident(mdCity);

                ths.run();
                getaCar().setImage(new Image(mdCity.getCarByName(getCarname()).getImgName()));

                if (mdCity.getCarByName(getCarname()).isIsParked()) {
                    getAnim0().pause();
                }
                if (mdCity.getCarByName(getCarname()).getSpeed() == 0) {
                    getAnim0().pause();
                } else {
                    //  anim.playFromStart();
                    getAnim0().setRate(mdCity.getCarByName(getCarname()).convertSpeedToRate());

                }

            } catch (Exception ex2) {

                ex2.printStackTrace();
            }

        }
    }

    public void actionListenerCar() {
        aCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent
            ) {
                System.out.println("clicked on carrr" + carname);
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    //-----------------------------------------------------normal op
                    try {

                        mdCity.setCarToControl(carname);
                        Timeline.Status status = timeline.getStatus();
                        if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {

                            // fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", true);
                        } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                    fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", false);
                        }
                        mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftclick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));

                        //--------------------------------------------------end normal op
                        anim0.setRate(mdCity.getCarByName(carname).convertSpeedToRate());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

    }

    /**
     * as we extend thread we need this call a function that check all cars
     * inside city and slow down or stop them if the car infront is slower than
     * them
     */
    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * @return the mdTimer
     */
    public MdTimer getMdTimer() {
        return mdTimer;
    }

    /**
     * @param mdTimer the mdTimer to set
     */
    public void setMdTimer(MdTimer mdTimer) {
        this.mdTimer = mdTimer;
    }

    /**
     * @return the anim0
     */
    public PathTransition getAnim0() {
        return anim0;
    }

    /**
     * @param anim0 the anim0 to set
     */
    public void setAnim0(PathTransition anim0) {
        this.anim0 = anim0;
    }

    /**
     * @return the carname
     */
    public String getCarname() {
        return carname;
    }

    /**
     * @param carname the carname to set
     */
    public void setCarname(String carname) {
        this.carname = carname;
    }

    /**
     * @return the aCar
     */
    public ImageView getaCar() {
        return aCar;
    }

    /**
     * @param aCar the aCar to set
     */
    public void setaCar(ImageView aCar) {
        this.aCar = aCar;
    }

}
