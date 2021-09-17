package demo.interfaces;

public class PalomaMensajera extends Paloma implements Comunicador
{
	@Override
	public void enviarMensaje(String msg) 
	{
		try
		{
			// duermo 7 segundos
			Thread.sleep(7000);
			
			// emito el mensaje
			System.out.println("CU CURRR CURRR, "+msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
