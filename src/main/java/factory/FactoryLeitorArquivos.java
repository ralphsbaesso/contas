package factory;

import dominio.Conta;
import dominio.leitorarquivo.AbstractCarregarArquivo;
import dominio.leitorarquivo.ReadTwoColumnValue;
import enuns.ELeitorArquivo;

public class FactoryLeitorArquivos {
	
	private FactoryLeitorArquivos() {};
	
	public static AbstractCarregarArquivo leitorArquivo(Conta conta) {
		
		if(conta.getArquivo().TWO_COLUMN_VALUE == ELeitorArquivo.TWO_COLUMN_VALUE) {
			return new ReadTwoColumnValue(conta);
		}
		return null;
	}

}
