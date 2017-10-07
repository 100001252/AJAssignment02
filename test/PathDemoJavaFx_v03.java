/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAmain;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;

import javafx.util.Duration;

public class PathDemoJavaFx_v03 extends Application {
//here is a test to get distance of a car section by section
    //car sectional move

    public void start(Stage primaryStage) {

        ImageView car = new ImageView();

        car.setImage(new Image("caryellow.jpg"));
        car.setX(-car.getImage().getWidth() / 2);
        car.setY(500 - car.getImage().getHeight());
        car.setRotate(250);

        PathElement[] path
                = {
                    new MoveTo(0, 500),
                    new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
                    new LineTo(900, 550),
                    new ArcTo(100, 100, 0, 800, 500, false, false),//second line bottom arc
                    new LineTo(200, 500),
                    new ArcTo(100, 100, 0, 100, 400, false, true),//thirs arc
                    new LineTo(900, 400),
                    new ArcTo(100, 100, 0, 800, 300, false, false),//fourth arc
                    new LineTo(200, 300),
                    new ArcTo(100, 100, 0, 100, 200, false, true),//fifth arc
                    new LineTo(900, 200),
                    new ArcTo(200, 200, 0, 700, 0, false, false),//6 arc
                    new LineTo(100, 0),
                    new ArcTo(80, 80, 0, 0, 0, false, false),//7 arc
                    new LineTo(0, 300),
                    new ClosePath()
                };
        PathElement[] pathCar = {new MoveTo(0, 500),
            new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
            new LineTo(900, 550)};

        Path roadCar = new Path();

        roadCar.setStroke(Color.BLACK);

        roadCar.setStrokeWidth(
                50);
        roadCar.getElements()
                .addAll(pathCar);

        Path road = new Path();

        road.setStroke(Color.BLACK);

        road.setStrokeWidth(
                50);
        road.getElements()
                .addAll(path);

        Path divider = new Path();

        divider.setStroke(Color.WHITE);

        divider.setStrokeWidth(
                4);
        divider.getStrokeDashArray().addAll(10.0, 10.0);
        divider.getElements().addAll(path);

        PathTransition anim = new PathTransition();

        anim.setNode(car);

        anim.setPath(roadCar);

        anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);

        anim.setInterpolator(Interpolator.LINEAR);

        anim.setDuration(
                new Duration(2000));
        // anim.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();

        root.getChildren()
                .addAll(road, divider, car);

        root.setTranslateX(
                50);
        root.setTranslateY(
                50);

        //root.getChildren().get(3).setOpacity(0.2);
        root.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me
            ) {
                Animation.Status status = anim.getStatus();
                if (status == Animation.Status.RUNNING
                        && status != Animation.Status.PAUSED) {
                    anim.pause();
                    // System.out.println(anim.currentTimeProperty().toString() + " | object" + anim.getPath().getLayoutX());
                    // System.out.println("translate f: (" + car.getX() + "," + car.getY() + ")");
                    //// System.out.println("translate f: (" + anim + "," + car.getY() + ")");
                    //  // System.out.println(">>>>test f: (" + anim3.getNode().getCursor().toString());
                } else {
                    // anim.interpolate(0.2);
                    anim.play();
//                    PathElement[] pathcar2
//                            = {
//                                new MoveTo(0, 500),
//                                new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
//                                new LineTo(900, 550)};
//                    Path roadcar2 = new Path();
//                    roadcar2.getElements().addAll(pathcar2);
//                    anim.setPath(roadcar2);

                }
            }
        }
        );

        Scene scene = new Scene(root, 1100, 680, Color.DARKGREEN);

        primaryStage.setTitle(
                "PathTransition Demo");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
