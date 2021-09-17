package demo.interfaces;

public class Telegrafo extends Antiguedad implements Comunicador
{
	@Override
	public void enviarMensaje(String msg) 
	{
		try
		{
			// duermo 3 segundos
			Thread.sleep(3000);
			
			// emito el mensaje
			System.out.println("PI PIIII PI PI PIII, "+msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
