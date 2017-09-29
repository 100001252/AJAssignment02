
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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

public class PathDemoJavaFx extends Application {

    public void start(Stage primaryStage) {
        ImageView car = new ImageView();
        ImageView car2 = new ImageView();
        Circle car3 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
        //car.setImage(new Image("file:res/car.gif"));
        car.setImage(new Image("taxi.jpg"));
        car.setX(-car.getImage().getWidth() / 2);
        car.setY(500 - car.getImage().getHeight());
        car.setRotate(50);

        car2.setImage(new Image("taxi.jpg"));
        car2.setX(-car.getImage().getWidth() / 2);
        car2.setY(400 - car.getImage().getHeight());
        car2.setRotate(50);

        car3.setCenterX(50); // setX(-car.getImage().getWidth() / 2);
        car3.setCenterY(100);//setY(400 - car.getImage().getHeight());
        car3.setRotate(50);

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

        Path road = new Path();
        road.setStroke(Color.BLACK);
        road.setStrokeWidth(50);
        road.getElements().addAll(path);

        Path divider = new Path();
        divider.setStroke(Color.WHITE);
        divider.setStrokeWidth(4);
        divider.getStrokeDashArray().addAll(10.0, 10.0);
        divider.getElements().addAll(path);

        PathTransition anim = new PathTransition();
        anim.setNode(car);
        anim.setPath(road);
        anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim.setInterpolator(Interpolator.LINEAR);
        anim.setDuration(new Duration(20000));
        // anim.setCycleCount(Timeline.INDEFINITE);

        PathTransition anim2 = new PathTransition();
        anim2.setNode(car2);
        anim2.setPath(road);
        anim2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim2.setInterpolator(Interpolator.LINEAR);
        anim2.setDuration(new Duration(100000));
        anim2.setDelay(Duration.seconds(2));

        PathTransition anim3 = new PathTransition();
        anim3.setNode(car3);
        anim3.setPath(road);
        anim3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim3.setInterpolator(Interpolator.LINEAR);
        anim3.setDuration(new Duration(100000));
        anim3.setDelay(Duration.seconds(1));
        // anim2.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();
        root.getChildren().addAll(road, divider, car, car2, car3);

        root.setTranslateX(50);
        root.setTranslateY(50);
        ///mouse on click
        root.setOnMouseClicked(me
                -> {
            Animation.Status status = anim.getStatus();
            if (status == Animation.Status.RUNNING
                    && status != Animation.Status.PAUSED) {
                anim.pause();
                System.out.println(anim.currentTimeProperty().toString() + " | object" + anim.getPath().getLayoutX());
                System.out.println("translate f: (" + car.getX() + "," + car.getY() + ")");
                //System.out.println("translate f: (" + anim + "," + car.getY() + ")");
                //  System.out.println(">>>>test f: (" + anim3.getNode().getCursor().toString());
            } else {
                anim.play();
                anim2.play();
                anim3.play();
            }
        });

        Scene scene = new Scene(root, 1100, 680, Color.DARKGREEN);

        primaryStage.setTitle("PathTransition Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start2(Stage primaryStage) {
        ImageView car = new ImageView();
        //car.setImage(new Image("file:res/car.gif"));
        car.setImage(new Image("taxi.jpg"));
        car.setX(-car.getImage().getWidth() / 2);
        car.setY(500 - car.getImage().getHeight());
        car.setRotate(50);

//        PathElement[] path
//                = {
//                    new MoveTo(0, 300),
//                    new ArcTo(100, 100, 0, 100, 400, false, false),
//                    new LineTo(300, 400),
//                    new ArcTo(100, 100, 0, 400, 300, false, false),
//                    new LineTo(400, 100),
//                    new ArcTo(100, 100, 0, 300, 0, false, false),
//                    new LineTo(100, 0),
//                    new ArcTo(100, 100, 0, 0, 100, false, false),
//                    new LineTo(0, 300),
//                    new ClosePath()
//                };
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
        PathElement[] pathCar
                = {
                    new MoveTo(0, 500),
                    new ArcTo(50, 50, 0, 50, 550, false, false),//first arc
                    new LineTo(200, 550)

                };

        Path road = new Path();
        road.setStroke(Color.BLACK);
        road.setStrokeWidth(50);
        road.getElements().addAll(path);
//
//        Path divider = new Path();
//        divider.setStroke(Color.WHITE);
//        divider.setStrokeWidth(4);
//        divider.getStrokeDashArray().addAll(10.0, 10.0);
//        divider.getElements().addAll(path);

        PathTransition anim = new PathTransition();
        anim.setNode(car);
        anim.setPath(road);
        anim.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim.setInterpolator(Interpolator.LINEAR);
        anim.setDuration(new Duration(20000));
        anim.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();
        //  root.getChildren().addAll(road, divider, car);
        root.getChildren().addAll(road, car);
        root.setTranslateX(50);
        root.setTranslateY(50);
        ///mouse on click
        root.setOnMouseClicked(me
                -> {
            Animation.Status status = anim.getStatus();
            if (status == Animation.Status.RUNNING
                    && status != Animation.Status.PAUSED) {
                anim.pause();
            } else {
                anim.play();
            }
        });
        Scene scene = new Scene(root, 1100, 680, Color.DARKGREEN);

        primaryStage.setTitle("PathTransition Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
