package demo;

import java.util.Scanner;

public class DemoFunciones
{
//	public static boolean esPar(int n)
//	{
//		boolean ok=false;
//		if( n%2==0 )
//		{
//			ok=true;
//		}
//		
//		return ok;
//	}
	
	public static boolean esPar(int n)
	{
		return n%2==0;
	}
	
	public static boolean esImpar(int n)
	{
		return !esPar(n);
	}
	
	
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingresa un valor: ");
		int a = scanner.nextInt();
		
		if( esImpar(a) )
		{
			System.out.println(a+" es impar");
		}
		
		
		scanner.close();
	}
}
