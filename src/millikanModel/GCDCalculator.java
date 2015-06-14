package millikanModel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;

public class GCDCalculator {

    private int countedCharge;

    public GCDCalculator() {
        countedCharge = 0;
    }

    public BigInteger chargeCalcNew(Charges unitCharge) {
        ArrayList<BigInteger> listdemo = new ArrayList<>(unitCharge.getCharges_int());
        ArrayList<BigInteger> list = new ArrayList<>(unitCharge.getCharges_int());
        while (list.size() > 1)
        {
            for (int i = 0; i < list.size() - 1; i++)
                listdemo.add(unitCharge.gcd(list.get(i), list.get(i + 1)));
            for (int k = list.size() - 1; k > -1; k--)
                list.remove(k);
            for (int i = 0; i < listdemo.size(); i++) {
                BigInteger aListdemo = listdemo.get(i);
                list.add(aListdemo);
            }
            for (int k = listdemo.size() - 1; k > -1; k--)
                listdemo.remove(k);
        }
        if (list.size() > 0)
            return list.get(0);
        else
            return BigInteger.ZERO;
    }

    public int getCountedCharge() {
        return countedCharge;
    }

    public void setCountedCharge(int countedCharge) {
        this.countedCharge = countedCharge;
    }
}
