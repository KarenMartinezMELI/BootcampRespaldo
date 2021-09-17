package demo.genericos1;

public class DemoMiColeccion1
{
	public static void main(String[] args)
	{
		MiColeccion<Integer> x = new MiColeccion<>();

		for(int i=1; i<50; i++)
		{
			x.add(i);
		}
		
		for(int i=0; i<x.size(); i++)
		{
			int s = x.get(i);
			System.out.println(s);
		}
	}
}
