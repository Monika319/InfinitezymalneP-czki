package millikanModel;

import java.util.ArrayList;
import java.util.ListIterator;

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
//                 System.out.println(listdemo.get(i));
                for (int j = 0; j < listdemo.size(); j++) {
                    listdemo.set(j,
                            unitCharge.gcd(listdemo.get(j), list.get(i)));
//                    System.out.println(listdemo.get(j));
                }
                list.remove(0);
            }
            this.countedCharge = listdemo.get(0);
        }
        return countedCharge;
    }
    public int chargeCalcNew(Charges unitCharge) {
        //UnitaryCharge unitCharge = new UnitaryCharge(charges_g);
        ArrayList<Integer> listdemo = new ArrayList<Integer>(unitCharge.getCharges_int());
        ArrayList<Integer> list = new ArrayList<Integer>(unitCharge.getCharges_int());
//        int n=0;
      while(list.size()>1)
      {

      for(int i=0;i<list.size()-1;i++)
          listdemo.add(unitCharge.gcd(list.get(i), list.get(i + 1)));
      for(int k=list.size()-1;k>-1;k--)
              list.remove(k);
      for(int j=0;j<listdemo.size();j++)
          list.add(listdemo.get(j));
      for(int k=listdemo.size()-1;k>-1;k--)
          listdemo.remove(k);
//      n+=1;
//      System.out.print("n"+n);
//          if(n>20)
//              break;
      }
        if(list.size()>0)
            return list.get(0);
        else
            return 0;
    }

}
