package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adaptergson.EmptyStringToDouble;
import adaptergson.FactoryGson;
import adaptergson.StringToCalendar;
import controle.ITransportador;
import dominio.Conta;
import dominio.Entidade;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhConta extends AbstractVH {
	
	private Conta conta;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		this.conta = new Conta();
		Gson gson = FactoryGson.getGson();
		
		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class);

		String jsonConta= request.getParameter("entidade");
		
		if(jsonConta != null) {
			
			try {
				this.conta = gson.fromJson(jsonConta, Conta.class);			
			}catch (Exception e) {
				this.conta = null;
				e.printStackTrace();
			}
		}

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {
			
		}else if( requisicao.getOperacao().equals(EOperacao.ALTERAR)) {
			
		}else if(requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {
		
		}else if(requisicao.getOperacao().equals(EOperacao.LISTAR)) {
			
		}

		return this.conta;

	}

	@Override
	public void setView(ITransportador transportador, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		TransportadorWeb transpotadorWeb = new TransportadorWeb();
		Gson json = new Gson();

		transpotadorWeb.recebeObjetoMensagem(transportador);

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {

		} else if (requisicao.getOperacao().equals(EOperacao.ALTERAR)) {
			
		} else if (requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {
			
		} else if (requisicao.getOperacao().equals(EOperacao.LISTAR)) {
			
			List<Conta> contas = (List) transpotadorWeb.getEntidades();
			
			for(Conta conta : contas) {
				conta.getCorrentista().setContas(null);
				conta.getCorrentista().setItens(null);
				conta.setTransacoes(null);
			}
			
		}
		
		if(this.requisicao.getDestino() != null) {
			
			request.setAttribute("conta",json.toJson(transportador.getEntidades().get(0)));
			RequestDispatcher rd = request.getRequestDispatcher(this.requisicao.getDestino());
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());

	}
}
