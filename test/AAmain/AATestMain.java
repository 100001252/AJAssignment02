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
        //     runCityJavaFxMain_justForTestOld();
        //runCityJavaFx01_justForTest();
//        runCityJavaFxDemo_justForTest();
        //  runCityJavaFxDemo_justForTest2();//all process in timeline thread(there is concurrent error)
        //runCityJavaFxDemo_justForTest3();//same as original just shorter process for stopAccident
        // testHashMap();
        //    runCityJavaFx01_justForTest_hashmap();
        // runCityJavaFx01_justForTest_hashmap2();  ///no error running
        // runCityJavaFx01_justForTest_hashmap3();  ///no error running
        runCityJavaFx01_justForTest_hashmap4();  ///no error running
        //  runCityJavaFx01_justForTest_hashmap5();//using hit eventlistener not timeline does not work int that way
    }

    public static void runCityJavaFx01_justForTest_hashmap5() {

        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap5.class);
            }
        }.start();

    }

    public static void runCityJavaFxDemo_justForTest3() {

        try {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(VwCityJavaFxDemo_justForTest3.class);
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void runCityJavaFxDemo_justForTest2() {
        int initialSpeed = 100;
        MdCity mdcity = new MdCity();
        MdTimer mdTimer = new MdTimer();

        try {
            new Thread() {
                @Override
                public void run() {
                    javafx.application.Application.launch(VwCityJavaFxDemo_justForTest2.class);
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void runCityJavaFxMain_justForTestOld() {
        MdTimer mdTimer = new MdTimer();
        MdCity mdcity = new MdCity();
        // VwCityJavaFxMain_justForTestOld vc = new VwCityJavaFxMain_justForTestOld("1", "#005544", mdcity, mdTimer, 100);

        //vc.start(window);
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFxMain_justForTestOld.class);
            }
        }.start();

    }

    public static void runCityJavaFx01_justForTest_hashmap4() {

        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap4.class);
            }
        }.start();

    }

    public static void runCityJavaFx01_justForTest_hashmap3() {
//        MdTimer mdTimer = new MdTimer();
//        MdCity mdcity = new MdCity();
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap3.class);
            }
        }.start();

    }

    public static void runCityJavaFx01_justForTest_hashmap2() {
//        MdTimer mdTimer = new MdTimer();
//        MdCity mdcity = new MdCity();
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap2.class);
            }
        }.start();

    }

    public static void runCityJavaFx01_justForTest_hashmap() {
//        MdTimer mdTimer = new MdTimer();
//        MdCity mdcity = new MdCity();
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(VwCityJavaFx01_JustForTest1_hashmap.class);
            }
        }.start();

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
        // VwCityJavaFxMain_justForTestOld vc = new VwCityJavaFxMain_justForTestOld("1", "#005544", mdcity, mdTimer, 100);

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
