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
    private final static double normalizationConst = 1;
    private MillikanFrame frame;

    public OilDrop(double rMin, double rMax, int qMin, int qMax, MillikanFrame mf) {
        frame = mf;
        v1 = 0.0;
        System.out.println(v1);
        v2 = 0.0;
        v = 0;
        // heating oil density by
        // http://www.engineeringtoolbox.com/liquids-densities-d_743.html
        oilDensity = 920;
        Random generator = new Random();
        radius = rMin + Math.abs(rMax - rMin) * generator.nextDouble();

        // System.out.println("wreszcie ten promień"+radius);
        //   radius=10e-4;
        y = radius;
        charge = Constants.e
                * (qMin + generator.nextInt(Math.abs(qMax - qMin)));
    }

    public void move() {
        //Mnoznik 10E6 aby przeskalowac rzeczywiste jednostki [metr] na piksele
        //System.out.println(this.getV1());
        //y += 10E6 * (0.1 * v);
        //System.out.println("RADIUS "+radius);
        y += (0.1 * v);
        if (y < 0) y = 0;

        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        //u-prędkość przy a=0 w ruchu swobodnym
        double u = (Constants.g / k) * (m - Constants.airDensity * (4 / 3) * Math.PI * Math.pow(radius, 3));
//        System.out.println("predkosc v " + v);
//        System.out.println("poloenie y " + y);

//        double a = Constants.g * (1 - (Constants.airDensity / oilDensity)) - k / m * v;
        //  double a=Constants.g-k/m*v;
        //  double u =m / k * (Constants.g -a);
        double a = 0;
        double A = 0;
        // double A=Constants.g+k/m*v;
        double w;


        if ((y * normalizationConst) < frame.getP1().getC().getY1()) {
            System.out.println("a przed obl 1 petla = :" + Double.toString(a));
            a = Constants.g * (1 - (Constants.airDensity / oilDensity)) - (k / m) * v;
            System.out.println("a po obl 1 petla = :" + Double.toString(a));
            v += a * 0.1;
//            System.out.println("Predkosc v:" + Double.toString(v));
//            System.out.println("a = :" + Double.toString(a));
//


            //    System.out.println("Czas t:"+Double.toString(frame.getT()));


//            System.out.println("Polozenie y:" + Double.toString(y));
//            System.out.println("przyspieszenie a " + a);


//            if ((v > u)&&((y)<=50)) {
//                v = u;
//            }
            // System.out.println("v: "+Double.toString(v));
            System.out.println("y przeliczone: " + y * 10E6 + "wymiar: " + frame.getP1().getC().getY1());
        } else if (((y * normalizationConst) < frame.getP1().getC().getY2()) && ((y * normalizationConst) > frame.getP1().getC().getY1())) {
            System.out.println("y przeliczone: " + y + "wymiar: " + frame.getP1().getC().getY2());
            //  A=A-charge/m*frame.getP1().getC().getE();

            // w=u+charge/k*frame.getP1().getC().getE();
            //poniższe 2 linijki są poprawne

            // w=u+charge/k*frame.getP1().getC().getE()-k*v; to działająca linijka na w

            // w=charge/k*frame.getP1().getC().getE()+m/k*(A-Constants.g);
            System.out.println("A przed obl 2 petla = :" + Double.toString(A));
            A = (charge / m) * frame.getP1().getC().getE() - a;
            System.out.println("A po obl 2 petla = :" + Double.toString(A));
            // System.out.println("przyspieszenie A:" + Double.toString(A));

            w = u - (charge / k) * frame.getP1().getC().getE();
            // System.out.println("predkosc w:" + w);
//            System.out.println("predkosc v before:" + v);
//            System.out.println("przyspieszenie A " + A);
            // v += A * frame.getT()*10;
            v += A * 0.1;
        System.out.println("Predkosc v ujemne:" + Double.toString(v));
//            System.out.println("Polozenie y:" + Double.toString(y));
            //   System.out.println("v: "+Double.toString(v));
//            if (v > w){
//                v = w;
//            }
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
