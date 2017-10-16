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
public class VwCityJavaFx03_mainTest extends Application {

    //------------------allvariable that you use in this view and you should define at start
    private String colorHash;
    private MdCity mdCity;
    private MdTimer mdTimer;
    private Color labelsColor = Color.BLACK;
    private String labelsBackgroudColor = "white";
    private int initialSpeed;
    //-----------variable
    private int maxTime = 20;//maximum seconds of running this app
    private int numberOfCars = 10;
    private ArrayList<String> listofPossibleCar = new ArrayList<>();

    //private HashMap<String, ImageView> hashImageViewCar = new HashMap<>();
    private HashMap<String, ImageView> hashImageViewCar = new HashMap<String, ImageView>();
    private HashMap<String, ImageView> hashImageViewClone = new HashMap<String, ImageView>();
    private ArrayList<ImageView> lstImageViewSchoolSignStart = new ArrayList<>();
    private ArrayList<ImageView> lstImageViewSchoolSignEnd = new ArrayList<>();
    private HashMap<String, Label> hashLabelViewClone = new HashMap<String, Label>();
    private ArrayList<HBox> lstHboxViewClone = new ArrayList<>();
    private HashMap<String, PathTransition> hashPathTransitions = new HashMap<String, PathTransition>();
    private HashMap<String, Timeline> hashTimeline = new HashMap<String, Timeline>();

    public VwCityJavaFx03_mainTest(String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;
    }

    private String chooseImageById(int id) {
        String image = "car-21d811.png";
        int listsize = listofPossibleCar.size();

        while ((id % listsize) > (listsize - 1)) {
            id--;

        }
        image = listofPossibleCar.get(id % listsize);
        return image;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {//--------------------------------newpaternstart------------------------------------------------start new patern
            for (int i = 0; i < numberOfCars; i++) {
                boolean choosroadgoOrback = true; //(i > (numberOfCars / 2)) ? false : true;
                mdCity.addCar(new MdCar(new Location(180, 400), chooseImageById(i), "c" + Integer.toString(i), choosroadgoOrback, i, this.initialSpeed));
            }

// find better location            mdCity.addSchoolSign(new MdSchoolSign("sc1", new Location(800, 100), new Location(300, 100)));
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

                                if (carobj.getSpeed() == 10 && !carobj.isIsParked()) {
                                    fullStopControl(hashPathTransitions.get(carname), carname, "user");
                                } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                    fullStopControl(hashPathTransitions.get(carname), carobj.getName(), "user");

                                }
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
                        new LineTo(900, 600),
                        new ArcTo(200, 200, 0, 600, 400, true, false),
                        new ArcTo(100, 100, 0, 400, 300, true, true), //second line bottom arc
                        new LineTo(600, 200),
                        new ArcTo(180, 100, 0, 550, 0, true, false),//thirs arc
                        new LineTo(100, 0),
                        new ArcTo(90, 0, 0, 50, 50, true, false),//thirs arc

                        new ClosePath()
                    };

//----------------------end
//--------------------------------------draw road black
            Path road = new Path();
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(50);
            road.getElements().addAll(path);
            road.setLayoutX(200);

            Path divider = new Path();
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(4);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(path);
            divider.setLayoutX(200);

            Path roadCargo = new Path();
            roadCargo.setLayoutX(200);

            roadCargo.getElements().addAll(path);

//            Path roadCarreturn = new Path();
//            roadCarreturn.setLayoutX(200);
//            roadCarreturn.getElements().addAll(pathCarReturn);
            //--------------------------------------------------------------------------------end of draw road
            for (MdCar carobj : mdCity.getLstCar()) {
                String carname = carobj.getName();
                PathTransition pathTransobj = new PathTransition();
                //Duration.seconds(mdCity.getLstCar().get(i).convertSpeedToDuration()), mdCity.getLstCar().get(i).isIsRouteToGo() ? roadCargo : roadCarreturn, lstImageViewCar.get(i))
                pathTransobj.setNode(hashImageViewCar.get(carname));
                pathTransobj.setPath(roadCargo);
                pathTransobj.setDuration(new Duration(35000));
                //lstPathTransitions.get(i).setDelay(new Duration(50));
                pathTransobj.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransobj.setInterpolator(Interpolator.LINEAR);
                pathTransobj.setCycleCount(Timeline.INDEFINITE);
                pathTransobj.setAutoReverse(false);
                pathTransobj.setDelay(Duration.seconds(carobj.getDelay()));
                hashPathTransitions.put(carname, pathTransobj);

            }
            //------------------------------------circle for stop all
            Circle circ1 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
            circ1.setLayoutX(400);
            circ1.setLayoutY(200);

