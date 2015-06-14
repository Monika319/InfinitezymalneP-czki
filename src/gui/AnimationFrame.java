package gui;

import millikanModel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
  * Responsible for Animation visualisation.
  * Paints Capacitor, 2 Photodetectors for measuring times, Oild drop with given radius.
  * Animations work is based on timer. Every 100ms the view is refreshed.
  */
public class AnimationFrame extends JPanel {

    private MillikanFrame frame;
    private Photodetector pd1;
    private Photodetector pd2;
    private Capacitor C;
    private int ballDiameter;
    protected boolean initialize =true;
    Timer timer;

    /**
     * Class for initializing painted objects and managing oil drop movement(Timer used).
     * @param mf-Millian Frame-main frame of the program
     */
    AnimationFrame(MillikanFrame mf) {

        super(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(500, 470));
        this.add(new java.awt.Container());


        ballDiameter = 30;
        frame = mf;
        pd1 = new Photodetector(110, 150);
        C = new Capacitor(270, 470);
        pd2 = new Photodetector(280, 320);

        final Test test=new Test(new OilDrop(frame),C,pd1,pd2);

        timer = new Timer(100, new ActionListener() {
//            int i=0;
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                frame.currentDrop.move();
                repaint();
                test.setDrop(frame.currentDrop);
                //WYSTARCZY TO ODKOMENTOWAC BY MIEC LOGI NA KONSOLI
//                test.test();
            }
        });


    }

    /**
     * Paints Photodetectors and oil drop.
     * @param g- Object of Class Graphics for painting photodetectors
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        pd1.paintPhotoDetector(g, this);
        pd2.paintPhotoDetector(g, this);
        g.setColor(Color.yellow);
        Double y=new Double(frame.currentDrop.getY().doubleValue()* Constants.normalizationConst);
        g.fillOval(((this.getWidth() / 2) - ballDiameter / 2), y.intValue()-ballDiameter/2, ballDiameter, ballDiameter);
        C.paintCapacitor(g, this);

    }
    public void reset()
    {
        pd1.reset();
        pd2.reset();
    }

    /**
     *
     * @return Photodetector 1 in free fall.
     */
    public Photodetector getPd1() {
        return pd1;
    }

    /**
     *
     * @return Oil drop diameter
     */
    public int getBallDiameter() {
        return ballDiameter;
    }

    /**
     *
     * @return Photodetector 2 in electric field
     */
    public Photodetector getPd2() {
        return pd2;
    }

    /**
     *
     * @return Painted capacitor.
     */
    public Capacitor getC() {
        return C;
    }

}
