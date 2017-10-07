/* main javafxclass that all car running in it
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import helper.Location;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFrame;
import model.*;
import helper.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author XC8184
 */
public class VwCityJavaFx01_mainTest extends Application {

    //------------------allvariable that you use in this view and you should define at start
    private String colorHash;
    private MdCity mdCity;
    private MdTimer mdTimer;
    private Color labelsColor = Color.BLACK;
    private String labelsBackgroudColor = "white";
    private int initialSpeed;
    //-----------variable
    private int maxTime = 500;//maximum seconds of running this app

    public VwCityJavaFx01_mainTest(String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

//-----------define all cars
            mdCity.addCar(new MdCar(new Location(180, 400), "carbluepolice.jpg", "c1", true, 1, this.initialSpeed));
            mdCity.addCar(new MdCar(new Location(180, 400), "carred.png", "c2", true, 2, this.initialSpeed));
            mdCity.addCar(new MdCar(new Location(180, 400), "carblue.png", "c3", true, 3, this.initialSpeed));
            mdCity.addSchoolSign(new MdSchoolSign("sc1", new Location(800, 100), new Location(300, 100)));

            ImageView car1 = new ImageView();
            ImageView car2 = new ImageView();
            ImageView car3 = new ImageView();
            ImageView car1Clone = new ImageView();
            ImageView car2Clone = new ImageView();
            ImageView car3Clone = new ImageView();
            Label car1lblSpeed = new Label("Speed");
            car1lblSpeed.setTextFill(labelsColor);
            Label car2lblSpeed = new Label("Speed");
            car2lblSpeed.setTextFill(labelsColor);
            Label car3lblSpeed = new Label("Speed");
            car3lblSpeed.setTextFill(labelsColor);

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
            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
            car1.setX(mdCity.getCarByName("c1").getLocation().getX());
            car1.setY(mdCity.getCarByName("c1").getLocation().getY());
            car1.setRotate(-90);
            car1Clone.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
            HBox hb1 = new HBox();
            hb1.getChildren().addAll(car1Clone, car1lblSpeed);
            hb1.setLayoutY(20);

            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
            car2.setX(mdCity.getCarByName("c1").getLocation().getX());
            car2.setY(mdCity.getCarByName("c1").getLocation().getY());
            car2.setRotate(-90);
            car2Clone.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
            HBox hb2 = new HBox();
            hb2.getChildren().addAll(car2Clone, car2lblSpeed);
            hb2.setLayoutY(60);

            car3.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
            car3.setX(mdCity.getCarByName("c3").getLocation().getX());
            car3.setY(mdCity.getCarByName("c3").getLocation().getY());
            car3.setRotate(-90);
            car3Clone.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
            HBox hb3 = new HBox();
            hb3.getChildren().addAll(car3Clone, car3lblSpeed);
            hb3.setLayoutY(100);

            img_schoolZone_start.setImage(new Image(mdCity.getSchoolSignByName("sc1").getImgSchoolStart()));
            img_schoolZone_start.setX(mdCity.getSchoolSignByName("sc1").getLocationSchoolStart().getX());
            img_schoolZone_start.setY(mdCity.getSchoolSignByName("sc1").getLocationSchoolStart().getY());

            img_schoolZone_end.setImage(new Image(mdCity.getSchoolSignByName("sc1").getImgSchoolEnd()));
            img_schoolZone_end.setX(mdCity.getSchoolSignByName("sc1").getLocationSchoolEnd().getX());
            img_schoolZone_end.setY(mdCity.getSchoolSignByName("sc1").getLocationSchoolEnd().getY());

            circ1.setLayoutX(400);
            circ1.setLayoutY(200);
//---------------------------------------------------------------------------------------------------end defining car initial image..
//-------------------------------------------------------defining all path(normal, pathgocar,pathreturncar)
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
//--------------------------------------------------------------------------------end defining all path

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

            MdCar.setId_autoGen(0);
            PathTransition anim = new PathTransition();
            //-------anim
            //Group g1=new Group()
            anim.setNode(car1);
            anim.setPath(mdCity.getCarByName("c1").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setInterpolator(Interpolator.LINEAR);
            anim.setCycleCount(Timeline.INDEFINITE);
            anim.setDuration(new Duration(mdCity.getCarByName("c1").convertSpeedToDuration()));
            anim.setDelay(Duration.seconds(mdCity.getCarByName("c1").getDelay()));
            //---------anim2
            PathTransition anim2 = new PathTransition();
            anim2.setNode(car2);
            anim2.setPath(mdCity.getCarByName("c2").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim2.setCycleCount(Timeline.INDEFINITE);
            anim2.setInterpolator(Interpolator.LINEAR);
            anim2.setDuration(new Duration(mdCity.getCarByName("c2").convertSpeedToDuration()));
            anim2.setDelay(Duration.seconds(mdCity.getCarByName("c2").getDelay()));
            //----------anim3
            PathTransition anim3 = new PathTransition();
            anim3.setNode(car3);
            anim3.setPath(mdCity.getCarByName("c3").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            anim3.setCycleCount(Timeline.INDEFINITE);
            anim3.setInterpolator(Interpolator.LINEAR);
            anim3.setDuration(new Duration(mdCity.getCarByName("c3").convertSpeedToDuration()));
            anim3.setDelay(Duration.seconds(mdCity.getCarByName("c3").getDelay()));

            PathTransition anim4 = new PathTransition();
            Group root = new Group();
            //root.getChildren().addAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lbl3, table);
            //----------addallitem add all item
            //root.getChildren().addAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lbl3, lblTimer, img_schoolZone_start, img_schoolZone_end);
            root.getChildren().addAll(hb1, hb2, hb3, road, divider, car1, car2, car3, circ1, lbl1, lbl2, lblTimer, img_schoolZone_start, img_schoolZone_end);
            root.setTranslateX(50);
            root.setTranslateY(50);

            //---------animations.play()
            anim.play();
            anim2.play();
            anim3.play();

            //---------test race start-timeline1
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    mdCity.updateCarLocation("c1", car1.getX() + car1.getTranslateX(), car1.getY() + car1.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim.setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);

                            ths.run();
                            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));

                            if (mdCity.getCarByName("c1").isIsParked()) {
                                anim.pause();
                            }
                            if (mdCity.getCarByName("c1").getSpeed() == 0) {
                                anim.pause();
                            } else {
                                //  anim.playFromStart();
                                anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());

                            }
//                            lbl1.setText("carname=c1|speed=" + Integer.toString(mdCity.getCarByName("c1").getSpeed()) + "|x=" + car1.getTranslateX() + "|dist=" + mdCity.getCarByName("c1").getDistanceFromOrigin() + "|rate" + anim.getRate() + "\n ttt"
//                                    + "|x=" + car1.getTranslateX() + "|y=" + car1.getTranslateY() + "|time" + anim.getCurrentTime() + "\n "
//                                    + "|orientation:" + anim.getOrientation());
//                            lbl1.setText("carname=c1|speed=" + Integer.toString(mdCity.getCarByName("c1").getSpeed()) + "|rate" + anim.getRate() + "\n ttt"
//                                    + "Dist=" + Integer.toString(mdCity.getCarByName("c1").getDistanceFromOrigin()) + "  |time" + anim.getCurrentTime() + "\n "
//                            );

                        } catch (Exception ex2) {
                            // System.out.println("searchhhhhhfor23424234");
                            ex2.printStackTrace();
                        }

                    }
                }
            }));//----//---------test race end-timeline1

            //---------test race start-timeline2
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    //mdCity.updateCarLocation("c2", car2.getTranslateX(), car2.getTranslateY());
                    mdCity.updateCarLocation("c2", car2.getX() + car2.getTranslateX(), car2.getY() + car2.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim2.setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
                            ths.run();
                            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));

                            if (mdCity.getCarByName("c2").isIsParked()) {
                                anim2.pause();
                            }

                            if (mdCity.getCarByName("c2").getSpeed() == 0) {
                                anim2.pause();
                            } else {
                                //  anim.playFromStart();
                                anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
                            }
                            //lbl2.setText("carname=c2|speed=" + Integer.toString(mdCity.getCarByName("c2").getSpeed()) + "|x=" + car2.getTranslateX() + "|y=" + car2.getTranslateY() + " + x=" + car1.getTranslateX() + "|dist=" + mdCity.getCarByName("c2").getDistanceFromOrigin() + "|rate" + anim2.getRate());
                        } catch (Exception ex2) {
                            // System.out.println("searchhhhhhfor23424234");
                            ex2.printStackTrace();
                        }

                    }
                }
            }));//----//---------test race end-timeline2

            //---------test race start-timeline3
            Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    //mdCity.updateCarLocation("c3", car3.getTranslateX(), car3.getTranslateY());
                    mdCity.updateCarLocation("c3", car3.getX() + car3.getTranslateX(), car3.getY() + car3.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim3.setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
                            ths.run();
                            car3.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
                            if (mdCity.getCarByName("c3").isIsParked()) {
                                anim3.pause();
                            }
                            if (mdCity.getCarByName("c3").getSpeed() == 0) {
                                anim3.pause();
                            } else {
                                //  anim.playFromStart();
                                anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
                            }

                        } catch (Exception ex2) {
                            // System.out.println("searchhhhhhfor23424234");
                            ex2.printStackTrace();
                        }

                    }
                }
            }));//----//---------test race end-timeline3
            //---------test race start-timeline4 it is a timeline that we never stop
            Timeline timeline4 = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    try {
                        car1lblSpeed.setText(Integer.toString(mdCity.getCarByName("c1").getSpeed()) + " km/hr");
                        car2lblSpeed.setText(Integer.toString(mdCity.getCarByName("c2").getSpeed()) + " km/hr");
                        car3lblSpeed.setText(Integer.toString(mdCity.getCarByName("c3").getSpeed()) + " km/hr");
                        //----------------------------just for dbug purposes

                        //----------------------------end of debug
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (mdTimer.getSec() == maxTime) {//--------end of process
                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, false);
                        root.getChildren().removeAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lblTimer, img_schoolZone_start, img_schoolZone_end);
                        showTableCar(root);
                        showTableCarRecords(root);
                        tableCarRecords1.setLayoutX(650);
                        btnReturnToStart = new JFXButton("return To Start");
                        btnReturnToStart.setStyle("-fx-background-color:blue; -fx-color:white;");
                        btnReturnToStart.setTextFill(Color.WHITE);
                        btnReturnToStart.setLayoutX(500);
                        btnReturnToStart.setLayoutY(500);
                        btnSaveToDatabase = new JFXButton("Save");
                        btnSaveToDatabase.setLayoutX(400);
                        btnSaveToDatabase.setLayoutY(500);
                        btnSaveToDatabase.setStyle("-fx-background-color:blue; -fx-color:white;");
                        btnSaveToDatabase.setTextFill(Color.WHITE);
                        root.getChildren().addAll(tableCar, tableCarRecords1, btnReturnToStart, btnSaveToDatabase);

                        btnSaveToDatabase.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Db db = new Db();
                                try {
                                    db.btnSaveToDbClicked(mdCity);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }

                        });

                        btnReturnToStart.setOnMouseClicked(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    btnReturnToStartClicked(event);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }

                        });

                    }
                    if (mdTimer.getSec() <= maxTime) {
                        lblTimer.setText("Timer:" + mdTimer.getSec());
                    }

                }
            }));//----//---------test race end-timeline4

            ///mouse-onclick-car1
            car1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent
                ) {
                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        try {
                            mdCity.setCarToControl("c1");
                            fullStopControl(timeline1, anim, "c1", "user");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl("c1");
                            Timeline.Status status = timeline1.getStatus();
                            //--------------------------------------------------end normal op
                            anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }//end middle button mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl("c1");
                            Timeline.Status status = timeline1.getStatus();
                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline1, anim, "c1", "user", true);
                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline1, anim, "c1", "user", false);
                            }
                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftclick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));

                            //--------------------------------------------------end normal op
                            anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });

            //------------------///mouse-onclick-car2
            car2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent
                ) {
                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        try {
                            mdCity.setCarToControl("c2");
                            fullStopControl(timeline2, anim2, "c2", "user");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
                        //-----------------------------------------------------normal op
                        try {
                            mdCity.setCarToControl("c2");
                            Timeline.Status status = timeline2.getStatus();
                            anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl("c2");
                            Timeline.Status status = timeline2.getStatus();
                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline2, anim2, "c2", "user", true);
                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline2, anim2, "c2", "user", false);
                            }
                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press left click to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));

                            //--------------------------------------------------end normal op
                            anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });
