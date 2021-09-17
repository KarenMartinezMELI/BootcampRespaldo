package demo.claseabstracta;

public class Rectangulo extends FiguraGeometrica
{
	private int base;
	private int altura;
	
	public Rectangulo(int b,int h)
	{
		this.base = b;
		this.altura = h;
	}
	
	@Override
	public double area()
	{
		return base*altura;
	}
}
