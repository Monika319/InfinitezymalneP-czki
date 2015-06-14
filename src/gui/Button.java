package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;

/**
 *  Class for adding images to buttons.
 */
class Button extends JButton {
    /**
     *
     * @param name -Button name
     * @param width -Button width
     * @param height - Button height
     */
    public Button(String name, Integer width, Integer height) {
        super();
        this.setIcon(getImage(name, width, height));
    }

    /**
     *
     * @param name - Image name
     * @param width -Image width
     * @param height -Image height
     * @return scaled icon for button
     */
    public ImageIcon getImage(String name, Integer width, Integer height) {

        ImageIcon icon = new ImageIcon(name);
        Image newimg = icon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return newIcon;
    }

}
