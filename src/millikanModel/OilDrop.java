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
    double a = 0;
    double A = 0;
    double w;
    //new OilDrop(0.5*10E-4, 1*10E-4, 1, 1000, this)
    public OilDrop(MillikanFrame mf ) {
        frame = mf;
        v1 = 0.0;
        System.out.println(v1);
        v2 = 0.0;
        v = 0;
        /* heating oil density by
        http://www.engineeringtoolbox.com/liquids-densities-d_743.html*/
        oilDensity = 920;
        Random generator = new Random();
        radius = 0.5*10E-4 + Math.abs(1*10E-4 - 0.5*10E-4) * generator.nextDouble();
        // System.out.println("wreszcie ten promień"+radius);
        //   radius=10e-4;
        y = radius;
        charge = Constants.e
                * (10 + generator.nextInt(Math.abs(1000 - 10)));
    }

    public void move() {
        //System.out.println("RADIUS "+radius);
        y += (0.1 * v);
        if (y < 0)
        {
            y = 0;
            v=0;
        }

        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        //u-prędkość przy a=0 w ruchu swobodnym
        double u = (Constants.g / k) * (m - Constants.airDensity * (4 / 3) * Math.PI * Math.pow(radius, 3));
//        System.out.println("predkosc v " + v);
//        System.out.println("poloenie y " + y);
        if ((y * Constants.normalizationConst) < frame.getP1().getC().getY1()) {
//            System.out.println("a przed obl 1 petla = :" + Double.toString(a));
            a = Constants.g * (1 - (Constants.airDensity / oilDensity)) - (k / m) * v;
//            System.out.println("a po obl 1 petla = :" + Double.toString(a));
            v += a * 0.1;
            if(Math.abs((y * Constants.normalizationConst)-frame.getP1().getPd1().getY1())<1)
                frame.getP1().getPd1().setT1();
            if(Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY2())<1)
                frame.getP1().getPd1().setT2();
//            System.out.println("Predkosc v:" + Double.toString(v));
//            System.out.println("a = :" + Double.toString(a));
//            System.out.println("Czas t:"+Double.toString(frame.getT()));
//            System.out.println("Polozenie y:" + Double.toString(y));
//            System.out.println("przyspieszenie a " + a);
        } else if (((y * Constants.normalizationConst) < frame.getP1().getC().getY2()) && ((y * Constants.normalizationConst) > frame.getP1().getC().getY1())) {
//            System.out.println("y przeliczone: " + y + "wymiar: " + frame.getP1().getC().getY2());
//            System.out.println("A przed obl 2 petla = :" + Double.toString(A));
            A = (charge / m) * frame.getP1().getC().getE() - a;
//            System.out.println("pole elektryczne"+frame.getP1().getC().getE());
//            System.out.println("A po obl 2 petla = :" + Double.toString(A));
            // System.out.println("przyspieszenie A:" + Double.toString(A));

            w = u - (charge / k) * frame.getP1().getC().getE();
            // System.out.println("predkosc w:" + w);
//            System.out.println("predkosc v before:" + v);
//            System.out.println("przyspieszenie A " + A);
            v += A * 0.1;
            if(Math.abs((y * Constants.normalizationConst)-frame.getP1().getPd2().getY1())<2)
                frame.getP1().getPd2().setT1();
            if(Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY2())<2)
                frame.getP1().getPd2().setT2();
//            System.out.println("Predkosc v ujemne:" + Double.toString(v));
//            System.out.println("Polozenie y:" + Double.toString(y));
            //   System.out.println("v: "+Double.toString(v));
        } else v = 0;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.drawLine((int) radius, (int) Math.round(frame.currentDrop.getY()), (int) (2 * radius), (int) (2 * radius));
    }

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
}
