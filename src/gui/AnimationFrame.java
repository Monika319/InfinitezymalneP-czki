package gui;

import millikanModel.Capacitor;
import millikanModel.OilDrop;
import millikanModel.Photodetector;

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
        //  pd1=new Photodetector(163,140);
        // pd1=new Photodetector(271,311);
        //   pd1.registerTime();
        C = new Capacitor(270, 470);
        // pd2 = new Photodetector(380, 420);
        pd2 = new Photodetector(273, 313);
        oilDrop = new OilDrop(1E-7, 2E-7, 1, 1000, frame);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.currentDrop.move();
                //oilDrop.move();

                repaint();

            }
        });


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        pd1.paintPhotoDetector(g, this);
        pd2.paintPhotoDetector(g, this);
        g.setColor(Color.yellow);
        g.fillOval((int) ((this.getWidth() / 2) - ballDiameter / 2), (int) Math.round(frame.currentDrop.getY() * 10E6), ballDiameter, ballDiameter);
        C.paintCapacitor(g, this);

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
