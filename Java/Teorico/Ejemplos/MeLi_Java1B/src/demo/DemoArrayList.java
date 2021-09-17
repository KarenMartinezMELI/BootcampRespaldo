package demo;

import java.util.ArrayList;

public class DemoArrayList
{
	public static void main(String args[])
	{
		ArrayList<String> nombres = new ArrayList<>();
		nombres.add("Pablo");
		nombres.add("Pedro");
		nombres.add("Juan");
		nombres.add("Carlos");

		for(String s:nombres)
		{
			System.out.println(s);
		}

		ArrayList<Integer> numeros = new ArrayList<>();
		numeros.add(1);
		numeros.add(5);
		numeros.add(9);
		numeros.add(18);

		for(Integer i:numeros)
		{
			System.out.println(i);
		}
		
		int h = Integer.parseInt("ABCDEF",16);
		System.out.println(h);
		
	}
}
