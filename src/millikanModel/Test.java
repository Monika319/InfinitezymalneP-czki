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
    private double y;
    private long startTime;
    private String line;
    private OilDrop drop;
    public PrintWriter writer=null;

    public Test(OilDrop currentDrop,PrintWriter writerr)
    {
        writer=writerr;
        drop=currentDrop;
        q=drop.getCharge();
        check();
        startTime = System.currentTimeMillis();
        writer.print(String.format("Begin of file t: %d%n",startTime));

    }
    public void check()
    {
         u=drop.getV();
         u1=drop.getV1();
         u2=drop.getV2();
         y=drop.getY();
    }
    public void writeLine()
    {
        line=String.format("t: %15d y: %15e v: %15e v1: %15e v2: %15e%n ",System.currentTimeMillis()-startTime,y,u,u1,u2);
        writer.print(line);

    }

    public void test()
    {
        check();
        writeLine();

    }

}
