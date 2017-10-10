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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class VwCityJavaFx01_JustForTest extends Application {

    //------------------allvariable that you use in this view and you should define at start
    private String colorHash;
    private MdCity mdCity;
    private MdTimer mdTimer;
    private Color labelsColor = Color.BLACK;
    private String labelsBackgroudColor = "white";
    private int initialSpeed;
    private int numberOfCars = 20;
    //-----------variable
    private int maxTime = 20;//maximum seconds of running this app
    private ArrayList<ImageView> lstImageViewCar = new ArrayList<>();
    private ArrayList<ImageView> lstImageViewClone = new ArrayList<>();
    private ArrayList<ImageView> lstImageViewSchoolSignStart = new ArrayList<>();
    private ArrayList<ImageView> lstImageViewSchoolSignEnd = new ArrayList<>();
    private ArrayList<Label> lstLabelViewClone = new ArrayList<>();
    private ArrayList<HBox> lstHboxViewClone = new ArrayList<>();
    private ArrayList<PathTransition> lstPathTransitions = new ArrayList<>();

    public VwCityJavaFx01_JustForTest(String colorHash, MdCity mdCityObj, MdTimer mdtimerobj, int initialSpeed) {
        this.colorHash = colorHash;
        this.mdCity = mdCityObj;
        this.mdTimer = mdtimerobj;
        this.initialSpeed = initialSpeed;
    }

    public VwCityJavaFx01_JustForTest() {
        this.colorHash = "#055b08";
        this.mdCity = new MdCity();
        this.mdTimer = new MdTimer();
        this.initialSpeed = 100;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < numberOfCars / 2; i++) {
            mdCity.addCar(new MdCar(new Location(180, 400), "car-21d811.png", "c" + Integer.toString(i), true, i, this.initialSpeed));
        }
        for (int i = numberOfCars / 2; i < numberOfCars; i++) {
            mdCity.addCar(new MdCar(new Location(180, 400), "car-21d811.png", "c" + Integer.toString(i), false, i, this.initialSpeed));
        }
        mdCity.addSchoolSign(new MdSchoolSign("sc1", new Location(800, 100), new Location(300, 100)));

        //for (MdCar carobj : mdCity.getLstCar()) {
        for (int i = 0; i < mdCity.getLstCar().size(); i++) {
            lstImageViewCar.add(new ImageView(new Image(mdCity.getLstCar().get(i).getImgName())));
            lstImageViewCar.get(i).setX(mdCity.getLstCar().get(i).getLocation().getX());
            lstImageViewCar.get(i).setY(mdCity.getLstCar().get(i).getLocation().getY());
            lstImageViewCar.get(i).setRotate(-90);
            lstImageViewClone.add(new ImageView(new Image(mdCity.getLstCar().get(i).getImgName())));
            lstLabelViewClone.add(new Label("speed for" + mdCity.getLstCar().get(i).getName()));
            lstLabelViewClone.get(i).setTextFill(labelsColor);
            lstHboxViewClone.add(new HBox());
            lstHboxViewClone.get(i).getChildren().addAll(lstImageViewClone.get(i), lstLabelViewClone.get(i));
            lstHboxViewClone.get(i).setLayoutY(i * 20);

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
        for (int i = 0; i < mdCity.getLstCar().size(); i++) {
            lstPathTransitions.add(new PathTransition());
            //Duration.seconds(mdCity.getLstCar().get(i).convertSpeedToDuration()), mdCity.getLstCar().get(i).isIsRouteToGo() ? roadCargo : roadCarreturn, lstImageViewCar.get(i))
            lstPathTransitions.get(i).setNode(lstImageViewCar.get(i));
            lstPathTransitions.get(i).setPath(mdCity.getLstCar().get(i).isIsRouteToGo() ? roadCargo : roadCarreturn);
            lstPathTransitions.get(i).setDuration(new Duration(25000));
            //lstPathTransitions.get(i).setDelay(new Duration(50));
            lstPathTransitions.get(i).setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            lstPathTransitions.get(i).setInterpolator(Interpolator.LINEAR);
            lstPathTransitions.get(i).setCycleCount(Timeline.INDEFINITE);
            lstPathTransitions.get(i).setDelay(Duration.seconds(mdCity.getLstCar().get(i).getDelay()));

        }
        //-----------------------------------add all element
        Group root = new Group();
        root.getChildren().addAll(road, divider, lblTimer);
        //hbbox
        for (HBox hbobj : lstHboxViewClone) {
            root.getChildren().add(hbobj);
        }
        //imageviewcars
        for (ImageView imgCar : lstImageViewCar) {
            root.getChildren().add(imgCar);
        }
        //schoolzone images
        for (int i = 0; i < lstImageViewSchoolSignStart.size(); i++) {
            root.getChildren().add(lstImageViewSchoolSignStart.get(i));
            root.getChildren().add(lstImageViewSchoolSignEnd.get(i));
        }
        root.setTranslateX(50);
        root.setTranslateY(50);
        //play all animation

        Scene scene = new Scene(root, 1350, 750, Color.DARKGREEN);
        primaryStage.setTitle(
                "PathTransition Demo");
        primaryStage.setScene(scene);
        scene.setFill(Color.web(this.colorHash));
        primaryStage.show();
        for (PathTransition ps : lstPathTransitions) {
            ps.play();
        }

    }

}
