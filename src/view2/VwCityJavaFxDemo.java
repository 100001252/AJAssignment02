/* main javafxclass that all car running in it
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 *
 * @author XC8184
 */
public class VwCityJavaFxDemo extends Application {

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
    //-----------variable
    private int maxTime = 500;//maximum seconds of running this app

    public VwCityJavaFxDemo(String times, String maxtime, String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;
        this.maxTime = Integer.parseInt(maxtime);
        this.demoShowTimes = Integer.parseInt(times);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Generate secure random number
            SecureRandom randomNumber = new SecureRandom();
            int carNumber = 2 + randomNumber.nextInt(5);

//-----------define all cars
            int id;
            for (int i = 0; i < carNumber; i++) {
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
//            mdCity.addCar(new MdCar(new Location(180, 400), "carbluepolice.jpg", "c1", true, 1, this.initialSpeed));
//            mdCity.addCar(new MdCar(new Location(180, 400), "carred.png", "c2", true, 2, this.initialSpeed));
//            mdCity.addCar(new MdCar(new Location(180, 400), "carblue.png", "c3", true, 3, this.initialSpeed));
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
//            ImageView car2 = new ImageView();
//            ImageView car3 = new ImageView();
//            ImageView car1Clone = new ImageView();
//            ImageView car2Clone = new ImageView();
//            ImageView car3Clone = new ImageView();
//            Label car1lblSpeed = new Label("Speed");
//            car1lblSpeed.setTextFill(labelsColor);
//            Label car2lblSpeed = new Label("Speed");
//            car2lblSpeed.setTextFill(labelsColor);
//            Label car3lblSpeed = new Label("Speed");
//            car3lblSpeed.setTextFill(labelsColor);

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
//            car1Clone.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
//            HBox hb1 = new HBox();
//            hb1.getChildren().addAll(car1Clone, car1lblSpeed);
//            hb1.setLayoutY(20);
//
//            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
//            car2.setX(mdCity.getCarByName("c1").getLocation().getX());
//            car2.setY(mdCity.getCarByName("c1").getLocation().getY());
//            car2.setRotate(-90);
//            car2Clone.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
//            HBox hb2 = new HBox();
//            hb2.getChildren().addAll(car2Clone, car2lblSpeed);
//            hb2.setLayoutY(60);
//
//            car3.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
//            car3.setX(mdCity.getCarByName("c3").getLocation().getX());
//            car3.setY(mdCity.getCarByName("c3").getLocation().getY());
//            car3.setRotate(-90);
//            car3Clone.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
//            HBox hb3 = new HBox();
//            hb3.getChildren().addAll(car3Clone, car3lblSpeed);
//            hb3.setLayoutY(100);
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
//            //---------anim2
//            PathTransition anim2 = new PathTransition();
//            anim2.setNode(car2);
//            anim2.setPath(mdCity.getCarByName("c2").isIsRouteToGo() ? roadCargo : roadCarreturn);
//            anim2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//            anim2.setCycleCount(Timeline.INDEFINITE);
//            anim2.setInterpolator(Interpolator.LINEAR);
//            anim2.setDuration(new Duration(mdCity.getCarByName("c2").convertSpeedToDuration()));
//            anim2.setDelay(Duration.seconds(mdCity.getCarByName("c2").getDelay()));
//            //----------anim3
//            PathTransition anim3 = new PathTransition();
//            anim3.setNode(car3);
//            anim3.setPath(mdCity.getCarByName("c3").isIsRouteToGo() ? roadCargo : roadCarreturn);
//            anim3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//            anim3.setCycleCount(Timeline.INDEFINITE);
//            anim3.setInterpolator(Interpolator.LINEAR);
//            anim3.setDuration(new Duration(mdCity.getCarByName("c3").convertSpeedToDuration()));
//            anim3.setDelay(Duration.seconds(mdCity.getCarByName("c3").getDelay()));

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
            
//            anim.play();
//            anim2.play();
//            anim3.play();

            //---------test race start-timeline1
            int ti = 0, timelineId;
            for (ImageView aCar : cars) {
                timelineId = ti + 1;
                String ID = Integer.toString(timelineId);
                String carname = "c" + ID;
                PathTransition anim0 = anims.get(ti);
                Timeline aTimeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        mdCity.updateCarLocation(carname, aCar.getX() + aCar.getTranslateX(), aCar.getY() + aCar.getTranslateY());
                        if (mdTimer.getSec() > 3) {
                            try {

                                anim0.setDelay(Duration.seconds(0));
                                ThreadStopAccident ths = new ThreadStopAccident(mdCity);

                                ths.run();
                                aCar.setImage(new Image(mdCity.getCarByName(carname).getImgName()));

                                if (mdCity.getCarByName(carname).isIsParked()) {
                                    anim0.pause();
                                }
                                if (mdCity.getCarByName(carname).getSpeed() == 0) {
                                    anim0.pause();
                                } else {
                                    //  anim.playFromStart();
                                    anim0.setRate(mdCity.getCarByName(carname).convertSpeedToRate());

                                }

                            } catch (Exception ex2) {

                                ex2.printStackTrace();
                            }

                        }
                    }
                }));
                ti++;
                timelines.add(aTimeline);
            }

