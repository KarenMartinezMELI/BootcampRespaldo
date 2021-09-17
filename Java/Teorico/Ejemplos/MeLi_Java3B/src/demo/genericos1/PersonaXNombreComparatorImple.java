package demo.genericos1;

import java.util.Comparator;

public class PersonaXNombreComparatorImple implements Comparator<Persona>
{
	@Override
	public int compare(Persona a, Persona b)
	{
		return a.getNombre().compareTo(b.getNombre());
	}

}
