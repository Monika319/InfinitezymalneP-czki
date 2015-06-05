package millikanModel;

import gui.AnimationFrame;
import gui.ElectricField;
import gui.MillikanFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rafal on 12.05.15.
 */
public class Capacitor {
    private double voltage;
    private double E;
    private double d;
    private int y1;
    private int y2;
    private int power;



    public Capacitor(int h1, int h2) {
        //frame=mf;

        power = -3;
        y1 = h1;
        y2 = h2;
       // d = Math.abs(h2 - h1)*0.00025;
        //przeliczenie na metry
        d=Math.abs(h2-h1)*10E-4;
        System.out.println("odleglosc miedzy plytkami C: "+d);
        voltage = 0;
        makeField();
    }

    void makeField() {
        E = -voltage / d;
        System.out.println(Double.toString(E));
    }

    public void paintCapacitor(Graphics g, AnimationFrame af) {
        Double yWidthDoubleValue = new Double((af.getWidth() / 2));
        int yWidthIntValue = yWidthDoubleValue.intValue();
        g.setColor(Color.GRAY);
        g.drawLine(0, y1, yWidthIntValue - af.getBallDiameter(), y1);
        g.drawLine(yWidthIntValue + af.getBallDiameter(), y1, 2 * yWidthIntValue, y1);
        g.drawLine(0, y2, (int) (af.getWidth()), y2);
    }


    public double getE() {
        return E;
    }

    public double getVoltage() {
        return voltage;
    }

    public double getD() {
        return d;
    }

    public void setVoltage(int val) {
        voltage = val * Math.pow(10, power);
        makeField();
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setPower(int power)
    {
        this.power = power;
    }


}
