package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 09.05.15.
 */
public class ElectricField extends JPanel
{
    private MillikanFrame frame;
    private int voltageValue;
    private JTextField valueField;
    private JSlider valueSlider;
    public ElectricField(MillikanFrame fm) {
        super(new BorderLayout());
        frame=fm;
        this.setMaximumSize(new Dimension(180, 20));
        this.setPreferredSize(new Dimension(150, 40));
        voltageValue = 0;
        // this.setBorder(BorderFactory.createLineBorder(Color.black));
        String[] fieldUnits = {"mV", "V", "kV","MV","GV","TV"};
        valueField = new JFormattedTextField(voltageValue);
        valueField.setEditable(true);
        valueSlider = new JSlider(0,10000   );
        valueSlider.setValue(0);
        valueSlider.addChangeListener(frame.listeners.change);
        valueField.addActionListener(frame.listeners.electricTextListener);
        JComboBox unitBox = new JComboBox(fieldUnits);
        unitBox.addActionListener(frame.listeners.unitBoxListener);
        //unitBox.setSelectedIndex(0);
        valueField.setPreferredSize(new Dimension(100, 25));

        this.add(valueField, BorderLayout.WEST);
        this.add(unitBox, BorderLayout.EAST);
        this.add(valueSlider, BorderLayout.SOUTH);

    }

    public void setVoltageValue(int voltageValue)
    {
        this.voltageValue = voltageValue;
    }

    public int getVoltageValue()
    {
        return voltageValue;
    }
    public void setAll()
    {
        valueSlider.setValue(voltageValue);
        valueField.setText(Integer.toString(voltageValue));
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
