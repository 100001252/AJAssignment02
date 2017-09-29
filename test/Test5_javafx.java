
import com.jfoenix.controls.JFXButton;
import helper.Location;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.*;
import view.VwCityView;
import view.*;

/* this test is in order to do JAVAFX movement
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XC8184
 */
public class Test5_javafx extends Application {

    private JFXButton button;
    private Label label;

    @Override
    public void start(Stage primaryStage) throws Exception {
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
            mdCity.addCar(new MdCar(new Location(0, 100), "taxi.jpg", "c1"));
//             mdCity.addCar(new MdCar(new Location(180, 100), "taxi-break.jpg", "c2"));
//            mdCity.addCar(new MdCar(new Location(440, 100), "taxi.jpg", "c3"));
//            mdCity.addCar(new MdCar(new Location(0, 200), "taxi.jpg", "c4"));
//            mdCity.addCar(new MdCar(new Location(180, 200), "taxi.jpg", "c5"));
//            mdCity.addCar(new MdCar(new Location(440, 200), "taxi.jpg", "c6"));
            mdCity.getCarByName("c1").setSpeed(100);
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

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }

}
