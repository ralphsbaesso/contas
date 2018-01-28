package processosarquivos;

import java.util.List;

import dominio.Conta;
import dominio.Transferencia;

public interface IProcessadorArquivos {
	
	List<Transferencia> processaArquivo(Conta conta);

}
