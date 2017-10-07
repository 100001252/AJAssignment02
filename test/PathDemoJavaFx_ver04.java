
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
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

public class PathDemoJavaFx_ver04 extends Application {
//here is a test to control rythm of game with timeline

    public void start(Stage primaryStage) {
        ImageView car = new ImageView();

        Circle car3 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
        //car.setImage(new Image("file:res/car.gif"));
        car.setImage(new Image("caryellow.jpg"));
        car.setX(-car.getImage().getWidth() / 2);
        car.setY(500 - car.getImage().getHeight());
        car.setRotate(250);

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

        PathTransition anim3 = new PathTransition();
        anim3.setNode(car3);
        anim3.setPath(road);
        anim3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        anim3.setInterpolator(Interpolator.LINEAR);
        anim3.setDuration(new Duration(100000));
        anim3.setDelay(Duration.seconds(1));
        // anim2.setCycleCount(Timeline.INDEFINITE);

        Group root = new Group();
        root.getChildren().addAll(road, divider, car, car3);

        root.setTranslateX(50);
        root.setTranslateY(50);
        ///mouse on click

        //anim.play();
        //---------test
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            double dx = 7; //Step on x or velocity
            double dy = 3; //Step on y

            @Override
            public void handle(ActionEvent t) {
                //move the ball
//                ball.setLayoutX(ball.getLayoutX() + dx);
//                ball.setLayoutY(ball.getLayoutY() + dy);
                // // System.out.println(ball.getLayoutX());
                car.setLayoutX(car.getLayoutX());
                car.setLayoutY(car.getLayoutY());

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //---------end test

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
