
import helper.DebugLog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MdCity;
import model.MdTimer;
import view2.VwCityJavaFxDemo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author feiyeyuji
 */
public class demoTest extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        //-------------------------------------------------------view load

        MdCity mdcity = new MdCity();
        MdTimer mdTimer = new MdTimer();
        
        try {
            VwCityJavaFxDemo vc = new VwCityJavaFxDemo("2", "33", "#005544", mdcity, mdTimer, 100,true);
            vc.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //---------------------------------------------------------model load
        //-------------------------------------------------------controller load
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
}
