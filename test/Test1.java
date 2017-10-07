
import helper.Location;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.*;
import view.VwCityView;
import view.*;

class Hi extends Thread {

    public void run() {
        for (int i = 1; i < 10; i++) {
            // System.out.println("Hi");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }

    }

}

class ThreadmdCityMove extends Thread {

    private MdCity mdCity;

    public ThreadmdCityMove(MdCity mdcity) {

        this.mdCity = mdcity;
    }

    public void run() {
        this.mdCity.move();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }

    }

}

class ThreadmdCityStopAccident extends Thread {

    private MdCity mdCity;

    public ThreadmdCityStopAccident(MdCity mdcity) {

        this.mdCity = mdcity;
    }

    public void run() {
        this.mdCity.stopAccident();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }

    }

}

class Hello extends Thread {

    public void run() {
        for (int i = 1; i < 10; i++) {
            // System.out.println("Hello");
            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XC8184
 */
public class Test1 {

    public static void main(String[] args) {
        //        Location loc = new Location(0, 0);
        //        MdCar mdcar = new MdCar(loc);
        //        Path path = Paths.get("/src/taxi.jpg");
        //        // System.out.println(path.toString());

        //VwCityView.CityView cv = new VwCityView().new CityView(700, 700);
        //mdcar.getVehicleImage().toString();
        //cv.drawImage(2, 3, mdcar.getVehicleImage());
        //--------------------------------------------------------------------------------------------->>>
        //taxiMove();
        //   myTest_multiThread();
        //testTextMove();//moving text at the same time
        //mytest_TestVwMoveCars();//calculation still are inside view
        //  mytest_TestVwMoveCars2();//make all calc out of view
        //  mytest_TestVwMoveCars3();//better implement all in mdcity
        mytest_TestVwMoveCars4();//expect to avoid accident(work on two different thread to fix it)

    }

    public static void myTest_multiThread() {
        Hi objhi = new Hi();
        Hello objHello = new Hello();

        objhi.start();
        objHello.start();

    }

    public static void mytest_TestVwMoveCars4() {
        MdCity mdCity = new MdCity();
        TestVwMoveCars tvwmoveCar = new TestVwMoveCars(mdCity.getLstCar());
        try {
            JFrame f = new JFrame();
            f.setSize(1000, 1000);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            mdCity.addCar(new MdCar(new Location(180, 100), "caryellow.jpg", "c1", true, 1, 150));
            mdCity.addCar(new MdCar(new Location(440, 100), "caryellow.jpg", "c2", true, 2, 50));
            mdCity.addCar(new MdCar(new Location(0, 100), "caryellow.jpg", "c3", true, 3, 50));

            mdCity.addCar(new MdCar(new Location(0, 200), "caryellow.jpg", "c4", false, 0, 50));
            mdCity.addCar(new MdCar(new Location(180, 200), "caryellow.jpg", "c5", false, 1, 50));
            mdCity.addCar(new MdCar(new Location(440, 200), "caryellow.jpg", "c6", false, 2, 50));

            mdCity.getCarByName("c1").setSpeed(40);
            mdCity.getCarByName("c2").setSpeed(150);
//            mdCity.getCarByName("c4").setSpeed(150);
            mdCity.getCarByName("c3").setSpeed(150);

            f.add(tvwmoveCar);

//        PointTest p1 = new PointTest("a", 0, 80);
//        PointTest p2 = new PointTest("b", 0, 100);
//        ArrayList<PointTest> lstpt = new ArrayList<PointTest>();
//        lstpt.add(p1);
//        lstpt.add(p2);
//        f.add(new VwMoveTest(lstpt));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            for (int i = 0; i < 1000; i++) {
                try {

                    ThreadmdCityMove thMdcity = new ThreadmdCityMove(mdCity);
                    ThreadmdCityStopAccident thMdcity2 = new ThreadmdCityStopAccident(mdCity);
                    thMdcity.run();
                    thMdcity2.run();
//                    mdCity.move();
//                    Thread.sleep(100);
                    tvwmoveCar.repaint();

                } catch (Exception ex2) {
                }

//                try {
//                    mdCity.stopAccident();
//                    Thread.sleep(100);
//
//                } catch (Exception ex3) {
//                }
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            tvwmoveCar.showErrorMessage(ex.getMessage());

        }
    }

    public static void mytest_TestVwMoveCars3() {
        try {
            JFrame f = new JFrame();
            f.setSize(700, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MdCity mdCity = new MdCity();
            mdCity.addCar(new MdCar(new Location(0, 40), "taxi.jpg", "c1"));
            mdCity.getCarByName("c1").setSpeed(200);

            //mdCity.addCar(new MdCar(new Location(100, 400), "caryellow.jpg", "c1", true, 1, 150));
            mdCity.addCar(new MdCar(new Location(300, 40), "taxi.jpg", "c2"));
            mdCity.addCar(new MdCar(new Location(440, 40), "taxi.jpg", "c3"));

            TestVwMoveCars tvwmoveCar = new TestVwMoveCars(mdCity.getLstCar());
            f.add(tvwmoveCar);

//        PointTest p1 = new PointTest("a", 0, 80);
//        PointTest p2 = new PointTest("b", 0, 100);
//        ArrayList<PointTest> lstpt = new ArrayList<PointTest>();
//        lstpt.add(p1);
//        lstpt.add(p2);
//        f.add(new VwMoveTest(lstpt));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            for (int i = 0; i < 100000; i++) {
                try {

                    for (MdCar c : mdCity.getLstCar()) {
                        //    mdCity.stopAccident(c);
                        //  // System.out.println(c.toString());
                        c.setLocation(new Location(c.getLocation().getX() + (c.getSpeed() / 10), c.getLocation().getY()));
                        if (c.getLocation().getX() > 450) {

                            c.setLocation(new Location(0, c.getLocation().getY() + 60));
                        }

                        if (c.getLocation().getY() > 650) {
                            c.setLocation(new Location(100, 0));
                        }

                        Thread.sleep(100);
                        //tvwmoveCar.setLstCar(lstCar);
                        tvwmoveCar.repaint();
                    }
                    //p.setX(p.getX() + 10);
                    //p.get += 15;
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void mytest_TestVwMoveCars2() {
        try {
            JFrame f = new JFrame();
            f.setSize(700, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MdCar c1 = new MdCar(new Location(0, 40), "taxi.jpg", "c1");
            c1.setSpeed(200);
            MdCar c2 = new MdCar(new Location(300, 40), "taxi.jpg", "c2");
            MdCar c3 = new MdCar(new Location(440, 40), "taxi.jpg", "c3");

            ArrayList<MdCar> lstCar = new ArrayList<MdCar>();
            lstCar.add(c1);
            lstCar.add(c2);
            lstCar.add(c3);
            TestVwMoveCars tvwmoveCar = new TestVwMoveCars(lstCar);
            f.add(tvwmoveCar);

//        PointTest p1 = new PointTest("a", 0, 80);
//        PointTest p2 = new PointTest("b", 0, 100);
//        ArrayList<PointTest> lstpt = new ArrayList<PointTest>();
//        lstpt.add(p1);
//        lstpt.add(p2);
//        f.add(new VwMoveTest(lstpt));
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            for (int i = 0; i < 100000; i++) {
                try {

                    for (MdCar c : lstCar) {
                        c.setLocation(new Location(c.getLocation().getX() + (c.getSpeed() / 10), c.getLocation().getY()));
                        if (c.getLocation().getX() == 450) {
                            c.setLocation(new Location(0, c.getLocation().getY() + 60));
                        }

                        Thread.sleep(100);
                        tvwmoveCar.setLstCar(lstCar);
                        tvwmoveCar.repaint();
                    }
                    //p.setX(p.getX() + 10);
                    //p.get += 15;
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void mytest_TestVwMoveCars() {
        JFrame f = new JFrame();
        f.setSize(700, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MdCar c1 = new MdCar(new Location(0, 40), "taxi.jpg", "c1");
        MdCar c2 = new MdCar(new Location(300, 40), "taxi.jpg", "c2");
        MdCar c3 = new MdCar(new Location(440, 40), "taxi.jpg", "c3");

        ArrayList<MdCar> lstCar = new ArrayList<MdCar>();
        lstCar.add(c1);
        lstCar.add(c2);
        lstCar.add(c3);
        f.add(new TestVwMoveCars(lstCar));
//        PointTest p1 = new PointTest("a", 0, 80);
//        PointTest p2 = new PointTest("b", 0, 100);
//        ArrayList<PointTest> lstpt = new ArrayList<PointTest>();
//        lstpt.add(p1);
//        lstpt.add(p2);
//        f.add(new VwMoveTest(lstpt));
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void taxiMove() {
        VwCityView cv2 = new VwCityView();

        cv2.move();
    }

    public static void testTextMove() {
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PointTest p1 = new PointTest("a", 0, 80);
        PointTest p2 = new PointTest("b", 0, 100);
        ArrayList<PointTest> lstpt = new ArrayList<PointTest>();
        lstpt.add(p1);
        lstpt.add(p2);
        f.add(new VwMoveTest(lstpt));
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

}
