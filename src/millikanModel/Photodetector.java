package millikanModel;

import gui.AnimationFrame;

import java.awt.*;

public class Photodetector {

    private double t1;
    private double t2;
    private int y1;
    private int y2;
    private boolean on;
    private static int photoCounter=0;
    private int myCounter;


    public Photodetector(int h1, int h2) {
        photoCounter+=1;
        myCounter=photoCounter;
        y1 = h1;
        y2 = h2;
        on = false;
        t1=0;
        t2=0;
    }
    /**
     * Created by monika03 on 17.05.15.
     */
    public void paintPhotoDetector(Graphics g, AnimationFrame af) {

        g.setColor(Color.darkGray);
        g.fillRoundRect(9 * af.getWidth() / 10, (int) y1-5, af.getWidth() / 10, (int) (y2 - y1) + 10, (int) ((y2 - y1) / 4), (int) ((y2 - y1) / 4));

        if (on) {
            g.setColor(Color.red);
            g.drawLine(0,  y1, (int) (9 * af.getWidth() / 10),  y1);
            g.drawLine(0,  y2, (int) (9 * af.getWidth() / 10),  y2);
            g.setColor(Color.green);

        } else
        {
            g.setColor(Color.red);
        }

        g.fillOval((int) (9.5 * af.getWidth() / 10), (int) Math.abs((y2 + y1) / 2), (int) Math.abs((y2 - y1) / 2), (int) Math.abs((y2 - y1) / 2));
        if(t1>0)
            g.setColor(Color.blue);
        else g.setColor(Color.gray);
            g.fillOval((int) (9.1 * af.getWidth() / 10), y1-2, (int) Math.abs((y2 - y1) / 5), (int) Math.abs((y2 - y1) / 5));
        if(t2>0)
            g.setColor(Color.blue);
        else g.setColor(Color.gray);
        g.fillOval((int) (9.1 * af.getWidth() / 10), y2-6, (int) Math.abs((y2 - y1) / 5), (int) Math.abs((y2 - y1) / 5));
    }

//    /**
//     * Created by monika03 on 17.05.15.
//     */
//    public void paintPhotoDetector1(Graphics g, AnimationFrame af) {
//
//
//        g.setColor(Color.darkGray);
//        //g.fillRoundRect(9 * af.getWidth() / 10, (int) (y1 - 5.0), af.getWidth() / 10, (int) (y2 - y1) + 10, (int) ((y2 - y1) / 4), (int) ((y2 - y1) / 4));
//        g.fillRoundRect(9 * af.getWidth() / 10, 1 + (int) (y2 - y1) - 40 + af.getCapacitor().getY1(), af.getWidth() / 10, (int) (y2 - y1) + 10, (int) ((y2 - y1) / 4), (int) ((y2 - y1) / 4));
//
//       int yy1=1 +  (y2 - y1) - 38+ af.getCapacitor().getY1();
//        int yy2=1 +  (y2 - y1) + af.getCapacitor().getY1();
//
//
//       // System.out.println("y1: "+Integer.toString(yy1)+"y2: "+Integer.toString(yy2));
//        if (on) {
//            g.setColor(Color.red);
//            //rysuje linie dla pola elektrycznego
//            g.drawLine(0, 1 + (int) (y2 - y1) - 38+ af.getCapacitor().getY1(), (int) (9 * af.getWidth() / 10), 1 + (int) (y2 - y1) - 38 + af.getCapacitor().getY1());
//            g.drawLine(0, 1 + (int) (y2 - y1) + af.getCapacitor().getY1(), (int) (9 * af.getWidth() / 10), 1 + (int) (y2 - y1) + af.getCapacitor().getY1());
//            g.setColor(Color.green);
//
//
//        } else g.setColor(Color.red);
//        g.fillOval((int) (9.5 * af.getWidth() / 10), 1 + (int) (y2 - y1) - 15 + af.getCapacitor().getY1(), (int) Math.abs((y2 - y1) / 2), (int) Math.abs((y2 - y1) / 2));
//    }


    public void calculateV(OilDrop drop)
    {
        //LICZY WARTOSC BEZ ZNAKU
        if(myCounter==1)
            drop.setV1(Math.abs(((y2 - y1) / (t2 - t1))));
        else
            drop.setV1(Math.abs(((y2 - y1) / (t2 - t1))));
        System.out.println("Calculated v" + Integer.toString(myCounter)+" " + drop.getV1());
    }

    public double getT1() {
        return t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT1() {
        if(on==true)
        {
        this.t1 = System.currentTimeMillis();
        System.out.println("czas t1: " + t1);
        }
    }

    public void setT2() {
        if(on==true)
        {
        this.t2 = System.currentTimeMillis();
        System.out.println("czas t2: " + t2);
        }
    }

    public void reset()
    {
        t1=0.0;
        t2=0.0;
        on=false;
    }
    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public boolean isOn() {
        return on;
    }


    public void setOn(boolean on) {
        this.on = on;
    }
}
