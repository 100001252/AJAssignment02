
import com.sun.scenario.effect.impl.prism.PrImage;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import helper.Location;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.text.Font;

import javafx.stage.Stage;

import javafx.util.Duration;
import model.MdCar;
import model.*;
import javafx.scene.control.TableView;

class ThreadStopAccident extends Thread {

    private MdCity mdCity;

    public ThreadStopAccident(MdCity mdcity) {

        this.mdCity = mdcity;
    }

    public void run() {
        this.mdCity.stopAccidentOnRoute();
//        try {
//            Thread.sleep(100);
//        } catch (Exception e) {
//        }

    }

}

public class PathDemoJavaFx_ver07 extends Application {
//make it button and generic

    private TableView table = new TableView();

    public void start(Stage primaryStage) {

        try {

            MdCity mdCity = new MdCity();
            MdTimer mdTimer = new MdTimer();
//-----------define all cars
            mdCity.addCar(new MdCar(new Location(100, 400), "caryellow.jpg", "c1", true, 1, 100));
            mdCity.addCar(new MdCar(new Location(100, 400), "carred.jpg", "c2", true, 2, 100));
            mdCity.addCar(new MdCar(new Location(100, 400), "carblue.png", "c3", true, 3, 100));

            ImageView car1 = new ImageView();
            ImageView car2 = new ImageView();
            ImageView car3 = new ImageView();

            Label lbl1 = new Label("car1 speed");
            lbl1.setTextFill(Color.BLACK);
            lbl1.setLayoutX(300);
            lbl1.setLayoutY(260);

            Label lbl2 = new Label("car2 speed");
            lbl2.setTextFill(Color.BLUE);
            lbl2.setLayoutX(300);
            lbl2.setLayoutY(300);

            Label lbl3 = new Label("car3 speed");
            lbl3.setTextFill(Color.WHITE);
            lbl3.setLayoutX(300);
            lbl3.setLayoutY(360);

            //-------------end defining cars
//--------------------------------------------------------------------------------------defining car image or button
            Circle circ1 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
            //car.setImage(new Image("file:res/car.gif"));
            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
            car1.setX(mdCity.getCarByName("c1").getLocation().getX());
            car1.setY(mdCity.getCarByName("c1").getLocation().getY());
            car1.setRotate(-90);

            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
            car2.setX(mdCity.getCarByName("c1").getLocation().getX());
            car2.setY(mdCity.getCarByName("c1").getLocation().getY());
            car2.setRotate(-90);

            car3.setImage(new Image(mdCity.getCarByName("c3").getImgName()));
            car3.setX(mdCity.getCarByName("c3").getLocation().getX());
            car3.setY(mdCity.getCarByName("c3").getLocation().getY());
            car3.setRotate(-90);

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
            road.setStroke(Color.BLACK);
            road.setStrokeWidth(120);
            road.getElements().addAll(path);

            Path divider = new Path();
            divider.setStroke(Color.WHITE);
            divider.setStrokeWidth(2);
            divider.getStrokeDashArray().addAll(10.0, 10.0);
            divider.getElements().addAll(path);

            //-----------------end of draw road
            //----------------------------------------------------create all animation with different path
            Path roadCargo = new Path();
            roadCargo.getElements().addAll(pathCarGo);

            Path roadCarreturn = new Path();
            roadCarreturn.getElements().addAll(pathCarReturn);

            MdCar.setId_autoGen(0);
            PathTransition anim = new PathTransition();
            //-------anim
            anim.setNode(car1);
            anim.setPath(mdCity.getCarByName("c1").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setInterpolator(Interpolator.LINEAR);
            anim.setCycleCount(Timeline.INDEFINITE);
            anim.setDuration(new Duration(mdCity.getCarByName("c1").convertSpeedToDuration()));
            anim.setDelay(Duration.seconds(mdCity.getCarByName("c1").getDelay()));
            //---------anim2
            PathTransition anim2 = new PathTransition();
            anim2.setNode(car2);
            anim2.setPath(mdCity.getCarByName("c2").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            anim2.setCycleCount(Timeline.INDEFINITE);
            anim2.setInterpolator(Interpolator.LINEAR);
            anim2.setDuration(new Duration(mdCity.getCarByName("c2").convertSpeedToDuration()));
            anim2.setDelay(Duration.seconds(mdCity.getCarByName("c2").getDelay()));
            //----------anim3
            PathTransition anim3 = new PathTransition();
            anim3.setNode(car3);
            anim3.setPath(mdCity.getCarByName("c3").isIsRouteToGo() ? roadCargo : roadCarreturn);
            anim3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            anim3.setCycleCount(Timeline.INDEFINITE);
            anim3.setInterpolator(Interpolator.LINEAR);
            anim3.setDuration(new Duration(mdCity.getCarByName("c3").convertSpeedToDuration()));
            anim3.setDelay(Duration.seconds(mdCity.getCarByName("c3").getDelay()));

            PathTransition anim4 = new PathTransition();
            Group root = new Group();
            //root.getChildren().addAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lbl3, table);
            root.getChildren().addAll(road, divider, car1, car2, car3, circ1, lbl1, lbl2, lbl3);
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
                    mdCity.updateCarLocation("c1", car1.getTranslateX(), car1.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim.setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
                            ths.run();

                            if (mdCity.getCarByName("c1").isIsParked()) {
                                anim.pause();
                            }
                            if (mdCity.getCarByName("c1").getSpeed() == 0) {
                                anim.pause();
                            } else {
                                //  anim.playFromStart();
                                anim.setRate(mdCity.getCarByName("c1").convertSpeedToRate());

                            }
                            lbl1.setText("carname=c1|speed=" + Integer.toString(mdCity.getCarByName("c1").getSpeed()) + "|x=" + car1.getTranslateX() + "|dist=" + mdCity.getCarByName("c1").getDistanceFromOrigin() + "|rate" + anim.getRate() + "\n ttt"
                                    + "|x=" + car1.getTranslateX() + "|y=" + car1.getTranslateY() + "|time" + anim.getCurrentTime() + "\n "
                                    + "|orientation:" + anim.getOrientation());

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
                    mdCity.updateCarLocation("c2", car2.getTranslateX(), car2.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim2.setDelay(Duration.seconds(0));
                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
                            ths.run();

                            if (mdCity.getCarByName("c2").isIsParked()) {
                                anim2.pause();
                            }

                            if (mdCity.getCarByName("c2").getSpeed() == 0) {
                                anim2.pause();
                            } else {
                                //  anim.playFromStart();
                                anim2.setRate(mdCity.getCarByName("c2").convertSpeedToRate());
                            }
                            lbl2.setText("carname=c2|speed=" + Integer.toString(mdCity.getCarByName("c2").getSpeed()) + "|x=" + car2.getTranslateX() + "|y=" + car2.getTranslateY() + " + x=" + car1.getTranslateX() + "|dist=" + mdCity.getCarByName("c2").getDistanceFromOrigin() + "|rate" + anim2.getRate());
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
                    mdCity.updateCarLocation("c3", car3.getTranslateX(), car3.getTranslateY());
                    if (mdTimer.getSec() > 3) {
                        try {

                            anim3.setDelay(Duration.seconds(0));
//                            ThreadStopAccident ths = new ThreadStopAccident(mdCity);
//                            ths.run();  noneed as it is happening in first one
                            if (mdCity.getCarByName("c3").isIsParked()) {
                                anim3.pause();
                            }
                            if (mdCity.getCarByName("c3").getSpeed() == 0) {
                                anim3.pause();
                            } else {
                                //  anim.playFromStart();
                                anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
                            }
                            lbl3.setText("carname=c3|speed=" + Integer.toString(mdCity.getCarByName("c3").getSpeed()) + "|x=" + car3.getTranslateX() + "|y=" + car3.getTranslateY() + "|dist=" + mdCity.getCarByName("c3").getDistanceFromOrigin() + "|rate" + anim3.getRate());
                        } catch (Exception ex2) {
                            // System.out.println("searchhhhhhfor23424234");
                            ex2.printStackTrace();
                        }

                    }
                }
            }));//----//---------test race end-timeline3
            ///mouse-onclick-car1
            car1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent
                ) {
                    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        try {

                            if (timeline1.getStatus() == Timeline.Status.RUNNING) {
                                timeline1.pause();
                                anim.pause();
                                mdCity.getCarByName("c1").setIsParked(true);

                            } else {
                                mdCity.getCarByName("c1").setIsParked(false);

                                timeline1.play();
                                anim.play();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {

                            mdCity.setCarToControl("c1");
                            Timeline.Status status = timeline1.getStatus();

//                            if (mdCity.getCarByName("c1").getSpeed() - 10 >= 0) {
//                                mdCity.getCarByName("c1").decreaseSpeed(10);
//                            }
//                            if (mdCity.getCarByName("c1").getSpeed() == 0) {
//                                timeline1.pause();
//                                anim.pause();
//                            }
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
                            if (timeline2.getStatus() == Timeline.Status.RUNNING) {
                                timeline2.pause();
                                anim2.pause();
                                mdCity.getCarByName("c2").setIsParked(true);

                            } else {
                                mdCity.getCarByName("c2").setIsParked(false);

                                timeline2.play();
                                anim2.play();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        //-----------------------------------------------------normal op
                        try {
                            mdCity.setCarToControl("c2");
                            Timeline.Status status = timeline2.getStatus();

//                            if (mdCity.getCarByName("c2").getSpeed() - 10 >= 0) {
//                                mdCity.getCarByName("c2").decreaseSpeed(10);
//                            }
//
//                            if (mdCity.getCarByName("c2").getSpeed() == 0) {
//                                timeline2.pause();
//                                anim2.pause();
//                            }
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

                            if (timeline3.getStatus() == Timeline.Status.RUNNING) {
                                timeline3.pause();
                                anim3.pause();
                                mdCity.getCarByName("c3").setIsParked(true);

                            } else {
                                mdCity.getCarByName("c3").setIsParked(false);

                                timeline3.play();
                                anim3.play();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        try {
                            mdCity.setCarToControl("c3");
                            Timeline.Status status = timeline3.getStatus();

//                            if (mdCity.getCarByName("c3").getSpeed() - 10 >= 0) {
//                                mdCity.getCarByName("c3").decreaseSpeed(10);
//                            }
//                            if (mdCity.getCarByName("c3").getSpeed() == 0) {
//                                timeline3.pause();
//                                anim3.pause();
//                            }
                            //--------------------------------------------------end normal op
                            anim3.setRate(mdCity.getCarByName("c3").convertSpeedToRate());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });

            Scene scene = new Scene(root, 1100, 680, Color.DARKGREEN);
            //////////---------------------------table view
            //  Scene scene = new Scene(new Group());

            //  final Label label = new Label("Address Book");
            //  label.setFont(new Font("Arial", 20));
//            table.setEditable(true);
//
//            TableColumn firstNameCol = new TableColumn("First Name");
//            TableColumn lastNameCol = new TableColumn("Last Name");
//            TableColumn emailCol = new TableColumn("Email");
//
//            table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
//
//            final VBox vbox = new VBox();
//            vbox.setSpacing(5);
//            vbox.setPadding(new Insets(100, 0, 0, 100));
//            //vbox.getChildren().addAll(label, table);
//            vbox.getChildren().addAll(table);
//            ((Group) scene.getRoot()).getChildren().addAll(vbox);
            //primaryStage.setScene(scene);
//--------------------------------tableview end
            //--------------------------------------------------------------------keyevent
            scene.setOnKeyPressed(e
                    -> {
                try {
                    // System.out.println("carname:" + mdCity.getCarToControl());
                    if (e.getCode() == KeyCode.UP) {//keyup
                        mdCity.getCarByName(mdCity.getCarToControl()).increaseSpeed(10);
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
                        mdCity.getCarByName(mdCity.getCarToControl()).decreaseSpeed(10);
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
                        anim.pause();
                        timeline1.pause();
                        timeline2.pause();
                        timeline3.pause();

                    } else {
                        timeline1.play();
                        timeline2.play();
                        timeline3.play();

                    }

                }
            }
            );

            timeline1.setCycleCount(Timeline.INDEFINITE);
            timeline2.setCycleCount(Timeline.INDEFINITE);
            timeline3.setCycleCount(Timeline.INDEFINITE);

            timeline1.play();
            timeline2.play();

            timeline3.play();
            ///circle clicked
            circ1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // System.out.println("clickkkd on circle");
                    if (timeline1.getStatus() == Timeline.Status.RUNNING) {
                        timeline1.pause();
                        timeline2.pause();
                        timeline3.pause();
                        anim.pause();
                        anim2.pause();
                        anim3.pause();
                    } else {
                        timeline1.play();
                        timeline2.play();
                        timeline3.play();
                        anim.play();
                        anim2.play();
                        anim3.play();

                    }
                }

            });
            //---------end test

            primaryStage.setTitle(
                    "PathTransition Demo");
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    public void testToCall() {
        //0 SHOW SPEED OF EACH CAR AROUND THE CAR
        //need multithread 1. update car x val and y val 2. in mdcity
        //search for cars on same route and check distance between
        //every two and slow down the one which is faster
        //3 if anyone click on any car also slow down by 10km/hr
        //4. RECORD ALL STOP AUTOMATIC AND ALL MANUAL BREAK
        //
        //     // System.out.println("PathDemoJavaFx_ver07.testToCall()");
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
