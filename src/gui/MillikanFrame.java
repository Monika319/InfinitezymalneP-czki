package gui;

import millikanModel.ChargeCalculator;
import millikanModel.OilDrop;
import millikanModel.UnitaryCharge;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class MillikanFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    // private JPanel p1;
    public OilDrop currentDrop;
    private double electricField;
    private static final double t = 0.1;
    private AnimationFrame p1;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MillikanFrame panelWindow = new MillikanFrame();

            }
        });
    }

    public MillikanFrame() {
        initialize();

    }

    private void initialize() {
        electricField = 0;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        p1 = new AnimationFrame(this);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBackground(Color.green);

        JPanel p3 = new JPanel();
        p3.setMinimumSize(new Dimension(150, 500));
        p3.setPreferredSize(new Dimension(170, 500));
        JPanel listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(170, 400));

        UnitaryCharge chargee = new UnitaryCharge();
        int ile = chargee.gcd(64088, 16022);
        ArrayList<Integer> charges = new ArrayList<Integer>();
        ArrayList<chargeVariable <Integer>> testCharges;
        testCharges = new ArrayList<>();
        charges.add(16);
        testCharges.add(new chargeVariable<Integer>(16));
        charges.add(64);
        testCharges.add(new chargeVariable<Integer>(64));
        charges.add(64);
        testCharges.add(new chargeVariable<Integer>(64));
        charges.add(128);
        testCharges.add(new chargeVariable<Integer>(128));
        //charges.add(6601064);
        boolean wynik = chargee.elementsEqual(charges);
        ChargeCalculator calculator = new ChargeCalculator();
        int ladunek = calculator.chargeCalc(charges);
        System.out.println("ILE: " + ile + "wynik" + wynik);
        System.out.println(Integer.toString(ladunek));
        chargeVariable <String> emptyValue;
        emptyValue = new chargeVariable<>("");
        for (int i = 0; i < 4; i++) {
            //ListView(listPanel, (double) charges.get(i));
            System.out.println("Wypisuję:" + testCharges.get(i).setString());
           //ListView(listPanel, testCharges.get(i).get().toString());
            ListView(listPanel, testCharges.get(i).setString());

        }

        for (int i = 5; i < 17; i++) {
            ListView(listPanel, emptyValue.setString());
        }

        p3.add(listPanel);
        JButton ask = new JButton("ask");
        JToggleButton onOff = new JToggleButton("on/off");
        onOff.setSize(30, 30);

        JPanel eValuePanel = new JPanel();
        eValuePanel.setMaximumSize(new Dimension(1000, 25));

        eValuePanel.setBackground(Color.blue);
        JLabel eLabel = new JLabel("Estimated e:");
        JLabel eValueLabel = new JLabel(Double.toString(ladunek / Math.pow(10, 20)));
        eValuePanel.add(eLabel);
        eValuePanel.add(eValueLabel);

        p3.add(eValuePanel);
        p3.add(makeButtonsPanel());

        p2.add(ask, BorderLayout.WEST);

        p2.add(onOff, BorderLayout.EAST);
        ElectricField electricFieldPanel = new ElectricField();
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dimensions.width / 2 - WINDOW_WIDTH / 2, dimensions.height
                / 2 - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);

        setTitle("Symulacja doświadczenia Millikana");
        //zamiast tego bedzie dodawanie poprzez actionlistener
        currentDrop = new OilDrop(1E-7, 2E-7, 1, 1000, this);

        //start();
    }

    public void start() {

        Thread th;
        th = new Thread() {
            public void run() {

                while (true) {

                    currentDrop.move();
                    p1.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
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

        Listeners listeners = new Listeners(this);
        startButton.addActionListener(listeners.start);
        photocell1.addActionListener(listeners.photo1);
        photocell2.addActionListener(listeners.photo2);


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
    public void ListView(JPanel columnpanel,String charge) {
        JPanel rowPanel = new JPanel();


        rowPanel.setMaximumSize(new Dimension(1000, 15));
        columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        columnpanel.add(rowPanel);
        // rowPanel.setBounds(170, 70, 150, 20);
        JLabel dataList = new JLabel("");
        // dataList.setBounds(170, 70, 150, 20);

//        dataList.setText("Q_" + columnpanel.getComponentCount() + "="
//                + charge + "C");
        dataList.setText(charge+"C" );
        dataList.setVisible(true);
        rowPanel.add(dataList);

        if (columnpanel.getComponentCount() % 2 == 0)
            rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
    }

    public double getElectricField() {
        return electricField;
    }

    public double getT() {
        return t;
    }

    public void setElectricField(double electricField) {
        this.electricField = electricField;
    }

    public AnimationFrame getP1() {
        return p1;
    }
}

