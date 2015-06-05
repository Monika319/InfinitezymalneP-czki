package millikanModel;

import java.awt.*;
import java.util.Random;

import gui.ElectricField;
import gui.MillikanFrame;

import javax.swing.*;

public class OilDrop {
    private double radius;
    private double charge;
    private double oilDensity;
    private double v1;
    private double v2;
    private double y;
    private double v;
    //private final static double normalizationConst = 1;
    private MillikanFrame frame;
    public boolean isMoving = false;
    double a = 0;
    double A = 0;
    double w;
    double w2;

    //new OilDrop(0.5*10E-4, 1*10E-4, 1, 1000, this)
    public OilDrop(MillikanFrame mf) {
        frame = mf;
        v1 = 0.0;
        System.out.println(v1);
        v2 = 0.0;
        v = 0;
        /* heating oil density by
        http://www.engineeringtoolbox.com/liquids-densities-d_743.html*/
        oilDensity = 820;
        Random generator = new Random();
        //już w metrach
        radius = 0.5 * 10E-7 + Math.abs(1 * 10E-7 - 0.5 * 10E-7) * generator.nextDouble();
        y = radius;
        charge = Constants.e
                * (100 + generator.nextInt(Math.abs(10000 - 1000)));
        frame.currentDrop=this;
     }

    public void move() {
        y += (0.1 * v);
        isMoving = true;
        if (y < 0) {
            y = radius;
            //v = 0;
        }

        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        double u = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity));
//        System.out.println("u:" + u);
        w = m * (Constants.g / k) * ((Constants.airDensity / oilDensity)-1) + (charge * frame.getP1().getC().getE() / k);
        w2=m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity))-charge/k*Math.abs(frame.getP1().getC().getE());
        if ((y  * Constants.normalizationConst) < frame.getP1().getC().getY1())
        {
            //  a = Constants.g * (1 - (Constants.airDensity / oilDensity)) - (k / m) * v;
//            a = Constants.g * (1 - (Constants.airDensity / oilDensity));
//            System.out.println("(k/m)" + (k / m));
            //v += a * 0.1;
            isMoving = true;
            //w=0;
            v = u;
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY1()) < 2)
                frame.getP1().getPd1().setT1();
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY2()) < 2)
                frame.getP1().getPd1().setT2();
//            System.out.println("polozenie y: " + y);
//            System.out.println("Predkosc v:" + Double.toString(v));
        } else if ((((y-radius) * Constants.normalizationConst) < frame.getP1().getC().getY2()) && ((y *Constants.normalizationConst) >= frame.getP1().getC().getY1())) {
//            System.out.println("Wchodzę do drugiej pętli! ");
            isMoving = true;
//            A = (charge / m) * frame.getP1().getC().getE() - a;
            //  w = u - (charge / k) * frame.getP1().getC().getE();
//            System.out.println("otrzymane E*ladunek: " + charge * frame.getP1().getC().getE());
//            w = m * (Constants.g / k) * ((Constants.airDensity / oilDensity)-1) + (charge * frame.getP1().getC().getE() / k);
            if((Math.abs(frame.getP1().getC().getE())>m * (Constants.g /charge) * (1-(Constants.airDensity / oilDensity))))
                v=w;
            else
            v=w2;
//            System.out.println("polozenie y: " + y);
//            System.out.println("Predkosc v:" + Double.toString(v));
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY1()) < 2)
                frame.getP1().getPd2().setT1();
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY2()) < 2)
                frame.getP1().getPd2().setT2();
        } else {
            if((Math.abs(frame.getP1().getC().getE())>m * (Constants.g /charge) * (1-(Constants.airDensity / oilDensity))))
                v=w;
            else v=0;
        }
    }

//    public void paintComponent(Graphics g) {
//        g.setColor(Color.yellow);
//        g.drawLine((int) radius, (int) Math.round(frame.currentDrop.getY()-radius), (int) (2 * radius), (int) (2 * radius));
//    }

    public double getRadius() {
        return radius;
    }

    public double getCharge() {
        return charge;
    }

    public double getOilDensity() {
        return oilDensity;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public double getY() {
        return y;
    }

    public double getV() {
        return v;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double geta() {
        return a;
    }

    public double getA() {
        return A;
    }

    public double getW() {
        return w;
    }

    public double getW2()
    {
        return w2;
    }
}
