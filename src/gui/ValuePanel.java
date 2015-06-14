package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class for initializing panel to show elementary charge counted frm gcd.
 */
public class ValuePanel extends JPanel {
    private JLabel eValueLabel;
    private JLabel eLabel;

    public ValuePanel() {
        super();
        eValueLabel = new JLabel(Integer.toString(0));

        this.setMaximumSize(new Dimension(1000, 25));

//        this.setBackground(Color.WHITE);
        eLabel = new JLabel(Messages.getString("estimation"));

        this.add(eLabel);
        this.add(eValueLabel);
    }

    /**
     * Getting elementary charge(counted from gcd)
     *
     * @return elementary charge value Label
     */
    public JLabel geteValueLabel() {
        return eValueLabel;
    }

    /**
     * Getting name of panel, which contains elementary charge value
     *
     * @return name of panel, storing elementary charge value
     */
    public JLabel geteLabel() {
        return eLabel;
    }
}
