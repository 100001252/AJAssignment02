/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author XC8184
 */
public class VwMIStep01Controller implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Label lblfeedback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    /**
     * do login in first page
     *
     */
    private void doLogin(ActionEvent event) throws IOException {
        //loading second steps scen
        Parent vwMainInitialSecondStepParent = FXMLLoader.load(getClass().getResource("VwMainInitialStep02.fxml"));
        Scene vwMainInitialSecondStep = new Scene(vwMainInitialSecondStepParent, 1350, 750);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //--------end loading second step
        System.out.println("makiiinglogin");
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        if (username.equals("admin") && pass.equals("admin")) {
            System.out.println("welcome");
            window.setScene(vwMainInitialSecondStep);
            window.show();
        } else {
            lblfeedback.setText("User name and password does not match with our record");
            System.out.println("Incorect username and password");
        }
    }

    @FXML
    /**
     * needs to do signup but it is not active for this assignment
     */
    private void doSignup(ActionEvent event) throws IOException, Exception {
        System.out.println("start doing signup");

    }

    /**
     * this function brings popup wherever you are even in other controller/view
     *
     * @param text
     * @param yourTitle
     */
    public static void modal(String text, String yourTitle) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Window primaryStage = null;
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text));
        dialog.setTitle(yourTitle);
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    private void launch(Class<VwCityJavaFx> aClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
