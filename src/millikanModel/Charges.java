package millikanModel;

import gui.MillikanFrame;
import gui.chargeVariable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Charges {
    private ArrayList<Double> charges = new ArrayList<Double>();
    private ArrayList<Integer> charges_int = new ArrayList<Integer>();
    private double q;
    private int intCharge;
    private MillikanFrame frame;

    public Charges(MillikanFrame mf) {frame=mf;}
    public Charges(ArrayList<Integer> charges_g)
    {
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

        if((drop.getV1()<10E6)&&(drop.getV2()<10E6))
        {
            q = (4 / 3) * Math.PI * Math.pow((9 * Constants.airViscosity / 2), (3 / 2))
                    * Math.sqrt(1 / (Constants.g * (drop.getOilDensity() - Constants.airDensity)));
            q *= (drop.getV1() + drop.getV2()) * Math.sqrt(drop.getV1()) / E;
            double absQ = Math.abs(q);
            System.out.println("obliczone q="+q);
            System.out.println("prawdziwe q="+frame.currentDrop.getCharge());

            intCharge = (int) (absQ * Math.pow(10., 23.));
            System.out.println("intCharge="+intCharge);
            q=(double)intCharge*Math.pow(10.,-23.);
            if(q<1.6E-19)
            {
                JOptionPane.showMessageDialog(frame,
                        "An unexpected measurement error occured!",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("nowe q="+q);
            charges.add(q);
            int cutIntCharge=decimalCut(intCharge);
            System.out.println("cutCHarge:"+cutIntCharge);
            double doubleCutIntCharge=(double)cutIntCharge;
            for(int i=0;i<23;i++)
            {
                doubleCutIntCharge/=10;
            }
            System.out.println("Blad wzgledny pomiaru q:"+(q-doubleCutIntCharge)/q*100);
            charges_int.add(cutIntCharge);
            frame.ListView(frame.getListPanel(),new chargeVariable<>(doubleCutIntCharge));
        }
        else
            JOptionPane.showMessageDialog(frame,
                    "You have to measure both velocities",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        //System.err.println("NIE ZMIERZONO OBU PREDKOSCI!!!");
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
            return gcd(b, a % b);
        }
    }

    public ArrayList<Integer> getCharges_int()
    {
        return charges_int;
    }
    public int decimalCut(int charge)
    {
        int digits[]=new int[5];
        Integer newCharge=new Integer(charge);
        int len= newCharge.toString().length();

        for(int i=0;i<digits.length;i++)
        {
            digits[i]= (int) Math.floor(newCharge/Math.pow(10,len-i-1));
            newCharge=newCharge-digits[i]*(int)Math.pow(10,len-i-1);
//            System.out.println(digits[i]);
        }
        int newerCharge=0;
        for(int j=0;j<len;j++)
        {
            if(j<digits.length)
                newerCharge+= digits[j]*(int)Math.pow(10,len-j-1);
            else
                newerCharge+=(int)Math.pow(10,digits.length-j-1);
        }
        return newerCharge;
    }

}
