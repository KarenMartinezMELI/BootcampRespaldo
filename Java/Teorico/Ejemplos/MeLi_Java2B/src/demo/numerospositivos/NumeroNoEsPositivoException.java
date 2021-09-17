package demo.numerospositivos;

public class NumeroNoEsPositivoException extends RuntimeException
{
	public NumeroNoEsPositivoException()
	{
		super("El numero no es positivo");
	}
}
