/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.*;
import logic.*;
import helper.*;
import controller.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author XC8184
 */
public class VwCityView extends JFrame {

    // The dimensions of the GUI.
    public static final int CITY_VIEW_WIDTH = 600;
    public static final int CITY_VIEW_HEIGHT = 600;
    //  private City city;
    private CityView cityView;
    private int initialxCar = 2;
    private int initialyCar = 2;
    private int totalDistanceTravel = 0;
    private int distanceFromOrigin = 0;
    private Image emptyImage; //= new ImageIcon(getClass().getResource("/images/taxi.jpg")).getImage();

    /**
     * Constructor for objects of class CityGUI
     *
     * @param city The city whose state is to be displayed.
     */
    public VwCityView() {
        //City city argument
        //  this.city = city;
        cityView = new CityView(700, 700);
        getContentPane().add(cityView);
        setTitle("Driving View");
        setSize(CITY_VIEW_WIDTH, CITY_VIEW_HEIGHT);
        setVisible(true);

        cityView.preparePaint();

        //---------test
        emptyImage = new ImageIcon(getClass().getResource("images/taxi.jpg")).getImage();
        //  cityView.drawImage(initialxCar, initialyCar, emptyImage);
//        cityView.drawImage(20, 10, emptyImage);
//        cityView.drawImage(1, 11, emptyImage);
        // cityView.repaint();

        centerFrame();
        // this.move();
        //---------test end
    }

    public void move() {//just for test purposes zzzmove
        try {
            // System.out.println("moooooviinnngtestoption");
            for (int i = 0; i < 10000; i++) {

                if (getInitialyCar() == 2 && getInitialxCar() != 200) {
                    setInitialxCar(getInitialxCar() + 1);
                    totalDistanceTravel++;
                } else if (getInitialxCar() == 200 && getInitialyCar() != 200) {
                    setInitialyCar(getInitialyCar() + 1);
                    totalDistanceTravel++;
                } else if (getInitialyCar() == 200 && getInitialxCar() != 2) {
                    setInitialxCar(getInitialxCar() - 1);
                    totalDistanceTravel++;
                } else if (getInitialxCar() == 2 && getInitialyCar() != 2) {
                    setInitialyCar(getInitialyCar() - 1);
                    totalDistanceTravel++;
                }
//            if (initialyCar == 20) {
//                initialxCar--;
//
//            }
//            if (initialxCar == 0 && initialyCar == 20) {
//
//            }
                // cityView.drawImage(initialxCar, initialyCar, emptyImage);
                wait(100);

                // System.out.println("x=" + getInitialxCar() + " || y=" + getInitialyCar() + " | totalDistanceTraveled=" + totalDistanceTravel);
                //this.myRepaint();

                this.myRepaint();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void myRepaint() {
        // System.out.println("x=" + getInitialxCar() + " || y=" + getInitialyCar() + " | totalDistanceTraveled=" + totalDistanceTravel);
        cityView.repaint();
    }

    public void myPreparePaint() {
        cityView.preparePaint();
    }

    private void wait(int milliseconds) {//just for test purposes
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignore the exception
        }
    }

    private void centerFrame() {

        Dimension wSize = getSize();
        GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = graphicEnv.getCenterPoint();

        //int dx = centerPoint.x - wSize.width / 2;
        int dx = centerPoint.x - wSize.width / 3;
        int dy = centerPoint.y - wSize.height / 2;
        setLocation(dx, dy);
    }

    /**
     * @return the initialxCar
     */
    public int getInitialxCar() {
        return initialxCar;
    }

    /**
     * @param initialxCar the initialxCar to set
     */
    public void setInitialxCar(int initialxCar) {
        this.initialxCar = initialxCar;
    }

    /**
     * @return the initialyCar
     */
    public int getInitialyCar() {
        return initialyCar;
    }

    /**
     * @param initialyCar the initialyCar to set
     */
    public void setInitialyCar(int initialyCar) {
        this.initialyCar = initialyCar;
    }

//    /**
//     * Display the current state of the city.
//     */
//    public void mmrepaint() {
//        cityView.preparePaint();
//        Iterator items = city.getItems();
//        while (items.hasNext()) {
//            Object obj = items.next();
//            if (obj instanceof DrawableItem) {
//                DrawableItem item = (DrawableItem) obj;
//                Location location = item.getLocation();
//                cityView.drawImage(location.getX(), location.getY(), item.getImage());
//            }
//        }
//        cityView.repaint();
//    }
    /**
     * Provide a graphical view of a rectangular city. This is a nested class (a
     * class defined inside a class) which defines a custom component for the
     * user interface. This component displays the city. This is rather advanced
     * GUI stuff - you can ignore this for your project if you like.
     */
    private class CityView extends JPanel {

        private final int VIEW_SCALING_FACTOR = 20;

        private int cityWidth, cityHeight;
        private int xScale, yScale;
        private Dimension size;
        private Graphics g;
        private Image cityImage;

        /**
         * Create a new CityView component.
         */
        public CityView(int width, int height) {
            cityWidth = width;
            cityHeight = height;
            setBackground(Color.white);
            size = new Dimension(0, 0);
        }

        /**
         * Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(cityWidth * VIEW_SCALING_FACTOR,
                    cityHeight * VIEW_SCALING_FACTOR);
        }

        /**
         * Prepare for a new round of painting. Since the component may be
         * resized, compute the scaling factor again.
         */
        public void preparePaint() {
            if (!size.equals(getSize())) {  // if the size has changed...
                size = getSize();
                cityImage = cityView.createImage(size.width, size.height);
                g = cityImage.getGraphics();

                xScale = size.width / cityWidth;
                if (xScale < 1) {
                    xScale = VIEW_SCALING_FACTOR;
                }
                yScale = size.height / cityHeight;
                if (yScale < 1) {
                    yScale = VIEW_SCALING_FACTOR;
                }
            }
            g.setColor(Color.white);
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(Color.gray);
//            for (int i = 0, x = 0; x < size.width; i++, x = i * xScale) {
//                g.drawLine(x, 0, x, size.height - 1);
//            }
//            for (int i = 0, y = 0; y < size.height; i++, y = i * yScale) {
//                g.drawLine(0, y, size.width - 1, y);
//            }
        }

        /**
         * Draw the image for a particular item.
         */
        public void drawImage(int x, int y, Image image) {
            g.drawImage(image, x * xScale + 1, y * yScale + 1,
                    xScale - 1, yScale - 1, this);
        }

        /**
         * The city view component needs to be redisplayed. Copy the internal
         * image to screen.
         */
        public void paintComponent(Graphics g) {
            if (cityImage != null) {
                Dimension currentSize = getSize();
                if (size.equals(currentSize)) {
                    g.drawImage(cityImage, 0, 0, null);
                    g.drawImage(emptyImage, getInitialxCar(), getInitialyCar(), null);
                } else {
                    // Rescale the previous image.
                    g.drawImage(cityImage, 0, 0, currentSize.width, currentSize.height, null);
                    g.drawImage(emptyImage, getInitialxCar(), getInitialyCar(), null);
                }
            }
        }
    }
}
