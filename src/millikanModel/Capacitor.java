package millikanModel;

import gui.AnimationFrame;
import gui.ElectricField;
import gui.MillikanFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Class containing data from oil drop movement in electric field.
 */
public class Capacitor {
    private double voltage;
    private double E;
    private double d;
    private int y1;
    private int y2;
    private int power;

    /**
     * Constructor for setting capacitor dimensions, counting distance between capacitor plates
     * and setting electric field voltage to 0 as default.
     * @param h1 - upper capacitor plate y coordinate in pixels
     * @param h2 - lower capacitor plate y coordinate in pixels
     */
    public Capacitor(int h1, int h2) {
        power = -3;
        y1 = h1;
        y2 = h2;
        d = Math.abs(h2 - h1)/10E3;
        voltage = 0;
        makeField();
    }

    /**
     * Counting electric field value from distance between capacitor plates
     * and electric field voltage, set by user.
     */
    void makeField() {
        E = -voltage / d;
    }

    /**
     * Painting Capacitor in given frame
     * @param g - Graphics object
     * @param af - Animation frame
     */
    public void paintCapacitor(Graphics g, AnimationFrame af) {
        Double yWidthDoubleValue = new Double((af.getWidth() / 2));
        int yWidthIntValue = yWidthDoubleValue.intValue();
        g.setColor(Color.GRAY);
        g.drawLine(0, y1, yWidthIntValue - af.getBallDiameter(), y1);
        g.drawLine(yWidthIntValue + af.getBallDiameter(), y1, 2 * yWidthIntValue, y1);
        g.drawLine(0, y2, (int) (af.getWidth()), y2);
    }

    /**
     * Getting electric field value
     * @return electric fieldvalue
     */

    public double getE() {
        //dodano dzielenie przez 10, żeby zgadzaly sie obliczenia i jednostki
        return E;
    }

    /**
     * Setting electric field voltage in SI
     * @param val - relative electric field value(without power)
     */

    public void setVoltage(int val) {
        voltage = val * Math.pow(10, power);
        makeField();
    }

    /**
     *
     * @return  upper capacitor plate y coordinate in SI
     */
    public int getY1() {
        return y1;
    }

    /**
     *
     * @return  lower capacitor plate y coordinate in SI
     */
    public int getY2() {
        return y2;
    }

    /**
     * Settin power for electric field(depending on units)
     * @param power Electric field power
     */
    public void setPower(int power) {
        this.power = power;
    }

}
