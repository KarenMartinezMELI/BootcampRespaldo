package demo.genericos1;

import java.util.Comparator;

public class NumDescComparatorImple implements Comparator<Integer>
{

	@Override
	public int compare(Integer a,Integer b)
	{
		return b-a;
	}

}
