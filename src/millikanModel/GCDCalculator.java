package millikanModel;

import java.util.ArrayList;
import java.util.ListIterator;

public class GCDCalculator {

    private int countedCharge;
    private int unitCharge;

    public GCDCalculator() {
        countedCharge = 0;
    }

//    public int chargeCalc(Charges unitCharge) {
//
//        //UnitaryCharge unitCharge = new UnitaryCharge(charges_g);
//        ArrayList<Integer> listdemo = new ArrayList<Integer>(unitCharge.getCharges_int());
//        ArrayList<Integer> list = new ArrayList<Integer>(unitCharge.getCharges_int());
//        if (list.size() > 0) {
//            if (list.size() == 1) {
//                this.countedCharge = 0;
//            }
//            while (unitCharge.elementsEqual(listdemo) == false) {
////                for (int i = 0, j = 1; j < list.size(); j++, i++) {
////                    list.add(unitCharge.gcd(list.get(i), list.get(j)));
////                }
////                listdemo = list;
////            }
////                this.countedCharge=listdemo.get(0);
////                listdemo.remove(0);
////
//                for (int i = 0; i < list.size() - 1; i++) {
//                    System.out.println(listdemo.get(i));
//                    for (int j = 0; j < listdemo.size(); j++) {
//                        listdemo.set(j,
//                                unitCharge.gcd(listdemo.get(j), list.get(i)));
//                        System.out.println("listdemo.get(j): " + listdemo.get(j));
//                        System.out.println("listdemo.get(i): " + list.get(i));
//                    }
//                    list.remove(0);
//                }
//            }
//                this.countedCharge = listdemo.get(0);
//            }
//
//        return countedCharge;
//    }
//public int chargeCalc(ArrayList<Integer> charges_g) {
//    unitCharge = new UnitaryCharge(charges_g);
//    	ArrayList<Integer> listdemo = new ArrayList<Integer>(charges_g);
//    	ArrayList<Integer> list = new ArrayList<Integer>(charges_g);
//            	while (unitCharge.elementsEqual(listdemo) == false) {listdemo.remove(0);
//        		for (int i = 0; i < list.size() - 1; i++) {
//            				// System.out.println(listdemo.get(0));
//                    				for (int j = 0; j < listdemo.size(); j++) {
//                					listdemo.set(j,
//                        							unitCharge.gcd(listdemo.get(j), list.get(i)));
//                				System.out.println(listdemo.get(j));
//                				}
//            			list.remove(0);
//            			}
//        		this.coountedCharge = listdemo.get(0);
//        		}
//    		return coountedCharge;
//    	}
//
//            }


    public int chargeCalcNew(Charges unitCharge) {
        //UnitaryCharge unitCharge = new UnitaryCharge(charges_g);
        ArrayList<Integer> listdemo = new ArrayList<Integer>(unitCharge.getCharges_int());
        ArrayList<Integer> list = new ArrayList<Integer>(unitCharge.getCharges_int());
//        int n=0;
        while (list.size() > 1) {

            for (int i = 0; i < list.size() - 1; i++)
                listdemo.add(unitCharge.gcd(list.get(i), list.get(i + 1)));
            for (int k = list.size() - 1; k > -1; k--)
                list.remove(k);
            for (int j = 0; j < listdemo.size(); j++)
                list.add(listdemo.get(j));
            for (int k = listdemo.size() - 1; k > -1; k--)
                listdemo.remove(k);
//      n+=1;
//      System.out.print("n"+n);
//          if(n>20)
//              break;
        }
        if (list.size() > 0)
            return list.get(0);
        else
            return 0;
    }

    public double linestFit(Charges unitCharge) {
        double estimatedCharge = 0;
        double xMean = 0;
        double yMean = 0;
        int numberOfCharges = 0;
        double denominatorSum = 0;
        ArrayList<Double> chargesList = new ArrayList<Double>(unitCharge.getChargesDouble());
        numberOfCharges = chargesList.size();
        if (numberOfCharges == 1) {
            //jesli mamy 1 pomiar
            estimatedCharge = 0;

        } else {
            xMean = (double) ((numberOfCharges + 2) / 2) * numberOfCharges;

            for (int i = 0; i < numberOfCharges; i++) {
                yMean += chargesList.get(i);

            }
            yMean = yMean / numberOfCharges;

            for (int j = 0; j < numberOfCharges; j++) {
                estimatedCharge += (chargesList.get(j) - yMean) * (j + 1 - xMean);
                denominatorSum = (j + 1 - xMean) * (j + 1 - xMean);
            }
            estimatedCharge = (estimatedCharge / denominatorSum);
        }
        return estimatedCharge;
    }


}
