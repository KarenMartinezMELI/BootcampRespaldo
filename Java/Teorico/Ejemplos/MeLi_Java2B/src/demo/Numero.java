package demo;

public class Numero 
{
	public static final double PI = 3.14;
	
	private int valor;
	
	@Override
	public boolean equals(Object o)
	{
		if( !(o instanceof Numero) )
		{
			return false;
		}
		
		Numero x = (Numero) o;
		return this.valor==x.valor;
	}
	
	
	@Override
	public String toString()
	{
		return Integer.toString(valor);
	}
	
	public static void modificar(Numero a)
	{
		a = new Numero(10);
//		a.setValor(10);
	}
	
	public static int sumar(int a,int b)
	{
		return a+b;
	}
	
	public void sumar(Numero n)
	{
		this.valor += n.getValor();
	}
	
	public Numero sumar(int n)
	{
		sumar(new Numero(n));
		return this;
	}

	

	public Numero copiar()
	{
		Numero x =new Numero();
		x.setValor(valor);
		return x;
	}
	
	public Numero(int v)
	{
		valor = v;
	}
	
	public Numero()
	{
		valor = 0;
	}
	
	public boolean esPar()
	{
		return valor%2==0;
	}

	public int getValor()
	{
		return valor;
	}

	public void setValor(int valor)
	{
		this.valor=valor;
	}
}
