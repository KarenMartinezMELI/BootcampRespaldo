package demo.genericos1;

import java.util.Comparator;

public class LongAscComparatorImple implements Comparator<String>
{
	@Override
	public int compare(String a, String b)
	{
		return a.length()-b.length();
	}
	
}
