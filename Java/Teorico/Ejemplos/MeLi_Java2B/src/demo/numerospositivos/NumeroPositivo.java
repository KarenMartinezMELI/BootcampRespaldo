package demo.numerospositivos;

public class NumeroPositivo extends Numero
{
	public NumeroPositivo(int n) throws Exception
	{
		if( n<0 )
		{
			throw new Exception("El numero debe ser positivo");
		}

		super.setValor(n);
	}
	
	@Override
	public void setValor(int v) 
	{
		if( v<0 )
		{
			throw new NumeroNoEsPositivoException();
		}
		
		super.setValor(v);
	} 
}
