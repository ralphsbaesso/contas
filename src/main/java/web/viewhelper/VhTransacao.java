package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adaptergson.FactoryGson;
import controle.ITransportador;
import dominio.Conta;
import dominio.Entidade;
import dominio.Subitem;
import dominio.Transacao;
import dominio.Transferencia;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhTransacao extends AbstractVH {

	private Transacao transacao;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		this.transacao = new Transacao();
		Gson gson = FactoryGson.getGson();
		
		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class);

		String jsonTransacao = request.getParameter("entidade");
		
		if(jsonTransacao != null) {
			
			try {
				this.transacao = gson.fromJson(jsonTransacao, Transacao.class);
			} catch (Exception e) {
				this.transacao = null;
				e.printStackTrace();
			}
		}		

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {

		} else if (this.requisicao.getOperacao().equals(EOperacao.ALTERAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR.getValor())) {

		}

		return this.transacao;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		Gson json = new Gson();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.ALTERAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.LISTAR)) {

			List<Transacao> transacoes = (List) transportador.getEntidades();
			
			int idContaPrincipal = this.transacao.getConta().getId();
			
			for (Transacao transacao : transacoes) {
				
				Transferencia transferencia = transacao.getTransferencia();
				
				Transferencia novaTransferencia = new Transferencia();
				
				// id da transferencia;
				int id = transferencia.getId();
				novaTransferencia.setId(id);
				
				// resgatar a segunda conta se houver
				for(Transacao trans : transferencia.getTransacoes()) {
					
					if(trans.getConta().getId() != idContaPrincipal) {
						
						Conta conta = trans.getConta();
						conta.setCorrentista(null);
						conta.setTransacoes(null);
						
						Transacao novaTransacao = new Transacao();
						novaTransacao.setId(trans.getId());
						novaTransacao.setConta(conta);
						novaTransferencia.getTransacoes().add(novaTransacao);
					}
					
					
				}
				for(Transacao trans : transferencia.getTransacoes()) {
					
					// popular os dados da conta principal
					Conta contaPrincipal = this.transacao.getConta();
					if(contaPrincipal.getId() == trans.getConta().getId() && contaPrincipal.getNome() == null) {
						
						contaPrincipal.setNome(trans.getConta().getNome());
						break;
					}
					
				}
				// atribuir a nova transferencia com o mesmo Id para a transacao
				// manobra necessario para não cair num loop sem fim na transformação para json
				transacao.setTransferencia(novaTransferencia);
				
				Conta conta = transacao.getConta();
				
				if(conta != null) {
					
					conta.setCorrentista(null);
					conta.setTransacoes(null);
				}
				
				Subitem subitem = transacao.getSubitem();
				
				if(subitem != null) {
					
					subitem.getItem().setSubitens(null);
					subitem.getItem().setCorrentista(null);
				}			
			}			
		}
		
		if(requisicao.getDestino() != null) {			
			
			request.setAttribute("conta",json.toJson(this.transacao.getConta()));
			request.setAttribute("transacoes",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(requisicao.getDestino());
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());
		return;
	}
}
