package gui;


//import millikanModel.GCDCalculator;
import millikanModel.GCDCalculator;
import millikanModel.OilDrop;
import millikanModel.Test;
import millikanModel.Charges;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
    private Charges charges;
    boolean condition = false;
    private JPanel listPanel;
    private ValuePanel valuePanel;
    private JScrollPane scrollPane;
    public String language;
    //private ListView lv;

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
        language="polish";
        condition = true;
        listeners = new Listeners(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        p1 = new AnimationFrame(this);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBackground(Color.lightGray);

        JPanel p3 = new JPanel();
        p3.setMinimumSize(new Dimension(150, 450));
        p3.setPreferredSize(new Dimension(170, 450));


        listPanel = new JPanel();
        //jak tutaj zmieniam w dimension drugi parametr, to zmienia się ta lista
        listPanel.setPreferredSize(new Dimension(170, 700));


          charges= new Charges(this);
//        int ile = chargee.gcd(64088, 16022);
//        ArrayList<Integer> charges = new ArrayList<>();
//        ArrayList<chargeVariable<Integer>> testCharges;
//        testCharges = new ArrayList<>();
//        charges.add(16);
//        testCharges.add(new chargeVariable<>(16));
//        charges.add(64);
//        testCharges.add(new chargeVariable<>(64));
//        charges.add(64);
//        testCharges.add(new chargeVariable<>(64));
//        charges.add(128);
//        testCharges.add(new chargeVariable<>(128));
        //charges.add(6601064);
//        boolean wynik = chargee.elementsEqual(charges);
//        GCDCalculator calculator = new GCDCalculator();
//        int ladunek =0; //calculator.chargeCalc(charges);
//        System.out.println("ILE: " + ile + "wynik" + wynik);
//        System.out.println(Integer.toString(ladunek));

        chargeVariable<String> emptyValue;
        emptyValue = new chargeVariable<>(" ");
//        for (int i = 0; i < 4; i++) {
//            //ListView(listPanel, (double) charges.get(i));
//            System.out.println("Wypisuję:" + testCharges.get(i).setString());
//            //ListView(listPanel, testCharges.get(i).get().toString());
//            ListView(listPanel, testCharges.get(i));
//
//        }

//        for (int i = 0; i < 100; i++) {
//           // ListView(listPanel, emptyValue);
//        }
         scrollPane = new JScrollPane(listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(170, 350));
        p3.add(scrollPane);



        Button ask = new Button("res/ask.png", 20, 20);
        JToggleButton onOff = new JToggleButton("on/off");
        onOff.setSize(30, 20);

        onOff.addActionListener(listeners.plotListener);
        ask.addActionListener(listeners.askButtonListener);
        valuePanel=new ValuePanel();
//        valuePanel = new JPanel();
//        valuePanel.setMaximumSize(new Dimension(1000, 25));
//
//        valuePanel.setBackground(Color.WHITE);
//        JLabel eLabel = new JLabel(Messages.getString("estimation"));
//        JLabel eValueLabel = new JLabel(Double.toString(ladunek / Math.pow(10, 20)));
//        valuePanel.add(eLabel);
//        valuePanel.add(eValueLabel);

        p3.add(valuePanel);
        p3.add(new makeButtonsPanel(this));

        p2.add(ask, BorderLayout.WEST);

        p2.add(onOff, BorderLayout.EAST);
        ElectricField electricFieldPanel = new ElectricField(this);
        p3.add(electricFieldPanel);
        listeners.setEf(electricFieldPanel);

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
        currentDrop = new OilDrop(this);
/********************
        int charge=0;
        GCDCalculator calculator=new GCDCalculator();
        charges.getCharges_int().add(4);
        charges.getCharges_int().add(64);
        charges.getCharges_int().add(127);
            charge = calculator.chargeCalcNew(charges);

        valuePanel.geteValueLabel().setText(Double.toString(charge / Math.pow(10, 23)));
        System.out.println(Double.toString(charge / Math.pow(10, 23)));
 */
    }

//    public void resume() {
//        if (th != null) {
//
//            th = null;
//        }
//        th.start();
//    }
    public void setEValue()
    {
        BigInteger charge=BigInteger.ZERO;
        GCDCalculator calculator=new GCDCalculator();
        if(charges.getCharges_int().size()>1) {
//            charge = calculator.chargeCalc(charges);
         //   charge=calculator.chargeCalc(charges);
         charge = calculator.chargeCalcNew(charges);
            System.out.println("Charge from gcd: "+charge);
        }
        else if(charges.getCharges_int().size()==0){
        System.out.println("Dziwnie wchodzę do chargecalc");
        }else {
            charge = charges.getCharges_int().get(0);
        }
         BigDecimal dCharge=new BigDecimal(charge);
        dCharge.divide(new BigDecimal(10).pow(23),40, RoundingMode.HALF_UP);

        valuePanel.geteValueLabel().setText(dCharge.toString());
//        System.out.println("double bez rzutowania na string "+dCharge);
//        System.out.println("Ciekawe miejsce: "+Double.toString(charge / Math.pow(10, 23)));

    }
    public void ListView(JPanel columnpanel, chargeVariable charge, int chargePower) {
        JPanel rowPanel = new JPanel();

        rowPanel.setMinimumSize(new Dimension(170, 30));
        //należało ustawić serMinimumSize i zmieniać ustawienia
        rowPanel.setMaximumSize(new Dimension(1000, 30));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
        columnpanel.add(rowPanel);
        // rowPanel.setBounds(170, 70, 150, 20);
        JLabel dataList = new JLabel(" ");
        // dataList.setBounds(170, 70, 150, 20);

//        dataList.setText("Q_" + columnpanel.getComponentCount() + "="
//                + charge + "C");
//        if (charge.getClass()==double){
//
//        }
        int variableForPower=0;
//        double castChargeVariable= charge;
//        while(){
//
//        }
        if (charge.checkIfString()) {
            dataList.setText(charge.setString().substring(0,6)+"E-"+chargePower);
        } else {

            dataList.setText("Q_" + columnpanel.getComponentCount() + "=" + charge.setString().substring(0,6)+"E-"+chargePower + "C");
        }

        dataList.setVisible(true);
        rowPanel.add(dataList);

        if (columnpanel.getComponentCount() % 2 == 0)
            rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
        //DZIWNY TWOR PONIZEJ JEST NIEZBEDNY ABY DODANE LADUNKI OD RAZU SIE WYSWIETLALY
        scrollPane.getHorizontalScrollBar().setValue(1);
        scrollPane.getHorizontalScrollBar().setValue(0);

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

    public void setP1(AnimationFrame animationFrame) {
        p1 = animationFrame;
    }

    public Charges getCharges()
    {
        return charges;
    }

    public ValuePanel getValuePanel()
    {
        return valuePanel;
    }

    public JPanel getListPanel()
    {
        return listPanel;
    }
}
