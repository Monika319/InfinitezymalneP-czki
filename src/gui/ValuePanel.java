package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rafal on 01.06.15.
 */
public class ValuePanel extends JPanel
{
    private JLabel eValueLabel;
    private JLabel eLabel;
    public ValuePanel()
    {
        super();
        eValueLabel=new JLabel(Integer.toString(0));

        this.setMaximumSize(new Dimension(1000, 25));

        this.setBackground(Color.WHITE);
        eLabel = new JLabel(Messages.getString("estimation"));

        this.add(eLabel);
        this.add(eValueLabel);
    }

    public JLabel geteValueLabel()
    {
        return eValueLabel;
    }
}
