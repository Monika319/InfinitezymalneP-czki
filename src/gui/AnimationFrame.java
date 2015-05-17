package gui;

import millikanModel.Capacitor;
import millikanModel.Photodetector;
import javax.swing.*;
import java.awt.*;


public class AnimationFrame extends JPanel
{
    // private JPanel animationPanel;
    private MillikanFrame frame;
    private Photodetector pd1;
    private Photodetector pd2;
    private Capacitor C;
    private int ballDiameter;

    AnimationFrame(MillikanFrame mf)
    {
        super(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(new java.awt.Container());


        ballDiameter=30;
        frame = mf;
        pd1 = new Photodetector(110, 150);
     //   pd1.registerTime();
        C=new Capacitor(270,470);
        pd2 = new Photodetector(380, 420);

        // animationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        pd1.paintPhotoDetector1(g, this);
        pd2.paintPhotoDetector(g, this);
        g.setColor(Color.yellow);
        g.fillOval((int) ((this.getWidth() / 2)-ballDiameter/2), (int) Math.round(frame.currentDrop.getY()), ballDiameter, ballDiameter);
        C.paintCapacitor(g,this);
    }
public Capacitor getCapacitor(){
    return C;
}
    public Photodetector getPd1()
    {
        return pd1;
    }

    public int getBallDiameter()
    {
        return ballDiameter;
    }

    public Photodetector getPd2()
    {
        return pd2;
    }

    public Capacitor getC()
    {
        return C;
    }
}
