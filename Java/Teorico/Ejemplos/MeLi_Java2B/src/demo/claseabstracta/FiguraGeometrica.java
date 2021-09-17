package demo.claseabstracta;

public abstract class FiguraGeometrica
{
	public abstract double area();
	
	public void mostrarArea()
	{
		System.out.println("El area es: "+area());
	}
}
