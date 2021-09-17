package demo.interfaces;

public class Factory
{
	public static Comunicador crearComunicador()
	{
		return new PalomaMensajera();
	}
}
