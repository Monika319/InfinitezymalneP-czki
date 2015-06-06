package gui;

import millikanModel.OilDrop;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    AskButtonListener askButtonListener;
    PlotListener plotListener;
    ElectricTextListener electricTextListener;
    UnitBoxListener unitBoxListener;
    private long startAnimationTime;
    long finishAnimationTime;
    private static final int WINDOW_HEIGHT = 120;
    private static final int WINDOW_WIDTH = 260;
    private static int plotCounter = 0;
    private ElectricField ef;

    Listeners(MillikanFrame mf) {
        frame = mf;

        start = new StartListener();
        photo1 = new PhotoListener();
        photo2 = new PhotoListener();
        change = new ElectricListener();
        measure = new MeasureListener();
        languageListener = new LanguageListener();
        askButtonListener = new AskButtonListener();
        plotListener = new PlotListener();
        electricTextListener = new ElectricTextListener();
        unitBoxListener = new UnitBoxListener();
    }

    class StartListener implements ActionListener {
        StartListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (frame.getP1().initialize) {
                frame.getP1().timer.stop();
                frame.getP1().initialize = false;
            }
            OilDrop drop = new OilDrop(frame);
            // frame.getP1().setOilDrop(drop);
            frame.getP1().reset();
            frame.getP1().initialize = true;
            frame.getP1().timer.start();
            startAnimationTime = System.currentTimeMillis();
            System.out.println("start Animation Time: " + startAnimationTime);
        }
    }

    class PlotListener implements ActionListener {
        PlotListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //actionEvent.
            plotCounter++;
            PlotGraph plotGraph = new PlotGraph();
            System.out.println(Integer.toString(plotCounter));

            JFrame dataFrame = new JFrame();
            dataFrame.setTitle("Experiment's data");
            dataFrame.setPreferredSize(new Dimension(640, 480));
            ChartPanel chartPanel = new ChartPanel(plotGraph.lineGraph);
            dataFrame.setContentPane(chartPanel);
            dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dataFrame.pack();
            dataFrame.setVisible(true);
            if ((plotCounter % 2) == 0) {
                dataFrame.dispose();
            }


        }
    }

    class AskButtonListener implements ActionListener {
        AskButtonListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new InstructionsRead();
        }
    }


    class InstructionsRead {
        InstructionsRead() {
            initialize();
        }

        void initialize() {
            JFrame askFrame = new JFrame();
            askFrame.setTitle("Instructions");
            JPanel instructionsPanel = new JPanel();
            askFrame.setContentPane(instructionsPanel);
            askFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            BufferedReader in = null;
            JTextArea instructions = new JTextArea();
            try {
                in = new BufferedReader(new FileReader("res/instructions"));
                String str;
                while ((str = in.readLine()) != null) {
                    instructions.append("\n" + str);
                }
            } catch (IOException e) {
            } finally {
                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
            instructionsPanel.add(instructions);

            askFrame.pack();
            askFrame.setVisible(true);


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

                if (frame.getP1().getPd1().isOn()) {
                    frame.getP1().getPd1().setOn(false);
                    // frame.getP1().getPd1().setT2();
                } else {
                    frame.getP1().getPd1().setOn(true);
                    //frame.getP1().getPd1().setT1();
                }
            else if (frame.getP1().getPd2().isOn()) {
                frame.getP1().getPd2().setOn(false);
                //frame.getP1().getPd2().setT2();
            } else {
                frame.getP1().getPd2().setOn(true);
                //frame.getP1().getPd2().setT1();
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
            //System.out.println("Value from slider: " + Integer.toString(value));
            //frame.getP1().getC().setValue(value);
            frame.getP1().getC().setVoltage(value);
            ef.setVoltageValue(value);
            ef.setAll();
            //System.out.println(value- frame.getP1().getC().getVoltage()*1000);
        }
    }

    class UnitBoxListener implements ActionListener {
        UnitBoxListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox unitBox = (JComboBox) e.getSource();
            String s = (String) unitBox.getSelectedItem();
            if (s == "MV") {
                frame.getP1().getC().setPower(6);
            } else if (s == "V") {
                //System.out.println("Przed zamianą jednostek: " + frame.getP1().getC().getVoltage());
                frame.getP1().getC().setPower(1);
                //System.out.println("Po zamianie jednostek: " + frame.getP1().getC().getVoltage());
            } else if (s == "kV") {
                //System.out.println("Przed zamianą jednostek: " + frame.getP1().getC().getVoltage());
                frame.getP1().getC().setPower(3);
                // System.out.println("Po zamianie jednostek: " + frame.getP1().getC().getVoltage());
            } else if (s == "GV") {
                frame.getP1().getC().setPower(9);
            } else if (s == "TV") {
                frame.getP1().getC().setPower(12);
            } else {
                frame.getP1().getC().setPower(-3);
            }
            frame.getP1().getC().setVoltage(ef.getVoltageValue());


        }
    }

    class ElectricTextListener implements ActionListener {
        ElectricTextListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField electricField = (JTextField) e.getSource();
            if (electricField.getText() != null) {
                int value = Integer.parseInt(electricField.getText());
                frame.getP1().getC().setVoltage(value);
                ef.setVoltageValue(value);
                ef.setAll();
                //System.out.println(electricField.getText());

            }
        }
    }

    /**
     * Created by monika03 on 17.05.15.
     */
    class MeasureListener implements ActionListener {
        MeasureListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (frame.getP1().initialize) {
                frame.getP1().timer.stop();
                frame.getP1().initialize = !frame.getP1().initialize;
            }
            finishAnimationTime = System.currentTimeMillis();

            frame.getP1().getPd1().calculateV(frame.currentDrop);

            frame.getP1().getPd2().calculateV(frame.currentDrop);
            frame.getCharges().addCharge(frame.currentDrop, frame.getP1().getC().getE());
            frame.setEValue();
            frame.currentDrop = new OilDrop(frame);
            frame.getP1().reset();
            frame.getP1().repaint();

            System.out.println("finish Animation Time: " + finishAnimationTime);
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

    public void setEf(ElectricField ef) {
        this.ef = ef;
    }
}
