package demo.excepciones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo1
{
	public static void main(String[] args)
	{
		FileInputStream fis = null;

		try
		{
			fis = new FileInputStream("miarchivo.txt");
			
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
		finally
		{
			try
			{
				if( fis!=null) fis.close();				
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
			
		}
	}
}
