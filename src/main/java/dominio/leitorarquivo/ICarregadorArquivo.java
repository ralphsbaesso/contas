package dominio.leitorarquivo;

import java.io.BufferedReader;

import dominio.ListaTransferencia;

public interface ICarregadorArquivo {
	
	public ListaTransferencia read(BufferedReader reader) throws Exception;

}
