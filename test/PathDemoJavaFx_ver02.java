
import com.jfoenix.controls.JFXButton;
import helper.Location;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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

public class PathDemoJavaFx_ver02 extends Application {

    public void start(Stage primaryStage) {
        ImageView car = new ImageView();
        ImageView car2 = new ImageView();
        Circle car3 = new Circle(50, 20, 30, Color.BLUE); //new Circle(50, 20, 10);
        //car.setImage(new Image("file:res/car.gif"));
        car.setImage(new Image("caryellow.jpg"));
        car.setX(-car.getImage().getWidth() / 2);
        car.setY(500 - car.getImage().getHeight());
        car.setRotate(250);

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
    private JFXButton button;
    private Label label;

    public void start01(Stage primaryStage) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        primaryStage.setTitle("JavaFX Welcome");
        button = new JFXButton("testttmybutton");
        label = new Label("I am coresponding text to car");
        button.setStyle("-fx-background-image: url('/images/taxi.jpg')");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(label, 0, 0);
        grid.add(button, 1, 1);
//--------------------------------test
        MdCity mdCity = new MdCity();

        try {

            //-----------define all cars
            mdCity.addCar(new MdCar(new Location(0, 100), "taxi.jpg", "c1", true, 0, 100));
//             mdCity.addCar(new MdCar(new Location(180, 100), "taxi-break.jpg", "c2"));
//            mdCity.addCar(new MdCar(new Location(440, 100), "taxi.jpg", "c3"));
//            mdCity.addCar(new MdCar(new Location(0, 200), "taxi.jpg", "c4"));
//            mdCity.addCar(new MdCar(new Location(180, 200), "taxi.jpg", "c5"));
//            mdCity.addCar(new MdCar(new Location(440, 200), "taxi.jpg", "c6"));
            //mdCity.getCarByName("c1").setSpeed(100);
//            mdCity.getCarByName("c2").setSpeed(60);
//            mdCity.getCarByName("c3").setSpeed(40);
//            mdCity.getCarByName("c5").setSpeed(90);
//            mdCity.getCarByName("c6").setSpeed(110);
/////////////////------------------ednd test
            //----------------------------------movement
            TranslateTransition ts = new TranslateTransition();
            TranslateTransition ts2 = new TranslateTransition(Duration.seconds(2));

            ts.setDuration(Duration.seconds(2));
            ts.setNode(button);
            ts2.setNode(label);
            ts.setToY(-200);
            ts2.setToY(-200);

            ts2.play();
            ts.play();//do move step by step with a timer and then timer and between timer check condition move one by one

            //try to add path to move
            //------------------------------------end of move
            Scene scene = new Scene(grid, 700, 700);
            primaryStage.setScene(scene);
            //scene.getStylesheets().add(Login_v2_with_CSS.class.getResource("cssFile_for_login_v2_with_css.css").toExternalForm());
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * old one without car calculation
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start2(Stage primaryStage) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        primaryStage.setTitle("JavaFX Welcome");
        button = new JFXButton("testttmybutton");
        label = new Label("I am coresponding text to car");
        button.setStyle("-fx-background-image: url('/images/taxi.jpg')");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(label, 0, 0);
        grid.add(button, 1, 1);

        //----------------------------------movement
        TranslateTransition ts = new TranslateTransition();
        TranslateTransition ts2 = new TranslateTransition(Duration.seconds(2));
        ts.setDuration(Duration.seconds(2));
        ts.setNode(button);
        ts2.setNode(label);
        ts.setToY(-200);
        ts2.setToY(-200);

        ts2.play();
        ts.play();//do move step by step with a timer and then timer and between timer check condition move one by one
        //try to add path to move

        //------------------------------------end of move
        Scene scene = new Scene(grid, 700, 700);
        primaryStage.setScene(scene);
        //scene.getStylesheets().add(Login_v2_with_CSS.class.getResource("cssFile_for_login_v2_with_css.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
