package demo.genericos1;

import java.util.Comparator;

public class NumAscComparatorImple implements Comparator<Integer>
{

	@Override
	public int compare(Integer a,Integer b)
	{
		return a-b;
	}

}
