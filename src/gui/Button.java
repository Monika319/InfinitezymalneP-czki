package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;

/**
 * Created by monika03 on 09.05.15.
 */
class Button extends JButton {
    public Button(String name, Integer width, Integer height) {
        super();
        this.setIcon(getImage(name, width, height));
    }

    public ImageIcon getImage(String name, Integer width, Integer height) {

        ImageIcon icon = new ImageIcon(name);
        Image newimg = icon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return newIcon;
    }

}
