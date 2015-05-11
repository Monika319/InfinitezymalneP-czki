package gui;

import millikanModel.Photodetector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by rafal on 11.05.15.
 */
public class Listeners
{
    MillikanFrame frame;
    startListener start;
    photoListener photo1;
    photoListener photo2;

    Listeners(MillikanFrame mf)
    {
        frame = mf;
        start = new startListener();
        photo1 = new photoListener();
        photo2 = new photoListener();
    }

    class startListener implements ActionListener
    {
        startListener()
        {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            frame.start();
        }
    }

    class photoListener implements ActionListener
    {
        photoListener()
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
}
