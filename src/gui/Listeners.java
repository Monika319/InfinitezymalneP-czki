package gui;

import millikanModel.Photodetector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rafal on 11.05.15.
 */
public class Listeners
{
    MillikanFrame frame;
    StartListener start;
    PhotoListener photo1;
    PhotoListener photo2;
    ChangeListener change;

    Listeners(MillikanFrame mf)
    {
        frame = mf;
        start = new StartListener();
        photo1 = new PhotoListener();
        photo2 = new PhotoListener();
        change=new ElectricListener();
    }

    class StartListener implements ActionListener
    {
        StartListener()
        {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            frame.start();
        }
    }

    class PhotoListener implements ActionListener
    {
        PhotoListener()
        {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {

            String name = ((Button) actionEvent.getSource()).getName();
            System.out.println(name);
            if (name == "photocell1")
                if (frame.getP1().getPd1().isOn() == true)
                    frame.getP1().getPd1().setOn(false);
                else
                    frame.getP1().getPd1().setOn(true);
            else if (frame.getP1().getPd2().isOn() == true)
                frame.getP1().getPd2().setOn(false);
            else
                frame.getP1().getPd2().setOn(true);

        }
    }
    class ElectricListener implements ChangeListener
    {
        ElectricListener(){super();}
        @Override
        public void stateChanged(ChangeEvent changeEvent)
        {
            JSlider slider=(JSlider)changeEvent.getSource();
            int value=slider.getValue();
            frame.getP1().getC().setVoltage(value);
            //System.out.println(value- frame.getP1().getC().getVoltage()*1000);
        }
    }
}
