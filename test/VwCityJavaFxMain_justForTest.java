/* main javafxclass that all car running in it
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import view2.*;
import helper02.*;
import helper.Location;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import helper.*;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 *
 * @author XC8184
 */
public class VwCityJavaFxMain_justForTestOld extends Application {

    //------------------allvariable that you use in this view and you should define at start
    private String colorHash;
    private MdCity mdCity;
    private MdTimer mdTimer;
    private Color labelsColor = Color.BLACK;
    private String labelsBackgroudColor = "white";
    private int initialSpeed;
    private int demoShowTimes;
    private ArrayList<ImageView> cars = new ArrayList<>();
    private ArrayList<ImageView> carClones = new ArrayList<>();
    private ArrayList<Label> carSpeedLabels = new ArrayList<>();
    private ArrayList<HBox> carBoxs = new ArrayList<>();
    private ArrayList<PathTransition> anims = new ArrayList<>();
    private ArrayList<Timeline> timelines = new ArrayList<>();
    private ArrayList<ThreadGeneral> lstThreadGeneral = new ArrayList<>();
    //-----------variable
    private int maxTime = 20;//maximum seconds of running this app

    public VwCityJavaFxMain_justForTestOld(String times, String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;

        this.demoShowTimes = Integer.parseInt(times);
    }