//            Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent t) {
//                    mdCity.updateCarLocation("c1", car1.getX() + car1.getTranslateX(), car1.getY() + car1.getTranslateY());
//                    if (mdTimer.getSec() > 3) {
//                        try {
//
//                            anim.setDelay(Duration.seconds(0));
//                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
//
//                            ths.run();
//                            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
//
//                            if (mdCity.getCarByName("c1").isIsParked()) {
//                                anim.pause();
//                            }
//                            if (mdCity.getCarByName("c1").getSpeed() == 0) {
//                                anim.pause();
//                            } else {
//                                //  anim.playFromStart();
//                                anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());
//
//                            }
//
//                        } catch (Exception ex2) {
//
//                            ex2.printStackTrace();
//                        }
//
//                    }
//                }
//            }));//----//---------test race end-timeline1
//
//            //---------test race start-timeline2
//            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent t) {
//                    mdCity.updateCarLocation("c2", car2.getX() + car2.getTranslateX(), car2.getY() + car2.getTranslateY());
//                    if (mdTimer.getSec() > 3) {
//                        try {
//
//                            anim2.setDelay(Duration.seconds(0));
//                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
//                            ths.run();
//                            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
//
//                            if (mdCity.getCarByName("c2").isIsParked()) {
//                                anim2.pause();
//                            }
//
//                            if (mdCity.getCarByName("c2").getSpeed() == 0) {
//                                anim2.pause();
//                            } else {
//                                anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
//                            }
//
//                        } catch (Exception ex2) {
//                            // System.out.println("searchhhhhhfor23424234");
//                            ex2.printStackTrace();
//                        }
//
//                    }
//                }
//            }));//----//---------test race end-timeline2
//
//            //---------test race start-timeline3
//            Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent t) {
//                    //mdCity.updateCarLocation("c3", car3.getTranslateX(), car3.getTranslateY());
//                    mdCity.updateCarLocation("c3", car3.getX() + car3.getTranslateX(), car3.getY() + car3.getTranslateY());
//                    if (mdTimer.getSec() > 3) {
//                        try {
//
//                            anim3.setDelay(Duration.seconds(0));
//                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
//                            ths.run();
//                            car3.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
//                            if (mdCity.getCarByName("c3").isIsParked()) {
//                                anim3.pause();
//                            }
//                            if (mdCity.getCarByName("c3").getSpeed() == 0) {
//                                anim3.pause();
//                            } else {
//                                //  anim.playFromStart();
//                                anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
//                            }
//
//                        } catch (Exception ex2) {
//                            // System.out.println("searchhhhhhfor23424234");
//                            ex2.printStackTrace();
//                        }
//
//                    }
//                }
//            }));
            //----//---------test race end-timeline3
            //---------test race start-timeline4 it is a timeline that we never stop
            Timeline timeline4;//----//---------test race end-timeline4
            timeline4 = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
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
//                        car1lblSpeed.setText(Integer.toString(mdCity.getCarByName("c1").getSpeed()) + " km/hr");
//                        car2lblSpeed.setText(Integer.toString(mdCity.getCarByName("c2").getSpeed()) + " km/hr");
//                        car3lblSpeed.setText(Integer.toString(mdCity.getCarByName("c3").getSpeed()) + " km/hr");
//----------------------------just for dbug purposes

