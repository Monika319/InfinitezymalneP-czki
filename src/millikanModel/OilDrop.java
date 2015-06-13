package millikanModel;

import java.awt.*;
import java.util.ArrayList;
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
    //    public boolean isMoving = false;
    double a = 0;
    double A = 0;
    double w;
    double w2;
    int counter;

    public OilDrop(MillikanFrame mf) {
        frame = mf;
        v1 = 0.0;
        System.out.println(v1);
        v2 = 0.0;
        v = 0;
        counter = 0;
        /* heating oil density by
        http://www.engineeringtoolbox.com/liquids-densities-d_743.html*/
        oilDensity = 820;
        Random generator = new Random();
        //już w metrach
        radius = 0.5 * 10E-7 + Math.abs(1 * 10E-7 - 0.5 * 10E-7) * generator.nextDouble();
        y = radius;
        System.out.println("Lista intów: "+mf.getNumbers());
//            charge = Constants.e
//                    * (100 + generator.nextInt(Math.abs(10000 - 1000)));

       // charge=Constants.e*generator.nextInt(20);
        System.out.println("Losowany nextint od charge: "+(charge/Constants.e));
        System.out.println("size: "+mf.getNumbers().size());
        int losowy=generator.nextInt(mf.getNumbers().size());
        charge=mf.getNumbers().get(losowy)*Constants.e;
        mf.getNumbers().remove(losowy);

        frame.currentDrop = this;
    }

    public void move() {
        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        //PREDKOSC WOLNIEJSZEGO SPADKU
        w2 = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity)) - charge / k * Math.abs(frame.getP1().getC().getE());
        //PREDKOSC WZNOSZENIA
        w = -1 * (m * (Constants.g / k) * ((Constants.airDensity / oilDensity) - 1) + (charge * Math.abs(frame.getP1().getC().getE()) / k));
        y += (0.1 * v);
        counter++;


//        isMoving = true;
        if (y < 0) {
            y = radius;
        }
        //PREDKOSC SPADKU SWOBODNEGO
        double u = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity));
        //KULKA JEST PONAD KONDENSATOREM
        if ((y * Constants.normalizationConst) < frame.getP1().getC().getY1()) {
//           isMoving = true;
            v = u;
            //WARUNKI SPRAWDZAJACE ODLEGLOSC OD WIAZKI LASERA W PIERWSZEJ FOTOKOMORCE
//            if ((Math.abs(y * Constants.normalizationConst) > frame.getP1().getPd1().getY1()) && ((Math.abs(y * Constants.normalizationConst)) < frame.getP1().getPd1().getY2())) {
//                counter++;
//                System.out.println("COUNTER: " + counter);
//
//            }
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY1()) < 2) {
                frame.getP1().getPd1().setT1(counter * 0.1);
                //frame.getP1().getPd1().setT1(0);
            }

            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY2()) < 2) {
                frame.getP1().getPd1().setT2(counter * 0.1);
            }
//            if (Math.abs(y * Constants.normalizationConst) > frame.getP1().getPd1().getY2()) {
//                counter = 0;
//            }

        }
        //KULKA JEST W KONDENSATORZE
        else if ((((y - radius) * Constants.normalizationConst) < frame.getP1().getC().getY2()) && ((y * Constants.normalizationConst) >= frame.getP1().getC().getY1())) {

//            isMoving = true;
            //WARUNEK SPRAWDZA,CZY NATEZENIE POLA JEST DOSTATECZNE, BY KULKA SIE UNOSILA
            if ((Math.abs(frame.getP1().getC().getE()) > m * (Constants.g / charge) * (1 - (Constants.airDensity / oilDensity))))
                v = w;
                //JESLI NIE TO KULKA BEDZIE WOLNIEJ SPADAC Z PREDKOSCIA W2
            else
                v = w2;
            //WARUNKI SPRAWDZAJACE ODLEGLOSC OD WIAZKI LASERA W DRUGIEJ FOTOKOMORCE
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY1()) < 2) {
                frame.getP1().getPd2().setT1(counter * 0.1);
            }
            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY2()) < 2) {
                frame.getP1().getPd2().setT2(counter * 0.1);
            }
        }
        //KULKA JEST NA DNIE KONDENSATORA
        else {
            //WARUNEK SPRAWDZA,CZY NATEZENIE POLA JEST DOSTATECZNE, BY KULKA SIE UNOSILA
            if ((Math.abs(frame.getP1().getC().getE()) > m * (Constants.g / charge) * (1 - (Constants.airDensity / oilDensity))))
                v = w;
                //KULKA BEDZIE SPOCZYWAC NA DNIE KONDENSATORA
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
