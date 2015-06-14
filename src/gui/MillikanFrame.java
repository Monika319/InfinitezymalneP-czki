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

/**
 * Main frame of the program containg 3 Panels, Layouts and other visual components.
 */
public class MillikanFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    // private JPanel p1;
    public OilDrop currentDrop;
    private AnimationFrame p1;
    public Listeners listeners;
    private Charges charges;
    boolean condition = false;
    private JPanel listPanel;
    private ValuePanel valuePanel;
    private JScrollPane scrollPane;
    public String language;
    public ArrayList<Double> estimated;

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

    /**
     * Initializing main frame of the program, setting Layout and other important stuff for visual view
     */
    private void initialize() {
        estimated=new ArrayList<Double>();
        language="polish";
        condition = true;
        listeners = new Listeners(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        p1 = new AnimationFrame(this);
        JPanel p2 = new JPanel(new BorderLayout());
//        p2.setBackground(Color.lightGray);

        JPanel p3 = new JPanel();
        p3.setMinimumSize(new Dimension(150, 450));
        p3.setPreferredSize(new Dimension(170, 450));


        listPanel = new JPanel();
        //jak tutaj zmieniam w dimension drugi parametr, to zmienia się ta lista
        listPanel.setPreferredSize(new Dimension(170, 700));


          charges= new Charges(this);

         scrollPane = new JScrollPane(listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(170, 350));
        p3.add(scrollPane);



        Button ask = new Button("res/ask.png", 20, 20);
//        JToggleButton onOff = new JToggleButton("on/off");
//        onOff.setSize(30, 20);

//        onOff.addActionListener(listeners.plotListener);
        ask.addActionListener(listeners.askButtonListener);
        valuePanel=new ValuePanel();

        p3.add(valuePanel);
        p3.add(new makeButtonsPanel(this));

        p2.add(ask, BorderLayout.WEST);

//        p2.add(onOff, BorderLayout.EAST);
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

    }

    /**
     * Setting Elementary charge value counted from gcd for list of charges from simulation.
     */
    public void setEValue() {
        BigInteger charge = BigInteger.ZERO;
        GCDCalculator calculator = new GCDCalculator();
        if (charges.getCharges_int().size() > 1) {
            charge = calculator.chargeCalcNew(charges);
            System.out.println("Charge from gcd: " + charge);
        } else if (charges.getCharges_int().size() == 0) {}
        else {
            charge = charges.getCharges_int().get(0);
        }
        BigDecimal dCharge = new BigDecimal(charge);
        dCharge=dCharge.divide(new BigDecimal(10).pow(23), 40, RoundingMode.HALF_UP);
        System.out.println("DCharge" + dCharge);
        int chargePower=0;
        BigDecimal bd = dCharge; // the value you get
        double chargeForString=bd.doubleValue();
        double chargeForList = bd.doubleValue();
        do {
            chargeForList = chargeForList * 10;
            chargePower = chargePower + 1;
//            System.out.println("chargepower: "+chargePower);
        } while (chargeForList < 1);
        estimated.add(bd.doubleValue());
        valuePanel.geteValueLabel().setText( Double.toString(chargeForString).substring(0, 6)+"E-"+chargePower + "C");
    }

    /**
     * Displays counted charges as a list in column
     * @param columnpanel -Columin for storing list of charges from simulation
     * @param charge - counted charge from simulation
     * @param chargePower - the power of counted charge
     */
    public void ListView(JPanel columnpanel, double charge, int chargePower) {




        JPanel rowPanel = new JPanel();

        rowPanel.setMinimumSize(new Dimension(170, 30));
        //należało ustawić serMinimumSize i zmieniać ustawienia
        rowPanel.setMaximumSize(new Dimension(1000, 30));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        columnpanel.setLayout(new BoxLayout(columnpanel, BoxLayout.Y_AXIS));
        columnpanel.add(rowPanel);
        JLabel dataList = new JLabel(" ");


            dataList.setText("Q_" + columnpanel.getComponentCount() + "=" + Double.toString(charge).substring(0, 6)+"E-"+chargePower + "C");


        dataList.setVisible(true);
        rowPanel.add(dataList);

        if (columnpanel.getComponentCount() % 2 == 0)
            rowPanel.setBackground(SystemColor.inactiveCaptionBorder);
        //DZIWNY TWOR PONIZEJ JEST NIEZBEDNY ABY DODANE LADUNKI OD RAZU SIE WYSWIETLALY
        scrollPane.getHorizontalScrollBar().setValue(1);
        scrollPane.getHorizontalScrollBar().setValue(0);

    }


    /**
     * Getting animation frame
     * @return p1 - animation frame
     */
    public AnimationFrame getP1() {
        return p1;
    }

    /**
     * Getting list of counted charges
     * @return Charges class object
     */
    public Charges getCharges()
    {
        return charges;
    }

    /**
     * Getting panel with e value
     * @return evaluePanel
     */
    public ValuePanel getValuePanel()
    {
        return valuePanel;
    }

    /**
     * Getting list of charges in a Jpanel
     * @return listPanel
     */
    public JPanel getListPanel()
    {
        return listPanel;
    }
}
