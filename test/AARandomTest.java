/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.*;

/**
 *
 * @author XC8184
 */
public class AARandomTest {

    public static void main(String[] args) {
        //test1_formulaDistance(1, 2, 3, 10);
        test2_timerclass();
    }

    public static void test2_timerclass() {
        MdTimer mdt = new MdTimer();

    }

    public static void test1_formulaDistance(double x1, double y1, double x2, double y2) {
        double result = 0;
        //(x1-x2)
        //double firstpart=(x1-x2)^2
        double part1 = Math.pow((x1 - x2), 2);
        //double secondpart=(y1-y2)^2
        double part2 = Math.pow((y1 - y2), 2);
        result = Math.sqrt(part1 + part2);
        // System.out.println("test1-result= part1and2(" + part1 + ", " + part2 + " )" + result + " | " + Math.pow(3, 4) + "| ");
    }

}
