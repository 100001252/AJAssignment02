package AAmain;

import static java.awt.SystemColor.window;
import java.util.HashMap;
import static javax.annotation.Resource.AuthenticationType.APPLICATION;
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
 * @author XC8184
 */
public class AATestMain {

    public static void main(String[] args) {

        //runCityJavaFx01_justForTest();
        //  runCityJavaFxDemo_justForTest();
        // testHashMap();
        runCityJavaFx01_justForTest_hashmap();

    }

    public static void runCityJavaFx01_justForTest_hashmap() {
        MdTimer mdTimer = new MdTimer();
        MdCity mdcity = new MdCity();
        // VwCityJavaFxMain_justForTest vc = new VwCityJavaFxMain_justForTest("1", "#005544", mdcity, mdTimer, 100);

        //vc.start(window);
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap.class);
            }
        }.start();
//        StartUpTest startUpTest = StartUpTest.waitForStartUpTest();
//        startUpTest.printSomething();
    }

    public static void testHashMap() {
        HashMap<String, String> hashString = new HashMap<String, String>();
        hashString.put("t1", "1test");
        hashString.put("t2", "test2");
        hashString.put("t3", "test3");
        hashString.put("t3", "test546");
        System.out.println(hashString.get("t3"));

    }

    public static void runCityJavaFxDemo_justForTest() {
        int initialSpeed = 100;
        MdCity mdcity = new MdCity();
        MdTimer mdTimer = new MdTimer();

        try {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(VwCityJavaFxDemo_justForTest.class);
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void runCityJavaFx01_justForTest() {
        MdTimer mdTimer = new MdTimer();
        MdCity mdcity = new MdCity();
        // VwCityJavaFxMain_justForTest vc = new VwCityJavaFxMain_justForTest("1", "#005544", mdcity, mdTimer, 100);

        //vc.start(window);
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest.class);
            }
        }.start();
//        StartUpTest startUpTest = StartUpTest.waitForStartUpTest();
//        startUpTest.printSomething();
    }

}