    public VwCityJavaFxMain_justForTestOld() {
        this.colorHash = "blue";
        this.mdCity = new MdCity();
        this.mdTimer = new MdTimer();
        this.initialSpeed = 100;

        this.demoShowTimes = 1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Generate secure random number
            SecureRandom randomNumber = new SecureRandom();
            int carNumber = 16 + randomNumber.nextInt(26);

//-----------define all cars
            int id;
            for (int i = 0; i < (carNumber / 2); i++) {
                id = i + 1;
                String ID = Integer.toString(id);

                if (id % 5 == 1) {
                    //oldcode mdCity.addCar(new MdCar(new Location(180, 400), "car"+ID+".png", "c"+ID, true, id, this.initialSpeed));
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-21d811.png", "c" + ID, true, id, this.initialSpeed));
                } else if (id % 5 == 2) {
                    //mdCity.addCar(new MdCar(new Location(180, 400), "car" + ID + ".jpg", "c" + ID, true, id, this.initialSpeed));
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-5906e8.png", "c" + ID, true, id, this.initialSpeed));
                } else if (id % 5 == 3) {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-043d01.png", "c" + ID, true, id, this.initialSpeed));
                } else if (id % 5 == 4) {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-822d00.png", "c" + ID, true, id, this.initialSpeed));
                } else {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-f97704.png", "c" + ID, true, id, this.initialSpeed));
                }

            }
            for (int i = (carNumber / 2); i < carNumber; i++) {
                id = i + 1;
                String ID = Integer.toString(id);

                if (id % 5 == 1) {
                    //oldcode mdCity.addCar(new MdCar(new Location(180, 400), "car"+ID+".png", "c"+ID, true, id, this.initialSpeed));
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-21d811.png", "c" + ID, false, id, this.initialSpeed));
                } else if (id % 5 == 2) {
                    //mdCity.addCar(new MdCar(new Location(180, 400), "car" + ID + ".jpg", "c" + ID, true, id, this.initialSpeed));
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-5906e8.png", "c" + ID, false, id, this.initialSpeed));
                } else if (id % 5 == 3) {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-043d01.png", "c" + ID, false, id, this.initialSpeed));
                } else if (id % 5 == 4) {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-822d00.png", "c" + ID, false, id, this.initialSpeed));
                } else {
                    mdCity.addCar(new MdCar(new Location(180, 400), "car-f97704.png", "c" + ID, false, id, this.initialSpeed));
                }

            }

            mdCity.addSchoolSign(new MdSchoolSign("sc1", new Location(800, 100), new Location(300, 100)));

            //Initial all Arraylists
            for (int i = 0; i < carNumber; i++) {
                cars.add(new ImageView());
                carClones.add(new ImageView());

                Label carSpeedLabel = new Label("Speed");
                carSpeedLabel.setTextFill(labelsColor);
                carSpeedLabels.add(carSpeedLabel);

                carBoxs.add(new HBox());

                anims.add(new PathTransition());
            }
            ImageView car1 = new ImageView();

            ImageView img_schoolZone_start = new ImageView();
            ImageView img_schoolZone_end = new ImageView();

            Label lbl1 = new Label("1.L-CLICK on a car reduce speed by 10km/h");
            lbl1.setTextFill(labelsColor);
            lbl1.setLayoutX(300);
            lbl1.setLayoutY(260);
            lbl1.setStyle("-fx-background-color: " + labelsBackgroudColor + ";");

            Label lbl2 = new Label("2.After you CLICK on a car you can use arrow up/down to increase and decrease speed \n"
                    + "3. R-CLICK make car full stop \n"
                    + "4. keyboard SPACE make all car stops(and it will resume with another space click)");
            lbl2.setTextFill(labelsColor);
            lbl2.setLayoutX(300);
            lbl2.setLayoutY(280);
            lbl2.setStyle("-fx-background-color: " + labelsBackgroudColor + ";");

            Label lblTimer = new Label("Timer");
            lblTimer.setTextFill(labelsColor);
            lblTimer.setStyle("-fx-background-color: " + labelsBackgroudColor + ";");
            lblTimer.setLayoutX(300);
            lblTimer.setLayoutY(-50);

            //-------------end defining cars
//--------------------------------------------------------------------------------------defining car image or button
            Circle circ1 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
            //car.setImage(new Image("file:res/car.gif"));
            int carid = 1;
            int hboxY = 20;
            for (int counter = 0; counter < cars.size(); counter++) {
                String ID = Integer.toString(carid);
                String carname = "c" + ID;
                //ImageView carView = cars.get(counter);
                cars.get(counter).setImage(new Image(mdCity.getCarByName(carname).getImgName()));
                cars.get(counter).setX(mdCity.getCarByName(carname).getLocation().getX());
                cars.get(counter).setY(mdCity.getCarByName(carname).getLocation().getY());
                cars.get(counter).setRotate(-90);

                //ImageView carCloneView = carClones.get(counter);
                carClones.get(counter).setImage(new Image(mdCity.getCarByName(carname).getImgName()));
                carid++;

                carBoxs.get(counter).getChildren().addAll(carClones.get(counter), carSpeedLabels.get(counter));
                carBoxs.get(counter).setLayoutY(hboxY);
                hboxY += 40;
            }

            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
            car1.setX(mdCity.getCarByName("c1").getLocation().getX());
            car1.setY(mdCity.getCarByName("c1").getLocation().getY());
            car1.setRotate(-90);

            img_schoolZone_start.setImage(new Image(mdCity.getSchoolSignByName("sc1").getImgSchoolStart()));
            img_schoolZone_start.setX(mdCity.getSchoolSignByName("sc1").getLocationSchoolStart().getX());
            img_schoolZone_start.setY(mdCity.getSchoolSignByName("sc1").getLocationSchoolStart().getY());

            img_schoolZone_end.setImage(new Image(mdCity.getSchoolSignByName("sc1").getImgSchoolEnd()));
            img_schoolZone_end.setX(mdCity.getSchoolSignByName("sc1").getLocationSchoolEnd().getX());
            img_schoolZone_end.setY(mdCity.getSchoolSignByName("sc1").getLocationSchoolEnd().getY());

            circ1.setLayoutX(400);
            circ1.setLayoutY(200);
//---------------------------------------------------------------------------------------------------end defining car initial image..
//*****dontworry for now-------------------------------------------------------defining all path(normal, pathgocar,pathreturncar)--------
            PathElement[] path
                    = {
                        new MoveTo(0, 500),
                        new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
                        new LineTo(900, 550),
                        new ArcTo(50, 50, 0, 950, 500, false, false),//2 arc
                        new LineTo(950, 100),
                        new ArcTo(50, 50, 0, 900, 50, false, false),//3 arc
                        new LineTo(50, 50),
                        new ArcTo(50, 50, 0, 0, 100, false, false),//3 arc

                        new ClosePath()
                    };

            //road for ongoing car
            PathElement[] pathCarGo
                    = {
                        new MoveTo(20, 480),
                        new ArcTo(50, 50, 0, 80, 530, false, false),//first arc
                        new LineTo(870, 530),
                        new ArcTo(50, 50, 0, 920, 480, false, false),//2 arc
                        new LineTo(920, 80),
                        new ArcTo(50, 50, 0, 870, 80, false, false),//3 arc
                        new LineTo(50, 80),
                        new ArcTo(50, 50, 0, 20, 100, false, false),//3 arc

                        new ClosePath()
                    };

            PathElement[] pathCarReturn
                    = {//for start tdo not put any car return
                        new MoveTo(-20, 480),
                        new LineTo(-20, 80),
                        new ArcTo(50, 50, 0, 30, 20, false, true),//first arc
                        new LineTo(930, 20),
                        new ArcTo(50, 50, 0, 980, 80, false, true),//2 arc
                        new LineTo(980, 530),
                        new ArcTo(50, 50, 0, 920, 580, false, true),//3 arc
                        new LineTo(0, 580),
                        new ClosePath()
                    };
//****end kjlk--------------------------------------------------------------------------------end defining all path

//--------------------------------------draw road black
            Path road = new Path();
            road.setLayoutX(200);
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(120);
            road.getElements().addAll(path);

            Path divider = new Path();
            divider.setLayoutX(200);
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(2);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(path);

            //-----------------end of draw road
            //----------------------------------------------------create all animation with different path
            Path roadCargo = new Path();
            roadCargo.setLayoutX(200);
            roadCargo.getElements().addAll(pathCarGo);

            Path roadCarreturn = new Path();
            roadCarreturn.setLayoutX(200);
            roadCarreturn.getElements().addAll(pathCarReturn);

            //-------anim
            //Group g1=new Group()
            int pathi = 0;
            int pathid;
            for (PathTransition anim : anims) {
                pathid = pathi + 1;
                String ID = Integer.toString(pathid);
                String carname = "c" + ID;

                anim.setNode(cars.get(pathi));
                anim.setPath(mdCity.getCarByName(carname).isIsRouteToGo() ? roadCargo : roadCarreturn);
                anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                anim.setInterpolator(Interpolator.LINEAR);
                anim.setCycleCount(Timeline.INDEFINITE);
                anim.setDuration(new Duration(mdCity.getCarByName(carname).convertSpeedToDuration()));
                anim.setDelay(Duration.seconds(mdCity.getCarByName(carname).getDelay()));
                pathi++;
                anim.play();
            }
            PathTransition anim = new PathTransition();
            anim.setNode(car1);
            anim.setPath(mdCity.getCarByName("c1").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setInterpolator(Interpolator.LINEAR);
            anim.setCycleCount(Timeline.INDEFINITE);
            anim.setDuration(new Duration(mdCity.getCarByName("c1").convertSpeedToDuration()));
            anim.setDelay(Duration.seconds(mdCity.getCarByName("c1").getDelay()));
            anim.play();
            //------------------------------root group
            Group root = new Group();

            root.getChildren().addAll(road, divider, circ1, lbl1, lbl2, lblTimer, img_schoolZone_start, img_schoolZone_end);
            //root.getChildren().addAll(hb1, hb2, hb3, road, divider, car1, car2, car3, circ1, lbl1, lbl2, lblTimer, img_schoolZone_start, img_schoolZone_end);
            for (ImageView car : cars) {
                root.getChildren().add(car);
            }
            for (HBox hbox : carBoxs) {
                root.getChildren().add(hbox);
            }
            root.setTranslateX(50);
            root.setTranslateY(50);

            //---------animations.play()
//            anims.forEach((anim0) -> {
//                anim0.play();
//            });
            anims.forEach((anim0) -> {
                System.err.println(anim0.getStatus());
            });

            //---------test race start-timeline1
            int ti = 0, timelineId;
            for (ImageView aCar : cars) {
                timelineId = ti + 1;
                String ID = Integer.toString(timelineId);
                String carname = "c" + ID;
                PathTransition anim0 = anims.get(ti);

                ti++;
                lstThreadGeneral.add(new ThreadGeneral(mdCity, mdTimer, anim0, carname, aCar));

            }
            for (int i = 0; i < lstThreadGeneral.size(); i++) {
                lstThreadGeneral.get(i).run();
            }

            //----//---------test race end-timeline3
            //---------test race start-timeline4 it is a timeline that we never stop
            Timeline timeline4 = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    int carId = 1;
                    try {
                        for (Label carlabel : carSpeedLabels) {
                            String ID = Integer.toString(carId);
                            String carname = "c" + ID;
                            carlabel.setText(Integer.toString(mdCity.getCarByName(carname).getSpeed()) + " km/hr");
                            carId++;
                        }

                        //----------------------------just for dbug purposes
                        //----------------------------end of debug
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (mdTimer.getSec() == maxTime) {//--------end of process
                        System.out.println("Finished");
                    }
                    if (mdTimer.getSec() <= maxTime) {
                        lblTimer.setText("Timer:" + mdTimer.getSec());
                    }

                }
            }));//----//---------test race end-timeline4

            for (int i = 0; i < cars.size(); i++) {

                //--------------------------------------------------------------------start
                String Id = Integer.toString(i);
                String carname = lstThreadGeneral.get(i).getCarname();
                Timeline timeline1 = lstThreadGeneral.get(i).getTimeline();
                PathTransition anim0 = lstThreadGeneral.get(i).getAnim0();
                ImageView aCar = cars.get(i);

                aCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent
                    ) {
                        System.out.println("clicked on carrr");
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            //-----------------------------------------------------normal op
                            try {

                                mdCity.setCarToControl(carname);
                                Timeline.Status status = timeline1.getStatus();
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

                //---------------------------------------------------------------------end
            }
//            ///mouse-onclick-car1
//            int ei = 0;
//            int mouseActId;
//            for (ImageView aCar : cars) {
//                mouseActId = ei + 1;
//                String ID = Integer.toString(mouseActId);
//                String carname = "c" + ID;
//                Timeline timeline1 = timelines.get(ei);
//                PathTransition anim0 = anims.get(ei);
//                aCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent
//                    ) {
//                        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
//                            try {
//                                mdCity.setCarToControl(carname);
//                                fullStopControl(timeline1, anim0, carname, "user");
//
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//
//                        if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
//                            //-----------------------------------------------------normal op
//                            try {
//
//                                mdCity.setCarToControl(carname);
//                                Timeline.Status status = timeline1.getStatus();
//                                //--------------------------------------------------end normal op
//                                anim0.setRate(mdCity.getCarByName(carname).convertSpeedToRate());
//
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//
//                        }//end middle button mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                            //-----------------------------------------------------normal op
//                            try {
//
//                                mdCity.setCarToControl(carname);
//                                Timeline.Status status = timeline1.getStatus();
//                                if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                    fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", true);
//                                } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                    fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", false);
//                                }
//                                mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftclick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//
//                                //--------------------------------------------------end normal op
//                                anim0.setRate(mdCity.getCarByName(carname).convertSpeedToRate());
//
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//
//                        }
//                    }
//                });
//                ei++;
//            }
            Scene scene = new Scene(root, 1350, 750, Color.DARKGREEN);

//--------------------------------tableview end
            //--------------------------------------------------------------------keyevent
//            for (Timeline aLine : timelines) {
//                aLine.setCycleCount(Timeline.INDEFINITE);
//                aLine.play();
//            }
            timeline4.setCycleCount(Timeline.INDEFINITE);
            timeline4.play();
            ///circle clicked
            circ1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // System.out.println("clickkkd on circle");
                    Timeline timeline1 = timelines.get(0);
                    if (timeline1.getStatus() == Timeline.Status.RUNNING) {
                        stopAllMove(anims, timelines, mdTimer, false);
                    } else {
                        stopAllMove(anims, timelines, mdTimer, true);

                    }
                }

            });
            //---------end test

            primaryStage.setTitle(
                    "PathTransition Demo");
            primaryStage.setScene(scene);
            scene.setFill(Color.web(this.colorHash));
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }
//---------------variable

    /**
     * function to stop all animation and correcpndanse timelines
     *
     * @param anim
     * @param anim2
     * @param anim3
     * @param t1
     * @param t2
     * @param t3
     * @param mdtimer
     * @param start
     */
    public void stopAllMove(ArrayList<PathTransition> animations, ArrayList<Timeline> multiT, MdTimer mdtimer, boolean start) {
        if (mdtimer.getSec() < this.maxTime && start) {
            for (int i = 0; i < animations.size(); i++) {
                animations.get(i).play();
                multiT.get(i).play();
            }
//            t1.play();
//            t2.play();
//            t3.play();
//            anim.play();
//            anim2.play();
//            anim3.play();

        } else {
            for (int i = 0; i < animations.size(); i++) {
                animations.get(i).pause();
                multiT.get(i).pause();
            }
//            t1.pause();
//            t2.pause();
//            t3.pause();
//            anim.pause();
//            anim2.pause();
//            anim3.pause();

        }

    }

    /**
     * function for full stop
     *
     * @param timelineobj
     * @param anim
     * @param carname
     * @param ActionBy
     * @throws Exception
     */
    public void fullStopControl(Timeline timelineobj, PathTransition anim, String carname, String ActionBy) throws Exception {
        if (timelineobj.getStatus() == Timeline.Status.RUNNING) {
            timelineobj.pause();
            anim.pause();
            mdCity.getCarByName(carname).setIsParked(true, new MdVehicleAction("break", "break full stop", "user press full stop", ActionBy, carname));

        } else {
            mdCity.getCarByName(carname).setIsParked(false, new MdVehicleAction("breakstart", "start from break", "user continue from full stop", ActionBy, carname));

            timelineobj.play();
            anim.play();
        }

    }

    /**
     * function for full stop when they decrease speed
     *
     * @param timelineobj
     * @param anim
     * @param carname
     * @param ActionBy
     * @param stopCar
     * @throws Exception
     */
    public void fullStopControlWhenDecreaseSpeed(Timeline timelineobj, PathTransition anim, String carname, String ActionBy, boolean stopCar) throws Exception {
        if (stopCar) {
            timelineobj.pause();
            anim.pause();
            mdCity.getCarByName(carname).setIsParked(true, new MdVehicleAction("break", "break full stop", "user press full stop", ActionBy, carname));

        } else {
            timelineobj.play();
            anim.play();
            mdCity.getCarByName(carname).setIsParked(false, new MdVehicleAction("breakstart", "start from break", "user continue from full stop", ActionBy, carname));

        }

    }

}
