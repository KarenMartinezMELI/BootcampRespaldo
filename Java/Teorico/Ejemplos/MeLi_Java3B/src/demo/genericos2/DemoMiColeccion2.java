package demo.genericos2;

import java.util.Comparator;

public class DemoMiColeccion2
{
	public static void main(String[] args)
	{
		// coleccion de cadenas
		MiColeccion<String> x = new MiColeccion<>();
		x.add("Pedro");
		x.add("Alberto");
		x.add("Juan");
		
		Comparator<String> c1 = (a,b)->b.compareTo(a); 
		x.sort(c1);
		mostrarColeccion(x);

		x.sort((a,b)->a.length()-b.length());
		mostrarColeccion(x);
		
		// coleccion de enteros
		MiColeccion<Integer> a = new MiColeccion<>();
		a.add(5);
		a.add(2);
		a.add(8);
		a.add(3);
		a.sort((p,q)->p-q);
		mostrarColeccion(a);

				
		a.sort((p,q)->q-p);
		mostrarColeccion(a);

		// coleccion de personas
		MiColeccion<Persona> b = new MiColeccion<>();
		b.add(new Persona(30,"Pablo"));
		b.add(new Persona(20,"Pedro"));
		b.add(new Persona(10,"Juan"));
		b.add(new Persona(40,"Pablo"));
		b.add(new Persona(60,"Pedro"));
		b.add(new Persona(50,"Juan"));
		b.add(new Persona(80,"Pablo"));
		b.add(new Persona(70,"Pedro"));
		b.add(new Persona(90,"Juan"));
		
		Comparator<Persona> c2 = (p,q)->{
			          int z = p.getNombre().compareTo(q.getNombre());
			          if( z==0 )
			          {
			        	  z = p.getDni()-q.getDni();
			          }
			          return z;
		};
		
		b.sort(c2);
		mostrarColeccion(b);

		Comparator<Persona> c3 = (p,q)->0;
		b.sort(c3);
		mostrarColeccion(b);
		
	}
	
	public static <T> void mostrarColeccion(MiColeccion<T> x)
	{
		for(int i=0; i<x.size(); i++)
		{
			System.out.println( x.get(i) );
		}
		
		System.out.println("---");
	}
}
