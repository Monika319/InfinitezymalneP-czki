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
    int t1Counter = 0;
    int t2Counter = 0;

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
        frame.currentDrop = this;
    }

    public void move() {
        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        w2 = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity)) - charge / k * Math.abs(frame.getP1().getC().getE());
        w = m * (Constants.g / k) * ((Constants.airDensity / oilDensity) - 1) + (charge * frame.getP1().getC().getE() / k);
        y += (0.1 * v);
        isMoving = true;
        if (y < 0) {
            y = radius;
            //v = 0;
        }

        double u = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity));
        // w2 = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity)) - charge / k * Math.abs(frame.getP1().getC().getE());
        // w = m * (Constants.g / k) * ((Constants.airDensity / oilDensity) - 1) + (charge * frame.getP1().getC().getE() / k);

        if ((y * Constants.normalizationConst) < frame.getP1().getC().getY1()) {
            t2Counter = 0;

            isMoving = true;

            v = u;
            if (y * Constants.normalizationConst >= frame.getP1().getPd1().getY1() && (y * Constants.normalizationConst <= frame.getP1().getPd1().getY2())) {
                t1Counter++;
                //     System.out.println("Printuję t1Counter: " + t1Counter);
            } else {
                t1Counter = 0;
            }
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY1()) < 2) {
                frame.getP1().getPd1().setT1();

            }

            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY2()) < 2) {
                frame.getP1().getPd1().setT2(t1Counter * 0.1);
                System.out.println("Czas t1 z symulacji: " + Integer.toString(t1Counter));
            }
        } else if ((((y - radius) * Constants.normalizationConst) < frame.getP1().getC().getY2()) && ((y * Constants.normalizationConst) >= frame.getP1().getC().getY1())) {
            t1Counter = 0;
            isMoving = true;
            if ((Math.abs(frame.getP1().getC().getE()) > m * (Constants.g / charge) * (1 - (Constants.airDensity / oilDensity))))
                v = w;
            else
                v = w2;

            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY1()) < 2) {
                frame.getP1().getPd2().setT1();
            }
            if (y * Constants.normalizationConst >= frame.getP1().getPd2().getY1() && (y * Constants.normalizationConst <= frame.getP1().getPd2().getY2())) {
                t2Counter++;
                System.out.println("Printuję t2Counter: " + t2Counter);
            } else {
                t2Counter = 0;
            }
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY2()) < 2) {
                frame.getP1().getPd2().setT2(t2Counter * 0.1);
            }
        } else {
            if ((Math.abs(frame.getP1().getC().getE()) > m * (Constants.g / charge) * (1 - (Constants.airDensity / oilDensity))))
                v = w;
            else v = 0;
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

    public double getW2() {
        return w2;
    }
}
