package demo;

public class DemoNumero
{
	public static void main(String[] args)
	{
		Numero n = new Numero(5);
		Numero m = new Numero(5);

		String s = "soy una taza";
		
		if( n.equals(s) )
		{
			System.out.println("SON IGUALES");
		}
		
		
		System.out.println(n);
	}
}
