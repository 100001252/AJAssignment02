
import model.*;
import view.*;
import helper.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XC8184
 */
public class Test2_ver02 {//allthread functions are inside test1

    public static void main(String[] args) {

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
            //-----------define all cars
            mdCity.addCar(new MdCar(new Location(180, 100), "taxi-break.jpg", "c2"));
            mdCity.addCar(new MdCar(new Location(440, 100), "taxi.jpg", "c3"));
            mdCity.addCar(new MdCar(new Location(0, 100), "taxi.jpg", "c1"));
            mdCity.addCar(new MdCar(new Location(0, 200), "taxi.jpg", "c4"));
            mdCity.addCar(new MdCar(new Location(180, 200), "taxi.jpg", "c5"));
            mdCity.addCar(new MdCar(new Location(440, 200), "taxi.jpg", "c6"));
            mdCity.getCarByName("c1").setSpeed(100);
            mdCity.getCarByName("c2").setSpeed(60);
            mdCity.getCarByName("c3").setSpeed(40);
            mdCity.getCarByName("c5").setSpeed(100);
            mdCity.getCarByName("c6").setSpeed(110);
            //-------------end defining cars
            f.add(tvwmoveCar);
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

}
