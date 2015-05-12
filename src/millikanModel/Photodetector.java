package millikanModel;

import gui.AnimationFrame;

import java.awt.*;

public class Photodetector
{
    private double t1;
    private double t2;
    private double y1;
    private double y2;
    private boolean on;

    public Photodetector(double h1, double h2)
    {
        y1 = h1;
        y2 = h2;
        on = false;
    }

    public void paintPhotoDetector(Graphics g, AnimationFrame af)
    {
        g.setColor(Color.darkGray);
        g.fillRoundRect(9 * af.getWidth() / 10, (int) (y1 - 5.0), af.getWidth() / 10, (int) (y2 - y1)+10, (int) ((y2 - y1) / 4), (int) ((y2 - y1) / 4));
        if (on)
        {
            g.setColor(Color.red);
            g.drawLine(0,(int)y1,(int)(9 * af.getWidth() / 10),(int)y1);
            g.drawLine(0,(int)y2,(int)(9 * af.getWidth() / 10),(int)y2);
            g.setColor(Color.green);

        }
        else g.setColor(Color.red);
        g.fillOval((int)(9.5*af.getWidth()/10),(int)Math.abs((y2+y1)/2),(int)Math.abs((y2-y1)/2),(int)Math.abs((y2-y1)/2));
    }

    public void calculateV1(OilDrop drop)
    {
        drop.setV1(Math.abs(y2 - y1) / (t2 - t1));
    }

    public void calculateV2(OilDrop drop)
    {
        drop.setV2(Math.abs(y2 - y1) / (t2 - t1));
    }

    public double getT1()
    {
        return t1;
    }

    public double getT2()
    {
        return t2;
    }

    public double getY1()
    {
        return y1;
    }

    public double getY2()
    {
        return y2;
    }

    public boolean isOn()
    {
        return on;
    }

    public void setT1(double t1)
    {
        this.t1 = t1;
    }

    public void setT2(double t2)
    {
        this.t2 = t2;
    }

    public void setOn(boolean on)
    {
        this.on = on;
    }
}
