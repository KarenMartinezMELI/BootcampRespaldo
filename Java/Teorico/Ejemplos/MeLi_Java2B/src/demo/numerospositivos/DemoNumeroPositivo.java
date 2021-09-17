package demo.numerospositivos;

public class DemoNumeroPositivo
{
	public static void main(String[] args) 
	{
		try
		{
			NumeroPositivo np = new NumeroPositivo(-10);
			System.out.println(np);			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
}
