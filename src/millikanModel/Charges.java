package millikanModel;

import gui.MillikanFrame;
import gui.chargeVariable;

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
        //IF MA ZA ZADANIE ODRZUCIC V1(V2)=INFINITY
//        if((drop.getV1()<0)||(drop.getV2()>0))
//            System.err.println("ZLE ZMIERZONO PREDKOSCI!!!");
//        else
        if((drop.getV1()<10E6)&&(drop.getV1()<10E6))
        {
            q = (4 / 3) * Math.PI * Math.pow((9 * Constants.airViscosity / 2), (3 / 2))
                    * Math.sqrt(1 / (Constants.g * (drop.getOilDensity() - Constants.airDensity)));
            q *= (drop.getV1() + drop.getV2()) * Math.sqrt(drop.getV1()) / E;
            double absQ = Math.abs(q);
           System.out.println("q="+q);
            System.out.println("|q|="+absQ);
            //q=absQ
            //TODO:ZROBIC TU OBCIECIE
            intCharge = (int) Math.floor(absQ * Math.pow(10., 23.));
           System.out.println("intCharge="+intCharge);
           q=(double)intCharge*Math.pow(10.,-23.);
            System.out.println("q="+q);
            //System.out.println("new q="+q);
            charges.add(q);
            charges_int.add(intCharge);
            frame.ListView(frame.getListPanel(), new chargeVariable<>(intCharge));
        }
        //TODO:WSTAWIC OKIENKO ALBO KOMUNIKAT O BLEDZIE
        else
            System.err.println("NIE ZMIERZONO OBU PREDKOSCI!!!");
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
    } public int decimalCut(int charge)
    {
        int digits[]=new int[5];
        int newCharge=(int)Math.floor(charge*Math.pow(10.,23.));
        int len=0;
        while(newCharge/Math.pow(10.,(double)len)>=1.)
            len+=1;
        len-=1;
        for(int i=0;i<digits.length;i++)
        {

        }
        return newCharge;
    }
    //TODO:ZROBIC DOKONCZYC TO
//    public int decimalCut(int charge)
//    {
//        int digits[]=new int[5];
//        int newCharge=(int)Math.floor(charge*Math.pow(10.,23.));
//        int len=0;
//        while(newCharge/Math.pow(10.,(double)len)>=1.)
//            len+=1;
//        len-=1;
//        for(int i=0;i<digits.length;i++)
//        {
//
//        }
//        return newCharge;
//    }
}
