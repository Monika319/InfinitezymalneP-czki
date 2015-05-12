package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 09.05.15.
 */
public class ElectricField extends JPanel
{
    private MillikanFrame frame;
    public ElectricField(MillikanFrame fm) {
        super(new BorderLayout());
        frame=fm;
        this.setMaximumSize(new Dimension(180, 20));
        this.setPreferredSize(new Dimension(150, 40));
        Double voltageValue = 0D;
        // this.setBorder(BorderFactory.createLineBorder(Color.black));
        String[] fieldUnits = {"mV", "V", "kV"};
        JTextField valueField = new JFormattedTextField(voltageValue);
        valueField.setEditable(true);
        JSlider valueSlider = new JSlider(0,500);
        valueSlider.setValue(0);
        valueSlider.addChangeListener(frame.listeners.change);
        JComboBox unitBox = new JComboBox(fieldUnits);
        //unitBox.setSelectedIndex(0);
        valueField.setPreferredSize(new Dimension(100, 25));

        this.add(valueField, BorderLayout.WEST);
        this.add(unitBox, BorderLayout.EAST);
        this.add(valueSlider, BorderLayout.SOUTH);

    }

//    public double getFieldValue()
//    {
//        return fieldValue;
//    }
//
//    public void setFieldValue(double fieldValue)
//    {
//        this.fieldValue = fieldValue;
//    }
}
