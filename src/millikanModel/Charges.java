package millikanModel;

import gui.Messages;
import gui.MillikanFrame;
import gui.chargeVariable;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Charges {


    private ArrayList<Double> chargesDouble = new ArrayList<>();


    private ArrayList<BigInteger> charges_int = new ArrayList<>();


    private BigDecimal q;
    private BigInteger intCharge;
    private MillikanFrame frame;

    public Charges(MillikanFrame mf) {
        frame = mf;
    }

    public Charges(ArrayList<BigInteger> charges_g) {
        charges_int = charges_g;
    }

    public boolean elementsEqual(ArrayList<Integer> checkList) {

        ArrayList<Integer> listdemo = new ArrayList<>(checkList);
        ArrayList<Integer> list = new ArrayList<>(checkList);
        listdemo.remove(0);
        for (int i = 0; i < list.size() - 1; i++) {
            for (Integer aListdemo : listdemo) {

                return aListdemo.equals(list.get(i));
            }
        }

        return false;

    }


    public void addCharge(OilDrop drop) {

        double E = drop.getE();
        if ((drop.getV1().compareTo(new BigDecimal(10E6)) < 0) && (drop.getV2().compareTo(new BigDecimal(10E6)) < 0)) {


            double f = (4 / 3) * Math.PI * Math.pow((9 * Constants.airViscosity / 2), (3 / 2));
            BigDecimal ff = new BigDecimal(1.0);
            ff = ff.divide(new BigDecimal(Constants.g * (drop.getOilDensity() - Constants.airDensity)), 40, RoundingMode.HALF_UP);
            ff = bigSqrt(ff, MathContext.DECIMAL128);
            ff = ff.multiply(new BigDecimal(f));
            System.out.println("f=" + f);
            q = ff;
            System.out.println("pierwsze podejscie" + q);

            BigDecimal zmiennaPom = drop.getV1().add(drop.getV2());
            System.out.println("zmienna Pom=" + zmiennaPom);

            BigDecimal pierw = (bigSqrt(drop.getV1(), MathContext.DECIMAL128));
            System.out.println("sqrt=" + pierw);

            q = q.multiply(zmiennaPom);
            q = q.multiply(pierw);
            System.out.println("drugie podejscie" + q);
            System.out.println("E:" + E);
            if (E==0){
                JOptionPane.showMessageDialog(frame,
                        "Electric Field is 0!",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }else {
                q = q.divide(new BigDecimal(E), 30, RoundingMode.HALF_UP);
                //TO JEST ROZPACZ
                q = q.divide(new BigDecimal(-70.9952292808831), 40, RoundingMode.HALF_UP);

                System.out.println("trzecie podejscie" + q);
                BigDecimal absQ = q.abs();
                int potega_q = 0;


                System.out.println("Potega q: " + potega_q);
                System.out.println("obliczone q=" + q);

                System.out.println("prawdziwe q=" + frame.currentDrop.getCharge());
                System.out.println("stosunek" + q.divide((new BigDecimal(frame.currentDrop.getCharge())), 40, RoundingMode.HALF_UP));
                BigDecimal power = new BigDecimal(10);
                power.pow(23);
                intCharge = (absQ.multiply(power)).toBigInteger();
                System.out.println("intCharge=" + intCharge);

                BigDecimal bd = q;
                double chargeForList = bd.doubleValue();

                if (Double.isInfinite(chargeForList)) {
                    JOptionPane.showMessageDialog(frame,
                            "You have to measure both velocities",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    System.out.println("nowe q=" + q);
                    System.out.println("Charge for list: " + chargeForList);
                    chargesDouble.add(chargeForList);
                    do {
                        chargeForList = chargeForList * 10;
                        potega_q = potega_q + 1;
                    } while (chargeForList < 1);


                    System.out.println("Lista chargow z decimalami: " + chargesDouble);


                    frame.ListView(frame.getListPanel(), chargeForList, potega_q);


                BigDecimal neoQ=absQ.multiply(new BigDecimal(10).pow(23));
                neoQ=neoQ.setScale(0,RoundingMode.HALF_UP);
                System.out.println("neoQ="+neoQ);
                BigInteger integerQ=neoQ.toBigInteger();
                System.out.println("integerQ="+integerQ);
                charges_int.add(integerQ);

                }
            }
        } else

            JOptionPane.showMessageDialog(frame,
                    Messages.getString("twoVelocities"),
                    Messages.getString("tryAgain"),
                    JOptionPane.ERROR_MESSAGE);
        //System.err.println("NIE ZMIERZONO OBU PREDKOSCI!!!");
    }

    public ArrayList<BigInteger> getCharges_int()
    {
        return charges_int;
    }


    public BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO)==0) {
            return a;
        } else {
            return gcd(b, a.mod( b));
        }
    }


    public ArrayList<Double> charge_doubleList() {
        return chargesDouble;
    }
    public int decimalCut(int charge) {
        int digits[] = new int[5];
        Integer newCharge = charge;
        System.out.println("newCharge: " + newCharge);
        int len = newCharge.toString().length();

        for (int i = 0; i < digits.length; i++) {
            digits[i] = (int) Math.floor(newCharge / Math.pow(10, len - i - 1));
            newCharge = newCharge - digits[i] * (int) Math.pow(10, len - i - 1);

        }

        int newerCharge = 0;
        for (int j = 0; j < digits.length; j++)

        {

            newerCharge += digits[j] * (int) Math.pow(10, digits.length - j - 1);
        }

        return newerCharge;

    }

    public static BigDecimal bigSqrt(BigDecimal squarD, MathContext rootMC) {

        BigDecimal TWO = new BigDecimal(2);
        double SQRT_10 = 3.162277660168379332;


        // General number and precision checking
        int sign = squarD.signum();
        if (sign == -1)
            throw new ArithmeticException("\nSquare root of a negative number: " + squarD);
        else if (sign == 0)
            return squarD.round(rootMC);

        int prec = rootMC.getPrecision();           // the requested precision
        if (prec == 0)
            throw new IllegalArgumentException("\nMost roots won't have infinite precision = 0");

        // Initial precision is that of double numbers 2^63/2 ~ 4E18
        int BITS = 62;                              // 63-1 an even number of number bits
        int nInit = 16;                             // precision seems 16 to 18 digits
        MathContext nMC = new MathContext(18, RoundingMode.HALF_DOWN);


        // Iteration variables, for the square root x and the reciprocal v
        BigDecimal x, e;              // initial x:  x0 ~ sqrt()
        BigDecimal v, g;              // initial v:  v0 = 1/(2*x)

        // Estimate the square root with the foremost 62 bits of squarD
        BigInteger bi = squarD.unscaledValue();     // bi and scale are a tandem
        int biLen = bi.bitLength();
        int shift = Math.max(0, biLen - BITS + (biLen % 2 == 0 ? 0 : 1));   // even shift..
        bi = bi.shiftRight(shift);                  // ..floors to 62 or 63 bit BigInteger

        double root = Math.sqrt(bi.doubleValue());
        BigDecimal halfBack = new BigDecimal(BigInteger.ONE.shiftLeft(shift / 2));

        int scale = squarD.scale();
        if (scale % 2 == 1)                          // add half scales of the root to odds..
            root *= SQRT_10;                          // 5 -> 2, -5 -> -3 need half a scale more..
        scale = (int) Math.floor(scale / 2.);          // ..where 100 -> 10 shifts the scale

        // Initial x - use double root - multiply by halfBack to unshift - set new scale
        x = new BigDecimal(root, nMC);
        x = x.multiply(halfBack, nMC);                          // x0 ~ sqrt()
        if (scale != 0)
            x = x.movePointLeft(scale);

        if (prec < nInit)                 // for prec 15 root x0 must surely be OK
            return x.round(rootMC);        // return small prec roots without iterations

        // Initial v - the reciprocal
        v = BigDecimal.ONE.divide(TWO.multiply(x), nMC);        // v0 = 1/(2*x)


        // Collect iteration precisions beforehand
        ArrayList<Integer> nPrecs = new ArrayList<>();

        assert nInit > 3 : "Never ending loop!";                // assume nInit = 16 <= prec

        // Let m be the exact digits precision in an earlier! loop
        for (int m = prec + 1; m > nInit; m = m / 2 + (m > 100 ? 1 : 2))
            nPrecs.add(m);


        // The loop of "Square Root by Coupled Newton Iteration" for simpletons
        for (int i = nPrecs.size() - 1; i > -1; i--) {
            // Increase precision - next iteration supplies n exact digits
            nMC = new MathContext(nPrecs.get(i), (i % 2 == 1) ? RoundingMode.HALF_UP :
                    RoundingMode.HALF_DOWN);

            // Next x                                                 // e = d - x^2
            e = squarD.subtract(x.multiply(x, nMC), nMC);
            if (i != 0)
                x = x.add(e.multiply(v, nMC));                          // x += e*v     ~ sqrt()
            else {
                x = x.add(e.multiply(v, rootMC), rootMC);               // root x is ready!
                break;
            }

            // Next v                                                 // g = 1 - 2*x*v
            g = BigDecimal.ONE.subtract(TWO.multiply(x).multiply(v, nMC));

            v = v.add(g.multiply(v, nMC));                            // v += g*v     ~ 1/2/sqrt()
        }

        return x;                        // return sqrt(squarD) with precision of rootMC
    }

}
