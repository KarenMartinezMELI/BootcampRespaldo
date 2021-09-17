package demo.genericos;

public class Persona implements Comparable<Persona>
{
	private int dni;
	private String nombre;

	public Persona(int d,String n)
	{
		dni=d;
		nombre=n;
	}
	
	@Override
	public String toString()
	{
		return dni+", "+nombre;
	}
	
	public int getDni()
	{
		return dni;
	}
	public void setDni(int dni)
	{
		this.dni=dni;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	@Override
	public int compareTo(Persona otra)
	{
//		return -1*(this.dni-otra.dni);
		return -1*this.nombre.compareTo(otra.nombre);
	}
}
