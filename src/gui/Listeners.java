package gui;

import millikanModel.OilDrop;
import org.jfree.chart.ChartPanel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by rafal on 11.05.15.
 * Class that contains all listeners used in program.
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
            frame.getP1().reset();
            frame.getP1().initialize = true;
            frame.getP1().timer.start();
            startAnimationTime = System.currentTimeMillis();
            System.out.println("start Animation Time: " + startAnimationTime);
        }
    }
    /**
     * Listener for plotting graph: evaluated elementary charge after N experiments.
     */
    class PlotListener implements ActionListener {
        PlotListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
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
     * JPanel to view the user manual of the program.
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
                if(Objects.equals(frame.language, "english"))
                {
                    askFrame.setTitle("Instructions");
                    in = new BufferedReader(new FileReader("res/instructions"));
                }
                else if (Objects.equals(frame.language, "polish"))
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
     * Photodetector listener to register falling times in order
     * to fing velocities v1 and v2.
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
            if (Objects.equals(name, "photocell1"))

                if (frame.getP1().getPd1().isOn()) {
                    frame.getP1().getPd1().setOn(false);
                    button.setIcon(new ImageIcon("res/light_off_new.png"));

                } else {
                    frame.getP1().getPd1().setOn(true);
                    button.setIcon(new ImageIcon("res/light_on_new.png"));

                }
            else if (frame.getP1().getPd2().isOn()) {
                frame.getP1().getPd2().setOn(false);
                button.setIcon(new ImageIcon("res/light_off_new.png"));

            } else {
                frame.getP1().getPd2().setOn(true);
                button.setIcon(new ImageIcon("res/light_on_new.png"));

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
     * Listener responsible for changing units of electric field.
     */

    class UnitBoxListener implements ActionListener {
        UnitBoxListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox unitBox = (JComboBox) e.getSource();
            String s = (String) unitBox.getSelectedItem();
            if (Objects.equals(s, "MV")) {
                frame.getP1().getC().setPower(6);
            } else if (Objects.equals(s, "V")) {

                frame.getP1().getC().setPower(1);

            } else if (Objects.equals(s, "kV")) {

                frame.getP1().getC().setPower(3);

            } else if (Objects.equals(s, "GV")) {
                frame.getP1().getC().setPower(9);
            } else if (Objects.equals(s, "TV")) {
                frame.getP1().getC().setPower(12);
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

            frame.getP1().getPd1().calculateV(frame.currentDrop);

            frame.getP1().getPd2().calculateV(frame.currentDrop);
            frame.getCharges().addCharge(frame.currentDrop);
            frame.setEValue();
            frame.currentDrop = new OilDrop(frame);
            frame.getP1().reset();
            frame.getP1().repaint();

            System.out.println("finish Animation Time: " + finishAnimationTime);
        }
    }

    /**
     * Created by monika03 on 17.05.15.
     * Listener for multilanguage program: possible English and Polish versions.
     */
    class LanguageListener implements ActionListener {
        LanguageListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
//            if (Locale.getDefault() == Locale.ENGLISH) {
            Button button=(Button)actionEvent.getSource();
            if(Objects.equals(frame.language, "english")){
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

    public void setEf(ElectricField ef) {
        this.ef = ef;
    }
}