//----------------------------end of debug
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (mdTimer.getSec() == maxTime) {//--------end of process
                        System.out.println("Finished");
                        int sum = 0;
                        
                        final CategoryAxis xAxis = new CategoryAxis();
                        final NumberAxis yAxis = new NumberAxis();
                        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
                        final PieChart pieChart = new PieChart();
                        final Label pieDataLabel = new Label("");
                        
                        pieDataLabel.setTextFill(Color.ANTIQUEWHITE);
                        pieDataLabel.setStyle("-fx-font: 16 arial;");
                                                
                        xAxis.setLabel("Car Name");
                        yAxis.setLabel("Control Times");

                        XYChart.Series userSeries = new XYChart.Series();
                        userSeries.setName("User");
                        XYChart.Series systemSeries = new XYChart.Series();
                        systemSeries.setName("Auto");
                        
                        for(MdCar pieCar: mdCity.getLstCar()){
                            sum += pieCar.getLstVehicleAction().size();
                        }
                        
                        for(MdCar pieCar: mdCity.getLstCar()){
                            String name = pieCar.getName();int actionLstSize = pieCar.getLstVehicleAction().size();
                            userSeries.getData().add(new XYChart.Data(name,actionLstSize));
                            systemSeries.getData().add(new XYChart.Data(name,0));
                            int per = actionLstSize*100/sum;
                            pieChart.getData().add(new PieChart.Data(name, per));
                            
                        }
                        
                        barChart.getData().addAll(userSeries, systemSeries);
                        pieChart.getData().stream().forEach((data) -> {
                            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                                pieDataLabel.setTranslateX(e.getSceneX());
                                pieDataLabel.setTranslateY(e.getSceneY());
                                pieDataLabel.setText(String.valueOf(data.getPieValue())
                                        +"%");
                                
                            });
                        });
                                  
                        HBox chartBox = new HBox();
                        chartBox.getChildren().addAll(barChart,pieChart);
                        Scene aScene = new Scene(new Group(),1000,500);
                        ((Group) aScene.getRoot()).getChildren().addAll(chartBox,pieDataLabel);
                        primaryStage.setScene(aScene);
                        primaryStage.show();
                        
                    }
                    if (mdTimer.getSec() <= maxTime) {
                        lblTimer.setText("Timer:" + mdTimer.getSec());
                    }

                }
            }));

            ///mouse-onclick-car1
            int ei = 0;
            int mouseActId;
            for (ImageView aCar : cars) {
                mouseActId = ei + 1;
                String ID = Integer.toString(mouseActId);
                String carname = "c" + ID;
                Timeline timeline1 = timelines.get(ei);
                PathTransition anim0 = anims.get(ei);
                aCar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent
                    ) {
                        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                            try {
                                mdCity.setCarToControl(carname);
                                fullStopControl(timeline1, anim0, carname, "user");

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
                            //-----------------------------------------------------normal op
                            try {

                                mdCity.setCarToControl(carname);
                                Timeline.Status status = timeline1.getStatus();
                                //--------------------------------------------------end normal op
                                anim0.setRate(mdCity.getCarByName(carname).convertSpeedToRate());

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }//end middle button mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            //-----------------------------------------------------normal op
                            try {

                                mdCity.setCarToControl(carname);
                                Timeline.Status status = timeline1.getStatus();
                                if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                    fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", true);
                                } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
                                    fullStopControlWhenDecreaseSpeed(timeline1, anim0, carname, "user", false);
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
                ei++;
            }

//            car1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent
//                ) {
//                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
//                        try {
//                            mdCity.setCarToControl("c1");
//                            fullStopControl(timeline1, anim, "c1", "user");
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
//                        //-----------------------------------------------------normal op
//                        try {
//
//                            mdCity.setCarToControl("c1");
//                            Timeline.Status status = timeline1.getStatus();
//                            //--------------------------------------------------end normal op
//                            anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }//end middle button mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                        //-----------------------------------------------------normal op
//                        try {
//
//                            mdCity.setCarToControl("c1");
//                            Timeline.Status status = timeline1.getStatus();
//                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline1, anim, "c1", "user", true);
//                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline1, anim, "c1", "user", false);
//                            }
//                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftclick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//
//                            //--------------------------------------------------end normal op
//                            anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//                }
//            });
//
//            //------------------///mouse-onclick-car2
//            car2.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent
//                ) {
//                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
//                        try {
//                            mdCity.setCarToControl("c2");
//                            fullStopControl(timeline2, anim2, "c2", "user");
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
//                        //-----------------------------------------------------normal op
//                        try {
//                            mdCity.setCarToControl("c2");
//                            Timeline.Status status = timeline2.getStatus();
//                            anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                        //-----------------------------------------------------normal op
//                        try {
//
//                            mdCity.setCarToControl("c2");
//                            Timeline.Status status = timeline2.getStatus();
//                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline2, anim2, "c2", "user", true);
//                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline2, anim2, "c2", "user", false);
//                            }
//                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press left click to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//
//                            //--------------------------------------------------end normal op
//                            anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//                }
//            });
////-------------///mouse-onclick-car1
//            car3.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
//                        try {
//                            mdCity.setCarToControl("c3");
//                            fullStopControl(timeline3, anim3, "c3", "user");
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    if (mouseEvent.getButton().equals(MouseButton.MIDDLE)) {
//                        try {
//                            mdCity.setCarToControl("c3");
//                            Timeline.Status status = timeline3.getStatus();
//
//                            //--------------------------------------------------end normal op
//                            anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//
//                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                        //-----------------------------------------------------normal op
//                        try {
//
//                            mdCity.setCarToControl("c3");
//                            Timeline.Status status = timeline3.getStatus();
//                            if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && !mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline3, anim3, "c3", "user", true);
//                            } else if (mdCity.getCarByName(mdCity.getCarToControl()).getSpeed() == 10 && mdCity.getCarByName(mdCity.getCarToControl()).isIsParked()) {
//                                fullStopControlWhenDecreaseSpeed(timeline3, anim3, "c3", "user", false);
//                            }
//                            mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press leftClick to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
//
//                            //--------------------------------------------------end normal op
//                            anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//
//                    }
//                }
//            });
            Scene scene = new Scene(root, 1350, 750, Color.DARKGREEN);

//--------------------------------tableview end
            //--------------------------------------------------------------------keyevent
//            scene.setOnKeyPressed(e
//                    -> {
//                try {
//                    // System.out.println("carname:" + mdCity.getCarToControl());
//                    if (e.getCode() == KeyCode.UP) {//keyup
//                        mdCity.getCarByName(mdCity.getCarToControl()).increaseSpeed(10, new MdVehicleAction("inc", "increase speed by 10 km/hr", "user press arrow up to increase speed", "user", mdCity.getCarToControl()));
//
//
//                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
//                    }
//                    if (e.getCode() == KeyCode.DOWN) {//keydown
//                        mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10, new MdVehicleAction("dec", "Decrease speed", "user press arrowdown to decrease speed by 10km/hr", "user", mdCity.getCarToControl()));
////                        anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());
////                        anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
////                        anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
//
//                        if (mdCity.getCarByName("c1").getSpeed() == 0) {
//                            anim.pause();
//                            timeline1.pause();
//                        }
//                        if (mdCity.getCarByName("c2").getSpeed() == 0) {
//                            anim2.pause();
//                            timeline2.pause();
//                        }
//                        if (mdCity.getCarByName("c3").getSpeed() == 0) {
//                            anim3.pause();
//                            timeline3.pause();
//                        }
//                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
//                    }
//
//                    if (e.getCode() == KeyCode.LEFT) {
//
//                        //weaponRotation.setAngle(Math.max(-45, weaponRotation.getAngle() - 2));
//                    }
//                    if (e.getCode() == KeyCode.RIGHT) {
//                        // System.out.println("right");
//                        //  weaponRotation.setAngle(Math.min(45, weaponRotation.getAngle() + 2));
//                    }
//                } catch (Exception ex3) {
//                    ex3.printStackTrace();
//                }
//                if (e.getCode() == KeyCode.SPACE) {
//                    // System.out.println("space clickeddd");
//                    //Animation.Status status = anim.getStatus();
//                    Timeline.Status status = timeline1.getStatus();
//                    if (status == Timeline.Status.RUNNING
//                            && status != Timeline.Status.PAUSED) {
//                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, false);
//
//                    } else {
//                        stopAllMove(anim, anim2, anim3, timeline1, timeline2, timeline3, mdTimer, true);
//
//                    }
//
//                }
//            }
//            );
            for (Timeline aLine : timelines) {
                aLine.setCycleCount(Timeline.INDEFINITE);
                aLine.play();
            }
//            timeline1.setCycleCount(Timeline.INDEFINITE);
//            timeline2.setCycleCount(Timeline.INDEFINITE);
//            timeline3.setCycleCount(Timeline.INDEFINITE);
            timeline4.setCycleCount(Timeline.INDEFINITE);

//            timeline1.play();
//            timeline2.play();
//            timeline3.play();
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

