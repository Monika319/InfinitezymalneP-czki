package gui;

import millikanModel.Capacitor;
import millikanModel.OilDrop;
import millikanModel.Photodetector;
import millikanModel.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AnimationFrame extends JPanel {
    // private JPanel animationPanel;
    private MillikanFrame frame;
    private Photodetector pd1;
    private Photodetector pd2;
    private Capacitor C;
    private int ballDiameter;
    protected boolean initialize = false;
    Timer timer;
    private OilDrop oilDrop;

    AnimationFrame(MillikanFrame mf) {
        super(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(new java.awt.Container());


        ballDiameter = 30;
        frame = mf;
        pd1 = new Photodetector(110, 150);
        C = new Capacitor(270, 470);
        pd2 = new Photodetector(280, 320);
        oilDrop = new OilDrop(frame);
        final Test test=new Test(oilDrop,C,pd1,pd2);

        timer = new Timer(100, new ActionListener() {
            int i=0;
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                frame.currentDrop.move();
                repaint();
                test.setDrop(frame.currentDrop);
//                test.test();
            }
        });


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        pd1.paintPhotoDetector(g, this);
        pd2.paintPhotoDetector(g, this);
        g.setColor(Color.yellow);
        Double y=new Double(frame.currentDrop.getY());
        g.fillOval(((this.getWidth() / 2) - ballDiameter / 2), y.intValue(), ballDiameter, ballDiameter);
//        System.out.println("Frame y = " + y.intValue());
        C.paintCapacitor(g, this);

    }
    public void reset()
    {
        pd1.reset();
        pd2.reset();
    }
    public Capacitor getCapacitor() {
        return C;
    }

    public Photodetector getPd1() {
        return pd1;
    }

    public int getBallDiameter() {
        return ballDiameter;
    }

    public Photodetector getPd2() {
        return pd2;
    }

    public Capacitor getC() {
        return C;
    }


}
