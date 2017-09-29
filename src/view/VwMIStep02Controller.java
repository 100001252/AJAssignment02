/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import helper.*;

import model.*;
import view.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author XC8184
 */
public class VwMIStep02Controller implements Initializable {

    @FXML
    private ToggleGroup grpSpeed;
    @FXML
    private JFXButton btnStart;
    @FXML
    private RadioButton rdoMap01;
    @FXML
    private ToggleGroup grpMap;
    @FXML
    private RadioButton rdoMap02;
    @FXML
    private ImageView imgMap;
    @FXML
    private JFXCheckBox chkTermAndCondition;
    @FXML
    private Label lblFeedback;
    @FXML
    private JFXColorPicker txtBackGroundColor;

    //----------------------variable
    private MdCity mdCity;
    private MdTimer mdTimer;
    private String tt;
    @FXML
    private RadioButton rdoSpeed20;
    @FXML
    private RadioButton rdoSpeed40;
    @FXML
    private RadioButton rdoSpeed100;
    @FXML
    private JFXButton btnAbout;

    //----------------------endvariable
//    public VwMIStep02Controller(String test) {
//        this.tt = test;
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    /**
     * when user change radio button for map
     */
    public void radioMapSelect(ActionEvent event) {
        if (rdoMap01.isSelected()) {
            // System.out.println("map01" + this.tt);
            imgMap.setImage(new Image("images/map01.png"));
        } else {
            System.out.println("map02");
            imgMap.setImage(new Image("images/map02.png"));
        }
    }

    @FXML
    private void startButtonClicked(ActionEvent event) throws Exception {
        //VwReportView vwReport = new VwReportView();
        boolean chktc = chkTermAndCondition.isSelected();
        //------check which initial speed chosen
        int initialSpeed = 100;
        initialSpeed = (rdoSpeed20.isSelected() ? 20 : (rdoSpeed40.isSelected() ? 40 : 100));

        if (chktc) {
            MdCity mdcity = new MdCity();
            MdTimer mdTimer = new MdTimer();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            VwCityJavaFx vc = new VwCityJavaFx(txtBackGroundColor.getValue().toString(), mdcity, mdTimer, initialSpeed);
            vc.start(window);
            //VwReportView vwreportView = new VwReportView(mdcity, mdTimer); if i have time
            MdCity mdCity = new MdCity();

        } else {
            lblFeedback.setText("Please check Term and condition");
        }

    }

    @FXML
    private void chkTermAndConditionChecked(ActionEvent event) {
        //boolean isChecked=chkTermAndCondition.

    }

    @FXML
    /**
     * show popup when btn readTermAndCondition clicked to show user term and
     * condition
     */
    private void btnReadTermAndCondition(ActionEvent event) {//termand condition read
        String text = "1.This Simulated safe-driving is in order to make you \n familar with driving awareness \n";
        text += "2.Right click on any car make the car full stop(full break) \n";
        text += "3.If you left click on any car you can use arrow up to increase \n speed of that car and arrow down to decrease speed \n";
        text += "4.each time you press arrow it decrease speed by 10 km/hr \n";
        text += "5.you can not use arrow to make speed to zero, and you need \n to use r-click for that full stop \n";
        text += "6.after full stop if you r-click again on car it start moving \n";

        VwMIStep01Controller.modal(text, "Term and Condition");
    }

    @FXML
    private void btnAboutClicked(ActionEvent event) {
        String text = "1. L-Click on any car reduce speed by 10km/h \n"
                + "2. Ater you click on a car you can use arrow up/down to increase/decrease speed \n"
                + "3. R-click make car full stop and another click make it move \n"
                + "4. keyboard space make all car stop(for resume press again)";
        VwMIStep01Controller.modal(text, "About");
    }

}
