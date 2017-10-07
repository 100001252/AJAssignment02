/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author feiyeyuji
 */
public class runner extends Application {
    public runner(){
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //-------------------------------------------------------view load

        //--loadjavafxview
        Parent root = FXMLLoader.load(getClass().getResource("Vw_step02Ass02.fxml"));//view an controller loaded
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
