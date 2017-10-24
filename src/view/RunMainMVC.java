/* class to start showing javafxui login page as first controller and view
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import helper.*;
import view.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author XC8184
 */
public class RunMainMVC extends Application {

    public RunMainMVC() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        //-------------------------------------------------------view load

        //--loadjavafxview
        Parent root = FXMLLoader.load(getClass().getResource("../view/UILogin.fxml"));//view an controller loaded
        //---------------just test
//        Parent root = FXMLLoader.load(getClass().getResource("../view/UITestDrive.fxml"));//view an controller loaded
        //-------------end of just test
        DebugLog.appendData2(getClass().getResource("../view2/Vw_step02Ass02.fxml").toString());
        Scene scene = new Scene(root, 1350, 750);
        stage.setScene(scene);
        stage.show();

        //---------------------------------------------------------model load
        //-------------------------------------------------------controller load
    }

    public static void main(String[] args) {
        // Test5_javafx t = new Test5_javafx("my name");
        launch();
    }
}
