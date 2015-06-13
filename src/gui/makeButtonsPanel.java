package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rafal on 24.05.15.
 */
public class makeButtonsPanel extends JPanel
{
    private Listeners listeners;
    public makeButtonsPanel(MillikanFrame frame)
    {

        super();
        listeners=frame.listeners;
        this.setBackground(Color.WHITE);

        this.setMinimumSize(new Dimension(120, 30));
        this.setPreferredSize(new Dimension(180, 80));

        Button startButton = new Button("res/start.png", 20, 20);
        startButton.setName("start");
        Button pomiarButton = new Button("res/measurement.png", 20, 20);
        Button saveButton = new Button("res/save.png", 20, 20);
        Button languageButton = new Button("res/english_new.png", 20, 20);
        Button photocell1 = new Button("res/light_off_new.png", 20, 20);
        photocell1.setName("photocell1");
        Button photocell2 = new Button("res/light_off_new.png", 20, 20);
        photocell2.setName("photocell2");


        startButton.addActionListener(listeners.start);
        photocell1.addActionListener(listeners.photo1);
        languageButton.addActionListener(listeners.languageListener);

        photocell2.addActionListener(listeners.photo2);
        pomiarButton.addActionListener(listeners.measure);


        this.add(startButton);
        this.add(pomiarButton);
        this.add(saveButton);
        this.add(languageButton);
        this.add(photocell1);
        this.add(photocell2);

    }
}
