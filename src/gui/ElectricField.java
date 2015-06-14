package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Electric Field Panel.
 * Class for setting eletric field values and units in JtextField and Slider.
 */
public class ElectricField extends JPanel {

    private MillikanFrame frame;
    private int voltageValue;
    private JTextField valueField;
    private JSlider valueSlider;

    /**
     * Created by monika03 on 09.05.15.
     * Storing elements and graohical objects connected with electric field.
     *
     * @param fm -main frame of the program
     */
    public ElectricField(MillikanFrame fm) {

        super(new BorderLayout());
        frame = fm;
        this.setMaximumSize(new Dimension(180, 20));
        this.setPreferredSize(new Dimension(150, 40));
        voltageValue = 0;
        String[] fieldUnits = {"mV", "V", "kV", "MV", "GV"};
        valueField = new JFormattedTextField(voltageValue);
        valueField.setEditable(true);
        valueSlider = new JSlider(0, 1000);
        valueSlider.setValue(0);
        valueSlider.addChangeListener(frame.listeners.change);
        valueField.addActionListener(frame.listeners.electricTextListener);
        JComboBox unitBox = new JComboBox(fieldUnits);
        unitBox.addActionListener(frame.listeners.unitBoxListener);
        valueField.setPreferredSize(new Dimension(100, 25));

        this.add(valueField, BorderLayout.WEST);
        this.add(unitBox, BorderLayout.EAST);
        this.add(valueSlider, BorderLayout.SOUTH);

    }

    /**
     * Setting Electric Field voltage in SI
     *
     * @param voltageValue - value of Electric field
     */
    public void setVoltageValue(int voltageValue) {
        this.voltageValue = voltageValue;
    }

    /**
     * Getting Electric Field voltage in SI
     *
     * @return - value of Electric field
     */
    public int getVoltageValue() {
        return voltageValue;
    }

    /**
     * Setting voltage value in textfield and in Jslider.
     */
    public void setAll() {
        valueSlider.setValue(voltageValue);
        valueField.setText(Integer.toString(voltageValue));
    }

}
