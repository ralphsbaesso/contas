package dao.implementacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import conexao.ControleConexao;
import dao.Idao;
import dominio.Entidade;
import dominio.Item;
import dominio.ListaTransferencia;
import dominio.Transacao;
import dominio.Transferencia;

public class DaoListaTransferencia implements Idao{

	@Override
	public boolean salvar(Entidade entidade) {

		ListaTransferencia listaTransferencia = (ListaTransferencia) entidade;
		
		for (int i = 0; i < listaTransferencia.getTransferencias().size(); i++) {  
			
			Transferencia transferencia = listaTransferencia.getTransferencias().get(i);
			
			Transacao tp = transferencia.getTransacaoPrincipal();
			Transacao ts = transferencia.getTransacaoSecundaria();
			
			try {
				
				transferencia.setTransacaoPrincipal(ControleConexao.entityManager.merge(tp));
				transferencia.setTransacaoSecundaria(ControleConexao.entityManager.merge(ts));
				
				ControleConexao.entityManager.persist(transferencia);
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} 

		return true;
	}

	@Override
	public boolean alterar(Entidade entidade) {

		ListaTransferencia listaTransferencia = (ListaTransferencia) entidade;

		for (int i = 0; i < listaTransferencia.getTransferencias().size(); i++) {  
			
			Transferencia transferencia = listaTransferencia.getTransferencias().get(i);
			
			try {
				ControleConexao.entityManager.merge(transferencia);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} 
			
		return true;
	}

	@Override
	public boolean excluir(Entidade entidade) {

		ListaTransferencia listaTransferencia = (ListaTransferencia) entidade;

		for (int i = 0; i < listaTransferencia.getTransferencias().size(); i++) {  
			
			Transferencia transferencia = listaTransferencia.getTransferencias().get(i);
			
			transferencia = ControleConexao.entityManager.find(Transferencia.class, transferencia.getId());
			
			if (listaTransferencia == null)
				continue;
			
			try {
				ControleConexao.entityManager.remove(transferencia);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} 
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List listar(Entidade entidade) {

		return null;
	}

}