            //-----------------end circ1
            //------------------------------------------------------------------------------bluecircleonclick
            circ1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // System.out.println("clickkkd on circle");
                    for (Map.Entry<String, PathTransition> ps : hashPathTransitions.entrySet()) {
                        if (ps.getValue().getStatus() == PathTransition.Status.RUNNING) {
                            ps.getValue().pause();
                        } else {
                            ps.getValue().play();

                        }
                    }

                    for (Map.Entry<String, Timeline> tl : hashTimeline.entrySet()) {
                        if (tl.getValue().getStatus() == Timeline.Status.RUNNING) {
                            tl.getValue().pause();
                        } else {
                            tl.getValue().play();

                        }

                    }

                }

            });

            //--------------------------------------------------------------------------------bluecircleonclickend
            //-----------------------------------add all element
            Group root = new Group();
            root.getChildren().addAll(circ1, road, divider, lblTimer);
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
                Timeline aTimeline = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        mdCity.updateCarLocation(carname, hashImageViewCar.get(carname).getX() + hashImageViewCar.get(carname).getTranslateX(), hashImageViewCar.get(carname).getY() + hashImageViewCar.get(carname).getTranslateY());
                        hashLabelViewClone.get(carname).setText(Integer.toString(carobj.getSpeed()) + " km/hr ");
                        hashImageViewCar.get(carname).setImage(new Image(carobj.getImgName()));
                        if (mdTimer.getSec() > 3) {
                            try {

                                hashPathTransitions.get(carname).setDelay(Duration.seconds(0));
                                ThreadStopAccident ths = new ThreadStopAccident(mdCity);

                                ths.run();
                                hashImageViewCar.get(carname).setImage(new Image(mdCity.getCarByName(carname).getImgName()));

                                if (mdCity.getCarByName(carname).isIsParked()) {
                                    hashPathTransitions.get(carname).pause();
                                    //temp hashTimeline.get(carname).pause();
                                }
                                if (mdCity.getCarByName(carname).getSpeed() == 0) {
                                    hashPathTransitions.get(carname).pause();
                                    //temp hashTimeline.get(carname).pause();
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

//---------------------------------------------------endnewpatern--------------------------------------------------------endnewpatern
            //---------test race start-timelinefinal it is a timeline that we never stop
            Timeline timelinefinal = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    try {

                        //----------------------------just for dbug purposes
                        //----------------------------end of debug
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (mdTimer.getSec() == maxTime) {//--------end of process
                        //stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, false);
                        root.getChildren().clear();//removeAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lblTimer, img_schoolZone_start, img_schoolZone_end);
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
            }));//----//---------test race end-timelinefinal

//--------------------------------tableview end
            timelinefinal.setCycleCount(Timeline.INDEFINITE);
            timelinefinal.play();
            ///circle clicked
            circ1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // System.out.println("clickkkd on circle");

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
        Parent vwMainInitialSecondStepParent = FXMLLoader.load(getClass().getResource("UITestDrive.fxml"));
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
    public void fullStopControl(PathTransition anim, String carname, String ActionBy) throws Exception {
        if (anim.getStatus() == PathTransition.Status.RUNNING) {
            anim.pause();
            mdCity.getCarByName(carname).setSpeed(0, new MdVehicleAction("break", "start from break step01 decrease speed to 0", "user press break that makes full stop", ActionBy, carname));
            mdCity.getCarByName(carname).setIsParked(true, new MdVehicleAction("break", "break full stop step01 handbreak", "user press full stop", ActionBy, carname));

        } else {
            mdCity.getCarByName(carname).setIsParked(false, new MdVehicleAction("breakstart", "start from break step01 handbreak release", "user continue from full stop", ActionBy, carname));
            mdCity.getCarByName(carname).setSpeed(initialSpeed, new MdVehicleAction("breakstart", "start from break step02 increase speed", "user continue from full stop", ActionBy, carname));

            anim.play();
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

    /**
     * @param numberOfCars the numberOfCars to set
     */
    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    /**
     * @param listofPossibleCar the listofPossibleCar to set
     */
    public void setListofPossibleCar(ArrayList<String> listofPossibleCar) {
        this.listofPossibleCar = listofPossibleCar;
    }

    /**
     * @param maxTime the maxTime to set
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

}
