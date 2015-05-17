package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Created by rafal on 11.05.15.
 */
public class Listeners {
    MillikanFrame frame;
    StartListener start;
    PhotoListener photo1;
    PhotoListener photo2;
    ChangeListener change;
    MeasureListener measure;
    LanguageListener languageListener;
    long startAnimationTime;
    long finishAnimationTime;


    Listeners(MillikanFrame mf) {
        frame = mf;
        start = new StartListener();
        photo1 = new PhotoListener();
        photo2 = new PhotoListener();
        change = new ElectricListener();
        measure = new MeasureListener();
        languageListener = new LanguageListener();
    }

    class StartListener implements ActionListener {
        StartListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // if (frame.condition==false) {
            //  System.out.println("Wchodzi do tej pętli! ");
            frame.start();
            //    } else {
            //    frame.resume();
            //  }
            startAnimationTime = System.currentTimeMillis();
            System.out.println("start Animation Time: " + startAnimationTime);
        }
    }

    class PhotoListener implements ActionListener {
        PhotoListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String name = ((Button) actionEvent.getSource()).getName();
            //wypisujemy, który przycisk jest włączony
            System.out.println(name);
            if (name == "photocell1")
                if (frame.getP1().getPd1().isOn() == true) {
                    frame.getP1().getPd1().setOn(false);
                    frame.getP1().getPd1().setT2();
                } else {
                    frame.getP1().getPd1().setOn(true);
                    frame.getP1().getPd1().setT1();
                }
            else if (frame.getP1().getPd2().isOn() == true) {
                frame.getP1().getPd2().setOn(false);
                frame.getP1().getPd2().setT2();
            } else {
                frame.getP1().getPd2().setOn(true);
                frame.getP1().getPd2().setT1();
            }

        }
    }

    class ElectricListener implements ChangeListener {
        ElectricListener() {
            super();
        }

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            JSlider slider = (JSlider) changeEvent.getSource();
            int value = slider.getValue();
            frame.getP1().getC().setVoltage(value);
            //System.out.println(value- frame.getP1().getC().getVoltage()*1000);
        }
    }

    class MeasureListener implements ActionListener {
        MeasureListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            frame.condition = false;
            frame.stop();
            finishAnimationTime = System.currentTimeMillis();
            System.out.println("finish Animation Time: " + finishAnimationTime);
            frame.getP1().getPd1().calculateV1(frame.currentDrop);
            frame.getP1().getPd1().calculateV2(frame.currentDrop);
            frame.getP1().getPd2().calculateV1(frame.currentDrop);
            frame.getP1().getPd2().calculateV2(frame.currentDrop);
        }
    }

    /**
     * Created by monika03 on 17.05.15.
     */
    class LanguageListener implements ActionListener {
        LanguageListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (Locale.getDefault() == Locale.ENGLISH) {
                Locale locale = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
                Messages.setLocale(locale);
            } else {
                Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
                Messages.setLocale(locale);
            }

            frame.repaint();


        }
    }
}
