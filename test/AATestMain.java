
import static java.awt.SystemColor.window;
import static javax.annotation.Resource.AuthenticationType.APPLICATION;
import model.MdCity;
import model.MdTimer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XC8184
 */
public class AATestMain {

    public static void main(String[] args) {
        //test1_formulaDistance(1, 2, 3, 10);
        runMainJustForTest();
    }

    public static void runMainJustForTest() {
        MdTimer mdTimer = new MdTimer();
        MdCity mdcity = new MdCity();
        VwCityJavaFxMain_justForTest vc = new VwCityJavaFxMain_justForTest("1", "#005544", mdcity, mdTimer, 100);

        //vc.start(window);
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFxMain_justForTest.class);
            }
        }.start();
//        StartUpTest startUpTest = StartUpTest.waitForStartUpTest();
//        startUpTest.printSomething();
    }
}
