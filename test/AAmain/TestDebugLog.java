/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAmain;

import helper.DebugLog;
import java.util.ArrayList;

/**
 *
 * @author XC8184
 */
public class TestDebugLog {

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
            lstfile.add("another one");
            DebugLog.saveData(lstfile);
            DebugLog.saveData2(lstfile);
            //IO_Support.saveData("C:\\zzzzjava\\aadworks\\javaDebug.txt", lstfile);
            DebugLog.appendData("here is my first test for debuging 02");
            DebugLog.appendData("here is my first test for debuging 03");
            DebugLog.appendData("here is my first test for debuging 04");
            DebugLog.appendData("here is my first test for debuging 05");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
