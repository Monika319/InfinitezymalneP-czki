package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 09.05.15.
 */
class AnimationFrame extends JPanel {
    // private JPanel animationPanel;

    AnimationFrame() {
        super(new FlowLayout(FlowLayout.CENTER));
        // animationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(Color.YELLOW);
        this.setPreferredSize(new Dimension(500, 500));
    }

}
