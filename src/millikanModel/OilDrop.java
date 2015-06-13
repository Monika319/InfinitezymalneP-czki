package millikanModel;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Random;

import gui.ElectricField;
import gui.MillikanFrame;

import javax.swing.*;

public class OilDrop {
    private double radius;
    private double charge;
    private double oilDensity;
    private BigDecimal v1;
    private BigDecimal v2;
    private BigDecimal y;
    private double v;
    private double E;
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
        v1 = new BigDecimal(0.0);
        v2 = new BigDecimal(0.0);
        v = 0;
        counter = 0;
        /* heating oil density by
        http://www.engineeringtoolbox.com/liquids-densities-d_743.html*/
        oilDensity = 820;
        Random generator = new Random();
        //już w metrach
        radius = 0.5 * 10E-7 + Math.abs(1 * 10E-7 - 0.5 * 10E-7) * generator.nextDouble();
        y = new BigDecimal(radius);
        charge = Constants.e
                * (2 + generator.nextInt(Math.abs(20)));
        frame.currentDrop = this;
    }

    public void move() {
        double k = 6 * Math.PI * Constants.airViscosity * radius;
        double m = (4 / 3) * Math.PI * Math.pow(radius, 3) * oilDensity;
        //PREDKOSC WOLNIEJSZEGO SPADKU
        w2 = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity)) - charge / k * Math.abs(frame.getP1().getC().getE());
        //PREDKOSC WZNOSZENIA
        w = -1 * (m * (Constants.g / k) * ((Constants.airDensity / oilDensity) - 1) + (charge * Math.abs(frame.getP1().getC().getE()) / k));
        y=y.add(new BigDecimal(0.1 * v));
        counter++;


//        isMoving = true;
        if (y.compareTo(BigDecimal.ZERO) < 0) {
            y = new BigDecimal(radius);
        }
        //PREDKOSC SPADKU SWOBODNEGO
        double u = m * (Constants.g / k) * (1 - (Constants.airDensity / oilDensity));
        //KULKA JEST PONAD KONDENSATOREM
        if ((y.multiply(new BigDecimal(Constants.normalizationConst))).compareTo(new BigDecimal(frame.getP1().getC().getY1())) < 0 ) {
//           isMoving = true;
            v = u;
            //WARUNKI SPRAWDZAJACE ODLEGLOSC OD WIAZKI LASERA W PIERWSZEJ FOTOKOMORCE
//            if ((Math.abs(y * Constants.normalizationConst) > frame.getP1().getPd1().getY1()) && ((Math.abs(y * Constants.normalizationConst)) < frame.getP1().getPd1().getY2())) {
//                counter++;
//                System.out.println("COUNTER: " + counter);
//
//            }
            BigDecimal bg1= y.multiply(new BigDecimal(Constants.normalizationConst));
            bg1=bg1.subtract(new BigDecimal(frame.getP1().getPd1().getY1()));
            bg1=bg1.abs();
            bg1=bg1.subtract(new BigDecimal(2));
            if(bg1.compareTo(BigDecimal.ZERO)<0)
//            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY1()) < 2)
            {
                frame.getP1().getPd1().setT1(counter * 0.1);
                frame.getP1().getPd1().setyBall1(y);
                //frame.getP1().getPd1().setT1(0);
            }
            BigDecimal bg2= y.multiply(new BigDecimal(Constants.normalizationConst));
            bg2=bg2.subtract(new BigDecimal(frame.getP1().getPd1().getY2()));
            bg2=bg2.abs();
            bg2=bg2.subtract(new BigDecimal(2));
            if(bg2.compareTo(BigDecimal.ZERO)<0){
//            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd1().getY2()) < 2) {
                frame.getP1().getPd1().setT2(counter * 0.1);
                frame.getP1().getPd1().setyBall2(y);

            }
//            if (Math.abs(y * Constants.normalizationConst) > frame.getP1().getPd1().getY2()) {
//                counter = 0;
//            }

        }
        //KULKA JEST W KONDENSATORZE
        else if ((y.multiply(new BigDecimal(Constants.normalizationConst))).compareTo(new BigDecimal(frame.getP1().getC().getY1())) >= 0
                 && y.subtract(new BigDecimal(radius)).multiply(new BigDecimal(Constants.normalizationConst)).compareTo(new BigDecimal(frame.getP1().getC().getY2()))<0)

        {

//            else if ((((y - radius) * Constants.normalizationConst) < frame.getP1().getC().getY2()) && ((y * Constants.normalizationConst) >= frame.getP1().getC().getY1())) {

//            isMoving = true;
            //WARUNEK SPRAWDZA,CZY NATEZENIE POLA JEST DOSTATECZNE, BY KULKA SIE UNOSILA
            if ((Math.abs(frame.getP1().getC().getE()) > m * (Constants.g / charge) * (1 - (Constants.airDensity / oilDensity))))
                v = w;
                //JESLI NIE TO KULKA BEDZIE WOLNIEJ SPADAC Z PREDKOSCIA W2
            else
                v = w2;
            //WARUNKI SPRAWDZAJACE ODLEGLOSC OD WIAZKI LASERA W DRUGIEJ FOTOKOMORCE
            BigDecimal bg3= y.multiply(new BigDecimal(Constants.normalizationConst));
            bg3=bg3.subtract(new BigDecimal(frame.getP1().getPd2().getY1()));
            bg3=bg3.abs();
            bg3=bg3.subtract(new BigDecimal(2));
            if(bg3.compareTo(BigDecimal.ZERO)<0){
//            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY1()) < 2) {
                frame.getP1().getPd2().setT1(counter * 0.1);
                frame.getP1().getPd2().setyBall1(y);
                E=frame.getP1().getC().getE();
            }
            BigDecimal bg4= y.multiply(new BigDecimal(Constants.normalizationConst));
            bg4=bg4.subtract(new BigDecimal(frame.getP1().getPd2().getY2()));
            bg4=bg4.abs();
            bg4=bg4.subtract(new BigDecimal(2));
            if(bg4.compareTo(BigDecimal.ZERO)<0){
//            if (Math.abs((y * Constants.normalizationConst) - frame.getP1().getPd2().getY2()) < 2) {
                frame.getP1().getPd2().setT2(counter * 0.1);
                frame.getP1().getPd2().setyBall2(y);
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

    public BigDecimal getV1() {
        return v1;
    }

    public void setV1(BigDecimal v1) {
        this.v1 = v1;
    }

    public BigDecimal getV2() {
        return v2;
    }

    public void setV2(BigDecimal v2) {
        this.v2 = v2;
    }

    public BigDecimal getY() {
        return y;
    }

    public double getV() {
        return v;
    }

//    public void setY(double y) {
//        this.y = y;
//    }

    public double geta() {
        return a;
    }

    public double getA() {
        return A;
    }

    public double getW() {
        return w;
    }

    public double getE()
    {
        return E;
    }

    public double getW2() {
        return w2;
    }
}
