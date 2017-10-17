/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import helper.*;

import model.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class UITestDriveController implements Initializable {

    @FXML
    private RadioButton rdoSpeed20;
    @FXML
    private ToggleGroup grpSpeed;
    @FXML
    private RadioButton rdoSpeed40;
    @FXML
    private RadioButton rdoSpeed100;
    @FXML
    private RadioButton rdoMap01;
    @FXML
    private ToggleGroup grpMap;
    @FXML
    private RadioButton rdoMap02;
    @FXML
    private RadioButton rdoMap03;
    @FXML
    private ImageView imgMap;
    @FXML
    private JFXCheckBox chkTermAndCondition;
    @FXML
    private JFXColorPicker txtBackGroundColor;
    @FXML
    private JFXButton btnStart;
    @FXML
    private Label lblFeedback;
    @FXML
    private JFXButton btnAbout;
    @FXML
    private JFXComboBox<Integer> cmbNumberOfCars;
    @FXML
    private JFXCheckBox chkcar01;
    @FXML
    private JFXCheckBox chkcar02;
    @FXML
    private JFXCheckBox chkcar03;
    @FXML
    private JFXCheckBox chkcar04;
    @FXML
    private JFXCheckBox chkcar05;
    @FXML
    private JFXCheckBox chkcar06;
    @FXML
    private JFXCheckBox chkcar07;
    @FXML
    private ToggleGroup grptraffichint;
    @FXML
    private RadioButton rdosignSchool;
    @FXML
    private RadioButton rdosignStop;
    @FXML
    private RadioButton rdosignTraffic;
    @FXML
    private ImageView imgsign;

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
        ObservableList<Integer> options
                = FXCollections.observableArrayList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                        15, 16, 17, 18, 19, 20);
        cmbNumberOfCars.setItems(options);
    }

    @FXML
    /**
     * when user change radio button for map
     */
    public void radioMapSelect(ActionEvent event) {
        try {
            DebugLog.appendData2(">>sdf234changeradiomap>");
            if (rdoMap01.isSelected()) {

                imgMap.setImage(new Image("images/map01.png"));
            } else if (rdoMap02.isSelected()) {
                // System.out.println("map02");
                imgMap.setImage(new Image("images/map02.png"));
            } else if (rdoMap03.isSelected()) {
                // System.out.println("map02");
                imgMap.setImage(new Image("images/map03.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void startButtonClicked(ActionEvent event) throws Exception {
        //VwReportView vwReport = new VwReportView();

        boolean validate = chkTermAndCondition.isSelected();
        if (!validate) {
            lblFeedback.setText("Please check Term and condition");
            throw new Exception("invalid term and condition");
        }
        Integer numberofcars = cmbNumberOfCars.getValue();
        if (numberofcars == null) {
            validate = false;
            lblFeedback.setText("Please choose number of cars");
            throw new Exception("invalid data");
        }
        validate = isCarOptionCheckedAtleast();
        if (!validate) {
            lblFeedback.setText("Please choose Minimum one colorful car from list");
            throw new Exception("invalid data");
        }
        //------check which initial speed chosen
        int initialSpeed = 100;
        initialSpeed = (rdoSpeed20.isSelected() ? 20 : (rdoSpeed40.isSelected() ? 40 : 100));

        if (validate) {
            MdCity mdcity = new MdCity();
            MdTimer mdTimer = new MdTimer();

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            int maxrunningTime = 100;
            if (rdoMap01.isSelected()) {
                DebugLog.appendData2(">>>jlkjew123qiru234123>>rdomap01 is selected so i need to start map02");

                VwCityJavaFx01_mainTest vc = new VwCityJavaFx01_mainTest(txtBackGroundColor.getValue().toString(), mdcity, mdTimer, initialSpeed);
                vc.setTrafficSign(getradiosignOptionselected());
                vc.setNumberOfCars(numberofcars);
                vc.setListofPossibleCar(getCarOptionSelected());
                vc.setMaxTime(maxrunningTime);

                vc.start(window);
                //                VwCityJavaFx vc = new VwCityJavaFx(txtBackGroundColor.getValue().toString(), mdcity, mdTimer, initialSpeed);
                //vc.start(window);

            } else if (rdoMap02.isSelected()) {
                DebugLog.appendData2(">>>23jlkasdfsdaf>>rdomap02 is selected so i need to start map02");
                VwCityJavaFx02_mainTest vc = new VwCityJavaFx02_mainTest(txtBackGroundColor.getValue().toString(), mdcity, mdTimer, initialSpeed);
                vc.setTrafficSign(getradiosignOptionselected());
                vc.setMaxTime(maxrunningTime);
                vc.setNumberOfCars(numberofcars);
                vc.setListofPossibleCar(getCarOptionSelected());
                vc.start(window);

            } else if (rdoMap03.isSelected()) {
                DebugLog.appendData2(">>>23jlkasdfsdaf>>rdomap02 is selected so i need to start map03");
                VwCityJavaFx03_mainTest vc = new VwCityJavaFx03_mainTest(txtBackGroundColor.getValue().toString(), mdcity, mdTimer, initialSpeed);
                vc.setTrafficSign(getradiosignOptionselected());
                vc.setMaxTime(maxrunningTime);
                vc.setNumberOfCars(numberofcars);
                vc.setListofPossibleCar(getCarOptionSelected());
                vc.start(window);

            }

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

        UILoginController.modal(text, "Term and Condition");
    }

    @FXML
    private void btnAboutClicked(ActionEvent event) {
        String text = "1. L-Click on any car reduce speed by 10km/h \n"
                + "2. Ater you click on a car you can use arrow up/down to increase/decrease speed \n"
                + "3. R-click make car full stop and another click make it move \n"
                + "4. keyboard space make all car stop(for resume press again)";

        UILoginController.modal(text, "about");

    }

    @FXML
    private void checkboxCarSelected(ActionEvent event) {
        //do onclick something here
        //zzzzz

    }

    private boolean isCarOptionCheckedAtleast() {
        if (chkcar01.isSelected() || chkcar02.isSelected() || chkcar03.isSelected()
                || chkcar04.isSelected() || chkcar05.isSelected() || chkcar06.isSelected() || chkcar07.isSelected()) {
            return true;
        } else {
            return false;
        }

    }

    private ArrayList<String> getCarOptionSelected() {
        ArrayList<String> carOptions = new ArrayList<String>();
        if (chkcar01.isSelected()) {
            carOptions.add("car-030dbf.png");
        }
        if (chkcar02.isSelected()) {
            carOptions.add("car-21d811.png");
        }
        if (chkcar03.isSelected()) {
            carOptions.add("car-67dbc9.png");
        }
        if (chkcar04.isSelected()) {
            carOptions.add("car-d7e514.png");
        }
        if (chkcar05.isSelected()) {
            carOptions.add("car-ffffff.png");
        }
        if (chkcar06.isSelected()) {
            carOptions.add("car-ff0505.png");
        }
        if (chkcar07.isSelected()) {
            carOptions.add("car-000000.png");
        }

        return carOptions;
    }

    @FXML
    private void radiosignChanged(ActionEvent event) {
        if (rdosignSchool.isSelected()) {
            imgsign.setImage(new Image("images/school_zone_start.png"));
        } else if (rdosignStop.isSelected()) {
            imgsign.setImage(new Image("images/stopsign.jpg"));
        } else if (rdosignTraffic.isSelected()) {
            imgsign.setImage(new Image("images/tl-g.png"));
        }
    }

    private int getradiosignOptionselected() {
        int result = 1;
        if (rdosignSchool.isSelected()) {
            result = 0;
        } else if (rdosignStop.isSelected()) {
            result = 1;
        } else if (rdosignTraffic.isSelected()) {
            result = 2;
        }
        return result;
    }

}
