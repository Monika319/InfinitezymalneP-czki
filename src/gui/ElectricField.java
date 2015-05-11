package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 09.05.15.
 */
public class ElectricField extends JPanel
{
    //private double fieldValue;
    public ElectricField() {
        super(new BorderLayout());
        //fieldValue=0;
        this.setMaximumSize(new Dimension(180, 20));
        this.setPreferredSize(new Dimension(150, 40));
        Double voltageValue = 0D;
        // this.setBorder(BorderFactory.createLineBorder(Color.black));
        String[] fieldUnits = {"mV", "V", "kV"};
        JTextField valueField = new JFormattedTextField(voltageValue);
        valueField.setEditable(true);
        JSlider valueSlider = new JSlider();
        JComboBox unitBox = new JComboBox(fieldUnits);
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
