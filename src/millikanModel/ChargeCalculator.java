package millikanModel;

import java.util.ArrayList;

public class ChargeCalculator {

    private int countedCharge;

    public ChargeCalculator() {
        // unitCharge=new UnitaryCharge(charges_g);
        // chargeCalc(charges_g);

    }

    public int chargeCalc(ArrayList<Integer> charges_g) {
        UnitaryCharge unitCharge = new UnitaryCharge(charges_g);
        ArrayList<Integer> listdemo = new ArrayList<Integer>(charges_g);
        ArrayList<Integer> list = new ArrayList<Integer>(charges_g);

        while (unitCharge.elementsEqual(listdemo) == false) {
            listdemo.remove(0);
            for (int i = 0; i < list.size() - 1; i++) {
                // System.out.println(listdemo.get(0));
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
