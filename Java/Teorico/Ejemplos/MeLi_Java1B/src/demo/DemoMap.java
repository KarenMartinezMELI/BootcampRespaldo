package demo;

import java.util.Map;
import java.util.TreeMap;

public class DemoMap
{
	public static void main(String args[])
	{
		TreeMap<String,Integer> x = new TreeMap<>();
		x.put("uno",1);
		x.put("dos",2);
		x.put("tres",3);
		x.put("cuatro",4);
		x.put("nulo",null);
		
		for(Map.Entry<String,Integer> e:x.entrySet())
		{
			String k = e.getKey();
			Integer v = e.getValue();
			
			System.out.println("{"+k+","+v+"}");
		}
		
		
		
		int i = x.get("tres");
		System.out.println(i);
		
		String k = "dlfdhgd";
		if( !x.containsKey(k) )
		{
			System.out.println("No existe una entrada con clave: "+k);
		}
		else
		{
			i = x.get(k);
			System.out.println(i);
			
		}
		
	}
}
