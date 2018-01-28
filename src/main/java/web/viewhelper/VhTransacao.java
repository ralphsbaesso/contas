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

		Gson gson = FactoryGson.getGson();

		this.transacao = new Transacao();

		String jsonTransacao = request.getParameter("transacao");
		
		if(jsonTransacao != null) {
			
			try {
				this.transacao = gson.fromJson(jsonTransacao, Transacao.class);
			} catch (Exception e) {
				this.transacao = null;
				e.printStackTrace();
			}
		}else {
			
			int id;
			try {
				id = Integer.valueOf(request.getParameter("txtContaId"));
			}catch(Exception e) {
				id = 0;
			}
			this.transacao.getConta().setId(id);
		}

		operacao = request.getParameter("operacao").toLowerCase();

		if (operacao.equals(EOperacao.SALVAR.getValor())) {

		} else if (operacao.equals(EOperacao.ALTERAR.getValor())) {

		} else if (operacao.equals(EOperacao.EXCLUIR.getValor())) {

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

		if (operacao.equals("salvar")) {

		} else if (operacao.equals("excluir")) {

		} else if (operacao.equals("alterar")) {

		} else if (operacao.equals("listar")) {

			List<Transacao> transacoes = (List) transportador.getEntidades();
			
			for (Transacao transacao : transacoes) {
				
				Transferencia transferencia = transacao.getTransferencia();
				
				if(transferencia != null) {
					
					for(Transacao t : transferencia.getTransacoes()) {
						
						Conta conta = t.getConta();
						conta.setCorrentista(null);
						conta.setTransacoes(null);
						
						t.setTransferencia(null);
						t.setSubitem(null);
					}
				}
				
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

		String destino = request.getParameter("destino");
		
		if(destino != null) {
			
			request.setAttribute("transacoes",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(destino);
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());
		return;
	}
}
