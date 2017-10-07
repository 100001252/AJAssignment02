/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.MdCity;
import model.MdTimer;
import view.VwCityJavaFx;

/**
 * FXML Controller class
 *
 * @author feiyeyuji
 */
public class Vw_step02Ass02Controller implements Initializable {

    @FXML
    private Button demoOnlyBtn;
    @FXML
    private Button testOnlyBtn;
    @FXML
    private Button demoAndTestBtn;

    private String showTime;
    private String Duration;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void demoOnlyAction(ActionEvent event) {
        Stage stage = new Stage();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(data -> {
//            // System.out.println("Times=" + data.getKey() + ", Duration=" + data.getValue());
            showTime = data.getKey();
            Duration = data.getValue();
        });
        
        // System.out.println("Times=" + showTime + ", Duration=" + Duration);
         int initialSpeed = 100;
         MdCity mdcity = new MdCity();
            MdTimer mdTimer = new MdTimer();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

         try{
             VwCityJavaFxDemo vc = new VwCityJavaFxDemo(Duration,"#005544", mdcity, mdTimer, initialSpeed);
             vc.start(window);
         }catch(Exception e){
             e.printStackTrace();
         }
            
    }

    @FXML
    private void testOnlyAction(ActionEvent event) {
    }

    @FXML
    private void demoAndTestAction(ActionEvent event) {
    }

    private Popup createPopup() {
        final Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setX(100);
        popup.setY(50);
        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        return popup;
    }
    
    private void runDemo(){
        
    }
    
    private Dialog<Pair<String, String>> popup(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Information Needed");
        dialog.setHeaderText("Please input how many times you want for display demo, and the duration of one demo");

        ButtonType loginButtonType = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField showTimes = new TextField();
        showTimes.setPromptText("How many times?");
        TextField duration = new TextField();
        duration.setPromptText("How many seconds?");

        grid.add(new Label("Times:"), 0, 0);
        grid.add(showTimes, 1, 0);
        grid.add(new Label("Duration:"), 0, 1);
        grid.add(duration, 1, 1);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(showTimes.getText(), duration.getText());
            }
            return null;
        });
        return dialog;
    }
}
