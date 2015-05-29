package millikanModel;

import java.io.*;

/**
 * Created by rafal on 16.05.15.
 */
public class Test
{

    private double q;
    private double u;
    private double u1;
    private double u2;
    private double pd1T1;
    private double pd1T2;
    private double pd2T1;
    private double pd2T2;
    private double y;
    private double E;



    private long startTime;
    private String line;
    private OilDrop drop;
    private Capacitor C;
    private Photodetector pd1;
    private Photodetector pd2;
    public Test(OilDrop currentDrop,Capacitor c,Photodetector PD1,Photodetector PD2)
    {
        drop=currentDrop;
        C=c;
        pd1=PD1;
        pd2=PD2;
        check();
        q=drop.getCharge();
        startTime = System.currentTimeMillis();
    }
    public void check()
    {

        u=drop.getV();
        u1=drop.getV1();
        u2=drop.getV2();
        y=drop.getY();
        E=C.getE();
        pd1T1=pd1.getT1();
        pd1T2=pd1.getT2();
        pd2T1=pd2.getT1();
        pd2T2=pd2.getT2();
    }
    public void writeLine()
    {
        line=String.format("t: %10d| y: %10e|v: %10e | E: %10e | q: %10e | t1(pd1): %10e |t2(pd1): %10e | t1(pd2): %10e |t2(pd2): %10e |%n ",System.currentTimeMillis()-startTime,y,u,E,q,pd1T1,pd1T2,pd2T1,pd2T2);
        System.out.print(line);
    }

    public void test()
    {
        check();
        writeLine();

    }

    public void setDrop(OilDrop drop)
    {
        this.drop = drop;
        q=this.drop.getCharge();
    }
}
