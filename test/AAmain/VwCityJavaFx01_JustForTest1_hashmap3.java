package AAmain;

/* main javafxclass that all car running in it
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import view.*;
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
import helper02.ThreadUpdateCar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
public class VwCityJavaFx01_JustForTest1_hashmap3 extends Application {

    //------------------allvariable that you use in this view and you should define at start
    private String colorHash;
    private MdCity mdCity;
    private MdTimer mdTimer;
    private Color labelsColor = Color.BLACK;
    private String labelsBackgroudColor = "white";
    private int initialSpeed;
    private int numberOfCars = 20; //46;
    //-----------variable
    private int maxTime = 50;//maximum seconds of running this app
    //private HashMap<String, ImageView> hashImageViewCar = new HashMap<>();
    private HashMap<String, ImageView> hashImageViewCar = new HashMap<String, ImageView>();
    private HashMap<String, ImageView> hashImageViewClone = new HashMap<String, ImageView>();
    private ArrayList<ImageView> lstImageViewSchoolSignStart = new ArrayList<>();
    private ArrayList<ImageView> lstImageViewSchoolSignEnd = new ArrayList<>();
    private HashMap<String, Label> hashLabelViewClone = new HashMap<String, Label>();
    private ArrayList<HBox> lstHboxViewClone = new ArrayList<>();
    private HashMap<String, PathTransition> hashPathTransitions = new HashMap<String, PathTransition>();
    private HashMap<String, Timeline> hashTimeline = new HashMap<String, Timeline>();

    public VwCityJavaFx01_JustForTest1_hashmap3(String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;
    }

    public VwCityJavaFx01_JustForTest1_hashmap3() {
        this.colorHash = "#055b08";
        this.mdCity = new MdCity();
        this.mdTimer = new MdTimer();
        this.initialSpeed = 100;
    }

    private String chooseImageById(int id) {
        String image = "car-21d811.png";
        if (id % 7 == 0) {
            image = "car-030dbf.png";
        } else if (id % 7 == 1) {
            image = "car-043d01.png";
        } else if (id % 7 == 2) {
            image = "car-67dbc9.png";
        } else if (id % 7 == 3) {
            image = "car-578eed.png";
        } else if (id % 7 == 4) {
            image = "car-822d00.png";
        } else if (id % 7 == 5) {
            image = "car-5906e8.png";
        } else if (id % 7 == 6) {
            image = "car-999999.png";
        }
        return image;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < numberOfCars; i++) {
            mdCity.addCar(new MdCar(new Location(180, 400), chooseImageById(i), "c" + Integer.toString(i), true, i, this.initialSpeed));
        }
//        for (int i = numberOfCars / 2; i < numberOfCars; i++) {
//            mdCity.addCar(new MdCar(new Location(180, 400), chooseImageById(i), "c" + Integer.toString(i), false, i, this.initialSpeed));
//        }
        mdCity.addSchoolSign(new MdSchoolSign("sc1", new Location(800, 100), new Location(300, 100)));

        //for (MdCar carobj : mdCity.getLstCar()) {
        int layoutyLabel = 0;
        for (MdCar carobj : mdCity.getLstCar()) {
            layoutyLabel++;

            final String carname = carobj.getName();
            hashImageViewCar.put(carname, new ImageView(new Image(carobj.getImgName())));
            hashImageViewCar.get(carname).setX(carobj.getLocation().getX());
            hashImageViewCar.get(carname).setY(carobj.getLocation().getY());
            hashImageViewCar.get(carname).setRotate(-90);

            //
            hashImageViewClone.put(carname, new ImageView(new Image(carobj.getImgName())));
            hashLabelViewClone.put(carname, new Label("speed for" + carobj.getName()));
            hashLabelViewClone.get(carname).setTextFill(labelsColor);
            HBox hboxobj = new HBox();
            hboxobj.setLayoutY(layoutyLabel * 20);
            hboxobj.getChildren().addAll(hashImageViewClone.get(carname), hashLabelViewClone.get(carname));
            lstHboxViewClone.add(hboxobj);

            //---------------------------------------------------------------------------------------onclick event for each car
            hashImageViewCar.get(carname).setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent
                ) {

                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        try {
                            mdCity.setCarToControl(carname);
                            fullStopControl(hashPathTransitions.get(carname), carname, "user");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
                        //-----------------------------------------------------normal op
                        try {
                            mdCity.setCarToControl(carname);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl(carname);
//                            if (mdCity.getLstCar().get(ID).getSpeed() == 10 && !mdCity.getLstCar().get(ID).isIsParked()) {
//                                fullStopControl(lstPathTransitions.get(ID), mdCity.getLstCar().get(ID).getName(), "user");
//                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControl(lstPathTransitions.get(ID), mdCity.getLstCar().get(ID).getName(), "user");
//                            }
                            mdCity.getCarByName(carname).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press left click to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));

                            //--------------------------------------------------end normal op
                            // anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });

            //----------------------------------------------------------------------------------------onclick event end
        }
        for (int i = 0; i < mdCity.getLstSchoolSign().size(); i++) {

            lstImageViewSchoolSignStart.add(new ImageView(new Image(mdCity.getLstSchoolSign().get(i).getImgSchoolStart())));
            lstImageViewSchoolSignStart.get(i).setX(mdCity.getLstSchoolSign().get(i).getLocationSchoolStart().getX());
            lstImageViewSchoolSignStart.get(i).setY(mdCity.getLstSchoolSign().get(i).getLocationSchoolStart().getY());

            lstImageViewSchoolSignEnd.add(new ImageView(new Image(mdCity.getLstSchoolSign().get(i).getImgSchoolEnd())));
            lstImageViewSchoolSignEnd.get(i).setX(mdCity.getLstSchoolSign().get(i).getLocationSchoolEnd().getX());
            lstImageViewSchoolSignEnd.get(i).setY(mdCity.getLstSchoolSign().get(i).getLocationSchoolEnd().getY());
        }

        Label lblTimer = new Label("Timer");
        lblTimer.setTextFill(labelsColor);
        lblTimer.setStyle("-fx-background-color: " + labelsBackgroudColor + ";");
        lblTimer.setLayoutX(300);
        lblTimer.setLayoutY(-50);
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
//----------------------end

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

        Path roadCargo = new Path();
        roadCargo.setLayoutX(200);
        roadCargo.getElements().addAll(pathCarGo);

        Path roadCarreturn = new Path();
        roadCarreturn.setLayoutX(200);
        roadCarreturn.getElements().addAll(pathCarReturn);

        //--------------------------------------------------------------------------------end of draw road
        for (MdCar carobj : mdCity.getLstCar()) {
            String carname = carobj.getName();
            PathTransition pathTransobj = new PathTransition();
            //Duration.seconds(mdCity.getLstCar().get(i).convertSpeedToDuration()), mdCity.getLstCar().get(i).isIsRouteToGo() ? roadCargo : roadCarreturn, lstImageViewCar.get(i))
            pathTransobj.setNode(hashImageViewCar.get(carname));
            pathTransobj.setPath(carobj.isIsRouteToGo() ? roadCargo : roadCarreturn);
            pathTransobj.setDuration(new Duration(25000));
            //lstPathTransitions.get(i).setDelay(new Duration(50));
            pathTransobj.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransobj.setInterpolator(Interpolator.LINEAR);
            pathTransobj.setCycleCount(Timeline.INDEFINITE);
            pathTransobj.setDelay(Duration.seconds(carobj.getDelay()));
            hashPathTransitions.put(carname, pathTransobj);

        }
        //-----------------------------------add all element
        Group root = new Group();
        root.getChildren().addAll(road, divider, lblTimer);
        //hbbox
        for (HBox hbobj : lstHboxViewClone) {
            root.getChildren().add(hbobj);
        }
        //imageviewcars
        for (Map.Entry<String, ImageView> imgCar : hashImageViewCar.entrySet()) {
            root.getChildren().add(imgCar.getValue());
        }
        //schoolzone images
        for (int i = 0; i < lstImageViewSchoolSignStart.size(); i++) {
            root.getChildren().add(lstImageViewSchoolSignStart.get(i));
            root.getChildren().add(lstImageViewSchoolSignEnd.get(i));
        }
        root.setTranslateX(50);
        root.setTranslateY(50);

        //-------------------------------------------------------------------------add timeline
//        for (int i = 0; i < mdCity.getLstCar().size(); i++) {
//            final int ID = i;
//            MdCar carobj = mdCity.getLstCar().get(ID);
//            lstTimeline.add(new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent t) {
//
//                    mdCity.updateCarLocation(carobj.getName(), lstImageViewCar.get(ID).getX() + lstImageViewCar.get(ID).getTranslateX(), lstImageViewCar.get(ID).getY() + lstImageViewCar.get(ID).getTranslateY());
//                    if (mdTimer.getSec() > 3) {
//                        try {
//
//                            lstPathTransitions.get(ID).setDelay(Duration.seconds(0));
//
//                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
//
//                            ths.run();
//
//                            // DebugLog.appendData2("imagenameforc1>>>> " + mdCity.getCarByName("c1").getImgName());
//                            if (carobj.isIsParked()) {
//                                lstPathTransitions.get(ID).pause();
//                            }
//                            if (mdCity.getCarByName("c1").getSpeed() == 0) {
//                                lstPathTransitions.get(ID).pause();
//                            } else {
//                                //  anim.playFromStart();
//                                lstPathTransitions.get(ID).setRate(mdCity.getLstCar().get(ID).convertSpeedToRate());
//
//                            }
//                        } catch (Exception ex2) {
//                            // System.out.println("searchhhhhhfor23424234");
//                            ex2.printStackTrace();
//                        }
//
//                    }
//                }
//            })));//end addsingletimeline
//        }
        //-------------------------------------------------------------------------end add timeline
        Scene scene = new Scene(root, 1350, 750, Color.DARKGREEN);
        primaryStage.setTitle(
                "PathTransition Test");
        primaryStage.setScene(scene);
        scene.setFill(Color.web(this.colorHash));
        primaryStage.show();

        //---------------------------------------------------------------------------------------only one timline
        for (MdCar carobj : mdCity.getLstCar()) {
            final String carname = carobj.getName();
            Timeline aTimeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    mdCity.updateCarLocation(carname, hashImageViewCar.get(carname).getX() + hashImageViewCar.get(carname).getTranslateX(), hashImageViewCar.get(carname).getY() + hashImageViewCar.get(carname).getTranslateY());
                    hashLabelViewClone.get(carname).setText(Integer.toString(carobj.getSpeed()) + " km/hr");
                    hashImageViewCar.get(carname).setImage(new Image(carobj.getImgName()));
                    if (mdTimer.getSec() > 3) {
                        try {

                            hashPathTransitions.get(carname).setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);

                            ths.run();
                            hashImageViewCar.get(carname).setImage(new Image(mdCity.getCarByName(carname).getImgName()));

                            if (mdCity.getCarByName(carname).isIsParked()) {
                                hashPathTransitions.get(carname).pause();
                            }
                            if (mdCity.getCarByName(carname).getSpeed() == 0) {
                                hashPathTransitions.get(carname).pause();
                            } else {
                                //  anim.playFromStart();
                                hashPathTransitions.get(carname).setRate(mdCity.getCarByName(carname).convertSpeedToRate());

                            }

                        } catch (Exception ex2) {

                            ex2.printStackTrace();
                        }

                    }
                }
            }));
            aTimeline.setCycleCount(Timeline.INDEFINITE);
            hashTimeline.put(carobj.getName(), aTimeline);
        }
//----//---------test race end-timeline1

        //-----------------------------------------------------------------------------------------only one timline end
        //----------------------------onclickevent
        //----------------------------onclickevent
        //play all animation
        for (Map.Entry<String, Timeline> tl : hashTimeline.entrySet()) {
            tl.getValue().play();
        }
        for (Map.Entry<String, PathTransition> ps : hashPathTransitions.entrySet()) {
            ps.getValue().play();
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
    public void fullStopControl(PathTransition anim, String carname, String ActionBy) throws Exception {
        if (anim.getStatus() == PathTransition.Status.RUNNING) {
            anim.pause();
            mdCity.getCarByName(carname).setIsParked(true, new MdVehicleAction("break", "break full stop", "user press full stop", ActionBy, carname));

        } else {
            mdCity.getCarByName(carname).setIsParked(false, new MdVehicleAction("breakstart", "start from break step01 handbreak release", "user continue from full stop", ActionBy, carname));
            mdCity.getCarByName(carname).setSpeed(initialSpeed, new MdVehicleAction("breakstart", "start from break step02 increase speed", "user continue from full stop", ActionBy, carname));

            anim.play();
        }

    }

}
