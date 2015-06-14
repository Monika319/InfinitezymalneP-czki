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
 *  Class that contains all listeners used in program.
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
//    PlotListener plotListener;
    ElectricTextListener electricTextListener;
    UnitBoxListener unitBoxListener;
    private long startAnimationTime;
    long finishAnimationTime;
    private ElectricField ef;

    /**
     * Implements listeners used in program
     * @param mf -main frame
     */
    Listeners(MillikanFrame mf) {
        frame = mf;

        start = new StartListener();
        photo1 = new PhotoListener();
        photo2 = new PhotoListener();
        change = new ElectricListener();
        measure = new MeasureListener();
        languageListener = new LanguageListener();
        askButtonListener = new AskButtonListener();
//        plotListener = new PlotListener();
        electricTextListener = new ElectricTextListener();
        unitBoxListener = new UnitBoxListener();
    }

    /**
     * Animation start button listener responsible for starting measurement.
     */
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

//    class PlotListener implements ActionListener {
//        PlotListener() {
//            super();
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            //actionEvent.
//            plotCounter++;
//            PlotGraph plotGraph = new PlotGraph(frame);
//            System.out.println(Integer.toString(plotCounter));
//
//            JFrame dataFrame = new JFrame();
//            dataFrame.setTitle("Experiment's data");
//            dataFrame.setPreferredSize(new Dimension(640, 480));
//            ChartPanel chartPanel = new ChartPanel(plotGraph.lineGraph);
//            dataFrame.setContentPane(chartPanel);
//            dataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            dataFrame.pack();
//            dataFrame.setVisible(true);
//            if ((plotCounter % 2) == 0) {
//                dataFrame.dispose();
//            }
//
//
//        }
//    }

    /**
     * Listener for button to open user manual of the program.
     */
    class AskButtonListener implements ActionListener {
        AskButtonListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new InstructionsRead();
        }
    }

    /**
     *  JPanel to view the user manual of the program.
     */
    class InstructionsRead {
        InstructionsRead() {
            initialize();
        }

        void initialize() {
            JFrame askFrame = new JFrame();

            JPanel instructionsPanel = new JPanel();
            askFrame.setContentPane(instructionsPanel);
            askFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            BufferedReader in = null;
            JTextArea instructions = new JTextArea();
            try {
                if(frame.language=="english")
                {
                    askFrame.setTitle("Instructions");
                    in = new BufferedReader(new FileReader("res/instructions"));
                }
                else if (frame.language=="polish")
                {
                    askFrame.setTitle("Instrukcje");
                    in = new BufferedReader(new FileReader("res/instructions_pl"));
                }
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

    /**
     *  Photodetector listener to register falling times in order
     +      to find velocities v1 and v2.
     */
    class PhotoListener implements ActionListener {
        PhotoListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            Button button=(Button)actionEvent.getSource();
            String name = button.getName();
            //wypisujemy, który przycisk jest włączony
            System.out.println(name);
            if (name == "photocell1")

                if (frame.getP1().getPd1().isOn()) {
                    frame.getP1().getPd1().setOn(false);
                    button.setIcon(new ImageIcon("res/light_off_new.png"));
                    // frame.getP1().getPd1().setT2();
                } else {
                    frame.getP1().getPd1().setOn(true);
                    button.setIcon(new ImageIcon("res/light_on_new.png"));
                    //frame.getP1().getPd1().setT1();
                }
            else if (frame.getP1().getPd2().isOn()) {
                frame.getP1().getPd2().setOn(false);
                button.setIcon(new ImageIcon("res/light_off_new.png"));
                //frame.getP1().getPd2().setT2();
            } else {
                frame.getP1().getPd2().setOn(true);
                button.setIcon(new ImageIcon("res/light_on_new.png"));
                //frame.getP1().getPd2().setT1();
            }

        }
    }

    /**
     * Listener responsible for changing electric field value.
     */
    class ElectricListener implements ChangeListener {
        ElectricListener() {
            super();
        }

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            JSlider slider = (JSlider) changeEvent.getSource();
            int value = slider.getValue();

            frame.getP1().getC().setVoltage(value);
            ef.setVoltageValue(value);
            ef.setAll();
        }
    }

    /**
     *  Listener responsible for changing units of electric field.
     */

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
            } else {
                frame.getP1().getC().setPower(-3);
            }
            frame.getP1().getC().setVoltage(ef.getVoltageValue());


        }
    }

    /**
     * Listener responsible for setting electric field value.
     */
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
     * Responsible for finishing animation, calculating velocities v1and v2, measuring new charge
     * and evaluating the elementary charge from set of measurements.
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
            if(frame.currentDrop.getE()==0)
            JOptionPane.showMessageDialog(frame,
                    "Electric Field is 0!",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            else
            {
                frame.getP1().getPd1().calculateV(frame.currentDrop);
                frame.getP1().getPd2().calculateV(frame.currentDrop);
                frame.getCharges().addCharge(frame.currentDrop);
                frame.setEValue();
            }

            frame.currentDrop = new OilDrop(frame);
            frame.getP1().reset();
            frame.getP1().repaint();

            System.out.println("finish Animation Time: " + finishAnimationTime);
        }
    }

    /**
     * Created by monika03 on 17.05.15.
     *  Listener for multilanguage program: possible English and Polish versions
     */
    class LanguageListener implements ActionListener {
        LanguageListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
//            if (Locale.getDefault() == Locale.ENGLISH) {
            Button button=(Button)actionEvent.getSource();
            if(frame.language=="english"){
                frame.language="polish";
                button.setIcon(new ImageIcon("res/english_new.png"));
                Locale locale = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
                Messages.setLocale(locale);

            } else {
                frame.language="english";
                button.setIcon(new ImageIcon("res/polish_new.png"));
                Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
                Messages.setLocale(locale);
            }
            frame.setTitle(Messages.getString("title"));
            frame.getValuePanel().geteLabel().setText(Messages.getString("estimation"));
            frame.repaint();


        }
    }

    /**
     * Setting balue of electric field, used in calculations
     * @param ef - electric field value set by user
     */
    public void setEf(ElectricField ef) {
        this.ef = ef;
    }
}
