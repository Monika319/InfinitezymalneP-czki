package gui;

import millikanModel.Photodetector;

import javax.swing.*;
import java.awt.*;


public class AnimationFrame extends JPanel
{
    // private JPanel animationPanel;
    private MillikanFrame frame;
    private Photodetector pd1;
    private Photodetector pd2;

    AnimationFrame(MillikanFrame mf)
    {
        super(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(500, 500));
        this.add(new java.awt.Container());

        frame = mf;
        pd1 = new Photodetector(100, 140);
        pd2 = new Photodetector(300, 340);
        // animationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillOval((int) (this.getWidth() / 2), (int) Math.round(frame.currentDrop.getY()), 30, 30);
        pd1.paintPhotoDetector(g, this);
        pd2.paintPhotoDetector(g, this);
    }

    public Photodetector getPd1()
    {
        return pd1;
    }

    public Photodetector getPd2()
    {
        return pd2;
    }
}
