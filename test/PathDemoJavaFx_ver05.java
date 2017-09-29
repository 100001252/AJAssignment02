
import helper.Location;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
import model.MdCar;
import model.MdCity;

public class PathDemoJavaFx_ver05 extends Application {
//here is a test to control rythm of game with timeline

    public void start(Stage primaryStage) {
        MdCity mdCity = new MdCity();
        try {
//-----------define all cars
            mdCity.addCar(new MdCar(new Location(400, 150), "caryellow.jpg", "c1", true, 0, 50));
            mdCity.addCar(new MdCar(new Location(0, 100), "caryellow.jpg", "c2", true, 0, 50));
            mdCity.addCar(new MdCar(new Location(0, 200), "caryellow.jpg", "c3", true, 0, 50));

            mdCity.addCar(new MdCar(new Location(0, 400), "caryellow.jpg", "c4", false, 0, 50));
            mdCity.addCar(new MdCar(new Location(0, 500), "caryellow.jpg", "c5", false, 0, 50));
            mdCity.addCar(new MdCar(new Location(0, 600), "caryellow.jpg", "c6", false, 0, 50));

            ImageView car1 = new ImageView();
            ImageView car2 = new ImageView();
            ImageView car3 = new ImageView();
            ImageView car4 = new ImageView();
            ImageView car5 = new ImageView();
            //-------------end defining cars

            Circle circ1 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
            //car.setImage(new Image("file:res/car.gif"));
            car1.setImage(new Image(mdCity.getCarByName("c1").getImgName()));
            car1.setX(mdCity.getCarByName("c1").getLocation().getX());
            car1.setY(mdCity.getCarByName("c1").getLocation().getY());
            car1.setRotate(-90);

            car2.setImage(new Image(mdCity.getCarByName("c2").getImgName()));
            car2.setX(mdCity.getCarByName("c2").getLocation().getX());
            car2.setY(mdCity.getCarByName("c2").getLocation().getY());
            car2.setRotate(-90);

            circ1.setLayoutX(400);
            circ1.setLayoutY(300);

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
//--------------------------------------draw road
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
            PathTransition anim = new PathTransition();
            anim.setNode(car1);
            anim.setPath(road);
            anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            anim.setInterpolator(Interpolator.LINEAR);
            anim.setDuration(new Duration(20000));

            PathTransition anim2 = new PathTransition();
            anim2.setNode(car2);
            anim2.setPath(road);
            anim2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            anim2.setInterpolator(Interpolator.LINEAR);
            anim2.setDuration(new Duration(20000));
            // anim2.setDelay(Duration.seconds(1));

            // anim.setCycleCount(Timeline.INDEFINITE);
//        PathTransition anim3 = new PathTransition();
//        anim3.setNode(car3);
//        anim3.setPath(road);
//        anim3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
//        anim3.setInterpolator(Interpolator.LINEAR);
//        anim3.setDuration(new Duration(100000));
//        anim3.setDelay(Duration.seconds(1));
            // anim2.setCycleCount(Timeline.INDEFINITE);
            Group root = new Group();
            root.getChildren().addAll(road, divider, car1, car2, circ1);

            root.setTranslateX(50);
            root.setTranslateY(50);
            ///mouse on click

            //anim.play();
            //---------test
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
                double dx = 10; //Step on x or velocity
                double dy = 10; //Step on y

                @Override
                public void handle(ActionEvent t) {

                    //---------test anim
//                    PathElement[] path2 = {
//                        new MoveTo(0, 500),
//                        new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
//                        new LineTo(300, 550)
////                        new LineTo(900, 550),
////                        new ArcTo(50, 50, 0, 950, 500, false, false),//2 arc
////                        new LineTo(950, 100),
////                        new ArcTo(50, 50, 0, 900, 50, false, false),//3 arc
////                        new LineTo(50, 50),
////                        new ArcTo(50, 50, 0, 0, 100, false, false),//3 arc
////
////                        new ClosePath()
//                    };
//                    Path r2 = new Path();
//                    r2.getElements().addAll(path2);
//                    anim.setPath(r2);
                    anim.play();

                    anim2.play();

                    System.out.println("(" + car1.getTranslateX() + "|||" + car2.getTranslateX() + "," + car1.getTranslateY() + ")");
//                    if (car1.getTranslateX() > 0) {
//                        anim.pause();
//                    }

                    //----------end testanim
                    //move the ball
//                ball.setLayoutY(ball.getLayoutY() + dy);
                    // System.out.println(ball.getLayoutX());
                    //all rotation in oneplace
//                    if (car1.getLayoutY() == 380 || car1.getLayoutY() > 380 && car1.getLayoutX() < 900) {
//                        if (car1.getLayoutY() == 380) {
//                            car1.setRotate(180);
//                        }
//                        car1.setTranslateX(car1.getLayoutX() + dx);
//
//                    }
//
//                    if (car1.getLayoutX() == 0) {
//                        car1.setLayoutY(car1.getLayoutY() + dy);
//                    } else if (car1.getLayoutY() == -50 || car1.getLayoutY() < -50) {
//                        if (car1.getLayoutY() == -50 && car1.getLayoutX() > 800) {
//                            car1.setRotate(360);
//                        }
//                        if (car1.getLayoutX() == 10) {
//                            car1.setRotate(270);
//                        }
//                        car1.setLayoutX(car1.getLayoutX() - dx);
//                    }
//                    if (car1.getLayoutX() == 850 || car1.getLayoutX() > 850) //                RotateTransition rt = new RotateTransition();
//                    {
//                        if (car1.getLayoutX() == 850) {
//                            car1.setRotate(90);
//                        }
//                        car1.setLayoutY(car1.getLayoutY() - dy);
//                    }
//        rt.setNode(text);
                    //        rt.setFromAngle(0);
                    //        rt.setToAngle(360);
                    //        rt.setInterpolator(Interpolator.LINEAR);
                    //        rt.setCycleCount(Timeline.INDEFINITE);
                    //        rt.setDuration(new Duration(3000));
                }
            }));

            root.setOnMouseClicked(me
                    -> {
//            if (statusPaused) {
//                statusPaused = false;
//            } else {
//                statusPaused = true;
//            }

            });

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            //---------end test

            Scene scene = new Scene(root, 1100, 680, Color.DARKGREEN);

            primaryStage.setTitle("PathTransition Demo");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
