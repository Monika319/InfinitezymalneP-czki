package millikanModel;

import java.util.Random;

import gui.ElectricField;
import gui.MillikanFrame;

import javax.swing.*;

public class OilDrop
{
    private double radius;
    private double charge;
    private double oilDensity;
    private double v1;
    private double v2;
    private double y;
    private double v;
    private MillikanFrame frame;

    public OilDrop(double rMin, double rMax, int qMin, int qMax, MillikanFrame mf)
    {
        frame = mf;
        v1 = 0;
        v2 = 0;
        v = 0;
        // heating oil density by
        // http://www.engineeringtoolbox.com/liquids-densities-d_743.html
        oilDensity = 920;
        Random generator = new Random();
        radius = rMin + Math.abs(rMax - rMin) * generator.nextDouble();
        y = radius;
        charge = Constants.e
                * (qMin + generator.nextInt(Math.abs(qMax - qMin)));
    }

    public void move()
    {
        //Mnoznik 10E6 aby przeskalowac rzeczywiste jednostki [metr] na piksele
        y += 10E6 * (frame.getT() * v);
        if(y<0) y=0;
        System.out.println(y);
        //System.out.println(y);
        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = 4 / 3 * Math.PI * Math.pow(radius, 3) * oilDensity;
        double u = Constants.g / k * (m - Constants.airDensity * 4 / 3 * Math.PI * Math.pow(radius, 3));
        double a=Constants.g*(1-(Constants.airDensity/oilDensity))-k/m*v;
        if (y < frame.getP1().getC().getY1())
        {

            v+=a*frame.getT();
            if(v>u)
            {
                v=u;
            }
        }
        else if(y < frame.getP1().getC().getY2())
        {
            double A=a+charge/m*frame.getP1().getC().getE();
//            System.out.println(A);
//            System.out.println(v);
//            System.out.println(y);
            double w=u+charge/k*frame.getP1().getC().getE();
            v+=A* frame.getT();
            if(v>w) v=w;
        }
        else v=0;
//        System.out.println(y);
//        System.out.print("__");
//        System.out.print(v);
//        System.out.print("__");


    }

    public double getRadius()
    {
        return radius;
    }

    public double getCharge()
    {
        return charge;
    }

    public double getOilDensity()
    {
        return oilDensity;
    }

    public double getV1()
    {
        return v1;
    }

    public void setV1(double v1)
    {
        this.v1 = v1;
    }

    public double getV2()
    {
        return v2;
    }

    public void setV2(double v2)
    {
        this.v2 = v2;
    }

    public double getY()
    {
        return y;
    }

    public double getV()
    {
        return v;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
