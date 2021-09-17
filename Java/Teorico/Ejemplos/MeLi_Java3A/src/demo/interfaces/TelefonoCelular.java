package demo.interfaces;

public class TelefonoCelular extends Telefono implements Comunicador
{
	@Override
	public void enviarMensaje(String msg)
	{
		System.out.println("HELLO MOTO, "+msg);
	}
}
