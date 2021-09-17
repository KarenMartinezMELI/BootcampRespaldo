package demo;

import java.util.Date;

public class DemoPolimorfismo
{
	public static void main(String[] args)
	{
		Object arr[] = new Object[3];
		arr[0] = "Hola";
		arr[1] = new Date();
		arr[2] = new Numero(10);
		
		imprimirArray(arr);
	}

	
	
	
	
	
	private static void imprimirArray(Object arr[])
	{
		for(Object x:arr)
		{
			System.out.println(x);
		}
	}
	
	
}
