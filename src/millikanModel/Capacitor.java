package millikanModel;

import gui.AnimationFrame;

import java.awt.*;

/**
 * Created by rafal on 12.05.15.
 */
public class Capacitor
{
    private double voltage;
    private double E;
    private int d;
    public Capacitor()
    {
        d=100;
        voltage=0;
        makeField();
    }
    public Capacitor(int a)
    {
        d=a;
        voltage=0;
        makeField();
    }
    void makeField()
    {
        E=voltage/d;
    }
    public void paintCapacitor(Graphics g, AnimationFrame af)
    {
        g.setColor(Color.GRAY);
        int y1=(int)Math.min(af.getPd2().getY1(),af.getPd2().getY2())-40;
        System.out.println(y1);
        int y2=(int)Math.max(af.getPd2().getY1(),af.getPd2().getY2())+80;
        g.drawLine(0,y1,(int)(af.getWidth()/2)-af.getBallDiameter(),y1);
        g.drawLine((int)(af.getWidth()/2)+af.getBallDiameter(),y1,(int)(af.getWidth()),y1);
        g.drawLine(0,y2,(int)(af.getWidth()),y2);
    }

    public double getE()
    {
        return E;
    }

    public double getVoltage()
    {
        return voltage;
    }

    public int getD()
    {
        return d;
    }

    public void setVoltage(double voltage)
    {
        this.voltage = voltage;
        makeField();
    }

    public void setD(int d)
    {
        this.d = d;
        makeField();
    }
}
