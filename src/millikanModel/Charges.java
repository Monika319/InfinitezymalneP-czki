package millikanModel;

import gui.MillikanFrame;
import gui.chargeVariable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Charges {
    private ArrayList<Double> charges = new ArrayList<Double>();
    private ArrayList<Integer> charges_int = new ArrayList<Integer>();
    private ArrayList<Double> chargesDouble = new ArrayList<Double>();
    private double q;
    private int intCharge;
    private MillikanFrame frame;

    public Charges(MillikanFrame mf) {
        frame = mf;
    }

    public Charges(ArrayList<Integer> charges_g) {
        charges_int = charges_g;
    }

    public boolean elementsEqual(ArrayList<Integer> checkList) {
        // TODO: Można ładniej- po jednym elem.
        // FIXME: SDakask
        ArrayList<Integer> listdemo = new ArrayList<Integer>(checkList);
        ArrayList<Integer> list = new ArrayList<Integer>(checkList);
        listdemo.remove(0);
        for (int i = 0; i < list.size() - 1; i++) {
            for (Integer aListdemo : listdemo) {

                return aListdemo.equals(list.get(i));
            }
        }

        return false;

    }

    public void addCharge(OilDrop drop, double E) {
        //FIXME:napisz to funkcyjnie
        // GCDCalculator calculator = new GCDCalculator();

        if ((drop.getV1() < 10E6) && (drop.getV2() < 10E6)) {
            double f = (4 / 3) * Math.PI * Math.pow(drop.getRadius(), 3) * (drop.getOilDensity() - Constants.airDensity) * Constants.g;
//            q = (4 / 3) * Math.PI * Math.pow((9 * Constants.airViscosity / 2), (3 / 2))
//                    * Math.sqrt(1 / (Constants.g * (drop.getOilDensity() - Constants.airDensity)));
            // q *= (drop.getV1() + drop.getV2()) * Math.sqrt(drop.getV1()) / E;
            //tutaj powinien byc wspolczynnik E/10, zeby odpowiednio liczylo ladnek!
            //  q = (f / (E/10)) * (Math.abs(drop.getV2() / drop.getV1()) - 1);

      //      q = (f / (E / 10)) * (Math.abs(drop.getV2() / drop.getV1()) - 1) / 10;
            q = (f / E ) * (Math.abs(drop.getV2() / drop.getV1()) - 1) ;
            double absQ = Math.abs(q);
            int potega_q = 0;

//            while(q<1){
//                q=q*10;
//                potega_q++;
//            }
            System.out.println("Potega q: " + potega_q);
            System.out.println("obliczone q=" + q);

            chargesDouble.add(Math.abs(q));
            //sortuje za kazdym razem po dodaniu ladunku
            Collections.sort(chargesDouble);


            System.out.println("chargesDouble: " + chargesDouble);

            System.out.println("prawdziwe q=" + frame.currentDrop.getCharge());

            intCharge = (int) (absQ * Math.pow(10., 23.));
            System.out.println("intCharge=" + intCharge);
            q = (double) intCharge * Math.pow(10., -23.);
//            if (q < 1.6E-19) {
//                JOptionPane.showMessageDialog(frame,
//                        "An unexpected measurement error occured!",
//                        "Try again",
//                        JOptionPane.ERROR_MESSAGE);
//            } else
            if (Double.isInfinite(q)) {
                JOptionPane.showMessageDialog(frame,
                        "You have to measure both velocities",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
                //System.err.println("NIE ZMIERZONO OBU PREDKOSCI!!!");
            } else {
                //if (q >= 1.6E-19) {
                System.out.println("nowe q=" + q);
                charges.add(q);
                int cutIntCharge = decimalCut(intCharge);
                System.out.println("cutCHarge:" + cutIntCharge);


                this.getCharges_int().add(cutIntCharge);
                // int charge = calculator.chargeCalcNew(this);

                double doubleCutIntCharge = (double) cutIntCharge;
                while (doubleCutIntCharge > 1) {
                    doubleCutIntCharge = doubleCutIntCharge / 10;
                }
                // System.out.println("Blad wzgledny pomiaru q:" + (q - doubleCutIntCharge) / q * 100);
                do {
                    q = q * 10;
                    potega_q = potega_q + 1;
                } while (q < 1);
                //tu ma byc potega 19+5
                //  doubleCutIntCharge=doubleCutIntCharge*Math.pow(10.,-potega_q+1);

                //doubleCutIntCharge=cutIntCharge*Math.pow(10.,-24);

                // doubleCutIntCharge=;
//                for (int i = 0; i < 23; i++) {
//                    doubleCutIntCharge /= 1000;
//                }
                // double cutChargeforList = cutIntCharge * Math.pow(10., -(7 + potega_q));
                double cutChargeforList = cutIntCharge * Math.pow(10., -(4 + potega_q));
                System.out.println("Double cut int charge:  " + doubleCutIntCharge);
                System.out.println("Cut charge for list: " + cutChargeforList);

                charges_int.add(cutIntCharge);
                //  frame.ListView(frame.getListPanel(), new chargeVariable<>(doubleCutIntCharge));

                //frame.ListView(frame.getListPanel(),);
                frame.ListView(frame.getListPanel(), new chargeVariable<>(cutChargeforList), potega_q);
                //   frame.ListView(frame.getListPanel(),new chargeVariable<>(charge));
            }
        }
        //  } else
//        if() {
//            JOptionPane.showMessageDialog(frame,
//                    "You have to measure both velocities",
//                    "Try again",
//                    JOptionPane.ERROR_MESSAGE);
//            //System.err.println("NIE ZMIERZONO OBU PREDKOSCI!!!");
//        }
    }


    public double getQ() {
        return q;
    }

    public double getIntCharge() {
        return intCharge;
    }

    public ArrayList<Double> chargeList() {
        return charges;
    }

    public ArrayList<Integer> charge_intList() {
        return charges_int;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return
                    gcd(b, a % b);
        }
    }

    public ArrayList<Integer> getCharges_int() {
        return charges_int;
    }

    public ArrayList<Double> getChargesDouble() {
        return chargesDouble;
    }

    public int decimalCut(int charge) {
        int digits[] = new int[5];
        Integer newCharge = new Integer(charge);
        System.out.println("newCharge: " + newCharge);
        int len = newCharge.toString().length();
        //  System.out.println("len: " + len);

        for (int i = 0; i < digits.length; i++) {
            digits[i] = (int) Math.floor(newCharge / Math.pow(10, len - i - 1));
            newCharge = newCharge - digits[i] * (int) Math.pow(10, len - i - 1);
            //  System.out.println(digits[i]);
        }
        // System.out.println("digits.length: " + digits.length);
        int newerCharge = 0;
        for (int j = 0; j < digits.length; j++)
        //for(int j=0;j<len;j++)
        {
//            if(j<digits.length)
//                newerCharge+= digits[j]*(int)Math.pow(10,len-j-1);
//            else
            newerCharge += digits[j] * (int) Math.pow(10, digits.length - j - 1);
        }
        //jak damy tak, to ta liczba na pewno będzie podzielna przez 16
//        while(newerCharge%16!=0){
//            newerCharge=newerCharge*10;
//        }
        return newerCharge;
        //return newerCharge * 1000;
    }

}
