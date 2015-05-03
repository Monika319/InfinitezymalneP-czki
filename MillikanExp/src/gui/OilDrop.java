package gui;
import java.util.Random;


public class OilDrop
{
	private double radius;
	private double charge;
	private double oilDensity;
	private double v1;
	private double v2;
	public OilDrop(double rMin,double rMax,int qMin,int qMax) 
	{
		v1=0;
		v2=0;
		//heating oil density by http://www.engineeringtoolbox.com/liquids-densities-d_743.html
		oilDensity=920;
		Random generator = new Random();
		radius=rMin+Math.abs(rMax-rMin)*generator.nextDouble();
		charge=(new Constants().e)*(qMin+generator.nextInt(Math.abs(qMax-qMin)));
	}
	public double getRadius()
	{
		return radius;
	}
	public double getCharge()
	{
		return charge;
	}
	public double getOilDensity()
	{
		return oilDensity;
	}
	public double getV1()
	{
		return v1;
	}
	public void setV1(double v1)
	{
		this.v1 = v1;
	}
	public double getV2()
	{
		return v2;
	}
	public void setV2(double v2)
	{
		this.v2 = v2;
	}
	
}