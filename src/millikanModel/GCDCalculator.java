package millikanModel;

import java.util.ArrayList;

public class GCDCalculator {

    private int countedCharge;

    public GCDCalculator()
    {
        countedCharge=0;
    }

    public int chargeCalc(Charges unitCharge) {
        //UnitaryCharge unitCharge = new UnitaryCharge(charges_g);
        ArrayList<Integer> listdemo = new ArrayList<Integer>(unitCharge.getCharges_int());
        ArrayList<Integer> list = new ArrayList<Integer>(unitCharge.getCharges_int());
        if(list.size()>0)
        while (unitCharge.elementsEqual(listdemo) == false) {
            listdemo.remove(0);

            for (int i = 0; i < list.size() - 1; i++) {
                 System.out.println(listdemo.get(i));
                for (int j = 0; j < listdemo.size(); j++) {
                    listdemo.set(j,
                            unitCharge.gcd(listdemo.get(j), list.get(i)));
                    System.out.println(listdemo.get(j));
                }
                list.remove(0);
            }
            this.countedCharge = listdemo.get(0);
        }
        return countedCharge;
    }

}
