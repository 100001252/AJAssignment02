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
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author XC8184
 */
public class TestVwMoveCars extends JPanel {

    private Graphics g;
    private int x;
    private int y;
    private ArrayList<MdCar> lstCar;

    public TestVwMoveCars(ArrayList<MdCar> lscar) {
        lstCar = new ArrayList<MdCar>();
        lstCar = lscar;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd = (Graphics2D) g;
        //----------------------------------draw line for surface
//                    for (int i = 0, x = 0; x < 700; i++, x = i * xScale) {
        g.drawLine(0, 117, 900, 117);
        for (int i = 0; i < 900; i++) {
            g.drawLine(i, 117, i + 1, 119);
        }
        g.drawLine(0, 217, 900, 217);
        for (int i = 0; i < 900; i++) {
            g.drawLine(i, 217, i + 1, 219);
        }
//            }
//            for (int i = 0, y = 0; y < size.height; i++, y = i * yScale) {
//                g.drawLine(0, y, size.width - 1, y);
//            }

//--------------------draw line for surface
        for (MdCar c : getLstCar()) {

            gd.drawString(c.getName() + "(" + Integer.toString(c.getSpeed()) + ")" + Integer.toString(c.getDistanceFromOrigin()) + "-->" + Integer.toString(c.getDistanceToFrontCar()), c.getLocation().getX() + 10, c.getLocation().getY() - 5);
            g.drawImage(new ImageIcon(getClass().getResource("images/" + c.getImgName())).getImage(), c.getLocation().getX(), c.getLocation().getY(), this);
            JButton btn = new JButton(c.getName(), new ImageIcon(getClass().getResource("images/" + c.getImgName())));
            this.add(btn);

            repaint();
        }

    }

//      public void paint(Graphics g) {//original
//        super.paint(g);
//        Graphics2D gd = (Graphics2D) g;
//        for (MdCar c : lstCar) {
//            //gd.drawString("Car Name " + c.getName() + " my y=" + Integer.toString(c.getLocation().getX()), c.getLocation().getX(), c.getLocation().getY() + 20);
//            gd.drawString(c.getName() + "(" + Integer.toString(c.getSpeed()) + ")" + Integer.toString(c.getLocation().getX()), c.getLocation().getX() + 10, c.getLocation().getY() - 5);
//            g.drawImage(new ImageIcon(getClass().getResource("images/" + c.getImgName())).getImage(), c.getLocation().getX(), c.getLocation().getY(), this);
//            try {
//                Thread.sleep(100);
//                c.setLocation(new Location(c.getLocation().getX() + 10, c.getLocation().getY()));
//                if (c.getLocation().getX() == 450) {
//                    c.setLocation(new Location(0, c.getLocation().getY() + 60));
//                }
//                //p.setX(p.getX() + 10);
//                //p.get += 15;
//            } catch (Exception e) {
//            }
//            repaint();
//        }
//
//    }
    /**
     * Draw the image for a particular item.
     */
    public void drawImage(int x, int y, Image image) {
//        g.drawImage(image, x * xScale + 1, y * yScale + 1,
//                xScale - 1, yScale - 1, this);
        g.drawImage(image, x, y,
                1, 1, this);
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
     * @return the lstCar
     */
    public ArrayList<MdCar> getLstCar() {
        return lstCar;
    }

    /**
     * @param lstCar the lstCar to set
     */
    public void setLstCar(ArrayList<MdCar> lstCar) {
        this.lstCar = lstCar;
    }

    public void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, null, JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.OK_OPTION);
    }

}
