/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author XC8184
 */
public class DebugLog {

    private int debugNumber;//this is to pass a debug and only if

    private static Scanner in = new Scanner(System.in);
    private static String filename1 = "C:\\zzzzjava\\aadworks\\javaDebug.txt";
    private String path;
    private String pathtoWrite;
    private ArrayList<String> textData;

    public double[][] matr = new double[64][1];
    //           read              file

    public static String getString(String prompt) {
        System.out.print(prompt + " ");
        return in.nextLine();
    }

    public static Double getDouble(String prompt) {
        Double d = 0.00;
        while (true) {
            try {
                System.out.print(prompt + " ");
                d = Double.parseDouble(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Not a valid Double");
            }
        }
        return d;
    }

    public static Integer getInteger(String prompt) {
        Integer i = 0;
        while (true) {
            try {
                System.out.print(prompt + " ");
                i = Integer.parseInt(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }

    public static void println(String toPrint) {
        System.out.println(toPrint);
    }

    public static void appendData(ArrayList<String> data) throws IOException {
        String fileName = filename1;
        try {
            ArrayList<String> oldData = readData(fileName);
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for (String os : oldData) {
                pw.println(os);
            }
            for (String s : data) {
                pw.println(s);
            }
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void appendData(String vString) throws IOException {
        String fileName = filename1;
        try {
            ArrayList<String> oldData = readData(fileName);
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for (String os : oldData) {
                pw.println(os);
            }

            pw.println(vString);

            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveData(ArrayList<String> data) throws IOException {
        String fileName = filename1;
        PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        for (String s : data) {
            pw.println(s);
        }
        pw.close();
    }

    public static ArrayList<String> readData(String fileName) throws Exception {
        if (fileName.equals("")) {
            fileName = filename1;
        }
        ArrayList<String> data = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));

        String temp = in.readLine();
        while (temp != null) {
            data.add(temp);
            temp = in.readLine();
        }
        in.close();
        return data;
    }

    public static Integer getInteger() {
        Integer i = 0;
        while (true) {
            try {
                System.out.print("Please enter an integer");
                i = Integer.parseInt(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }

    public double findLow(double[][] me) {
        double res = 1800;

        for (int i = 0; i < me.length; i++) {
            if (me[i][0] < res) {
                res = me[i][0];
            }
        }
        return res;
    }

}
