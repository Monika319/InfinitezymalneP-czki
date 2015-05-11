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
        //System.out.print(this.getSize());
        this.setIcon(getImage(name, width, height));
    }

    // dodane będą tutaj actionlistenery, keylistenery,itp...
    // this.setIcon(icon);
    // JButton which = new JButton(name);
    // this.setSize(new Dimension(width, height));
    public ImageIcon getImage(String name, Integer width, Integer height) {
        // URL resource = getClass().getClassLoader().getResource(name);

        ImageIcon icon = new ImageIcon(name);
        Image newimg = icon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);

        return newIcon;
    }

}
