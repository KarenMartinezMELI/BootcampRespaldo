package demo.genericos1;

import java.util.Comparator;

public class AlfaDescComparatorImple implements Comparator<String>
{	
	@Override
	public int compare(String a, String b)
	{
		return -1*a.compareTo(b);
	}
}
