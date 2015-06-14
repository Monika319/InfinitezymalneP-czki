package millikanModel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class contains Greatest Common divider calculator for finding elementary charge value.
 */
public class GCDCalculator {

    public GCDCalculator() {}

    /**
     * Greatest Common divider calculator
     * @param unitCharge list of charges
     * @return evaluated elementary charge
     */
 public BigInteger chargeCalcNew(Charges unitCharge) {
        ArrayList<BigInteger> listdemo = new ArrayList<BigInteger>(unitCharge.getCharges_int());
        ArrayList<BigInteger> list = new ArrayList<BigInteger>(unitCharge.getCharges_int());
        while (list.size() > 1)
        {
            for (int i = 0; i < list.size() - 1; i++)
                listdemo.add(unitCharge.gcd(list.get(i), list.get(i + 1)));
            for (int k = list.size() - 1; k > -1; k--)
                list.remove(k);
            for (int j = 0; j < listdemo.size(); j++)
                list.add(listdemo.get(j));
            for (int k = listdemo.size() - 1; k > -1; k--)
                listdemo.remove(k);
        }
        if (list.size() > 0)
            return list.get(0);
        else
            return BigInteger.ZERO;
    }

}
