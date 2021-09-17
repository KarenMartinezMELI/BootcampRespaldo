package demo.genericos;

public class MiColeccion <T extends Comparable<T>>
{
	private Object[] arr = null;
	private int idx = 0;
	
	public MiColeccion()
	{
		arr = new Object[3];
	}
	
	public void add(T elm) 
	{
		if( idx==arr.length )
		{
			Object nuevo[] = new Object[arr.length*2];
			for(int i=0; i<arr.length; i++)
			{
				nuevo[i] = arr[i];
			}
			
			arr = nuevo;
		}
				
		arr[idx] = elm;
		idx++;
	}
	
	public T get(int i)
	{
		return (T)arr[i];
	}
	
	public int size()
	{
		return idx;
	}
	
	public void sort()
	{
		for(int j=0; j<idx; j++)
		{
			for(int i=0; i<idx-1; i++)
			{
				if( get(i+1).compareTo(get(i))<0 )
				{
					T aux = get(i+1);
					arr[i+1] = get(i);
					arr[i] = aux;
				}
			}
		}
	}
	
}