//-------------///mouse-onclick-car1
            car3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        try {
                            mdCity.setCarToControl("c3");
                            fullStopControl(timeline3, anim3, "c3", "user");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
                        try {
                            mdCity.setCarToControl("c3");
                            Timeline.Status status = timeline3.getStatus();

                            //--------------------------------------------------end normal op
                            anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl("c3");
                            Timeline.Status status = timeline3.getStatus();
                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline3, anim3, "c3", "user", true);
                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                fullStopControlWhenDecreaseSpeed(timeline3, anim3, "c3", "user", false);
                            }
                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftClick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));

                            //--------------------------------------------------end normal op
                            anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });

            Scene scene = new Scene(root, 1350, 750, Color.DARKGREEN);

//--------------------------------tableview end
            //--------------------------------------------------------------------keyevent
            scene.setOnKeyPressed(e
                    -> {
                try {
                    // System.out.println("carname:" + mdCity.getCarToControl());
                    if (e.getCode() == KeyCode.UP) {//keyup
                        mdCity.getCarByName(mdCity.getCarToControl()).increaseSpeed(10, new MdVehicleAction("inc", "increase speed by 10 km/hr", "user press arrow up to increase speed", "user", mdCity.getCarToControl()));
//                        if (mdCity.getCarByName("c1").getSpeed() > 0) {
//
//                            timeline1.play();
//                            anim.play();
//                        }
//
//                        if (mdCity.getCarByName("c2").getSpeed() > 0) {
//
//                            timeline2.play();
//                            anim2.play();
//                        }
//                        if (mdCity.getCarByName("c3").getSpeed() > 0) {
//
//                            timeline3.play();
//                            anim3.play();
//                        }

                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
                    }
                    if (e.getCode() == KeyCode.DOWN) {//keydown
                        mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//                        anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());
//                        anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
//                        anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());

                        if (mdCity.getCarByName("c1").getSpeed() == 0) {
                            anim.pause();
                            timeline1.pause();
                        }
                        if (mdCity.getCarByName("c2").getSpeed() == 0) {
                            anim2.pause();
                            timeline2.pause();
                        }
                        if (mdCity.getCarByName("c3").getSpeed() == 0) {
                            anim3.pause();
                            timeline3.pause();
                        }
                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
                    }

                    if (e.getCode() == KeyCode.LEFT) {

                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
                    }
                    if (e.getCode() == KeyCode.RIGHT) {
                        // System.out.println("right");
                        //  weaponRotation.setAngle(Math.min(45, weaponRotation.getAngle() + 2));
                    }
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }
                if (e.getCode() == KeyCode.SPACE) {
                    // System.out.println("space clickeddd");
                    //Animation.Status status = anim.getStatus();
                    Timeline.Status status = timeline1.getStatus();
                    if (status == Timeline.Status.RUNNING
                            && status != Timeline.Status.PAUSED) {
                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, false);

                    } else {
                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, true);

                    }

                }
            }
            );

            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline2.setCycleCount(Timeline.INDEFINITE);
            timeline3.setCycleCount(Timeline.INDEFINITE);
            timeline4.setCycleCount(Timeline.INDEFINITE);

            timeline1.play();
            timeline2.play();
            timeline3.play();
            timeline4.play();
            ///circle clicked
            circ1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // System.out.println("clickkkd on circle");

                    if (timeline1.getStatus() == Timeline.Status.RUNNING) {
                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, false);
                    } else {
                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, true);

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
    TableView<MdCar> tableCar;
    TableView<MdVehicleAction> tableCarRecords1;
    TableView<MdVehicleAction> tableCarRecords2;
    TableView<MdVehicleAction> tableCarRecords3;
    JFXButton btnReturnToStart;
    TextField txtSaveName;
    JFXButton btnSaveToDatabase;

    /**
     * all logic for cars table view
     *
     * @param root
     * @return
     */
    public TableView showTableCar(Group root) {
        //-------------tableviewcreate
        TableColumn<MdCar, String> nameColumn = new TableColumn<>("Car name");
        // nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MdCar, String> column2 = new TableColumn<>("Image Name");
        // column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("imgName"));

        TableColumn<MdCar, Integer> destinationColumn = new TableColumn<>("End Distance From Origin");
        // destinationColumn.setMinWidth(100);
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("distanceFromOrigin"));

        TableColumn<MdCar, Integer> column3 = new TableColumn<>("Break By user");
        // column3.setMinWidth(50);
        column3.setCellValueFactory(new PropertyValueFactory<>("cumulitivebreakMan"));

        TableColumn<MdCar, Integer> column4 = new TableColumn<>("Break By Auto");
        // column4.setMinWidth(50);
        column4.setCellValueFactory(new PropertyValueFactory<>("cumulitivebrakAuto"));

        TableColumn<MdCar, Integer> column5 = new TableColumn<>("Decrease Speed User");
        // column5.setMinWidth(50);
        column5.setCellValueFactory(new PropertyValueFactory<>("cumulitivedecMan"));

        TableColumn<MdCar, Integer> column6 = new TableColumn<>("Decrease Speed Auto");
        //  column6.setMinWidth(50);
        column6.setCellValueFactory(new PropertyValueFactory<>("cumulitivedecAuto"));

        tableCar = new TableView<>();
        tableCar.setItems(getCars());
        tableCar.getColumns().addAll(nameColumn, destinationColumn, column2, column3, column4, column5, column6);
        return tableCar;
        // root.getChildren().add(tableCar);
        //-------------tableviewend
    }

    /**
     * table view to show car records with exact detail why they
     * decrease/increase speed (by user/auto)
     *
     * @param root
     * @return
     */
    public TableView showTableCarRecords(Group root) {
        TableColumn<MdVehicleAction, String> column1 = new TableColumn<>("CarName");
        //column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("carName"));

        TableColumn<MdVehicleAction, String> column3 = new TableColumn<>("Time");
        column3.setMinWidth(100);
        column3.setCellValueFactory(new PropertyValueFactory<>("actionTimeInString"));
//
        TableColumn<MdVehicleAction, String> column2 = new TableColumn<>("Auto/Manual");
        //column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("actionedBy"));

        TableColumn<MdVehicleAction, String> column4 = new TableColumn<>("action Name");
        // column4.setMinWidth(100);
        column4.setCellValueFactory(new PropertyValueFactory<>("nameFriendly"));

        TableColumn<MdVehicleAction, String> column5 = new TableColumn<>("description");
        //column5.setMinWidth(100);
        column5.setCellValueFactory(new PropertyValueFactory<>("description"));

//
        tableCarRecords1 = new TableView<>();
        tableCarRecords1.setItems(getCarRecords());
        tableCarRecords1.getColumns().addAll(column1, column2, column3, column4, column5);
//
        //root.getChildren().add(tableCarRecords1);
        return tableCarRecords1;

    }

    /**
     * for table view of cars
     *
     * @return
     */
    public ObservableList<MdCar> getCars() {
        ObservableList<MdCar> myCars = FXCollections.observableArrayList();
        for (MdCar c : mdCity.getLstCar()) {
            c.calcNumberOfBreaks();
            // System.out.println("number of break" + c.getCumulitivedecMan());
            myCars.add(c);

        }
        return myCars;
    }

    /**
     * obsarvablelist for Tableview
     *
     * @return
     */
    public ObservableList<MdVehicleAction> getCarRecords() {
        ObservableList<MdVehicleAction> CarRecords = FXCollections.observableArrayList();
        for (MdCar c : mdCity.getLstCar()) {
            for (MdVehicleAction vaction : c.getLstVehicleAction()) {
                //-----
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //Or whatever format fits best your needs.

                //--------
                vaction.setActionTimeInString(sdf.format(vaction.getActionTime()));
                CarRecords.add(vaction);
            }
        }

        return CarRecords;
    }

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
    public void stopAllMove(PathTransition anim, PathTransition anim2, PathTransition anim3, Timeline t1, Timeline t2, Timeline t3, MdTimer mdtimer, boolean start) {
        if (mdtimer.getSec() < this.maxTime && start) {
            t1.play();
            t2.play();
            t3.play();
            anim.play();
            anim2.play();
            anim3.play();

        } else {
            t1.pause();
            t2.pause();
            t3.pause();
            anim.pause();
            anim2.pause();
            anim3.pause();

        }

    }

    /**
     * there is a button at the end to return to start page
     *
     * @param event
     * @throws IOException
     */
    public void btnReturnToStartClicked(MouseEvent event) throws IOException {
        // System.out.println("ttttesfsdf gobacktostart");
        Parent vwMainInitialSecondStepParent = FXMLLoader.load(getClass().getResource("VwMainInitialStep02.fxml"));
        Scene vwMainInitialSecondStep = new Scene(vwMainInitialSecondStepParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(vwMainInitialSecondStep);
        window.show();

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
