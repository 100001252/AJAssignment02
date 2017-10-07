/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAmain;

import helper02.*;
import java.util.*;

/**
 *
 * @author XC8184
 */
public class TestIo_Support {

    public static void main(String[] args) {// ToDO
        try {
            testWriteToFile("this string will be written");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testWriteToFile(String s) {
        try {
            ArrayList<String> lstfile = new ArrayList<String>();
            lstfile.add(s);
            IO_Support.saveData("C:\\zzzzjava\\aadworks\\javaDebug.txt", lstfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
