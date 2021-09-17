package demo.excepciones;

import java.io.FileInputStream;

public class Demo2
{
	public static void main(String[] args)
	{
		try(FileInputStream fis = new FileInputStream("miarchivo.txt"))
		{	
			int c = fis.read();
			while( c>=0 )
			{
				System.out.print((char)c);
				c = fis.read();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
