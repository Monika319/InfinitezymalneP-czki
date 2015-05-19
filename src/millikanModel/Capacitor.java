package millikanModel;

import gui.AnimationFrame;
import gui.MillikanFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rafal on 12.05.15.
 */
public class Capacitor
{
    private double voltage;
    private double E;
    private int d;
    private int y1;
    private int y2;
    private int power;
    //private MillikanFrame frame;

    public Capacitor(int h1, int h2)
    {
        //frame=mf;
        power=-3;
        y1=h1;
        y2=h2;
        d=Math.abs(h2-h1);
        voltage=0;
        makeField();
    }
    void makeField()
    {
        E=-voltage/d;
        System.out.println(Double.toString(E));
    }
    public void paintCapacitor(Graphics g, AnimationFrame af)
    {
        g.setColor(Color.GRAY);
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
        this.voltage = voltage*Math.pow(10,this.power);
        makeField();
    }

    public int getY1()
    {
        return y1;
    }

    public int getY2()
    {
        return y2;
    }

}
