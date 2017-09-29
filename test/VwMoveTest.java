/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.*;
import logic.*;
import helper.*;
import controller.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author XC8184
 */
public class VwMoveTest extends JPanel {

    private int x;
    private int y;
    private ArrayList<PointTest> lstpoint;

    public VwMoveTest(ArrayList<PointTest> lspoint) {
        lstpoint = new ArrayList< PointTest>();
        lstpoint = lspoint;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd = (Graphics2D) g;
        for (PointTest p : getLstpoint()) {
            gd.drawString("Mobe text " + p.getName() + " my y=" + Integer.toString(p.getY()), p.getX(), p.getY());
            try {
                Thread.sleep(100);
                p.setX(p.getX() + 10);
                //p.get += 15;
            } catch (Exception e) {
            }
            repaint();
        }

    }

    public void moveTo(int x, int y) {

    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the lstpoint
     */
    public ArrayList<PointTest> getLstpoint() {
        return lstpoint;
    }

    /**
     * @param lstpoint the lstpoint to set
     */
    public void setLstpoint(ArrayList<PointTest> lstpoint) {
        this.lstpoint = lstpoint;
    }

}
