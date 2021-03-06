package demo.claseabstracta;

public class Circulo extends FiguraGeometrica
{
	private int radio;
	
	public Circulo(int r)
	{
		this.radio = r;
	}
	
	@Override
	public double area()
	{
		return Math.PI*Math.pow(radio,2);
	}
}
