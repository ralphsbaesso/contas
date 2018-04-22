package negocio;

import controle.ITransportador;
import dao.Idao;
import dao.implementacao.DaoConta;
import dominio.Conta;
import dominio.ListaTransferencia;
import dominio.leitorarquivo.AbstractCarregarArquivo;
import enuns.ESemafaro;
import factory.FactoryLeitorArquivos;

public class LeitorArquivo implements IStrategy {

	@Override
	public boolean processar(ITransportador transportador) {
		
		ListaTransferencia lista = (ListaTransferencia) transportador.getEntidade();
		
		//verifica se existe arquivo anexo
		if(lista.getReader() == null) {
			//vazio, para somente este processo.
			return true;
		}
		
		Conta conta = lista.getTransferencias().get(0).getTransacoes().get(0).getConta();
		
		Idao dao = new DaoConta();
		conta = (Conta) dao.listar(conta).get(0);
		
		AbstractCarregarArquivo carregadorArquivo = FactoryLeitorArquivos.leitorArquivo(conta);
		
		try {
			lista= carregadorArquivo.read(lista.getReader());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transportador.setMensagens(e.getMessage());
			return transportador.setSemafaro(ESemafaro.VERMELHO);
		}
		
		return true;
	}
}
