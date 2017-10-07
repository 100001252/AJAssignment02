/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAmain;

import helper02.DebugLog;
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
            //IO_Support.saveData("C:\\zzzzjava\\aadworks\\javaDebug.txt", lstfile);
            DebugLog.appendData(lstfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
