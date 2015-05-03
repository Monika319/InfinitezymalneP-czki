package gui;

import java.util.ArrayList;
import java.util.List;

public class UnitaryCharge {
	private List<Double> charges = new ArrayList<Double>();
	private List<Integer> charges_gcd = new ArrayList<Integer>();
	private double q;
	private double absQ;
	private int realCharge;
	
	public UnitaryCharge(){
		
	}
	public UnitaryCharge(List<Integer> charges_g) {
		charges_gcd=charges_g;
	}
	
	public boolean elementsEqual(List<Integer> checkList) {
		ArrayList<Integer> listdemo = new ArrayList<Integer>(checkList);
		ArrayList<Integer> list = new ArrayList<Integer>(checkList);
		listdemo.remove(0);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < listdemo.size(); j++) {
				if (listdemo.get(j) == list.get(i)) {
					return true;
				} else
					return false;
			}
		}

		return false;

	}

	public void calculateCharge(OilDrop drop, double E) {
		Constants C = new Constants();
		q = (4 / 3) * Math.PI * Math.pow((9 * C.airViscosity / 2), (3 / 2))
				* Math.sqrt(1 / (C.g * (drop.getOilDensity() - C.airDensity)));
		q *= (drop.getV1() + drop.getV2()) * Math.sqrt(drop.getV1()) / E;
		absQ = Math.abs(q);
		realCharge = (int) (absQ * Math.pow(10., 23.));
		charges.add(q);
		charges_gcd.add(realCharge);
	}

	

	public double getQ() {
		return q;
	}

	public double getRealCharge() {
		return realCharge;
	}

	public List<Double> chargeList() {
		return charges;
	}
	public List<Integer> chargeintList() {
		return charges_gcd;
	}

	int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}
