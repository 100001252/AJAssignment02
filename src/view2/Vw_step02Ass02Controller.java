/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view2;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        Dialog<Pair<String, String>> dialog = createPopupDialog();

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(data -> {
//            // System.out.println("Times=" + data.getKey() + ", Duration=" + data.getValue());
            showTime = data.getKey();
            Duration = data.getValue();
        });
        // System.out.println("Times=" + showTime + ", Duration=" + Duration);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        runDemo(window, false);

    }

    @FXML
    private void testOnlyAction(ActionEvent event) {
        try {
            Parent vwMainInitialSecondStepParent = FXMLLoader.load(getClass().getResource("../view/UITestDrive.fxml"));
            Scene vwMainInitialSecondStep = new Scene(vwMainInitialSecondStepParent, 1350, 750);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(vwMainInitialSecondStep);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(Vw_step02Ass02Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void demoAndTestAction(ActionEvent event) {
        Dialog<Pair<String, String>> dialog = createPopupDialog();

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(data -> {
//            // System.out.println("Times=" + data.getKey() + ", Duration=" + data.getValue());
            showTime = data.getKey();
            Duration = data.getValue();
        });
        for (int i = 0; i < 100; i++) {
            if (showTime.equals("") || Duration.equals("")) {
                dialog.setContentText("Please do not leave input empty");
            }else if(isNumber(showTime) || isNumber(Duration)){
                
            }else{
                dialog.setContentText("Please input number data");
            }
            
        }

        // System.out.println("Times=" + showTime + ", Duration=" + Duration);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        runDemo(window, true);

    }

    private boolean isNumber(String aString){
        try{
            int i = Integer.parseInt(aString);
        }catch(NumberFormatException numE){
            return false;
        }
        return true;
    }
    private Popup createPopup() {
        final Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setX(100);
        popup.setY(50);
        popup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
        return popup;
    }

    private void runDemo(Stage window, boolean plusTest) {
        int initialSpeed = 100;
        MdCity mdcity = new MdCity();
        MdTimer mdTimer = new MdTimer();

        try {
            VwCityJavaFxDemo vc = new VwCityJavaFxDemo(showTime, Duration, "#005544", mdcity, mdTimer, initialSpeed, plusTest);
            vc.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Dialog<Pair<String, String>> createPopupDialog() {
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
        showTimes.setPromptText("How many times? up to 3");
        TextField duration = new TextField();
        duration.setPromptText("How many seconds? up to 60");

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
