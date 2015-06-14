package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 09.05.15.
 * Class for adding images to buttons.
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

        return new ImageIcon(newimg);
    }

}
