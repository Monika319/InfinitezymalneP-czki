package gui;


public class Photodetector
{
	private double t1;
	private double t2;
	private double distance;
	Photodetector()
	{
		distance=0.01;
	}
	Photodetector(double time1)
	{
		t1=time1;
		distance=0.01;
	}
	Photodetector(double time1,double time2)
	{
		distance=0.01;
		t1=time1;
		t2=time2;
	}
	Photodetector(double dist,double time1,double time2)
	{
		distance=dist;
		t1=time1;
		t2=time2;
	}
	public double getT1()
	{
		return t1;
	}
	public void setT1(double t1)
	{
		this.t1 = t1;
	}
	public double getT2()
	{
		return t2;
	}
	public void setT2(double t2)
	{
		this.t2 = t2;
	}
	public void calculateV1(OilDrop drop)
	{
		drop.setV1(distance/(t2-t1));
	}
	public void calculateV2(OilDrop drop)
	{
		drop.setV2(distance/(t2-t1));
	}
}
