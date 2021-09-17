package demo.genericos;

public class DemoMiColeccion2
{
	public static void main(String[] args)
	{
		// coleccion de cadenas
		MiColeccion<String> x = new MiColeccion<>();
		x.add("Pedro");
		x.add("Alberto");
		x.add("Juan");
		
		x.sort();
		mostrarColeccion(x);
		
		// coleccion de enteros
		MiColeccion<Integer> a = new MiColeccion<>();
		a.add(5);
		a.add(2);
		a.add(8);
		a.add(3);

		a.sort();
		mostrarColeccion(a);
		
		// coleccion de personas
		MiColeccion<Persona> b = new MiColeccion<>();
		b.add(new Persona(30,"Pablo"));
		b.add(new Persona(20,"Pedro"));
		b.add(new Persona(60,"Juan"));
		
		b.sort();
		mostrarColeccion(b);
	}
	
	public static <T extends Comparable<T>> void mostrarColeccion(MiColeccion<T> x)
	{
		for(int i=0; i<x.size(); i++)
		{
			System.out.println( x.get(i) );
		}
	}
}
