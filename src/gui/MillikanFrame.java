package gui;


import millikanModel.ChargeCalculator;
import millikanModel.OilDrop;
import millikanModel.Test;
import millikanModel.UnitaryCharge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;

public class MillikanFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    // private JPanel p1;
    public OilDrop currentDrop;
    private static final double t = 0.1;
    private AnimationFrame p1;
    public Listeners listeners;
    private Thread th;
    int count = 0;

    boolean condition = false;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Messages.setLocale(Locale.getDefault());
                MillikanFrame panelWindow = new MillikanFrame();

            }
        });
    }

    public MillikanFrame() {
        initialize();

    }

    private void initialize() {
        condition = true;
        listeners = new Listeners(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        p1 = new AnimationFrame(this);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBackground(Color.lightGray);

        JPanel p3 = new JPanel();
        p3.setMinimumSize(new Dimension(150, 500));
        p3.setPreferredSize(new Dimension(170, 500));

        JPanel listPanel;
        listPanel = new JPanel();
        //jak tutaj zmieniam w dimension drugi parametr, to zmienia się ta lista
        listPanel.setPreferredSize(new Dimension(170, 700));


        UnitaryCharge chargee = new UnitaryCharge();
        int ile = chargee.gcd(64088, 16022);
        ArrayList<Integer> charges = new ArrayList<>();
        ArrayList<chargeVariable<Integer>> testCharges;
        testCharges = new ArrayList<>();
        charges.add(16);
        testCharges.add(new chargeVariable<>(16));
        charges.add(64);
        testCharges.add(new chargeVariable<>(64));
        charges.add(64);
        testCharges.add(new chargeVariable<>(64));
        charges.add(128);
        testCharges.add(new chargeVariable<>(128));
        //charges.add(6601064);
        boolean wynik = chargee.elementsEqual(charges);
        ChargeCalculator calculator = new ChargeCalculator();
        int ladunek = calculator.chargeCalc(charges);
        System.out.println("ILE: " + ile + "wynik" + wynik);
        System.out.println(Integer.toString(ladunek));

        chargeVariable<String> emptyValue;
        emptyValue = new chargeVariable<>(" ");
        for (int i = 0; i < 4; i++) {
            //ListView(listPanel, (double) charges.get(i));
            System.out.println("Wypisuję:" + testCharges.get(i).setString());
            //ListView(listPanel, testCharges.get(i).get().toString());
            ListView(listPanel, testCharges.get(i));

        }

        for (int i = 5; i < 100; i++) {
            ListView(listPanel, emptyValue);


        }
        JScrollPane scrollPane = new JScrollPane(listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(170, 350));
        p3.add(scrollPane);


        Button ask = new Button("res/ask.png", 20, 20);
        JToggleButton onOff = new JToggleButton("on/off");
        onOff.setSize(30, 30);

        onOff.addActionListener(listeners.plotListener);
        ask.addActionListener(listeners.askButtonListener);

        JPanel eValuePanel = new JPanel();
        eValuePanel.setMaximumSize(new Dimension(1000, 25));

        eValuePanel.setBackground(Color.blue);
        JLabel eLabel = new JLabel(Messages.getString("estimation"));
        JLabel eValueLabel = new JLabel(Double.toString(ladunek / Math.pow(10, 20)));
        eValuePanel.add(eLabel);
        eValuePanel.add(eValueLabel);

        p3.add(eValuePanel);
        p3.add(makeButtonsPanel());

        p2.add(ask, BorderLayout.WEST);

        p2.add(onOff, BorderLayout.EAST);
        ElectricField electricFieldPanel = new ElectricField(this);
        p3.add(electricFieldPanel);

        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = gbc.weighty = 97;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(p1, gbc);

        gbc.gridy = 1;
        gbc.weightx = gbc.weighty = 3;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(p2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 20;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(p3, gbc);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, Messages.getString("askForClose"), Messages.getString("exitConfirmation"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    condition = false;

                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);

        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dimensions.width / 2 - WINDOW_WIDTH / 2, dimensions.height
                / 2 - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);

        setTitle(Messages.getString("title"));
        //zamiast tego bedzie dodawanie poprzez actionlistener
        currentDrop = new OilDrop(1E-7, 2E-7, 1, 1000, this);

        //start();
    }


    public void start() {


        //p1.repaint();

        condition = true;
        currentDrop = new OilDrop(1E-7, 2E-7, 1, 1000, this);
        if (th == null) {
            th = new Thread() {
                public void run() {

                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileWriter("out.txt"));
                        Test test = new Test(currentDrop, writer);
                        while (condition) {
                            count++;
                            currentDrop.move();

//                            if((count % 10)==0) {
                            p1.repaint();
//                            }


                            test.test();
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                            }
                        }

                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    } finally {
                        try {
                            writer.close();
                        } catch (Exception ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                    }
                }
            };

            th.start();
        }

    }


    public void resume() {
        if (th != null) {

            th = null;
        }
        th.start();
    }

    public JPanel makeButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);

        buttonsPanel.setMinimumSize(new Dimension(120, 30));
        buttonsPanel.setPreferredSize(new Dimension(180, 80));

        Button startButton = new Button("res/start.png", 20, 20);
        startButton.setName("start");
        Button pomiarButton = new Button("res/measurement.png", 20, 20);
        Button saveButton = new Button("res/save.png", 20, 20);
        Button languageButton = new Button("res/globe.png", 20, 20);
        Button photocell1 = new Button("res/lightoff.jpg", 20, 20);
        photocell1.setName("photocell1");
        Button photocell2 = new Button("res/lighton.png", 20, 20);
        photocell2.setName("photocell2");

        //Listeners listeners = new Listeners(this);
        startButton.addActionListener(listeners.start);
        photocell1.addActionListener(listeners.photo1);
        languageButton.addActionListener(listeners.languageListener);

        photocell2.addActionListener(listeners.photo2);
        pomiarButton.addActionListener(listeners.measure);


        buttonsPanel.add(startButton);
        buttonsPanel.add(pomiarButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(languageButton);
        buttonsPanel.add(photocell1);
        buttonsPanel.add(photocell2);

        return buttonsPanel;
    }

    // public ImageIcon getImage(String name,Integer width,Integer height){
    // ImageIcon icon= new
    // ImageIcon(getClass().getClassLoader().getResource("save.png"));
    // Image newimg = icon.getImage().getScaledInstance(width, height,
    // java.awt.Image.SCALE_SMOOTH);
    // ImageIcon newIcon = new ImageIcon(newimg);
    // //System.out.println(getClass().getClassLoader().toString());
    // return newIcon;
    // }

    // columnpanel-dodaje rowpanel
    public void ListView(JPanel columnpanel, chargeVariable charge) {
        JPanel rowPanel = new JPanel();

        rowPanel.setMinimumSize(new Dimension(170, 25));
        //należało ustawić serMinimumSize i zmieniać ustawienia
        rowPanel.setMaximumSize(new Dimension(1000, 15));
        columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        columnpanel.add(rowPanel);

        // rowPanel.setBounds(170, 70, 150, 20);
        JLabel dataList = new JLabel(" ");
        // dataList.setBounds(170, 70, 150, 20);

//        dataList.setText("Q_" + columnpanel.getComponentCount() + "="
//                + charge + "C");

        if (charge.checkIfString()) {

            dataList.setText(charge.setString());
        } else {

            dataList.setText("Q_" + columnpanel.getComponentCount() + "=" + charge.setString() + "C");
        }

        dataList.setVisible(true);
        rowPanel.add(dataList);

        if (columnpanel.getComponentCount() % 2 == 0)
            rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
    }

    public Thread getThread() {
        return th;
    }

    public double getT() {
        return t;
    }

    public AnimationFrame getP1() {
        return p1;
    }
}
