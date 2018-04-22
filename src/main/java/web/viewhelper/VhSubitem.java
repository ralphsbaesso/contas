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
import dominio.Entidade;
import dominio.Subitem;
import enuns.EOperacao;
import web.TransportadorWeb;

public class VhSubitem extends AbstractVH {
	
	private Subitem subitem;

	@Override
	public Entidade getEntidade(HttpServletRequest request) {

		this.subitem = new Subitem();
Gson gson = FactoryGson.getGson();
		
		String jsonRequisicao = request.getParameter("requisicao");
		requisicao = gson.fromJson(jsonRequisicao, Requisicao.class);

		String jsonSubitem = request.getParameter("entidade");
		
		if(jsonSubitem != null) {
			
			try {
				this.subitem = gson.fromJson(jsonSubitem, Subitem.class);			
			}catch (Exception e) {
				this.subitem = null;
				e.printStackTrace();
			}
		}

		if (requisicao.getOperacao().equals(EOperacao.SALVAR)) {
			
		}else if( requisicao.getOperacao().equals(EOperacao.ALTERAR)) {
			
		}else if(requisicao.getOperacao().equals(EOperacao.EXCLUIR)) {
			
		}else if(requisicao.getOperacao().equals(EOperacao.LISTAR)) {
			
		}

		return this.subitem;

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
			
			List<Subitem> subitens = (List) transpotadorWeb.getEntidades();
			
			for(Subitem subitem : subitens) {
				subitem.getItem().setCorrentista(null);
				subitem.getItem().setSubitens(null);
			}
		}
		
		if(this.requisicao.getDestino() != null) {
			
			request.setAttribute("contas",json.toJson(transportador.getEntidades()));
			RequestDispatcher rd = request.getRequestDispatcher(this.requisicao.getDestino());
			rd.forward(request, response);
			return;
		}
		
		out.print(transpotadorWeb.enviarObjetoWeb());

	}
}
