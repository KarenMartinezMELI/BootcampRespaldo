package demo.genericos1;

import java.util.Comparator;

public class PersonaXDNIComparatorImple implements Comparator<Persona>
{
	@Override
	public int compare(Persona a, Persona b)
	{
		return a.getDni()-b.getDni();
	}

}
